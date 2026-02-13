package com.luchersol.core.util;

import java.util.Optional;

/**
 * CheckerException is a custom unchecked exception used for validation and checking errors.
 * It provides factory methods and custom string representation for error reporting.
 */
public class CheckerException extends RuntimeException {

    /**
     * The message associated with this exception.
     */
    private Message message_;

    /**
     * Constructs a CheckerException with the specified message.
     *
     * @param message_ the detail message
     */
    protected CheckerException(Message message_) {
        super(message_.getMessage());
        this.message_ = message_;
    }



    /**
     * Constructs a CheckerException from another exception, using its cause's localized message.
     *
     * @param exception the exception to wrap
     */
    protected CheckerException(Exception exception) {
        this(Message.ofMessage(
                Optional.ofNullable(exception.getCause())
                        .map(e -> e.getMessage())
                        .orElse(exception.getMessage())
                )
            );
    }


    /**
     * Factory method to create a CheckerException from another exception.
     *
     * @param exception the exception to wrap
     * @return a new CheckerException instance
     */
    public static CheckerException of(Exception exception){
        return exception instanceof CheckerException ?
                (CheckerException) exception :
                new CheckerException(exception);
    }

    public CheckerException negate() {
        this.message_.negate();
        return this;
    }

    /**
     * Returns a string representation of the exception, prefixed with " - ".
     *
     * @return the string representation of the exception
     */
    @Override
    public String toString() {
        return new StringBuilder(" - ")
                    .append(this.message_.getMessage())
                    .toString();
    }
}
