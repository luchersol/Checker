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

    /**
     * Retrieves the message text for the given key using the default locale.
     *
     * <p>
     * If the key is not found in the resource bundle, a default colored warning message
     * is returned.
     * </p>
     *
     * @param key the key of the message to retrieve
     * @return the message string for the given key, or a default colored message if not found
     */
    public static String getProperty(String key) {
        return getProperty(key, Locale.getDefault());
    }

    /**
     * Retrieves the message text for the given key using the specified locale.
     *
     * <p>
     * If the key is not found in the resource bundle for the given locale, a default
     * colored warning message is returned.
     * </p>
     *
     * @param key    the key of the message to retrieve
     * @param locale the locale to use for message retrieval
     * @return the message string for the given key and locale, or a default colored message if not found
     */
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
