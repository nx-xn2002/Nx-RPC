package com.nx.nxrpc;

import com.nx.nxrpc.config.RpcConfig;
import com.nx.nxrpc.constant.RpcConstant;
import com.nx.nxrpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * rpc application
 *
 * @author nx-xn2002
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * init
     *
     * @param newRpcConfig new rpc config
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
    }

    /**
     * init
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * get rpc config
     *
     * @return {@link RpcConfig }
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
