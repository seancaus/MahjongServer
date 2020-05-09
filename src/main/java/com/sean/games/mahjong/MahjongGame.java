package com.sean.games.mahjong;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.function.PersonSupplier;

import com.sean.games.Player;
import com.sean.games.SimpleGame;

// 每张牌都叫tile, 洗牌:mix the tiles，码牌:build walls，听牌:waiting，和牌:hu，自摸:self-drawn，点炮:win by discard.
public class MahjongGame extends SimpleGame{
    private List<Player>    players;
    private List<Integer>   tiles;
    private List<Integer>   walls;

    public MahjongGame(){
        players = new ArrayList<Player>();
        tiles   = new ArrayList<Integer>(144);
        walls   = new ArrayList<Integer>(144);
    }

    public void join(Player p){
        players.add(p);
    }

    public void leave(Player p){
        players.remove(p);
    }

    public void fillTiles(){
        // Define.Tile.values().stream();
        // Stream.generate(new PersonSupplier()).limit();
    }

    // 洗牌
    public void mixTiles(){
        this.walls.clear();
    }

    public void start(){

    }

    // 检查听牌
    public void checkWaiting(){

    }

    // 检查胡牌
    public void checkHu(){

    }

}