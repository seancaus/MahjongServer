package com.sean.hall.room;

import java.util.Hashtable;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.protobuf.Parser;
import com.sean.hall.IHallModule;
import com.sean.hall.room.MsgRoom.JoinRequest;
import com.sean.hall.room.MsgRoom.JoinResponse;
import com.sean.hall.room.MsgRoom.LeaveRequest;
import com.sean.hall.room.MsgRoom.Message;
import com.sean.server.IMessageHandler;
import com.sean.server.MessageManager;

import org.springframework.stereotype.Component;

@Component
public class RoomManager implements IHallModule, IMessageHandler<Message> {
    private Map<Integer, Room> roomMap = new Hashtable<>();

    public void createRoom()
    {
        Integer rid = 8013;
        Room room = new Room(rid);
        room.setRoomId(rid);
        this.roomMap.put(rid, room);
    }

    public void join(JoinRequest req)
    {
        int rid = req.getRoomId();
        if(!roomMap.containsKey(rid)){
            byte [] data = JoinResponse.newBuilder().setCode(-1).build().toByteArray();
//        Data msg = new Data();
//        msg.setCode(0);
//        msg.setData(d);
//        client.sendEvent("message", msg);
            return;
        }

        Room r = roomMap.get(rid);
        r.pushPlayer(String.valueOf(req.getPlayerId()));

    }

    public void leave(LeaveRequest req)
    {

    }

    public void startGame()
    {

    }

    @Override
    public Map<Integer, Parser<Message>> messageMapping() {
        return ImmutableMap.of(Integer.valueOf(1),Message.parser());
    }

    @Override
    public void handler(Message msg) {
        switch(msg.getMsgCase()){
            case JOINREQUEST:
            {
                join(msg.getJoinRequest());
                break;
            }
            case LEAVEREQUEST:
            {
                leave(msg.getLeaveRequest());
                break;
            }
            default:
                break;
        }
    }
}