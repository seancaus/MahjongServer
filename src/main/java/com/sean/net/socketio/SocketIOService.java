package com.sean.net.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.sean.net.ServerListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//@Service
public class SocketIOService {

    private static final Logger LOG = LoggerFactory.getLogger(SocketIOService.class);

    @Autowired
    private ServerListener event;

    @OnConnect
    public void onConnect(SocketIOClient client) {
        LOG.info("User: " + client.getSessionId() + " Connected");
        // server.getBroadcastOperations().sendMessage("message");
        event.onConnect();
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        LOG.info("User: " + client.getSessionId() + " Disconnect");
        event.onDisconnect();
    }

    @OnEvent(value = "message")
    public void onEvent(SocketIOClient client, AckRequest request, NetData data) {
        event.onMessage(data.getCode(), data.getData());

//        byte[] d = Message.newBuilder().setJoinResponse(JoinResponse.newBuilder().setSeat(1).build()).build().toByteArray();
//        Data msg = new Data();
//        msg.setCode(0);
//        msg.setData(d);
//        client.sendEvent("message", msg);
    }
}