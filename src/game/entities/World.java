package game.entities;

import com.awakening.app.game.Room;
import framework.gamestates.gui.WindowManager;
import framework.gamestates.util.MathHelper;
import game.entities.generator.LevelGenerator;
import game.entities.generator.RoomData;

import java.util.HashSet;

public class World {

    private Room[][] rooms;
    private int currentX;
    private int currentY;

    public World(HashSet<MathHelper.Direction>[][] roomsData) {
        this.rooms = new Room[roomsData.length][roomsData[0].length];
        for(int i = 0; i< LevelGenerator.WORLD_SIZE; i++) {
            for(int j=0;j<LevelGenerator.WORLD_SIZE;j++) {
                for(RoomData roomData : Init.ROOMS) {
                    if(roomData.getExits().equals(roomsData[i][j]))
                        this.rooms[i][j] = new Room(roomData);
                }
            }
        }
        this.currentX = 0;
        this.currentY = 0;
    }

    public Room getRoom(int x, int y) {
        return rooms[x][y];
    }

    public Room getRoom() {
        return rooms[currentX][currentY];
    }

    public Room getRoomRandom() {
        return rooms[MathHelper.randomInt(LevelGenerator.WORLD_SIZE)][MathHelper.randomInt(LevelGenerator.WORLD_SIZE)];
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

}