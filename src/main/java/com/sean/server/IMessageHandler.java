package com.sean.server;

import com.google.protobuf.Message;
import com.google.protobuf.Parser;

import java.util.Map;

public interface IMessageHandler<T extends Message> {
    <T extends Message> Map<Integer, Parser<T>> messageMapping();
    void handler(T msg);
}
