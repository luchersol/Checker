package com.luchersol.cli.command;

import static java.lang.reflect.Modifier.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import com.luchersol.core.util.AbstractChecker;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Subcommand for creating a Java class.
 * <p>
 * This command generates a Java class with the specified name
 * and package.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>
 * java -jar my-application.jar create-classes -c com.example.MyClass -n CheckerMyClass -p com.example.checker
 * </pre>
 *
 *
 * <p>Options:</p>
 * <ul>
 *   <li>-sp, --source-package &lt;PACKAGE_NAME&gt; :</li>
 *   <li>-sc, --source-class &lt;CHECKER_NAME&gt; :</li>
 *   <li>-tp, --target-package &lt;PACKAGE_NAME&gt; :</li>
 *   <li>-tc, --target-class &lt;CLASS_NAME&gt; :</li>
 * </ul>
 */
@Command(
        name = "create-classes",
        description = "Generates a Java class"
)
public class CreateCheckerCommand implements Callable<Integer> {

    @Option(names = {"-sp", "--source-package"}, required = true, description = "Package for the class")
    private String sourcePackage;

    @Option(names = {"-sc", "--source-class"}, required = true, description = "Name of the class to do generate")
    private String sourceClass;

    @Option(names = {"-tp", "--target-package"}, description = "Package for the class (default: sourcePackage)")
    private String targetPackage;

    @Option(names = {"-tc", "--target-class"}, description = "Name of the class to generate (default: Checker + sourceClass)")
    private String targetClass;

    @Option(names = {"-o", "--output-dir"}, description = "Output directory for generated sources (default: ${sys:user.dir}/src/main/java)")
    private String outputDir;

    @Option(names = {"-i", "--indent"}, defaultValue = "4", description = "Indent size for generated java file")
    private int indent;

    /**
     * Executes the command to create a Java class.
     * <p>
     * Currently, this method does not implement the class generation logic
     * and simply returns 0.
     * </p>
     *
     * @return 0 if the command executes successfully
     * @throws IOException if an input/output error occurs during execution
     * @throws ClassNotFoundException
     */
    @Override
    public Integer call() throws IOException, ClassNotFoundException {
        if(targetClass == null) targetClass = "Checker" + sourceClass;
        if(targetPackage == null) targetPackage = sourcePackage;
        if(outputDir == null) outputDir = System.getProperty("user.dir") + "/src/main/java";

        ClassName sourceClassName = ClassName.get(sourcePackage, sourceClass);
        ClassName targetClassName = ClassName.get(targetPackage, targetClass);

        // Create base class to extend checker class: AbstracterChecker<Class, ClassName>
        TypeName baseClass = ParameterizedTypeName.get(
                ClassName.get(AbstractChecker.class),
                sourceClassName,
                targetClassName
        );


        TypeSpec.Builder checkerClassBuilder = TypeSpec.classBuilder(targetClass)
                .addModifiers(Modifier.PUBLIC)
                .superclass(baseClass);

        addMethods(checkerClassBuilder, sourceClassName, targetClassName);

        TypeSpec checkerClass = checkerClassBuilder.build();

        JavaFile javaFile = JavaFile.builder(targetPackage, checkerClass)
                .indent(" ".repeat(indent))
                .build();

        Path targetPath = Path.of(outputDir);
        javaFile.writeTo(targetPath);
        System.out.println("Generated sources written to: " + targetPath.toAbsolutePath());
        return 0;
    }

    public MethodSpec getConstructor(ClassName sourceClassName) {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PROTECTED)
                .addParameter(sourceClassName, "obj")
                .addParameter(String.class, "name")
                .addStatement("super(obj, name)")
                .build();
    }

    public MethodSpec getSelfMethod(ClassName targetClassName) {
        return MethodSpec.methodBuilder("self")
            .addAnnotation(Override.class)
            .addModifiers(Modifier.PROTECTED)
            .returns(targetClassName)
            .addStatement("return this")
            .build();
    }

    public MethodSpec getCheckMethod(ClassName sourceClassName, ClassName targetClassName) {
        return MethodSpec.methodBuilder("check")
            .addModifiers(Modifier.PUBLIC)
            .addParameter(sourceClassName, "obj")
            .addParameter(String.class, "name")
            .returns(targetClassName)
            .addStatement("return new $T($L, $L)", targetClassName, "obj", "name")
            .build();
    }

    public void addMethods(TypeSpec.Builder builder, ClassName sourceClassName, ClassName targetClassName) throws ClassNotFoundException {
        MethodSpec constructor = getConstructor(sourceClassName);
        MethodSpec selfMethod = getSelfMethod(targetClassName);
        MethodSpec checkMethod = getCheckMethod(sourceClassName, targetClassName);

        builder.addMethods(List.of(constructor, selfMethod, checkMethod));
        addInnerMethods(builder, sourceClassName, targetClassName);
    }

    public void addInnerMethods(TypeSpec.Builder builder, ClassName sourceClassName, ClassName targetClassName)
            throws ClassNotFoundException {

        Class<?> clazz = Class.forName(sourceClassName.reflectionName());

        Arrays.stream(clazz.getDeclaredMethods())
            .filter(m -> !isStatic(m.getModifiers()))
            .filter(m -> !m.isSynthetic())
            .filter(m -> m.getReturnType() == boolean.class || m.getReturnType() == Boolean.class)
            .forEach(method -> {
                try {
                    builder.addMethod(buildBooleanCheckMethod(method, targetClassName));
                } catch (Exception e) {
                    System.err.printf("Error generating method %s: %s%n", method.getName(), e.getMessage());
                }
            });
    }

    private MethodSpec buildBooleanCheckMethod(Method method, ClassName targetClassName) {
        MethodSpec.Builder spec = MethodSpec.methodBuilder(method.getName())
            .addModifiers(Modifier.PUBLIC)
            .returns(targetClassName);

        ParameterSpec[] params = Arrays.stream(method.getParameters())
            .map(p -> ParameterSpec.builder(p.getType(), p.getName()).build())
            .toArray(ParameterSpec[]::new);

        spec.addParameters(Arrays.asList(params));

        String paramNames = Arrays.stream(method.getParameters())
            .map(Parameter::getName)
            .collect(Collectors.joining(", "));

        String errorMessage = String.format(
            "Calling method %s(%s) returned false",
            method.getName(),
            Arrays.stream(method.getParameterTypes())
                .map(Class::getSimpleName)
                .collect(Collectors.joining(", "))
        );

        spec.addStatement(
            "return is(obj -> obj.$L($L), $S)",
            method.getName(), paramNames, errorMessage
        );

        return spec.build();
    }
}
