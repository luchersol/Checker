package com.luchersol.core.util;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

public class Message {
    private String code;
    private String message;
    private Object[] args;

    public Message(String code, String message, Object... args) {
        this.code = code;
        this.message = message;
        this.args = args;
    }

    public static Message ofCode(String code, Object... args){
        return new Message(code, getText(code, args), args);
    }

    public static Message ofMessage(String message) {
        return new Message(null, message);
    }

    public String getCode() {
        return this.code;
    }

    /**
     * Formats the message for the given key with the provided arguments.
     *
     * @param key  the message key
     * @param args the arguments to format the message
     * @return the formatted message string
     */
    private static String getText(String code, Object... args){
        String format = MessageService.getProperty(code);
        return args.length == 0 ? format : String.format(format, args);
    }

    public static Message getErrorMessage() {
        String message = MessageService.getProperty("error_format_message");
        message = Ansi.ansi().fg(Color.MAGENTA).bold().a(message).reset().toString();
        return ofMessage(message);
    }

    public String getMessage() {
        return this.message;
    }

    public Message negate() {
        this.code += ".__not";
        this.message = getText(code, args);
        return this;
    }

}
