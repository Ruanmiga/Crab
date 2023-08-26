package com.ruanmiga.crab;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author anton
 */
public class Sockets {
    
    public static Socket connect(String hostname, int port) throws IOException{
        return connect(hostname, port, 0);
    }
    
    public static Socket connect(String hostname, int port, int timeout) throws IOException{
        Socket socket = new Socket();
        
        if(timeout <= 0) socket.connect(new InetSocketAddress(hostname, port));
        else socket.connect(new InetSocketAddress(hostname, port), timeout);
        
        return socket;
    }

}
