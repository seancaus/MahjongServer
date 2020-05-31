package com.sean.net.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.sean.net.IServer;

import com.sean.net.ServerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// import io.socket.client.IO;

@Component("SocketIoServer")
public class SocketIo implements IServer, ConnectListener, DisconnectListener, DataListener<NetData> {

    private static final Logger LOG = LoggerFactory.getLogger(SocketIo.class);

    @Autowired
    private SocketIOServer server;

    private ServerListener listener;

    public void start(ServerListener listener, int port) {
        if (null == listener) {
            LOG.error("ServerListener is null");
            return;
        }

        this.listener = listener;
        server.addConnectListener(this);
        server.addDisconnectListener(this);
        server.addEventListener("message", NetData.class, this);
        server.start();
    }

    @Override
    public void onConnect(SocketIOClient client) {
        listener.onConnect();
    }

    @Override
    public void onDisconnect(SocketIOClient client) {
        listener.onDisconnect();
    }

    @Override
    public void onData(SocketIOClient client, NetData data, AckRequest ackSender) throws Exception {
        listener.onMessage(data.getCode(), data.getData());
    }
}