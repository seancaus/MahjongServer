package com.sean.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageCenter {

    @Value("${server.port}")
    private int port = 8013;

    @Autowired
    @Qualifier("SocketIoServer")
    private IServer server;

    public void run(){
        server.start(port);
    }
}
