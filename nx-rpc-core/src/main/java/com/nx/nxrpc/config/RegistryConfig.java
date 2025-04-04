package com.nx.nxrpc.config;

import lombok.Data;

/**
 * registry config
 *
 * @author nx-xn2002
 */
@Data
public class RegistryConfig {

    /**
     * registry
     */
    private String registry = "etcd";

    /**
     * address
     */
    private String address = "http://localhost:2379";

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * timeout
     */
    private Long timeout = 30000L;
}
