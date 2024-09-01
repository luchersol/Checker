package util;

import static util.Message.sendMessage;

public class AbstractChecker<T> {
    
    protected T object;

    protected String name;

    protected ExceptionTracker exceptionTracker;

    protected boolean saveErrors;

    public AbstractChecker(String name){
        this.name = name;
        this.exceptionTracker = ExceptionTracker.empty();
        this.saveErrors = true;
    }

    public AbstractChecker(T object, String name) {
        this.object = object;
        this.name = name;
        this.exceptionTracker = ExceptionTracker.empty();
        this.saveErrors = true;
    }

    public void is(boolean condition, String message) {
        RuntimeException exception = new RuntimeException(new Error(message));
        if(!condition){
            if(saveErrors)
                this.exceptionTracker.addThrownException(exception);
            else
                throw exception;
        } else {
            if(saveErrors){
                this.exceptionTracker.addNotThrownException(exception);
            }
        }
    }

    public void isNot(boolean condition, String message){
        is(!condition, message);
    }

    public void isNull(String message) {
        is(this.object == null, message);
    }

    public void isNotNull(String message) {
        is(this.object != null, message);
    }

    public Boolean hasErrors(){
        return this.exceptionTracker.hasErrors();
    }

    public void reversed(){
        this.exceptionTracker.swapExceptions();
    }

    public Boolean hasNotErrors(){
        return this.exceptionTracker.hasNotErrors();
    }

    public void showThrownException(){
        this.exceptionTracker.showThrownException();
    }

    public void showNotThrownException(){
        this.exceptionTracker.showNotThrownException();
    }

    public void show(){
        this.exceptionTracker.show();
    }

}
