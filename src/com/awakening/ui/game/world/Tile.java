package com.awakening.ui.game.world;

import com.awakening.ui.framework.resources.Resources;

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

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }
}
