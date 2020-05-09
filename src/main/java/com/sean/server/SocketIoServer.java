package com.sean.server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// import io.socket.client.IO;

@Component
public class SocketIoServer  extends Server{

    @Value("${server.port}")
    private int port = 8013;

    public void start(){
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(port);

        final SocketIOServer server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("User: " + client.getSessionId() + " Connected");
                server.getBroadcastOperations().sendMessage("message");
            }
        });
        server.addDisconnectListener(new DisconnectListener(){
            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("User: " + client.getSessionId() + " Disconnect");
            }
        });
        server.addMessageListener(new DataListener<String>(){
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackSender) {
                System.out.println(data);
                client.sendMessage("hello");
            }
        });
        server.addEventListener("message", String.class, new DataListener<String>() {
            public void onData(SocketIOClient client, String data, AckRequest ackSender) {
                System.out.println(data);
                client.sendMessage("hello");
            }
        });
        // server.addJsonObjectListener(clazz, listener);

        server.start();
    }
}