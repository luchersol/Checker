package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.fusesource.jansi.Ansi;

public class Message {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = new FileInputStream("src\\main\\resources\\messages.properties")) {
            PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key
     * @return String
     */
    private static String getProperty(String key){
        String defaultMessage = Ansi.ansi()
                                    .fgBrightYellow().bold()
                                    .a(PROPERTIES.getProperty("default_message").formatted(key))
                                    .reset().toString();
        return PROPERTIES.getProperty(key, defaultMessage);
    }

    /**
     * @param key
     * @param args
     * @return String
     */
    private static String sendMessage(String key, Object... args){
        String format = getProperty(key);
        return args.length == 0 ? format : String.format(format, args);
    }

    /**
     * @param init
     * @param function
     * @return String
     */
    public static String sendMessage(String init, String function){
        return sendMessage(init, function, new Object[]{});
    }

    /**
     * @param init
     * @param function
     * @param args
     * @return String
     */
    public static String sendMessage(String init, String function, Object... args){
        StringBuilder format = new StringBuilder(init);
        if(!(init.endsWith(".") || function.startsWith(".")))
            format.append(".");
        format.append(function);
        return sendMessage(format.toString(), args);
    }

}
