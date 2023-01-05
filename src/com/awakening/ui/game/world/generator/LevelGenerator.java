package com.awakening.ui.game.world.generator;

import com.awakening.ui.framework.utils.MathHelper;

import java.util.HashSet;

public class LevelGenerator {

    public static final int WORLD_SIZE = 9;
    //To display names of each room
    private static String[][] roomNames; // 2D array of room names
    private HashSet<MathHelper.Direction>[][] roomsData; // 2D array of MathHelper.Directions

    @SuppressWarnings("unchecked")
    public void intializeGridForRooms() {
        this.roomsData = new HashSet[WORLD_SIZE][WORLD_SIZE];// 2D array of MathHelper.Directions
        this.roomNames = new String[WORLD_SIZE][WORLD_SIZE];// 2D array of room names
        for (int i = 0; i < this.roomsData.length; i++) {
            for (int j = 0; j < this.roomsData[i].length; j++) {
                this.roomsData[i][j] = new HashSet<>();// 2D array of MathHelper.Directions
                this.roomNames[i][j] = "";// 2D array of room names
            }
        }
    }

    // generateFloor() is called from the constructor of World.java
    // sets the room name and exits for each room
    public void generateFloor() {
        //basement
        this.roomsData[1][0].add(MathHelper.Direction.SOUTH);// 2D array of MathHelper.Directions
        this.roomNames[1][0] = "Basement"; // 2D array of room names
        //morgue
        this.roomsData[1][1].add(MathHelper.Direction.NORTH);
        this.roomNames[1][1] = "Morgue";

        //keypad room
        this.roomsData[1][1].add(MathHelper.Direction.EAST);
        this.roomsData[2][1].add(MathHelper.Direction.WEST);
        this.roomNames[2][1] = "Keypad Room";

        //emergency room
        this.roomsData[1][1].add(MathHelper.Direction.SOUTH);
        this.roomsData[1][2].add(MathHelper.Direction.NORTH);
        this.roomNames[1][2] = "Emergency Room";

        //office
        this.roomsData[1][2].add(MathHelper.Direction.WEST);
        this.roomsData[0][2].add(MathHelper.Direction.EAST);
        this.roomNames[0][2] = "Office";

        //front desk
        this.roomsData[1][2].add(MathHelper.Direction.SOUTH);
        this.roomsData[1][3].add(MathHelper.Direction.NORTH);
        this.roomNames[1][3] = "Front Desk";

        //hallway
        this.roomsData[1][2].add(MathHelper.Direction.EAST);
        this.roomsData[2][2].add(MathHelper.Direction.WEST);
        this.roomNames[2][2] = "Hallway";

        //patient room
        this.roomsData[2][2].add(MathHelper.Direction.EAST);
        this.roomsData[3][2].add(MathHelper.Direction.WEST);
        this.roomNames[3][2] = "Patient Room";

        //second floor rooms
        //pharmacy
        this.roomsData[6][1].add(MathHelper.Direction.SOUTH);
        this.roomNames[6][1] = "Pharmacy";

        //fellowship room
        this.roomsData[6][2].add(MathHelper.Direction.NORTH);
        this.roomNames[6][2] = "Fellowship Room";

        //gym
        this.roomsData[6][2].add(MathHelper.Direction.SOUTH);
        this.roomsData[6][3].add(MathHelper.Direction.NORTH);
        this.roomNames[6][3] = "Gym";

        //janitor closet
        this.roomsData[6][2].add(MathHelper.Direction.WEST);
        this.roomsData[5][2].add(MathHelper.Direction.EAST);
        this.roomNames[5][2] = "Janitor Closet";

        //u. hallway
        this.roomsData[6][2].add(MathHelper.Direction.EAST);
        this.roomsData[7][2].add(MathHelper.Direction.WEST);
        this.roomNames[7][2] = "U. Hallway";

        //finance
        this.roomsData[7][2].add(MathHelper.Direction.EAST);
        this.roomsData[8][2].add(MathHelper.Direction.WEST);
        this.roomNames[8][2] = "Finance";

    }

    public HashSet<MathHelper.Direction>[][] getRoomsData() {
        return roomsData;
    }

    public static String getRoomName(int x, int y){
        return roomNames[x][y];
    }
}
