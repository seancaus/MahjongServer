package com.sean.core;

import java.util.Hashtable;
import java.util.Map;

public class ClientManager {
    private Map<String, IClient> clientMap;

    public ClientManager(){
        clientMap = new Hashtable<>();
    }

    public boolean containsClient(String playerId){
        return this.clientMap.containsKey(playerId);
    }

    public void addClient(String playerId, IClient client) {
        if(this.containsClient(playerId)){
            this.clientMap.remove(playerId);
        }
        this.clientMap.put(playerId, client);
    }

    public IClient getClient(String playerId){
        return clientMap.get(playerId);
    }
}