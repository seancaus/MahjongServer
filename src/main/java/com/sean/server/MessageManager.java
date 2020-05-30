package com.sean.server;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;
import java.util.Map;

class Handler{
    IMessageHandler handler;
    Parser<? extends Message> parser;

    public Handler(IMessageHandler handler, Parser<? extends Message> parser) {
        this.handler = handler;
        this.parser = parser;
    }

    public IMessageHandler getHandler() {
        return handler;
    }

    public Parser<? extends Message> getParser() {
        return parser;
    }
}

public class MessageManager {
    private static final Logger LOG = LoggerFactory.getLogger(MessageManager.class);
    private static MessageManager instance;

    private Map<Integer,Handler>handlerMap;

    private MessageManager() {
        this.handlerMap = new Hashtable<>();
    }

    public static MessageManager getInstance(){
        if( null == MessageManager.instance ){
            MessageManager.instance = new MessageManager();
        }
        return MessageManager.instance;
    }

    public void register(int code, Parser<? extends Message> parser,IMessageHandler handler){
        Integer num = Integer.valueOf(code);
        if(handlerMap.containsKey(num)){
            LOG.debug("Already Parser for code:" + code);
            return;
        }
        handlerMap.put(num, new Handler(handler,parser));
    }

    public void unregister(int code){
        handlerMap.remove(Integer.valueOf(code));
    }

    public void dispatchMessage(int code, byte[]data){
        if(!handlerMap.containsKey(code)){
            LOG.error("No MessageHandler for code:" + code);
            return;
        }
        Handler handler = handlerMap.get(Integer.valueOf(code));
        try {
            Message msg = handler.getParser().parseFrom(data);
            handler.handler.handle(msg);
        } catch (InvalidProtocolBufferException e) {
            LOG.error(e.getMessage());
        }
    }

//    public Parser<? extends Message> getParser(int protoType){
//        return protoMap.get(Integer.valueOf(protoType));
//    }
}
