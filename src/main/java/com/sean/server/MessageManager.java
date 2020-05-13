package com.sean.server;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;

import java.util.Hashtable;
import java.util.Map;

class Handler{
    MessageHandler handler;
    Parser<? extends Message> parser;

    public Handler(MessageHandler handler, Parser<? extends Message> parser) {
        this.handler = handler;
        this.parser = parser;
    }

    public MessageHandler getHandler() {
        return handler;
    }

    public Parser<? extends Message> getParser() {
        return parser;
    }
}

public class MessageManager {
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

    public void register(int code, Parser<? extends Message> parser,MessageHandler handler){
        Integer num = Integer.valueOf(code);
        if(handlerMap.containsKey(num)){
            //TODO
            return;
        }
        handlerMap.put(num, new Handler(handler,parser));
    }

    public void unregister(int code){
        handlerMap.remove(Integer.valueOf(code));
    }

    public void dispatchMessage(int code, byte[]data){
        if(!handlerMap.containsKey(code)){
//            TODO
            return;
        }
        Handler handler = handlerMap.get(Integer.valueOf(code));
        try {
            Message msg = handler.getParser().parseFrom(data);
            handler.handler.handler(msg);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

//    public Parser<? extends Message> getParser(int protoType){
//        return protoMap.get(Integer.valueOf(protoType));
//    }
}
