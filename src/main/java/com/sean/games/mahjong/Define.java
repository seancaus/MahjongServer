package com.sean.games.mahjong;


public final class Define {
    enum Tile{
        // 筒
        DOT_1(1),DOT_2(2),DOT_3(3),DOT_4(4),DOT_5(5),DOT_6(6),DOT_7(7),DOT_8(8),DOT_9(9),
        // 条
        BAMBOO_1(11),BAMBOO_2(12),BAMBOO_3(13),BAMBOO_4(14),BAMBOO_5(15),BAMBOO_6(16),BAMBOO_7(17),BAMBOO_8(18),BAMBOO_9(19),
        // 万
        CHARACTER_1(21), CHARACTER_2(22), CHARACTER_3(23), CHARACTER_4(24), CHARACTER_5(25), CHARACTER_6(26), CHARACTER_7(27), CHARACTER_8(28), CHARACTER_9(29),
        // 东，南，西，北
        WIND_EAST(31), WIND_SOUTH(33), WIND_WEST(35), WIND_NORTH(37),
        // 中，发，白
        DRAGON_RED(39), DRAGON_GREEN(41), DRAGON_WHITE(43);
        // Flower.PlumBlossom,Orchid,Chrysanthemum,Bamboo
        // Season.Spring,Summer,Autumn,Winter

        private final int value;
        Tile(int value){
            this.value = value;
        }
    }
}



