package util;

import static util.Message.sendMessage;

import java.util.function.Predicate;

public class AbstractChecker<T> implements InterfaceChecker<AbstractChecker<T>, T>{
    
    protected T object;

    protected String name;

    protected ExceptionTracker exceptionTracker;

    protected boolean saveErrors;

    protected boolean stop;

    private static final String INIT_ABSTRACT_CHECKER = "abstract_checker";

    public AbstractChecker(T object, String name){
        this.object = object;
        this.name = name;
        this.exceptionTracker = ExceptionTracker.empty();
        this.saveErrors = true;
        this.stop = object == null;
    }

    public AbstractChecker(String name, ExceptionTracker exceptionTracker){
        this.name = name;
        this.exceptionTracker = exceptionTracker;
        this.saveErrors = true;
        this.stop = object == null;
    }

    public AbstractChecker(T object, String name, ExceptionTracker exceptionTracker) {
        this.object = object;
        this.name = name;
        this.exceptionTracker = exceptionTracker;
        this.saveErrors = true;
        this.stop = object == null;
    }

    public void stop() {
        this.stop = true;
    }

    public AbstractChecker<T> is(Predicate<T> condition, String message) {
        RuntimeException exception = new RuntimeException(new Error(message));
        if(stop) {
            return this;
        }
            
        if(!condition.test(this.object)){
            if(saveErrors){
                this.exceptionTracker.addThrownException(exception);
            }else{
                throw exception;
            }
        } else {
            if(saveErrors){
                this.exceptionTracker.addNotThrownException(exception);
            }
        }

        return this;
    }

    public AbstractChecker<T> is(Predicate<T> condition) {
        return is(condition, sendMessage(INIT_ABSTRACT_CHECKER, "is"));
    }

    public AbstractChecker<T> isNot(Predicate<T> condition, String message){
        return is(condition.negate(), message);
    }

    public AbstractChecker<T> isNot(Predicate<T> condition){
        return is(condition.negate(), sendMessage(INIT_ABSTRACT_CHECKER, "is_not"));
    }

    public Boolean hasErrors(){
        return this.exceptionTracker.hasErrors();
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
