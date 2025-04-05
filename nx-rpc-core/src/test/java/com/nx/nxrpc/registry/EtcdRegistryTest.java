package com.nx.nxrpc.registry;

import java.util.concurrent.ExecutionException;

import com.nx.nxrpc.config.RegistryConfig;
import com.nx.nxrpc.model.ServiceMetaInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class EtcdRegistryTest {
    static final Registry registry = new EtcdRegistry();

    @BeforeAll
    static void init() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("http://localhost:2379");
        registry.init(registryConfig);
    }

    @Test
    void heartBeat() throws ExecutionException, InterruptedException {
        ServiceMetaInfo serviceMetaInfo = ServiceMetaInfo.builder().serviceName("heart_beat_test").build();
        registry.register(serviceMetaInfo);
        // 阻塞 1 分钟
        Thread.sleep(60 * 1000L);
    }
}