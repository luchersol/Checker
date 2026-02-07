package com.luchersol.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingFormatArgumentException;
import java.util.Properties;

import org.fusesource.jansi.Ansi;

/**
 * Message provides utility methods for retrieving and formatting localized messages from a properties file.
 * It supports colored output using Jansi and handles missing format arguments gracefully.
 */
public class MessageService {

    /**
     * The properties object holding all loaded messages from the properties file.
     */
    private static final Properties MSG_PROPERTIES = new Properties();

    static {
        try (InputStream input = MessageService.class.getClassLoader().getResourceAsStream("messages.properties")) {
            MSG_PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MessageService(){
    }


    /**
     * Retrieves the message for the given key from the properties file, or a default colored message if not found.
     *
     * @param key the message key
     * @return the message string, or a default message if the key is not found
     */
    protected static String getProperty(String key){
        String defaultMessage = Ansi.ansi()
                                    .fgBrightYellow().bold()
                                    .a(MSG_PROPERTIES.getProperty("default_message").formatted(key))
                                    .reset().toString();
        return MSG_PROPERTIES.getProperty(key, defaultMessage);
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
