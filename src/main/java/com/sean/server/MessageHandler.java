package com.sean.server;

import com.google.protobuf.Message;

public interface MessageHandler<T extends Message> {
    void handler(T msg);
}
