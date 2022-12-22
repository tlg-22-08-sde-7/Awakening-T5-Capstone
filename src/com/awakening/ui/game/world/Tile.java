package com.awakening.ui.game.world;

import java.awt.*;

public class Tile extends Rectangle {

    private static final long serialVersionUID = 1L;

    public static final int SIZE = 75;

    private byte id;
    private boolean wall;

    public Tile(byte id, int posXinRoom, int posYinRoom, boolean isWall){
        super(posXinRoom * SIZE, posYinRoom * SIZE, SIZE, SIZE);
        this.id = id;
        wall = isWall;
    }

    public byte getID() {
        return id;
    }

    public boolean isWall() {
        return wall;
    }
}
