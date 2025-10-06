package com.luchersol.cli;

import java.util.concurrent.Callable;

import com.luchersol.cli.command.CreateClassCommand;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * Main class for the command-line interface (CLI) of the library for
 * checks and class generation.
 * <p>
 * This class uses <a href="https://picocli.info/">Picocli</a> to manage
 * commands and subcommands from the command line.
 * </p>
 *
 * <p>Supported commands:</p>
 * <ul>
 *   <li>{@link com.luchersol.cli.command.CreateClassCommand} - Subcommand to create classes.</li>
 * </ul>
 *
 *
 * <p>Example usage from the terminal:</p>
 * <pre>
 * java -jar my-application.jar create-class
 * </pre>
 *
 */
@Command(
        name = "checklib",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Library for checks and class generation",
        subcommands = { CreateClassCommand.class }
)
public class MainCLI implements Callable<Integer> {

    /**
     * Main method that runs the CLI application.
     * <p>
     * Creates a {@link CommandLine} instance with this class and executes
     * the command received from arguments.
     * </p>
     *
     * @param args arguments passed from the command line
     */
    public static void main(String[] args) {
        int exitCode = new CommandLine(new MainCLI()).execute(args);
        System.exit(exitCode);
    }

    /**
     * Method executed when no subcommand is specified.
     * <p>
     * Prints a message indicating the user should use a subcommand.
     * </p>
     *
     * @return 0 if no error occurs
     */
    @Override
    public Integer call() {
        System.out.println("Use a subcommand. For example: create-class");
        return 0;
    }
}
