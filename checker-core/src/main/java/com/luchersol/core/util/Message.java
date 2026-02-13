package com.luchersol.core.util;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

/**
 * Represents a formatted message with an optional code and arguments.
 *
 * <p>
 * This class is used for generating messages that can be displayed, logged,
 * or associated with validation errors. Messages can be created from a
 * predefined code (with arguments) or directly from a string.
 *
 */
public class Message {

    /** The message code, usually used as a key in message properties. */
    private String code;

    /** The formatted message string. */
    private String message;

    /** Optional arguments for formatting the message. */
    private Object[] args;

    /**
     * Constructs a new {@code Message} with the given code, message template, and arguments.
     *
     * @param code    the message code (can be {@code null} if not applicable)
     * @param message the message template or literal string
     * @param args    optional arguments to format the message
     */
    public Message(String code, String message, Object... args) {
        this.code = code;
        this.message = message;
        this.args = args;
    }

    /**
     * Creates a new {@code Message} from a code and optional arguments.
     * The text is retrieved from the message service.
     *
     * @param code the message code
     * @param args optional arguments for formatting
     * @return a new {@code Message} instance
     */
    public static Message ofCode(String code, Object... args){
        return new Message(code, getText(code, args), args);
    }

    /**
     * Creates a new {@code Message} from a literal string.
     *
     * @param message the literal message
     * @return a new {@code Message} instance
     */
    public static Message ofMessage(String message) {
        return new Message(null, message);
    }

    /**
     * Returns the message code.
     *
     * @return the code associated with this message, or {@code null} if none
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Formats the message text for the given code using the provided arguments.
     *
     * @param code the message code to retrieve from the message service
     * @param args the arguments to format the message
     * @return the formatted message string
     */
    private static String getText(String code, Object... args){
        String format = MessageService.getProperty(code);
        return args.length == 0 ? format : String.format(format, args);
    }

    /**
     * Returns a standard error message with formatting applied (e.g., color and style).
     *
     * @return a {@code Message} instance representing a formatted error
     */
    public static Message getErrorMessage() {
        String message = MessageService.getProperty("error_format_message");
        message = Ansi.ansi().fg(Color.MAGENTA).bold().a(message).reset().toString();
        return ofMessage(message);
    }

    /**
     * Returns the formatted message string.
     *
     * @return the message text
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns a negated version of this message by appending ".__not" to the code
     * and reformatting the message accordingly.
     *
     * @return this {@code Message} instance after negation
     */
    public Message negate() {
        this.code += ".__not";
        this.message = getText(code, args);
        return this;
    }
}
