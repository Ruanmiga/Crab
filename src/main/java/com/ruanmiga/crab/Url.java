/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ruanmiga.crab;

/**
 *
 * @author anton
 */
public class Url {

    private final String baseurl;
    private final String protocol;
    private final String host;
    private final int port;
    private final String path;

    public Url(String baseurl, String protocol, String host, int port, String path) {
        this.baseurl = baseurl;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
    }
    
    public String getBaseurl() {
        return baseurl;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

}
