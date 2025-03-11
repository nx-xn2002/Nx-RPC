package com.nx.nxrpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * service proxy factory
 *
 * @author nx-xn2002
 */
public class ServiceProxyFactory {
    /**
     * get proxy
     *
     * @param serviceClass service class
     * @param handler      supported handler {@link JdkServiceProxy}
     * @return {@link T }
     */
    public static <T> T getProxy(Class<T> serviceClass, InvocationHandler handler) {
        return serviceClass.cast(
            Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[] {serviceClass},
                handler
            )
        );
    }

    /**
     * get proxy
     * default supported handler {@link JdkServiceProxy}
     *
     * @param serviceClass service class
     * @return {@link T }
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        return getProxy(serviceClass, new JdkServiceProxy());
    }

    /**
     * get mock proxy
     *
     * @param serviceClass service class
     * @return {@link T }
     */
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return getProxy(serviceClass, new MockServiceProxy());
    }
}
