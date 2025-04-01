package com.nx.nxrpc.server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.nx.nxrpc.RpcApplication;
import com.nx.nxrpc.model.RpcRequest;
import com.nx.nxrpc.model.RpcResponse;
import com.nx.nxrpc.registry.LocalRegistry;
import com.nx.nxrpc.serializer.JdkSerializer;
import com.nx.nxrpc.serializer.Serializer;
import com.nx.nxrpc.serializer.SerializerFactory;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * http server handler
 *
 * @author nx-xn2002
 */
@Slf4j
public class VertxHttpServerHandler implements Handler<HttpServerRequest> {
    private final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

    @Override
    public void handle(HttpServerRequest request) {
        log.info("Received request: {} {}", request.method(), request.uri());
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                log.error("deserialize failed", e);
            }
            RpcResponse rpcResponse = new RpcResponse();
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request, rpcResponse);
                return;
            }
            try {
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.getDeclaredConstructor().newInstance(), rpcRequest.getArgs());
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setData(result);
                rpcResponse.setMessage("ok");
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                     InvocationTargetException e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(request, rpcResponse);
        });
    }

    void doResponse(HttpServerRequest request, RpcResponse rpcResponse) {
        HttpServerResponse response = request.response().putHeader("content-type", "application/json");

        try {
            byte[] serialized = serializer.serialize(rpcResponse);
            response.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            log.error("response failed", e);
            response.end(Buffer.buffer());
        }
    }
}
