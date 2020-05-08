package com.sean.server;

import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.sean.packer.proto.Packer;

import java.util.Hashtable;
import java.util.Map;

public class ProtoManager
{
    private static ProtoManager instance;
    private Map<Integer, Parser<? extends Message>> protoMap;

    private ProtoManager(){
        protoMap = new Hashtable<>();

        protoMap.put(Integer.valueOf(3), Packer.Message.parser());
    }

    public static ProtoManager getInstance(){
        if( null == ProtoManager.instance ){
            ProtoManager.instance = new ProtoManager();
        }
        return ProtoManager.instance;
    }

    public void registerMessage(int protoType, Parser<? extends Message> parser){
        Integer mt = Integer.valueOf(protoType);
        if(protoMap.containsKey(mt)){
            //TODO
            return;
        }
        protoMap.put(mt, parser);
    }

    public Parser<? extends Message> getParser(int protoType){
        return protoMap.get(Integer.valueOf(protoType));
    }
}
