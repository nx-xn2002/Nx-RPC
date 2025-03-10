package com.nx.nxrpc.config;

import lombok.Data;

/**
 * rpc config
 *
 * @author nx-xn2002
 */
@Data
public class RpcConfig {
    /**
     * name
     */
    private String name = "nx-rpc";

    /**
     * version
     */
    private String version = "1.0";

    /**
     * server host
     */
    private String serverHost = "localhost";

    /**
     * server port
     */
    private Integer serverPort = 8080;
}
