package com.nx.nxrpc.registry;

import java.util.List;

import com.nx.nxrpc.config.RegistryConfig;
import com.nx.nxrpc.model.ServiceMetaInfo;

/**
 * registry
 *
 * @author nx-xn2002
 */
public interface Registry {

    /**
     * init
     *
     * @param registryConfig registry config
     */
    void init(RegistryConfig registryConfig);

    /**
     * register
     *
     * @param serviceMetaInfo service meta info
     * @throws Exception exception
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * un register
     *
     * @param serviceMetaInfo service meta info
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * service discovery
     *
     * @param serviceKey service key
     * @return {@link List }<{@link ServiceMetaInfo }>
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * destroy
     */
    void destroy();
}
