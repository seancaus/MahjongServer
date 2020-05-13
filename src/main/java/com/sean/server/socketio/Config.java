package com.sean.server.socketio;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${server.port}")
    private int port = 8013;

    @Bean
    public com.corundumstudio.socketio.SocketIOServer getSocketIOServer(){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setTransports(Transport.POLLING,Transport.WEBSOCKET);
        config.setHostname("127.0.0.1");
        config.setPort(port);
        return new com.corundumstudio.socketio.SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}