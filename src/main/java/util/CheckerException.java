package util;

public class CheckerException extends RuntimeException {

    protected CheckerException(String message) {
        super(message);
    }


    protected CheckerException(Exception exception) {
        super(exception.getCause().getLocalizedMessage());
    }

    public static CheckerException of(Exception exception){
        return new CheckerException(exception);
    }

    @Override
    public String toString() {
        return new StringBuilder(" - ")
                    .append(getLocalizedMessage())
                    .toString();
    }
}
