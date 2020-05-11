package com.sean.games.mahjong;

import java.util.Hashtable;
import java.util.Map;

public class Player {
    private String playerId;
    private Map<Integer, Integer> cards = new Hashtable<>();

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }


}