package com.luchersol.cli.command;

import java.lang.reflect.Modifier;
import java.util.concurrent.Callable;

import org.reflections.Reflections;

import com.luchersol.core.util.AbstractChecker;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * Picocli command that scans a base package and lists all concrete
 * implementations of {@link AbstractChecker}.
 *
 * <p>The command uses the Reflections library to discover subclasses
 * at runtime and prints their fully qualified class names.</p>
 */
@Command(
    name = "create-classes",
    description = "List Checkers in route"
)
public class ListCheckerCommand implements Callable<Integer> {

    /**
     * Base package used for classpath scanning.
     */
    @Option(
        names = {"-p", "--package"},
        description = "Base package to scan"
    )
    private String basePackage;

    /**
     * Executes the command logic.
     *
     * <p>This method scans the provided package, retrieves all subtypes
     * of {@link AbstractChecker}, filters out abstract classes, sorts
     * them alphabetically, and prints the results.</p>
     *
     * @return exit code (0 for successful execution)
     * @throws Exception if scanning or reflection fails
     */
    @Override
    public Integer call() throws Exception {

        Reflections reflections = new Reflections(basePackage);

        var checkers = reflections.getSubTypesOf(AbstractChecker.class);

        if (checkers.isEmpty()) {
            System.out.println("No checkers found in package: " + basePackage);
            return 0;
        }

        System.out.println("Checkers found:");
        checkers.stream()
                .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
                .sorted((a, b) -> a.getSimpleName().compareToIgnoreCase(b.getSimpleName()))
                .forEach(clazz -> System.out.println("  - " + clazz.getName()));

        return 0;
    }
}
