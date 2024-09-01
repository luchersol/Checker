package util;
import java.util.ArrayList;
import java.util.List;

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
    }
    
    private List<InnerExceptionTracker> thrownExceptions;
    private List<InnerExceptionTracker> notThrownExceptions;
    private int cont = 0;

    public ExceptionTracker() {
        this.thrownExceptions = new ArrayList<>();
        this.notThrownExceptions = new ArrayList<>();
    }

    public static ExceptionTracker empty(){
        return new ExceptionTracker();
    }

    public void addThrownException(Exception e) {
        this.thrownExceptions.add(InnerExceptionTracker.of(cont, e));
        this.cont++;
    }

    public void addNotThrownException(Exception e) {
        this.notThrownExceptions.add(InnerExceptionTracker.of(cont, e));
        this.cont++;
    }

    public List<InnerExceptionTracker> getThrownExceptions() {
        return thrownExceptions;
    }

    public List<InnerExceptionTracker> getNotThrownExceptions() {
        return notThrownExceptions;
    }

    public boolean hasErrors() {
        return ! this.thrownExceptions.isEmpty();
    }

    public boolean hasNotErrors() {
        return this.thrownExceptions.isEmpty();
    }

    public void swapExceptions() {
        List<InnerExceptionTracker> temp = new ArrayList<>(thrownExceptions);
        this.thrownExceptions = new ArrayList<>(notThrownExceptions);
        this.notThrownExceptions = temp;
    }

    public void showThrownException(){
        System.out.println("Thrown Exception: " + this.thrownExceptions);
    }

    public void showNotThrownException(){
        System.out.println("Not Thrown Exception: " + this.notThrownExceptions);
    }

    public void show(){
        showThrownException();
        showNotThrownException();
    }

}
