package com.nx.nxrpc.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rpc response
 *
 * @author nx-xn2002
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse implements Serializable {

    /**
     * data
     */
    private Object data;

    /**
     * data type
     */
    private Class<?> dataType;

    /**
     * message
     */
    private String message;

    /**
     * exception
     */
    private Exception exception;

}