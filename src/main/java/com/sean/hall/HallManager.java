package com.sean.hall;

import java.util.List;

import com.sean.core.IMessageHandler;
import com.sean.core.MessageCenter;

import com.sean.core.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HallManager {

    @Autowired
    private List<IModule> modules;

    @Autowired
    private MessageCenter msgCenter;

    private void prepare() {
        modules.forEach((module) -> {
            if (module instanceof IMessageHandler) {
                MessageManager.getInstance().register((IMessageHandler) module);
            }
        });
    }

    public void run() {
        this.prepare();

        msgCenter.run();
    }
}