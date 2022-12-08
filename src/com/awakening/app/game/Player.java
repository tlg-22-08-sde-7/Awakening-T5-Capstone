package com.awakening.app.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name = "";
    private RoomMap.RoomLayout currentRoom;
    private List<Item> inventory = new ArrayList<>();


    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public RoomMap.RoomLayout getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(RoomMap.RoomLayout currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        inventory = inventory;
    }
}
