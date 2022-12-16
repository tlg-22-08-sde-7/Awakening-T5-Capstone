package com.awakening.app.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Player1 player1;
    private Player2 player2;
    private Player3 player3;
    private Player4 player4;
    private String name = "";
    private RoomMap.RoomLayout currentRoom;
    private List<Item.ItemsSetup> inventory = new ArrayList<Item.ItemsSetup>();

    public Player1 getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player1 player1) {
        this.player1 = player1;
    }

    public Player2 getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player2 player2) {
        this.player2 = player2;
    }

    public Player3 getPlayer3() {
        return player3;
    }

    public void setPlayer3(Player3 player3) {
        this.player3 = player3;
    }

    public Player4 getPlayer4() {
        return player4;
    }

    public void setPlayer4(Player4 player4) {
        this.player4 = player4;
    }

    public void addToInventory(Item.ItemsSetup item) {
        inventory.add(item);
    }

    public RoomMap.RoomLayout getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(RoomMap.RoomLayout currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<Item.ItemsSetup> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        inventory = inventory;
    }
    public String getPlayer(String playerName) {
        String player = null;
        if (playerName.equals(getPlayer1().getName())) {
            player = getPlayer1().getName();
        }
        else if (playerName.equals(getPlayer2().getName())) {
            player = getPlayer2().getName();
        }
        else if (playerName.equals(getPlayer3().getName())) {
            player = getPlayer3().getName();
        }
        else if (playerName.equals(getPlayer4().getName())) {
            player = getPlayer4().getName();
        } else {
            player = "Player not found";
        }
        return player;
    }

    // This will allow for the player inventory to be printed to the console/terminal
    public String printInventory() {
        String inventory = "";

        for(Item.ItemsSetup item : getInventory() ){
            inventory += item.getName() + " ";
        }

        return inventory;
    }

    @Override
    public String toString() {
        return "          " + getPlayer1().getName() +  ":  " + getPlayer1().getDescription() +
                "\n          " + getPlayer2().getName() + ":  " + getPlayer2().getDescription() +
                "\n          " + getPlayer3().getName() + ":   " + getPlayer3().getDescription() +
                "\n          " + getPlayer4().getName() + ": " + getPlayer4().getDescription();
    }

    public static class PlayerLayout {
        private String name;
        private String description;
        private int health;
        private int attack;

        public PlayerLayout(String name, String description, int health, int attack) {
            this.name = name;
            this.description = description;
            this.health = health;
            this.attack = attack;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public int getAttack() {
            return attack;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }
    }
    public static class Player1 extends Player.PlayerLayout {

        public Player1(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }
    public static class Player2 extends Player.PlayerLayout {

        public Player2(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }
    public static class Player3 extends Player.PlayerLayout {

        public Player3(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }
    public static class Player4 extends Player.PlayerLayout {

        public Player4(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }
}


