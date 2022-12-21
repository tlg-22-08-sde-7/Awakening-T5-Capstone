package framework.gamestates.gui.resources;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Resources {
    public static final byte EARTH = 0;
    public static final byte SAND = 1;
    public static final byte WALL = 2;
    public static final byte TILE = 3;
    public static final byte GRASS = 3;
    public static final byte DOWN1 = 4;
    public static final byte DOWN2 = 5;
    public static final byte LEFT1 = 6;
    public static final byte LEFT2 = 7;
    public static final byte RIGHT1 = 8;
    public static final byte RIGHT2 = 9;
    public static final byte UP1 = 10;
    public static final byte UP2 = 11;
//    public static final byte STAIRS = 13;
//    public static final byte CHEST = 14;
//    public static final byte ENEMY = 15;
//    public static final byte ENEMY_2 = 16;
//    public static final byte ATTACK = 17;
//    public static final byte HEART = 18;
//    public static final byte ARMOR = 19;
//    public static final byte GOLD = 20;

    public static final HashMap<String, BufferedImage> ROOMS = new HashMap<>();
    public static final ArrayList<BufferedImage> TEXTURES = new ArrayList<>();

}
