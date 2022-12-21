package com.awakening.ui.framework.resources;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Resources {

    public static final byte FLOOR = 0;
    public static final byte STONE = 1;
    public static final byte WALL = 2;
    public static final byte TILE = 3;
    public static final byte GRASS = 4;
    public static final byte PLAYER = 5;
    public static final byte PLAYER_2 = 6;
    public static final byte PLAYER_LEFT = 7;
    public static final byte PLAYER_LEFT_2 = 8;
    public static final byte PLAYER_RIGHT = 9;
    public static final byte PLAYER_RIGHT_2 = 10;
    public static final byte PLAYER_BACK = 11;
    public static final byte PLAYER_BACK_2 = 12;

    public static final HashMap<String, BufferedImage> ROOMS = new HashMap<>();
    public static final ArrayList<BufferedImage> TEXTURES = new ArrayList<>();
}
