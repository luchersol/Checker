
/**
 * Cloner provides a utility method for deep cloning objects using Kryo serialization.
 */
package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class Cloner {

    /**
     * The Kryo instance used for serialization and deserialization.
     */
    private static final Kryo kryo = new Kryo();

    static {
        // Kryo is configured to not require registration of classes.
        kryo.setRegistrationRequired(false);
    }


    /**
     * Performs a deep clone of the given object using Kryo serialization.
     *
     * @param <T>    the type of the object
     * @param object the object to deep clone
     * @return a deep clone of the object
     */
    @SuppressWarnings("unchecked")
    public static <T> T deepClone(T object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, object);
        output.close();

        Input input = new Input(new ByteArrayInputStream(baos.toByteArray()));
        return (T) kryo.readClassAndObject(input);
    }

}
