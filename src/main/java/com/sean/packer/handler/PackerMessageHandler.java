package com.sean.packer.handler;

import com.sean.packer.proto.Packer;
import com.sean.server.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PackerMessageHandler implements MessageHandler<Packer.Message> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handler(Packer.Message msg) {
        switch (msg.getMsgType().getNumber()){
            case Packer.MsgType.SYNCHOTPACK_VALUE:{
                handler(msg.getSyncHotPack());
                break;
            }
            default:
                logger.warn("unknow code:" + String.valueOf(msg.getMsgType().getNumber()));
                break;
        }
    }

    public void handler(Packer.SyncHotPack msg){
        System.out.println(msg.getFileName());
    }
}
