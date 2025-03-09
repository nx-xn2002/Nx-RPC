package com.nx.nxrpc.server;

import java.io.IOException;

import com.nx.nxrpc.model.RpcRequest;
import com.nx.nxrpc.model.RpcResponse;
import com.nx.nxrpc.serializer.JdkSerializer;
import com.nx.nxrpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

/**
 * http server handler
 *
 * @author nx-xn2002
 */
public class VertxHttpServerHandler implements Handler<HttpServerRequest> {
    private final Serializer serializer = new JdkSerializer();

    @Override
    public void handle(HttpServerRequest request) {
        System.out.println("Received request: " + request.method() + " " + request.uri());
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RpcResponse rpcResponse = new RpcResponse();
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                return;
            }
        });
    }

    void doResponse(HttpServerRequest request, RpcResponse rpcResponse) {
        HttpServerResponse response = request.response().putHeader("content-type", "application/json");

        try {
            byte[] serialized = serializer.serialize(rpcResponse);
            response.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            response.end(Buffer.buffer());
        }
    }
}
