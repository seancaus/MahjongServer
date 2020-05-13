package com.sean.server.socketio;

import com.corundumstudio.socketio.SocketIOServer;
import com.sean.server.IServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// import io.socket.client.IO;

@Component("SocketIoServer")
public class SocketIo implements IServer{

    @Autowired
    private SocketIOServer server;

    public void start(int port){
        server.start();
    }

}