package com.sean.hall;

import java.util.List;

import com.sean.server.IMessageHandler;
import com.sean.server.MessageCenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HallManager {

    @Autowired
    private List<IHallModule> modules;

    @Autowired
    private MessageCenter msgCenter;

    private void prepare(){
        modules.stream().filter((m)->m instanceof IMessageHandler);
        modules.forEach((module)->{
            if(module instanceof IMessageHandler){

            }
        });
    }

    public void run(){
        this.prepare();

        msgCenter.run();
    }
}