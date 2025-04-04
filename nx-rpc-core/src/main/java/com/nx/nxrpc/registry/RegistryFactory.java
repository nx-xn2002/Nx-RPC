package com.nx.nxrpc.registry;

import com.nx.nxrpc.spi.SpiLoader;

/**
 * registry factory
 *
 * @author nx-xn2002
 */
public class RegistryFactory {

    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * default registry
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * get instance
     *
     * @param key key
     * @return {@link Registry }
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }

}
