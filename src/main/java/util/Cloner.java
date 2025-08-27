package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class Cloner {

    private static final Kryo kryo = new Kryo();

    /**
     * @param object
     * @return T
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
