package com.nx.nxrpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * mock service proxy(jdk service proxy)
 *
 * @author nx-xn2002
 */
public class MockServiceProxy implements InvocationHandler {
    private Map<Class<?>, Object> mockMap = new HashMap<>();

    public MockServiceProxy() {
        initMockMap();
    }

    /**
     * mock
     *
     * @param clz        clz
     * @param mockObject mock object
     * @return {@link MockServiceProxy }
     */
    public MockServiceProxy mock(Class<?> clz, Object mockObject) {
        mockMap.put(clz, mockObject);
        return this;
    }

    private void initMockMap() {
        mockMap.put(boolean.class, false);
        mockMap.put(short.class, (short)0);
        mockMap.put(int.class, 0);
        mockMap.put(long.class, 0L);
        mockMap.put(String.class, "mock String");
    }

    /**
     * invoke
     *
     * @param proxy  proxy
     * @param method method
     * @param args   args
     * @return {@link Object }
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Class<?> methodReturnType = method.getReturnType();
        System.out.println("mock invoke " + method.getName());
        return mockMap.getOrDefault(methodReturnType, null);
    }
}
