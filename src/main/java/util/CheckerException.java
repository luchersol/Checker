package util;

public class CheckerException extends RuntimeException {

    public CheckerException(String message) {
        super(message);
    }


    public CheckerException(Exception exception) {
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
