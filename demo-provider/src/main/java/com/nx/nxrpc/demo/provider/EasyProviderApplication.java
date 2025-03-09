package com.nx.nxrpc.demo.provider;

import com.nx.nxrpc.demo.common.service.UserService;
import com.nx.nxrpc.demo.provider.service.UserServiceImpl;
import com.nx.nxrpc.registry.LocalRegistry;
import com.nx.nxrpc.server.HttpServer;
import com.nx.nxrpc.server.VertxHttpServer;

/**
 * easy provider application
 *
 * @author nx-xn2002
 */
public class EasyProviderApplication {
    public static void main(String[] args) {
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
