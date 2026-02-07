package com.luchersol.core.util;

import java.util.Locale;
import java.util.MissingFormatArgumentException;
import java.util.ResourceBundle;

import org.fusesource.jansi.Ansi;

/**
 * Message provides utility methods for retrieving and formatting localized messages from a properties file.
 * It supports colored output using Jansi and handles missing format arguments gracefully.
 */
public class MessageService {

    private static final String BASENAME = "messages";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MessageService(){
    }

    public static String getProperty(String key) {
        return getProperty(key, Locale.getDefault());
    }

    public static String getProperty(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(BASENAME, locale);
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            return Ansi.ansi()
                    .fgBrightYellow().bold()
                    .a(getProperty("default_message").formatted(key))
                    .reset().toString();
        }

    }


    /**
     * Sends a formatted message for the given initial key and function, with no arguments.
     *
     * @param init     the initial part of the message key
     * @param function the function or message type
     * @return the formatted message string
     */
    public static Message sendMessage(String init, String function){
        return sendMessage(init, function, new Object[]{});
    }


    /**
     * Sends a formatted message for the given initial key, function, and arguments.
     * Handles missing format arguments gracefully with a colored error message.
     *
     * @param init     the initial part of the message key
     * @param function the function or message type
     * @param args     the arguments to format the message
     * @return the formatted message string, or a colored error message if formatting fails
     */
    public static Message sendMessage(String init, String function, Object... args){
        try {
            StringBuilder format = new StringBuilder(init);
            if(!(init.endsWith(".") || function.startsWith(".")))
                format.append(".");

            format.append(function);
            return Message.ofCode(format.toString(), args);
        } catch (MissingFormatArgumentException e) {
            return Message.getErrorMessage();
        }

    }

}
