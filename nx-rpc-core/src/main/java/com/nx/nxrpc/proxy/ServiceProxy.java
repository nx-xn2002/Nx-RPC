package com.nx.nxrpc.proxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.nx.nxrpc.RpcApplication;
import com.nx.nxrpc.config.RpcConfig;
import com.nx.nxrpc.model.RpcRequest;
import com.nx.nxrpc.model.RpcResponse;
import com.nx.nxrpc.serializer.Serializer;
import com.nx.nxrpc.serializer.SerializerFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * jdk service proxy
 *
 * @author nx-xn2002
 */
@Slf4j
public class ServiceProxy implements InvocationHandler {
    static RpcConfig config = RpcApplication.getRpcConfig();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Serializer serializer = SerializerFactory.getInstance(config.getSerializer());
        RpcRequest rpcRequest = RpcRequest.builder()
            .serviceName(method.getDeclaringClass().getName())
            .methodName(method.getName())
            .parameterTypes(method.getParameterTypes())
            .args(args)
            .build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            try (HttpResponse httpResponse = HttpRequest.post(
                "http://" + config.getServerHost() + ":" + config.getServerPort()).body(bodyBytes).execute()) {
                byte[] result = httpResponse.bodyBytes();
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            log.error("service invoke failed", e);
        }
        return null;
    }
}
