package com.sean.core;

import java.util.Map;

import com.google.protobuf.Message;
import com.google.protobuf.Parser;

public interface IMessageHandler<T extends Message> {
    Map<Integer,Parser<? extends Message>> messageMapping();
    void handle(T msg);
}
