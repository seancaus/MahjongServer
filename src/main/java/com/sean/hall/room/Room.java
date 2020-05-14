package com.sean.hall.room;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int roomId;
    private List<String> players;

    public Room(int roomId) {
        this.roomId = roomId;
        this.players = new ArrayList<>();
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void pushPlayer(String player) {
        this.players.add(player);
    }
}