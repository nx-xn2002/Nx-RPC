package com.nx.nxrpc.demo.consumer;

import com.nx.nxrpc.demo.common.model.User;
import com.nx.nxrpc.demo.common.service.UserService;
import com.nx.nxrpc.proxy.ServiceProxyFactory;

/**
 * easy consumer demo
 *
 * @author nx-xn2002
 */
public class EasyConsumerDemo {
    public static void main(String[] args) {
        //UserService userService = ServiceProxyFactory.getMockProxy(UserService.class);
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setUsername("nx");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getUsername());
        } else {
            System.out.println("newUser == null");
        }
        System.out.println(userService.getUserEmail(user));
    }
}
