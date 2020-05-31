package com.sean.net.socketio;

import java.util.List;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.listener.ExceptionListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.netty.channel.ChannelHandlerContext;

@Configuration
public class Config {

    @Value("${server.port}")
    private int port = 8013;

    @Bean
    public com.corundumstudio.socketio.SocketIOServer getSocketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setTransports(Transport.POLLING, Transport.WEBSOCKET);
        config.setHostname("127.0.0.1");
        config.setPort(port);
        config.setExceptionListener(new ExceptionListener() {
            @Override
            public void onPingException(Exception e, SocketIOClient client) {
                // PING 超时异常！
            }

            @Override
            public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
                // 服务器异常！
            }

            @Override
            public void onDisconnectException(Exception e, SocketIOClient client) {
                // 断开异常！
            }

            @Override
            public void onConnectException(Exception e, SocketIOClient client) {
                // 连接异常！
            }

            @Override
            public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
                return false;
            }
        });
        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}