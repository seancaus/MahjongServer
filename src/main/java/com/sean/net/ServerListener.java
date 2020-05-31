package com.sean.net;

import com.sean.core.MessageManager;
import org.springframework.stereotype.Component;

@Component
public interface ServerListener {
    void onConnect();

    void onDisconnect();

    void onMessage(int code, byte[] data);

//     {
//        MessageManager.getInstance().dispatchMessage(code,data);
//    }
}
