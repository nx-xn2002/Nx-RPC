package com.nx.nxrpc.demo.common.service;

import com.nx.nxrpc.demo.common.model.User;

/**
 * user service
 *
 * @author nx-xn2002
 */
public interface UserService {
    /**
     * get user
     *
     * @param user user
     * @return {@link User }
     */
    User getUser(User user);

    /**
     * get user email
     *
     * @param user user
     * @return {@link String }
     */
    String getUserEmail(User user);
}
