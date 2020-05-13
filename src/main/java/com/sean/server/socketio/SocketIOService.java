package com.sean.server.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sean.games.mahjong.MsgMahjong.JoinResponse;
import com.sean.games.mahjong.MsgMahjong.Message;
import com.sean.games.mahjong.MsgMahjong.Message.DataCase;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.server.Client;

@Service
public class SocketIOService {
    
    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.out.println("User: " + client.getSessionId() + " Connected");
        // server.getBroadcastOperations().sendMessage("message");
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("User: " + client.getSessionId() + " Disconnect");
    }

    @OnEvent(value = "message")
    public void onEvent(SocketIOClient client, AckRequest request,  Data data) {
        try {
            Message msg = Message.parseFrom(data.getData());
            switch (msg.getDataCase()) {
                case JOINREQUEST:
                    System.out.print(msg);
                    break;
                case JOINRESPONSE:
                    System.out.print(msg);
                    break;
                default:
                    break;
            }
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        byte[] d = Message.newBuilder().setJoinResponse(JoinResponse.newBuilder().setSeat(1).build()).build().toByteArray();

        Data msg = new Data();
        msg.setCode(0);
        msg.setData(d);
        client.sendEvent("message", msg);
    }
}