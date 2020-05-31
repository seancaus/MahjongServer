package com.sean.core;

import com.sean.net.IServer;
import com.sean.net.ServerListener;
import com.sean.net.socketio.SocketIo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageCenter implements ServerListener {

    private static final Logger LOG = LoggerFactory.getLogger(SocketIo.class);

    @Value("${server.port}")
    private int port = 8013;

    @Autowired
    @Qualifier("SocketIoServer")
//    @Qualifier("SocketServer")
    private IServer server;

    public void run() {
        server.start(this, port);
    }

    @Override
    public void onConnect() {
        LOG.warn("onConnect");
    }

    @Override
    public void onDisconnect() {
        LOG.warn("onDisconnect");
    }

    @Override
    public void onMessage(int code, byte[] data) {
        MessageManager.getInstance().dispatchMessage(code, data);
    }
}
