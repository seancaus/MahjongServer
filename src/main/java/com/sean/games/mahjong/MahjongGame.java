package com.sean.games.mahjong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import java.util.function.PersonSupplier;

import com.sean.games.SimpleGame;

// 每张牌都叫tile, 洗牌:mix the tiles，码牌:build walls，听牌:waiting，和牌:hu，自摸:self-drawn，点炮:win by discard.
//Dealer 庄家
//Deal发牌，Die骰子，Draw摸牌,Discard打牌
public class MahjongGame extends SimpleGame{
    private List<Player>        players;
    private List<Define.Tile>   tiles;  // 麻将牌
    private List<Define.Tile>   walls;  // 麻将牌墙
    private List<Integer>       dice;   // 骰子
    private int                 dealer; // 庄家
    private int                 cur;    // 当前玩家

    private Random random;

    public MahjongGame(){
        cur     = 0;
        dealer  = 0;
        random  = new Random();
        dice    = new ArrayList<>();
        players = new ArrayList<>();
        walls   = new ArrayList<>(144);
    }

    public void join(Player p){
        players.add(p);
    }

    public void leave(Player p){
        players.remove(p);
    }

    public void fillTiles(){
        tiles = Stream.of(Define.Tile.values()).flatMap(tile -> Arrays.stream(new Define.Tile[]{tile,tile,tile,tile})).collect(Collectors.toList());
    }

    // 码牌
    public void buildWalls(){
        this.walls.clear();

        List<Define.Tile> list = tiles.subList(0, tiles.size());
        Stream.generate(new Supplier<Integer>() {
            int size = list.size();
            public Integer get() {
                return random.ints(0, size--).limit(1).findFirst().getAsInt();
            }
        }).limit(list.size()).forEach(i->{
            this.walls.add(list.remove(i.intValue()));
        });
    }

    // 掷骰子
    public void throwDice(){
        dice.clear();
        random.ints(1,7).limit(4).forEach(n-> dice.add(n));
    }

    // 发牌
    public void deal(){

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