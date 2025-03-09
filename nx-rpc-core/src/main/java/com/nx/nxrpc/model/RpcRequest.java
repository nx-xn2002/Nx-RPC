package com.nx.nxrpc.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rpc request
 *
 * @author nx-xn2002
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {

    /**
     * service name
     */
    private String serviceName;

    /**
     * method name
     */
    private String methodName;

    /**
     * parameter types
     */
    private Class<?>[] parameterTypes;

    /**
     * args
     */
    private Object[] args;

}