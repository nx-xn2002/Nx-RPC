package com.nx.nxrpc.demo.provider.service;

import com.nx.nxrpc.demo.common.model.User;
import com.nx.nxrpc.demo.common.service.UserService;

/**
 * user service impl
 *
 * @author nx-xn2002
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("获取到用户: 用户名[" + user.getUsername() + "]");
        user.setUsername(user.getUsername() + "[from provider]");
        return user;
    }
}
