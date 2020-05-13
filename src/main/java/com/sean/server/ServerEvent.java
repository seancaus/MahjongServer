package com.sean.server;

import org.springframework.stereotype.Component;

@Component
public class ServerEvent {
    public void onConnect(){

    }

    public void onDisconnect(){

    }

    public void onMessage(int code, byte[] data){
        MessageManager.getInstance().dispatchMessage(code,data);
    }
}
