package com.sean.net.socketio;

public class NetData {
    private int code;
    private byte[] data;

    public NetData() {
    }

    public NetData(int code, byte[] data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}