package com.nx.nxrpc.server;

/**
 * http server
 *
 * @author nx-xn2002
 */
public interface HttpServer {
    /**
     * Start a server on the port
     *
     * @param port port
     */
    void doStart(int port);
}
