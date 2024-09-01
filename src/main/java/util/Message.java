package util;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Message {
    
    private static Properties properties;

    static {
        try {
            InputStream input = new FileInputStream("../resources/messages.properties");
            properties.load(input);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
