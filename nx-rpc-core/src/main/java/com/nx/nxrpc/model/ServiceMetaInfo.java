package com.nx.nxrpc.model;

import cn.hutool.core.util.StrUtil;
import lombok.Builder;
import lombok.Data;

/**
 * service meta info
 *
 * @author nx-xn2002
 */
@Data
@Builder
public class ServiceMetaInfo {

    /**
     * service name
     */
    private String serviceName;

    /**
     * service version
     */
    private String serviceVersion = "1.0";

    /**
     * service host
     */
    private String serviceHost;

    /**
     * service port
     */
    private Integer servicePort;

    /**
     * service group
     */
    private String serviceGroup = "default";

    /**
     * get service key
     *
     * @return {@link String }
     */
    public String getServiceKey() {
        return String.format("%s:%s", serviceName, serviceGroup);
    }

    /**
     * get service node key
     *
     * @return {@link String }
     */
    public String getServiceNodeKey() {
        return String.format("%s/%s:%s", getServiceKey(), serviceHost, servicePort);
    }

    /**
     * get service address
     *
     * @return {@link String }
     */
    public String getServiceAddress() {
        if (!StrUtil.contains(serviceHost, "http")) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }
}
