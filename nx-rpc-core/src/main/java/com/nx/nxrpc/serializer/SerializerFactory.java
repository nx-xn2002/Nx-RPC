package com.nx.nxrpc.serializer;

import com.nx.nxrpc.spi.SpiLoader;

/**
 * serializer factory
 *
 * @author nx-xn2002
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * get instance
     *
     * @param key key
     * @return {@link Serializer }
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }
}
