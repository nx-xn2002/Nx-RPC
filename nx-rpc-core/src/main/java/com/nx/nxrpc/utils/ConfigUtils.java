package com.nx.nxrpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * config utils
 *
 * @author nx-xn2002
 */
public class ConfigUtils {

    /**
     * load config
     *
     * @param tClass t class
     * @param prefix prefix
     * @return {@link T }
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * load config
     *
     * @param tClass      t class
     * @param prefix      prefix
     * @param environment environment
     * @return {@link T }
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass, prefix);
    }
}
