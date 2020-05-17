package com.sean.packer.handler;

import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.sean.packer.proto.Packer;
import com.sean.server.IMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PackerMessageHandler implements IMessageHandler<Packer.Message> {
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

    @Override
    public <T extends Message> Map<Integer, Parser<T>> messageMapping() {
        return null;
    }

    public void handler(Packer.SyncHotPack msg){
        System.out.println(msg.getFileName());
    }
}
