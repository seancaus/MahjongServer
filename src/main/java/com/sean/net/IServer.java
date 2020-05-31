package com.sean.net;

public interface IServer {
    void start(ServerListener listener, int port);
}