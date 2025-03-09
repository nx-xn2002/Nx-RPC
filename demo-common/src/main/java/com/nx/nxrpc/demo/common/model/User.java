package com.nx.nxrpc.demo.common.model;

import java.io.Serializable;

/**
 * user
 *
 * @author nx-xn2002
 */
public class User implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
