package com.luchersol.cli;

import java.util.Set;
import java.util.concurrent.Callable;

import org.reflections.Reflections;

import com.luchersol.cli.command.CreateCheckerCommand;

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
 *   <li>{@link CreateCheckerCommand} - Subcommand to create classes.</li>
 * </ul>
 *
 *
 * <p>Example usage from the terminal:</p>
 * <pre>
 * java -jar checker-cli.jar create-class
 * </pre>
 *
 */
@Command(
        name = "checkcli",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Library for checks and class generation"
)
public class CheckerCLI implements Callable<Integer> {

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
        CommandLine cmd = new CommandLine(new CheckerCLI());

        Reflections reflections = new Reflections("com.luchersol.cli.command");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Command.class);
        for (Class<?> clazz : classes) {
            CommandLine sub = new CommandLine(clazz);
            cmd.addSubcommand(sub.getCommandName(), sub);
        }

        int exitCode = cmd.execute(args);
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
        System.out.println("Use a subcommand. Available subcommands:");
        CommandLine cmd = new CommandLine(this);

        cmd.getSubcommands().forEach((name, sub) -> {
            System.out.println("  " + name + " - " + sub.getCommandSpec().usageMessage().description()[0]);
        });
        return 0;
    }
}
