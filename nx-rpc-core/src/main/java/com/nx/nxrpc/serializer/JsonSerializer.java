package com.nx.nxrpc.serializer;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nx.nxrpc.model.RpcRequest;
import com.nx.nxrpc.model.RpcResponse;

/**
 * json serializer
 *
 * @author nx-xn2002
 */
public class JsonSerializer implements Serializer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * serialize
     *
     * @param obj obj
     * @return {@link byte[] }
     * @throws IOException ioexception
     */
    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(obj);
    }

    /**
     * deserialize
     *
     * @param bytes     bytes
     * @param classType class type
     * @return {@link T }
     * @throws IOException ioexception
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> classType) throws IOException {
        T obj = OBJECT_MAPPER.readValue(bytes, classType);
        if (obj instanceof RpcRequest) {
            return handleRequest((RpcRequest)obj, classType);
        }
        if (obj instanceof RpcResponse) {
            return handleResponse((RpcResponse)obj, classType);
        }
        return obj;
    }

    /**
     * handle request
     *
     * @param rpcRequest rpc request
     * @param type       type
     * @return {@link T }
     * @throws IOException ioexception
     */
    private <T> T handleRequest(RpcRequest rpcRequest, Class<T> type) throws IOException {
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] args = rpcRequest.getArgs();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> clazz = parameterTypes[i];
            if (!clazz.isAssignableFrom(args[i].getClass())) {
                byte[] argBytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                args[i] = OBJECT_MAPPER.readValue(argBytes, clazz);
            }
        }
        return type.cast(rpcRequest);
    }

    /**
     * handle response
     *
     * @param rpcResponse rpc response
     * @param type        type
     * @return {@link T }
     * @throws IOException ioexception
     */
    private <T> T handleResponse(RpcResponse rpcResponse, Class<T> type) throws IOException {
        // handle request data
        byte[] dataBytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(dataBytes, rpcResponse.getDataType()));
        return type.cast(rpcResponse);
    }
}
