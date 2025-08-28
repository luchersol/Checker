package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.fusesource.jansi.AnsiConsole;

public class ExceptionTracker  {

    private static String CLEAN_MESSAGE = Ansi.ansi().fgGreen().a("CLEAN").reset().toString();

    private String name;
    private Map<String, List<CheckerException>> thrownExceptions;
    private Map<String, List<CheckerException>> notThrownExceptions;
    private Map<String, List<CheckerException>> notCheckedExceptions;

    public ExceptionTracker(String name) {
        this.name = name;
        this.thrownExceptions = new HashMap<>();
        this.thrownExceptions.put(name, new ArrayList<>());
        this.notThrownExceptions = new HashMap<>();
        this.notThrownExceptions.put(name, new ArrayList<>());
        this.notCheckedExceptions = new HashMap<>();
        this.notCheckedExceptions.put(name, new ArrayList<>());
    }

    /**
     * @param name
     * @return ExceptionTracker
     */
    public static ExceptionTracker empty(String name){
        return new ExceptionTracker(name);
    }

    /**
     * @param e
     */
    public void addThrownException(Exception e) {
        this.thrownExceptions.get(name).add(CheckerException.of(e));
    }

    /**
     * @param e
     */
    public void addNotThrownException(Exception e) {
        this.notThrownExceptions.get(name).add(CheckerException.of(e));
    }

    /**
     * @param e
     */
    public void addNotCheckedException(Exception e) {
        this.notCheckedExceptions.get(name).add(CheckerException.of(e));
    }

    /**
     * @return Map<String, List<CheckerException>>
     */
    public Map<String, List<CheckerException>> getThrownExceptions() {
        return thrownExceptions;
    }

    /**
     * @return Map<String, List<CheckerException>>
     */
    public Map<String, List<CheckerException>> getNotThrownExceptions() {
        return notThrownExceptions;
    }

    /**
     * @param exceptionTracker
     */
    public void merge(ExceptionTracker exceptionTracker) {
        Function<Map<String, List<CheckerException>>, List<CheckerException>> f = map -> map.values().stream()
                .flatMap(List::stream).collect(Collectors.toList());
        String propertyName = exceptionTracker.name;

        this.thrownExceptions.putIfAbsent(propertyName, new ArrayList<>());
        this.notThrownExceptions.putIfAbsent(propertyName, new ArrayList<>());
        this.notCheckedExceptions.putIfAbsent(propertyName, new ArrayList<>());

        this.thrownExceptions.get(propertyName).addAll(f.apply(exceptionTracker.thrownExceptions));
        this.notThrownExceptions.get(propertyName).addAll(f.apply(exceptionTracker.notThrownExceptions));
        this.notCheckedExceptions.get(propertyName).addAll(f.apply(exceptionTracker.notCheckedExceptions));


    }

    /**
     * @return boolean
     */
    public boolean hasErrors() {
        return !this.thrownExceptions.isEmpty();
    }

    /**
     * @return boolean
     */
    public boolean hasNotErrors() {
        return this.thrownExceptions.isEmpty();
    }

    public void showThrownException(){
        showException("Thrown Exceptions: ", this.thrownExceptions, Color.RED);
    }

    public void showNotThrownException(){
        showException("Not Thrown Exceptions: ", this.notThrownExceptions, Color.GREEN);
    }

    public void showNotCheckedThrownException(){
        showException("Not Checked Exceptions: ", this.notCheckedExceptions, Color.YELLOW);
    }


    /**
     * @param excepctionMap
     * @param colorTextException
     */
    private void showException(String title, Map<String, List<CheckerException>> excepctionMap, Color colorTextException){
        AnsiConsole.systemInstall();
        try {
            StringBuilder titleBuilder = new StringBuilder(title);
            String titleMessage = Ansi.ansi().bold().a(titleBuilder).reset().toString();
            System.out.println(titleMessage);
            if(excepctionMap.values().stream().allMatch(List::isEmpty)) {
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
    public void show(){
        showThrownException();
        showNotThrownException();
        showNotCheckedThrownException();
    }

}
