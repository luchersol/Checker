package src.main;

import java.util.ArrayList;
import java.util.List;

public class AbstractChecker<T> {
    
    protected T object;

    protected List<Exception> errors;

    protected boolean saveErrors;

    public AbstractChecker(T object) {
        this.object = object;
        this.errors = new ArrayList<>();
        this.saveErrors = true;
    }

    public AbstractChecker(T object, List<Exception> errors) {
        this.object = object;
        this.errors = errors;
        this.saveErrors = true;
    }

    public AbstractChecker(T object, List<Exception> errors, boolean saveErrors) {
        this.object = object;
        this.errors = errors;
        this.saveErrors = saveErrors;
    }

    public void is(boolean condition, String message) {
        if(!condition){
            IllegalArgumentException exception = new IllegalArgumentException(message);
            if(saveErrors)
                this.errors.add(exception);
            else
                throw exception;
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

    public Boolean toBoolean(){
        return this.errors.isEmpty();
    }

}
