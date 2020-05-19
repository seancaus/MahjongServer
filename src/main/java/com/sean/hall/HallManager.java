package com.sean.hall;

import java.util.List;
import java.util.Map;

import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.sean.server.IMessageHandler;
import com.sean.server.MessageCenter;
import com.sean.server.MessageManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HallManager {

    @Autowired
    private List<IHallModule> modules;

    @Autowired
    private MessageCenter msgCenter;

    private void prepare() {
        modules.stream().filter(module -> module instanceof IMessageHandler).map(IMessageHandler.class::cast)
                .forEach(m -> {
                    Map<Integer, Parser<Message>> mapping = m.messageMapping();
                    mapping.forEach((key, value) -> {
                        MessageManager.getInstance().register(key, mapping.get(key), m);
                    });
                });
    }

    public void run() {
        this.prepare();

        msgCenter.run();
    }
}