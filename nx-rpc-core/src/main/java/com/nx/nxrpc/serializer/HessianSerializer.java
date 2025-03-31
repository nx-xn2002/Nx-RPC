package com.nx.nxrpc.serializer;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * hessian serializer
 *
 * @author nx-xn2002
 */
public class HessianSerializer implements Serializer {
    /**
     * serialize
     *
     * @param object object
     * @return {@link byte[] }
     * @throws IOException ioexception
     */
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(bos);
        ho.writeObject(object);
        return bos.toByteArray();
    }

    /**
     * deserialize
     *
     * @param bytes  bytes
     * @param tClass t class
     * @return {@link T }
     * @throws IOException ioexception
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> tClass) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        HessianInput hi = new HessianInput(bis);
        return tClass.cast(hi.readObject(tClass));
    }
}
