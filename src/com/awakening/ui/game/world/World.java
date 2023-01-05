package com.awakening.ui.game.world;

import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.framework.utils.MathHelper;
import com.awakening.ui.game.Init;
import com.awakening.ui.game.entities.Entity;
import com.awakening.ui.game.world.Room;
import com.awakening.ui.game.world.generator.LevelGenerator;
import com.awakening.ui.game.world.generator.RoomData;

import java.util.HashSet;

public class World {

    public static Room[][] rooms; // 2 Dimensional Array
    private static int currentX; // Current X Position
    private static int currentY; // Current Y Position

    // Constructor
    // Takes in a 2D array of MathHelper.Directions
    // This is used to generate the rooms.
    // Rooms are generated using the LevelGenerator class
    public World(HashSet<MathHelper.Direction>[][] roomsData){
        rooms = new Room[roomsData.length][roomsData[0].length];

        for(int i = 0; i< LevelGenerator.WORLD_SIZE; i++) {
            for(int j=0;j<LevelGenerator.WORLD_SIZE;j++) {
                for(RoomData roomData : Init.ROOMS) {
                    if(roomData.getExits().equals(roomsData[i][j]))
                        this.rooms[i][j] = new Room(roomData);
                }
            }
        }
        // player's location is set default to the basement using field currentX and currentY
        this.currentX = 1;
        this.currentY = 0;
    }

    public Room getRoom(int x, int y) {
        return rooms[x][y];
    }

    public static Room getRoom() {
        return rooms[currentX][currentY];
    }

    public static Room getFellowshipRoom() {
        return rooms[6][2];
    }
    public static Room getHallwayRoom() {
        return rooms[2][2];
    }

    public void changeRoom(Entity player) {
        if(player.getCenterX() < 0) {
            this.currentX--;
            player.setCenterX(WindowManager.WIDTH);
        }
        else if(player.getCenterX() > WindowManager.WIDTH) {
            this.currentX++;
            player.setCenterX(0);
        }
        else if(player.getCenterY() < 0) {
            this.currentY--;
            player.setCenterY(WindowManager.HEIGHT);
        }
        else if(player.getCenterY() > WindowManager.HEIGHT) {
            this.currentY++;
            player.setCenterY(0);
        }
    }
    // returns player's location on map/room
    public static int getCurrentX() {
        return currentX;
    }
    public static int getCurrentY() {
        return currentY;
    }

    public static void setCurrentX(int currentX) {
        World.currentX = currentX;
    }

    public static void setCurrentY(int currentY) {
        World.currentY = currentY;
    }
}
