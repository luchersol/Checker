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

import org.springframework.javapoet.ClassName;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.MethodSpec;
import org.springframework.javapoet.ParameterSpec;
import org.springframework.javapoet.ParameterizedTypeName;
import org.springframework.javapoet.TypeName;
import org.springframework.javapoet.TypeSpec;

import com.luchersol.core.util.AbstractChecker;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Subcommand for creating a Java class.
 * <p>
 * This command generates a Java class with the specified name
 * and package.
 *
 *
 * <p>Example usage:
 * <pre>
 * java -jar my-application.jar create-classes -c com.example.MyClass -n CheckerMyClass -p com.example.checker
 * </pre>
 *
 *
 * <p>Options:
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

    @Option(names = "--no-javadoc", description = "Disable Javadoc generation in the generated classes")
    private boolean noJavadoc;

    /**
     * Executes the command to create a Java class.
     * <p>
     * Currently, this method does not implement the class generation logic
     * and simply returns 0.
     *
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

        if(!noJavadoc) {
            checkerClassBuilder.addJavadoc("""

                A specialized checker for {@link $L} instances, providing a fluent API
                to assert various $T properties.

                <p>This class supports chaining multiple assertions in a fluent style and integrates
                with {@link com.luchersol.core.util.AbstractChecker} for generalized validation handling.

                @see $L
                @see com.luchersol.core.util.AbstractChecker

                """, sourceClassName.canonicalName(), sourceClassName, sourceClassName.canonicalName()
            );
        }

        TypeSpec checkerClass = checkerClassBuilder.build();

        JavaFile javaFile = JavaFile.builder(targetPackage, checkerClass)
                .indent(" ".repeat(indent))
                .build();


        Path targetPath = Path.of(outputDir);
        javaFile.writeTo(targetPath);
        System.out.println("Generated sources written to: " + targetPath.toAbsolutePath());
        return 0;
    }

    /**
     * Generates a constructor method for the checker class.
     *
     * @param sourceClassName the class name of the source class
     * @return a {@link MethodSpec} for the constructor
     */
    public MethodSpec getConstructor(ClassName sourceClassName) {
        MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PROTECTED)
                .addParameter(sourceClassName, "obj")
                .addParameter(String.class, "name")
                .addStatement("super(obj, name)");

        if(noJavadoc) return constructor.build();

        constructor.addJavadoc("""
                Constructs a new {@code $L} with the specified string and name.

                @param obj the {@link $T} to bee associated with this checker
                @param name the name of the checker
                """, targetClass, sourceClassName
            );

        return constructor.build();
    }

    /**
     * Generates the self() method for the checker class.
     *
     * @param targetClassName the class name of the target checker class
     * @return a {@link MethodSpec} for the self method
     */
    public MethodSpec getSelfMethod(ClassName targetClassName) {
        MethodSpec.Builder spec = MethodSpec.methodBuilder("self")
            .addAnnotation(Override.class)
            .addModifiers(Modifier.PROTECTED)
            .returns(targetClassName)
            .addStatement("return this");

        if(noJavadoc) return spec.build();

        spec.addJavadoc("""
            Returns this instance (for fluent API usage).

            @return this $T instance
            """, targetClassName
        );

        return spec.build();
    }

    /**
     * Generates the check() method for the checker class.
     *
     * @param sourceClassName the class name of the source class
     * @param targetClassName the class name of the target checker class
     * @return a {@link MethodSpec} for the check method
     */
    public MethodSpec getCheckMethod(ClassName sourceClassName, ClassName targetClassName) {
        MethodSpec.Builder checkMethod = MethodSpec.methodBuilder("check")
            .addModifiers(Modifier.PUBLIC)
            .addParameter(sourceClassName, "obj")
            .addParameter(String.class, "name")
            .returns(targetClassName)
            .addStatement("return new $T($L, $L)", targetClassName, "obj", "name");


        if(noJavadoc) return checkMethod.build();

        checkMethod.addJavadoc("""
            Creates a new $T instance for the given $T with a default name.

            @param $T the $T value to be checked
            @return a $T instance for further validations
            """, targetClassName, sourceClassName, sourceClassName, sourceClassName, targetClassName
        );


        return checkMethod.build();
    }

    /**
     * Adds the main methods to the checker class builder.
     *
     * @param builder the {@link TypeSpec.Builder} for the checker class
     * @param sourceClassName the class name of the source class
     * @param targetClassName the class name of the target checker class
     * @throws ClassNotFoundException if the source class cannot be found
     */
    public void addMethods(TypeSpec.Builder builder, ClassName sourceClassName, ClassName targetClassName) throws ClassNotFoundException {
        MethodSpec constructor = getConstructor(sourceClassName);
        MethodSpec selfMethod = getSelfMethod(targetClassName);
        MethodSpec checkMethod = getCheckMethod(sourceClassName, targetClassName);

        builder.addMethods(List.of(constructor, selfMethod, checkMethod));
        addInnerMethods(builder, sourceClassName, targetClassName);
    }

    /**
     * Adds inner methods based on the public boolean methods of the source class.
     *
     * @param builder the {@link TypeSpec.Builder} for the checker class
     * @param sourceClassName the class name of the source class
     * @param targetClassName the class name of the target checker class
     * @throws ClassNotFoundException if the source class cannot be found
     */
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

    /**
     * Builds a check method for a boolean method from the source class.
     *
     * @param method the {@link Method} to wrap
     * @param targetClassName the class name of the target checker class
     * @return a {@link MethodSpec} for the check method
     */
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


        if(noJavadoc) return spec.build();

        spec.addJavadoc("""
                Checks if $L.

                $L
                @return this $T instance for chaining
                """,
                method.getName().replaceAll("([A-Z])", " $1").toLowerCase().trim(),
                Arrays.stream(params)
                        .map(p -> "@param " + p.name + " the " + p.type + " parameter")
                        .collect(Collectors.joining("\n")),
                targetClassName
        );

        return spec.build();
    }
}
