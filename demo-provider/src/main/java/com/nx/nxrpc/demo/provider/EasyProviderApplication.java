package com.nx.nxrpc.demo.provider;

import java.util.List;

import com.nx.nxrpc.RpcApplication;
import com.nx.nxrpc.config.RegistryConfig;
import com.nx.nxrpc.config.RpcConfig;
import com.nx.nxrpc.demo.common.service.UserService;
import com.nx.nxrpc.demo.provider.service.UserServiceImpl;
import com.nx.nxrpc.model.ServiceMetaInfo;
import com.nx.nxrpc.registry.LocalRegistry;
import com.nx.nxrpc.registry.Registry;
import com.nx.nxrpc.registry.RegistryFactory;
import com.nx.nxrpc.server.HttpServer;
import com.nx.nxrpc.server.VertxHttpServer;

/**
 * easy provider application
 *
 * @author nx-xn2002
 */
public class EasyProviderApplication {
    public static void main(String[] args) {
        RpcConfig config = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = config.getRegistryConfig();
        RpcApplication.init();
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = ServiceMetaInfo.builder().serviceName(UserService.class.getName())
            .serviceHost(
                config.getServerHost()).servicePort(config.getServerPort()).build();
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
        System.out.println(serviceMetaInfoList);
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(config.getServerPort());
    }
}
