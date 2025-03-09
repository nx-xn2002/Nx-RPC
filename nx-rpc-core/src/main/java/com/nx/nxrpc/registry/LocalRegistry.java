package com.nx.nxrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * local registry
 *
 * @author nx-xn2002
 */
public class LocalRegistry {
    /**
     * registry
     */
    private static final Map<String, Class<?>> REGISTRY = new ConcurrentHashMap<>();

    /**
     * register
     *
     * @param serviceName service name
     * @param implClass   impl class
     */
    public static void register(String serviceName, Class<?> implClass) {
        REGISTRY.put(serviceName, implClass);
    }

    /**
     * get service by serviceName
     *
     * @param serviceName service name
     * @return {@link Class }<{@link ? }>
     */
    public static Class<?> get(String serviceName) {
        return REGISTRY.get(serviceName);
    }

    /**
     * remove service by serviceName
     *
     * @param serviceName service name
     */
    public static void remove(String serviceName) {
        REGISTRY.remove(serviceName);
    }
}
