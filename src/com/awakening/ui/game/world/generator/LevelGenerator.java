package com.awakening.ui.game.world.generator;

import com.awakening.ui.framework.utils.MathHelper;

import java.util.HashSet;

public class LevelGenerator {

    public static final int WORLD_SIZE = 5;

    private int posX;
    private int posY;

    private HashSet<MathHelper.Direction>[][] roomsData;

    @SuppressWarnings("unchecked")
    public void intializeGridForRooms() {
        this.roomsData = new HashSet[WORLD_SIZE][WORLD_SIZE];
        for (int i = 0; i < this.roomsData.length; i++) {
            for (int j = 0; j < this.roomsData[i].length; j++) {
                this.roomsData[i][j] = new HashSet<>();
            }
        }
    }

    public void generate() {
        //basement
        this.roomsData[1][0].add(MathHelper.Direction.SOUTH);

        //morgue
        this.roomsData[1][1].add(MathHelper.Direction.NORTH);

        //keypad room
        this.roomsData[1][1].add(MathHelper.Direction.EAST);
        this.roomsData[2][1].add(MathHelper.Direction.WEST);

        //emergency room
        this.roomsData[1][1].add(MathHelper.Direction.SOUTH);
        this.roomsData[1][2].add(MathHelper.Direction.NORTH);

        //office
        this.roomsData[1][2].add(MathHelper.Direction.WEST);
        this.roomsData[0][2].add(MathHelper.Direction.EAST);

        //front desk
        this.roomsData[1][2].add(MathHelper.Direction.SOUTH);
        this.roomsData[1][3].add(MathHelper.Direction.NORTH);

        //hallway
        this.roomsData[1][2].add(MathHelper.Direction.EAST);
        this.roomsData[2][2].add(MathHelper.Direction.WEST);

        //patient room
        this.roomsData[2][2].add(MathHelper.Direction.EAST);
        this.roomsData[3][2].add(MathHelper.Direction.WEST);

    }


    private boolean isValidPosition(int x, int y) {
        return x >= 0 && y >= 0 && x < WORLD_SIZE && y < WORLD_SIZE;
    }

    public HashSet<MathHelper.Direction>[][] getRoomsData() {
        return roomsData;
    }
}
