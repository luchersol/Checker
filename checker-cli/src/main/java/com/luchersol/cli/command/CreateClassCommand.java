package com.luchersol.cli.command;

import java.io.IOException;
import java.util.concurrent.Callable;

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
 * java -jar my-application.jar create-classes -n MyClass -p com.example
 * </pre>
 *
 *
 * <p>Options:</p>
 * <ul>
 *   <li>-n, --name &lt;CLASS_NAME&gt; : Name of the class to generate (required)</li>
 *   <li>-p, --package &lt;PACKAGE_NAME&gt; : Package for the class (required)</li>
 * </ul>
 */
@Command(
        name = "create-classes",
        description = "Generates a Java class"
)
public class CreateClassCommand implements Callable<Integer> {

    /**
     * The name of the Java class to generate.
     */
    @Option(names = {"-n", "--name"}, required = true, description = "Name of the class to generate")
    private String className;

    /**
     * The package name for the Java class.
     */
    @Option(names = {"-p", "--package"}, required = true, description = "Package for the class")
    private String packageName;

    /**
     * Executes the command to create a Java class.
     * <p>
     * Currently, this method does not implement the class generation logic
     * and simply returns 0.
     * </p>
     *
     * @return 0 if the command executes successfully
     * @throws IOException if an input/output error occurs during execution
     */
    @Override
    public Integer call() throws IOException {
        return 0;
    }
}
