package com.sean.server;

public interface IClient {
    void sendMessage(int code, byte[] data);
}