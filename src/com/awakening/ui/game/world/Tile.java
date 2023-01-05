package com.awakening.ui.game.world;

import com.awakening.ui.framework.resources.Resources;

import java.awt.*;

public class Tile extends Rectangle {

    private static final long serialVersionUID = 1L;
    // All the tiles sizes are set to 75 in accordance to windows height and width
    public static final int SIZE = 75;

    private byte id;
    // boolean wall is used to detect the collision in the game
    private boolean wall;


    // Tile class constructor
    // Takes in a byte id and an x and y position
    // Sets the tile's id and x and y position
    // Sets the tile's width and height to the SIZE constant
    // Sets the tile's wall boolean to true if the id is a wall
    // Sets the tile's wall boolean to false if the id is not a wall
    // Sets the tile's bounds to the tile's x and y position and the tile's width and height
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
