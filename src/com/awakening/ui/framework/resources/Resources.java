package com.awakening.ui.framework.resources;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Resources {

    // Resources class
    // Stores all the images and data from json files



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
    public static final byte STAIRS = 13;
    public static final byte CHEST = 14;
    public static final byte ENEMY = 15;
    public static final byte ENEMY_2 = 16;
    public static final byte ATTACK = 17;
    public static final byte HEART = 18;
    public static final byte ARMOR = 19;
    public static final byte GOLD = 20;
    public static final byte PLAYER_1_STAND_DOWN = 21;
    public static final byte PLAYER_1_WALK_DOWN = 22;
    public static final byte PLAYER_1_STAND_LEFT = 23;
    public static final byte PLAYER_1_WALK_LEFT = 24;
    public static final byte PLAYER_1_STAND_RIGHT = 25;
    public static final byte PLAYER_1_WALK_RIGHT = 26;
    public static final byte PLAYER_1_STAND_UP = 27;
    public static final byte PLAYER_1_WALK_UP = 28;
    public static final byte PLAYER_3_STAND_DOWN = 29;
    public static final byte PLAYER_3_WALK_DOWN = 30;
    public static final byte PLAYER_3_STAND_LEFT = 31;
    public static final byte PLAYER_3_WALK_LEFT = 32;
    public static final byte PLAYER_3_STAND_RIGHT = 33;
    public static final byte PLAYER_3_WALK_RIGHT = 34;
    public static final byte PLAYER_3_STAND_UP = 35;
    public static final byte PLAYER_3_WALK_UP = 36;
    public static final byte PLAYER_4_STAND_DOWN = 37;
    public static final byte PLAYER_4_WALK_DOWN = 38;
    public static final byte PLAYER_4_STAND_LEFT = 39;
    public static final byte PLAYER_4_WALK_LEFT = 40;
    public static final byte PLAYER_4_STAND_RIGHT = 41;
    public static final byte PLAYER_4_WALK_RIGHT = 42;
    public static final byte PLAYER_4_STAND_UP = 43;
    public static final byte PLAYER_4_WALK_UP = 44;
    public static final byte AXE = 45;
    public static final byte BANDAGES = 46;
    public static final byte BARBELL = 47;
    public static final byte BATTERIES = 48;
    public static final byte CAMERA = 49;
    public static final byte CELLPHONE = 50;
    public static final byte FIRE_EXTINGUISHER = 51;
    public static final byte FIRST_AID_KIT = 52;
    public static final byte KEY = 53;
    public static final byte JOURNAL = 54;
    public static final byte MASTER_KEY = 55;
    public static final byte PAPER_CLIP = 56;
    public static final byte PATIENT_FILE = 57;
    public static final byte PICTURE = 58;
    public static final byte PRESS_PASS = 59;
    public static final byte TYLENOL = 60;
    public static final byte WOOD_CANE = 61;
    public static final byte UPPER_STAIRS = 62;
    public static final byte DOOR = 63;
    public static final byte MASTER_DOOR = 64;

    public static final byte HOSPITAL_BED = 65;
    public static final byte BAT = 66;

    public static final byte BAT_2 = 67;
    public static final byte GHOST_MAIN1 = 68;
    public static final byte GHOST_MAIN2 = 69;
    //public static final byte ENEMY = 13;

    // Stores the images in a HashMap with the image's name as the key
    public static final HashMap<String, BufferedImage> ROOMS = new HashMap<>();
    public static final ArrayList<BufferedImage> TEXTURES = new ArrayList<>();
}
