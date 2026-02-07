package com.luchersol.core.util;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;

/**
 * ExceptionTracker tracks and manages exceptions thrown, not thrown, and not checked during validation or testing.
 * It provides methods to add, merge, and display categorized exceptions with colored output.
 */
public class ExceptionTracker  {

    /**
     * The message displayed when no exceptions are present.
     */
    private static String CLEAN_MESSAGE = Ansi.ansi().fgGreen().a("CLEAN").reset().toString();

    /**
     * The name associated with this tracker (e.g., test or checker name).
     */
    private String name;

    /**
     * Map of thrown exceptions categorized by name.
     */
    private Map<String, Set<CheckerException>> thrownExceptions;

    /**
     * Map of not thrown exceptions categorized by name.
     */
    private Map<String, Set<CheckerException>> passedChecks;

    /**
     * Map of not checked exceptions categorized by name.
     */
    private Map<String, Set<CheckerException>> notCheckedExceptions;

    /**
     * Constructs an ExceptionTracker for the given name.
     *
     * @param name the name associated with this tracker
     */
    public ExceptionTracker(String name) {
        this.name = name;
        this.thrownExceptions = new HashMap<>();
        this.thrownExceptions.put(name, new LinkedHashSet<>());
        this.passedChecks = new HashMap<>();
        this.passedChecks.put(name, new LinkedHashSet<>());
        this.notCheckedExceptions = new HashMap<>();
        this.notCheckedExceptions.put(name, new LinkedHashSet<>());
    }


    /**
     * Creates an empty ExceptionTracker for the given name.
     *
     * @param name the name associated with this tracker
     * @return a new ExceptionTracker instance
     */
    public static ExceptionTracker empty(String name){
        return new ExceptionTracker(name);
    }


    /**
     * Adds an exception to the list of thrown exceptions.
     *
     * @param e the exception that was thrown
     */
    public void addThrownException(Exception e) {
        this.thrownExceptions.get(name).add(CheckerException.of(e));
    }


    /**
     * Adds an exception to the list of not thrown exceptions.
     *
     * @param e the exception that was expected but not thrown
     */
    public void addPassedChecks(Exception e) {
        this.passedChecks.get(name).add(CheckerException.of(e));
    }


    /**
     * Adds an exception to the list of not checked exceptions.
     *
     * @param e the exception that was not checked
     */
    public void addNotCheckedException(Exception e) {
        this.notCheckedExceptions.get(name).add(CheckerException.of(e));
    }


    /**
     * Returns the map of thrown exceptions categorized by name.
     *
     * @return the map of thrown exceptions
     */
    public Map<String, Set<CheckerException>> getThrownExceptions() {
        return thrownExceptions;
    }


    /**
     * Returns the map of not thrown exceptions categorized by name.
     *
     * @return the map of not thrown exceptions
     */
    public Map<String, Set<CheckerException>> getPassedChecks() {
        return passedChecks;
    }

    /**
     * Merges another ExceptionTracker's exceptions into this one, combining all categories by name.
     *
     * @param exceptionTracker the ExceptionTracker to merge from
     */
    public void merge(ExceptionTracker exceptionTracker) {
        Function<Map<String, Set<CheckerException>>, Set<CheckerException>> f = map -> map.values().stream()
                .flatMap(Set::stream).collect(Collectors.toSet());
        String propertyName = exceptionTracker.name;

        this.thrownExceptions.putIfAbsent(propertyName, new LinkedHashSet<>());
        this.passedChecks.putIfAbsent(propertyName, new LinkedHashSet<>());
        this.notCheckedExceptions.putIfAbsent(propertyName, new LinkedHashSet<>());

        this.thrownExceptions.get(propertyName).addAll(f.apply(exceptionTracker.thrownExceptions));
        this.passedChecks.get(propertyName).addAll(f.apply(exceptionTracker.passedChecks));
        this.notCheckedExceptions.get(propertyName).addAll(f.apply(exceptionTracker.notCheckedExceptions));


    }


    /**
     * Checks if there are any thrown exceptions.
     *
     * @return true if there are thrown exceptions, false otherwise
     */
    public boolean hasErrors() {
        return !this.thrownExceptions.isEmpty();
    }


    /**
     * Checks if there are no thrown exceptions.
     *
     * @return true if there are no thrown exceptions, false otherwise
     */
    public boolean hasNotErrors() {
        return this.thrownExceptions.isEmpty();
    }


    /**
     * Displays all thrown exceptions in red color.
     */
    public void showThrownException(){
        showException("Thrown Exceptions:", this.thrownExceptions, Color.RED);
    }


    /**
     * Displays all not thrown exceptions in green color.
     */
    public void showPassedChecks(){
        showException("Passed Checks:", this.passedChecks, Color.GREEN);
    }


    /**
     * Displays all not checked exceptions in yellow color.
     */
    public void showNotCheckedThrownException(){
        showException("Not Checked Exceptions:", this.notCheckedExceptions, Color.YELLOW);
    }


    /**
     * Displays exceptions from the given map with the specified color and title.
     *
     * @param title               the title to display
     * @param excepctionMap       the map of exceptions to display
     * @param colorTextException  the color to use for exception messages
     */
    private void showException(String title, Map<String, Set<CheckerException>> excepctionMap, Color colorTextException){
        AnsiConsole.systemInstall();
        try {
            StringBuilder titleBuilder = new StringBuilder(title);
            String titleMessage = Ansi.ansi().bold().a(titleBuilder).reset().toString();
            System.out.println(titleMessage);
            if(excepctionMap.values().stream().allMatch(Set::isEmpty)) {
                System.out.println("\t" + CLEAN_MESSAGE);
            } else {
                excepctionMap.entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .forEach(entry -> {
                    StringBuilder nameObjectBuilder = new StringBuilder(entry.getKey());
                    String messageNameObject = Ansi.ansi().fgBlue().bold().a(nameObjectBuilder).reset().toString();
                    System.out.println("\t" + messageNameObject);
                    entry.getValue().forEach(exception -> {
                        StringBuilder messageExceptionBuilder = new StringBuilder(exception.toString());
                        String messageException = Ansi.ansi().fg(colorTextException).bold().a(messageExceptionBuilder).reset().toString();
                        System.out.println("\t\t" + messageException);
                    });
                });
            }
        } catch (Exception e) {
            String exceptionMessage = Ansi.ansi().fgRed().bold().a(e).reset().toString();
            System.out.println(exceptionMessage);
        }
        AnsiConsole.systemUninstall();
    }

    /**
     * Displays all categories of exceptions: thrown, not thrown, and not checked.
     */
    public void show(){
        showThrownException();
        showPassedChecks();
        showNotCheckedThrownException();
    }

}
