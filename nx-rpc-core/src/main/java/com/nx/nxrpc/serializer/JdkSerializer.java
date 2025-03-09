package com.nx.nxrpc.serializer;

import java.io.*;

/**
 * JDK Serializer
 *
 * @author nx-xn2002
 */
public class JdkSerializer implements Serializer {

    /**
     * serialize
     *
     * @param object object
     * @return {@link byte[] }
     * @throws IOException ioexception
     */
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * deserialize
     *
     * @param bytes bytes
     * @param type  type
     * @return {@link T }
     * @throws IOException ioexception
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return type.cast(objectInputStream.readObject());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
