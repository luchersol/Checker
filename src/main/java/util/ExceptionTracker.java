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

    private static class InnerExceptionTracker {

        private int index;
        private Exception exception;

        public InnerExceptionTracker(int index, Exception exception) {
            this.index = index;
            this.exception = exception;
        }

        public static InnerExceptionTracker of(int index,Exception exception){
            return new InnerExceptionTracker(index, exception);
        }

        @Override
        public String toString() {
            return this.exception.getMessage();
        }
    }

    private String name;
    private Map<String, List<InnerExceptionTracker>> thrownExceptions;
    private Map<String, List<InnerExceptionTracker>> notThrownExceptions;
    private Map<String, List<InnerExceptionTracker>> notCheckedExceptions;
    private int cont;

    public ExceptionTracker(String name) {
        this.name = name;
        this.thrownExceptions = new HashMap<>();
        this.thrownExceptions.put(name, new ArrayList<>());
        this.notThrownExceptions = new HashMap<>();
        this.notThrownExceptions.put(name, new ArrayList<>());
        this.notCheckedExceptions = new HashMap<>();
        this.notCheckedExceptions.put(name, new ArrayList<>());
        this.cont = 0;
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
        this.thrownExceptions.get(name).add(InnerExceptionTracker.of(cont, e));
        this.cont++;
    }

    /**
     * @param e
     */
    public void addNotThrownException(Exception e) {
        this.notThrownExceptions.get(name).add(InnerExceptionTracker.of(cont, e));
        this.cont++;
    }

    /**
     * @param e
     */
    public void addNotCheckedException(Exception e) {
        this.notCheckedExceptions.get(name).add(InnerExceptionTracker.of(cont, e));
        this.cont++;
    }
    /**
     * @return Map<String, List<InnerExceptionTracker>>
     */
    public Map<String, List<InnerExceptionTracker>> getThrownExceptions() {
        return thrownExceptions;
    }

    /**
     * @return Map<String, List<InnerExceptionTracker>>
     */
    public Map<String, List<InnerExceptionTracker>> getNotThrownExceptions() {
        return notThrownExceptions;
    }

    /**
     * @param exceptionTracker
     */
    public void merge(ExceptionTracker exceptionTracker) {
        Function<Map<String, List<InnerExceptionTracker>>, List<InnerExceptionTracker>> f = map -> map.values().stream()
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
    private void showException(String title, Map<String, List<InnerExceptionTracker>> excepctionMap, Color colorTextException){
        AnsiConsole.systemInstall();
        StringBuilder titleBuilder = new StringBuilder(title);
        String titleMessage = Ansi.ansi().bold().a(titleBuilder).reset().toString();
        System.out.println(titleMessage);
        for (Map.Entry<String, List<InnerExceptionTracker>> entry : excepctionMap.entrySet()) {
            StringBuilder nameObjectBuilder = new StringBuilder(entry.getKey());
            String messageNameObject = Ansi.ansi().fgBlue().bold().a(nameObjectBuilder).reset().toString();
            System.out.println("\t" + messageNameObject);
            for (InnerExceptionTracker exception : entry.getValue()) {
                StringBuilder messageExceptionBuilder = new StringBuilder(exception.toString());
                String messageException = Ansi.ansi().fg(colorTextException).bold().a(messageExceptionBuilder).reset().toString();
                System.out.println("\t\t" + messageException);
            }
        }
        AnsiConsole.systemUninstall();
    }
    public void show(){
        showThrownException();
        showNotThrownException();
        showNotCheckedThrownException();
    }

}
