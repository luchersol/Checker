package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

public class Message {
    
    private static Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream("src\\main\\resources\\messages.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperty(String key){
        String defaultMessage = Ansi.ansi()
                                    .fgBrightYellow().bold()
                                    .a(properties.getProperty("default_message").formatted(key))
                                    .reset().toString();
        return properties.getProperty(key, defaultMessage);
    }

    private static String sendMessage(String key, Object... args){
        String format = getProperty(key);
        return String.format(format, args);
    }

    public static String sendMessage(String init, String function){
        return sendMessage(init, function, new Object[]{});
    }

    public static String sendMessage(String init, String function, Object... args){
        StringBuilder format = new StringBuilder(init);
        if(!(init.endsWith(".") || function.startsWith(".")))
            format.append(".");
        format.append(function);
        return sendMessage(format.toString(), args);
    }

    public static void main(String[] args) {
        System.out.println(sendMessage("collection.checker_collection", "args"));
    }

}
