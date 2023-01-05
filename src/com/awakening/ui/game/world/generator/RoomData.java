package com.awakening.ui.game.world.generator;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.framework.utils.MathHelper;
import com.awakening.ui.game.world.Tile;

import java.awt.*;
import java.util.HashSet;

public class RoomData {

    private Tile[][] tilesData;

    private HashSet<MathHelper.Direction> exits;


    // roomData is a 2D array of bytes, where each byte represents a tile type.
    // The tile types are defined in the Tile class.
    // RoomData also contains a HashSet of the exits of the room.
    // The exits are used to determine which room to generate next.
    // The exits are also used to determine which doors to draw on the room.
    // The exits are also used to determine which rooms are adjacent to each other.
    public RoomData(byte[][] tilesData, MathHelper.Direction... exits) {
        this.tilesData = new Tile[tilesData.length][tilesData[0].length];
        for(int i=0;i<this.tilesData.length;i++) {
            for(int j=0;j<this.tilesData[i].length;j++) {

                this.tilesData[i][j] = new Tile(tilesData[i][j], j, i, tilesData[i][j] == 1 || tilesData[i][j] == 2);

            }
        }
        this.exits = new HashSet<>();
        for(MathHelper.Direction direction : exits) {
            this.exits.add(direction);
        }
    }

    public void render(Graphics graphics) {
        for(int i=0;i<this.tilesData.length;i++) {
            for(int j=0;j<this.tilesData[i].length;j++) {
                graphics.drawImage(Resources.TEXTURES.get(this.tilesData[i][j].getID()), j*Tile.SIZE, i*Tile.SIZE, Tile.SIZE, Tile.SIZE, null);
            }
        }
    }

    public HashSet<MathHelper.Direction> getExits() {
        return exits;
    }

    public Tile getTileAt(int x, int y) {
        return tilesData[y][x];
    }

    public int getSizeY() {
        return tilesData.length;
    }

    public int getSizeX() {
        return tilesData[0].length;
    }
}
