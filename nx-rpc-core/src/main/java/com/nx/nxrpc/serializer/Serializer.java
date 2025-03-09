package com.nx.nxrpc.serializer;

import java.io.IOException;

/**
 * serializer
 *
 * @author nx-xn2002
 */
public interface Serializer {
    /**
     * serialize
     *
     * @param object object
     * @return {@link byte[] }
     * @throws IOException ioexception
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * deserialize
     *
     * @param bytes bytes
     * @param type  type
     * @return {@link T }
     * @throws IOException ioexception
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}
