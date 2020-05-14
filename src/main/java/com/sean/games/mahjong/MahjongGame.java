package com.sean.games.mahjong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sean.games.SimpleGame;
import com.sean.games.mahjong.MsgMahjong.Message;
import com.sean.server.MessageHandler;
import com.sean.server.MessageManager;

import org.springframework.stereotype.Component;

// 每张牌都叫tile, 洗牌:mix the tiles，码牌:build walls，听牌:waiting，和牌:hu，自摸:self-drawn，点炮:win by discard.
//Dealer 庄家
//Deal发牌，Die骰子，Draw摸牌,Discard打牌
@Component
public class MahjongGame extends SimpleGame implements MessageHandler<Message> {

    private int dealerIndex; // 庄家 & 座位东
    private int curIndex; // 当前行牌玩家
    private List<Player> players;// 东南西北玩家

    private int[] dices; // 掷骰数

    private List<Define.Tile> tiles; // 麻将牌
    private List<Define.Tile> walls; // 麻将牌墙

    private Random random;

    public MahjongGame() {
        curIndex = 0;
        dealerIndex = 0;
        random = new Random();
        players = new ArrayList<>();
        walls = new ArrayList<>(144);
    }

    public void join(Player p) {
        players.add(p);
    }

    public void leave(Player p) {
        players.remove(p);
    }

    public void fillTiles() {
        tiles = Stream.of(Define.Tile.values())
                .flatMap(tile -> Arrays.stream(new Define.Tile[] { tile, tile, tile, tile }))
                .collect(Collectors.toList());
    }

    // 选择座位
    public void chooseSeat() {

    }

    // 定庄
    public void chooseDealer() {

    }

    // 洗牌
    public void shufflingTiles() {
    }

    // 洗牌&码牌
    public void stackTiles() {
        this.walls.clear();
        List<Define.Tile> list = tiles.subList(0, tiles.size());
        Stream.generate(new Supplier<Integer>() {
            int size = list.size();

            public Integer get() {
                return random.ints(0, size--).limit(1).findFirst().getAsInt();
            }
        }).limit(list.size()).forEach(i -> {
            this.walls.add(list.remove(i.intValue()));
        });
    }

    // 掷骰子
    public int[] throwDice(int n) {
        return random.ints(1, 7).limit(n).toArray();
    }

    // 发牌
    public void deal() {

    }

    public void start() {
        this.dices = throwDice(4);
        int playerIndex = Arrays.stream(this.dices).limit(2).sum();
        int index = (playerIndex + this.dealerIndex - 1) % 4; // 开牌玩家
        int stackIndex = Arrays.stream(this.dices).sum() + 1;
        System.out.print(stackIndex);
    }

    // 检查听牌
    public void checkWaiting() {

    }

    // 检查胡牌
    public void checkHu() {

    }

    @Override
    public void handler(Message msg) {
        switch (msg.getDataCase()) {
            case DEAL: {
                msg.getDeal();
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void prepare() {
        MessageManager.getInstance().register(0, Message.parser(), this);
    }
}