package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.Sound;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.framework.utils.MathHelper;
import com.awakening.ui.game.entities.Enemy;
import com.awakening.ui.game.entities.Entity;
import com.awakening.ui.game.entities.Player;
import com.awakening.ui.game.world.Feature;
import com.awakening.ui.game.world.Room;
import com.awakening.ui.game.world.Tile;
import com.awakening.ui.game.world.World;
import com.awakening.ui.game.world.generator.LevelGenerator;
import com.awakening.ui.game.world.generator.RoomData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class PlayingState extends GameState {

    // creates instances of different classes and arrays to be used
    private final LevelGenerator generator;
    private final World world;
    public static Player player;
    public static ArrayList<String> message = new ArrayList<>();
    public static ArrayList<Integer> messageCounter = new ArrayList<>();
    public static Sound music = new Sound();
    public static Sound se = new Sound();

    // Constructor
    public PlayingState(GameStateManager manager) {
        super(manager);
        generator = new LevelGenerator();
        player = new Player();

        this.generator.intializeGridForRooms();
        this.generator.generateFloor();
        this.world = new World(this.generator.getRoomsData());

        generateLevel(1);
        regenerateBlocks();
        generateItemsAndEnemies();
    }

    // loops the frames in the current state
    @Override
    protected void loop() {
        player.move();
        this.world.changeRoom(player);
        // Room names set up
        String roomName = LevelGenerator.getRoomName(World.getCurrentX(), World.getCurrentY());
        player.setPlayerLoc(roomName);
        this.collisions();

        checkWinLose();

        World.getRoom().featureInteraction(player);

        player.regenerateHealth();
        this.playerAttacks();
    }

    /*
        If the player has a master key, patient file, journal and make their way back to the Front Desk, the player wins the game
     */
    private void checkWinLose() {
        //Game Lose Scenario -> if the player's hp is less than or equal to 0, the player loses the game
        if (this.player.getHp() <= 0) {
            se.playSE(7);
            super.gameStateManager.stackState(new GameLoseState(gameStateManager));
        }
        //Game Win Scenario -> check if the master key, patient file and journal are already in the inventory
        boolean masteryKeyFound = false;
        boolean patientFileFound = false;
        boolean journalFound = false;

        for (int i = 0; i < Player.getPlayerInventory().size(); i++) {
            //if( this.player.getPlayerInventory().contains())
            Entity e = Player.getPlayerInventory().get(i);
            if (!patientFileFound && Player.getPlayerInventory().get(i).getID() == Resources.PATIENT_FILE) {
                patientFileFound = true;
            }
            if (!masteryKeyFound && Player.getPlayerInventory().get(i).getID() == Resources.MASTER_KEY) {
                masteryKeyFound = true;
            }
            if (!journalFound && Player.getPlayerInventory().get(i).getID() == Resources.JOURNAL) {
                journalFound = true;
            }
            if (masteryKeyFound
                    && patientFileFound
                    && journalFound
                    && player.getPlayerLoc().equalsIgnoreCase("Front Desk")) {
                se.playSE(12);
                super.gameStateManager.stackState(new GameWinState(gameStateManager));
                break;
            }
        }
    }

    @Override
    protected void render(Graphics graphics) {

        // displays current state
        World.getRoom().render(graphics);
        player.render(graphics);

        // displays current player and weapon
        if (Player.currentWeapon != null) {
            graphics.drawImage(Resources.TEXTURES.get(Player.currentWeapon.getID()),
                    player.getAttackBox().x, player.getAttackBox().y,
                    player.getAttackBox().width, player.getAttackBox().height, null);
        } else {
            graphics.drawImage(Resources.TEXTURES.get(Resources.ATTACK),
                    player.getAttackBox().x, player.getAttackBox().y,
                    player.getAttackBox().width, player.getAttackBox().height, null);
        }
        // Display position of player's health, armour and gold throughout the game
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("arial", Font.BOLD, 17));
        graphics.drawImage(Resources.TEXTURES.get(Resources.HEART), 0, 0, Tile.SIZE * 2 / 3, Tile.SIZE * 2 / 3, null);
        graphics.drawString(this.player.getHp() + "/" + this.player.getMaxHp(), Tile.SIZE * 2 / 3 + 4, 20);
        graphics.drawImage(Resources.TEXTURES.get(Resources.ARMOR), 92, 0, Tile.SIZE * 2 / 3, Tile.SIZE * 2 / 3, null);
        graphics.drawString(this.player.getArmor() + "", Tile.SIZE * 2 / 3 + 96, 20);
        graphics.drawImage(Resources.TEXTURES.get(Resources.GOLD), 168, 0, Tile.SIZE * 2 / 3, Tile.SIZE * 2 / 3, null);
        graphics.drawString(this.player.getGold() + "", Tile.SIZE * 2 / 3 + 173, 20);

        // render player's current location/ rooms name
        graphics.setColor(Color.BLACK);
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 23f));
        graphics.drawString(player.getPlayerLoc(), 7, Tile.SIZE);
        graphics.setColor(Color.WHITE);
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 23f));
        graphics.drawString(player.getPlayerLoc(), 5, Tile.SIZE-2);

        drawMessage(graphics);

    }

    // adds a text to be displayed using the drawMessage method
    public static void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    // draws a message. Always running in the background of the Playing State
    public void drawMessage(Graphics graphics) {

        int messageX = WindowManager.WIDTH - 1100;
        int messageY = WindowManager.HEIGHT - 100;
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 38f));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                graphics.setColor(Color.BLACK);
                graphics.drawString(message.get(i), messageX + 2, messageY + 2);
                graphics.setColor(Color.WHITE);
                graphics.drawString(message.get(i), messageX, messageY);
                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                    Room.itemPickedUp = false;
                }
            }
        }
    }

    // Keys to be used in the current State
    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                player.setUp(true);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                player.setRight(true);
                break;
            case KeyEvent.VK_Q:
            case KeyEvent.VK_SPACE:
                player.attack();
                break;
            case KeyEvent.VK_ESCAPE:
                super.gameStateManager.stackState(new Options(gameStateManager));
                break;
            case KeyEvent.VK_I:
                super.gameStateManager.stackState(new Inventory(gameStateManager));
                break;
            case KeyEvent.VK_M:
                super.gameStateManager.stackState(new Map(gameStateManager));
                break;
            case KeyEvent.VK_P:
                super.gameStateManager.stackState(new PauseState(gameStateManager));
                break;
            case KeyEvent.VK_ENTER:
                openMasterDoor();
                openDoor();
                break;
        }

    }

    // Makes the player stop moving when the keys are released
    @Override
    protected void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                player.setUp(false);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                player.setDown(false);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                player.setRight(false);
                break;
            case KeyEvent.VK_ESCAPE:
                break;
            case KeyEvent.VK_I:
                break;
            case KeyEvent.VK_M:
                break;
        }
    }

    private void generateLevel(int floorNum) {
        if (floorNum == 2) { //second floor
            World.setCurrentX(7);
            World.setCurrentY(2);
            // stairs to First Floor
            this.world.getRoom(7, 2).placeFeature(new Feature(Resources.UPPER_STAIRS, () -> generateLevel(3)));

        } else if (floorNum == 1) { //first floor

            this.world.getRoom(2, 2).placeFeature(new Feature(Resources.STAIRS, () -> generateLevel(2)));

        } else {
            World.setCurrentX(2);
            World.setCurrentY(2);
            this.world.getRoom(2, 2).placeFeature(new Feature(Resources.STAIRS, () -> generateLevel(2)));
        }

        this.spawnPlayer();
    }

    private void generateItemsAndEnemies() {
        //first floor
        //place items in the rooms per the requirement
        generateChestInRoom(1, 0);
        generateItemInRoom(1, 0, Resources.CAMERA);
        generateChestInRoom(1, 2);
        generateChestInRoom(0, 2);
        generateItemInRoom(0, 2, Resources.PAPER_CLIP);
        generateItemInRoom(0, 2, Resources.BATTERIES);
        generateChestInRoom(1, 2);
        generateItemInRoom(1, 2, Resources.BANDAGES);
        generateChestInRoom(2, 1);
        generateItemInRoom(2, 1, Resources.CELLPHONE);
        generateItemInRoom(2, 2, Resources.MASTER_DOOR);
        generateItemInRoom(2, 2, Resources.AXE);
        generateItemInRoom(1, 3, Resources.KEY);
        generateItemInRoom(3, 2, Resources.PATIENT_FILE);

        //place enemies in the room per the requirement
        generateEnemyInRoom(1, 0, 10); // Basement
        generateEnemyInRoom(1, 2, 10); // Emergency Room
        generateEnemyInRoom(1, 2, 10); // Emergency Room
        generateEnemyInRoom(0, 2, 10); // Office
        generateEnemyInRoom(2, 2, 10); // Hallway
        generateEnemyInRoom(2, 1, 10); // Keypad Room
        generateBatsInRoom(3, 2, 5); // Patient Room
        generateBatsInRoom(3, 2, 5); // Patient Room
        generateBatsInRoom(3, 2, 5); // Patient Room
        generateBatsInRoom(3, 2, 5); // Patient Room
        generateBatsInRoom(3, 2, 5); // Patient Room
        generateBatsInRoom(1, 1, 5); // Morgue
        generateBatsInRoom(1, 1, 5); // Morgue
        generateBatsInRoom(1, 3, 5); // Front Desk
        generateBatsInRoom(1, 3, 5); // Front Desk
        generateBatsInRoom(1, 3, 5); // Front Desk
        generateBatsInRoom(1, 3, 5); // Front Desk
        generateBatsInRoom(1, 3, 5); // Front Desk

        //second floor
        //place items in the rooms per the requirement
        generateChestInRoom(6, 1); //pharmacy
        generateItemInRoom(6, 1, Resources.FIRST_AID_KIT);
        generateItemInRoom(6, 1, Resources.PRESS_PASS);
        generateChestInRoom(6, 2); //fellowship room
        generateItemInRoom(6, 2, Resources.WOOD_CANE);
        generateChestInRoom(6, 3);
        generateItemInRoom(6, 3, Resources.BARBELL);
        generateChestInRoom(5, 2);
        generateItemInRoom(5, 2, Resources.MASTER_KEY);
        generateItemInRoom(6, 2, Resources.DOOR);
        generateChestInRoom(7, 2);
        generateItemInRoom(7, 2, Resources.FIRE_EXTINGUISHER);
        generateChestInRoom(8, 2);
        generateItemInRoom(8, 2, Resources.TYLENOL);
        generateItemInRoom(8, 2, Resources.JOURNAL);

        //place enemies in the room per the requirement
        generateEnemyInRoom(6, 1, 10); // Pharmacy
        generateEnemyInRoom(6, 2, 10); // Fellowship Room
        generateEnemyInRoom(6, 3, 10); // Gym
        generateMainGhostInRoom(5, 2, 20); // Janitor Closet
        generateEnemyInRoom(5, 2, 10); // Janitor Closet
        generateEnemyInRoom(5, 2, 10); // Janitor Closet
        generateEnemyInRoom(5, 2, 10); // Janitor Closet
        generateEnemyInRoom(7, 2, 10); // U. Hallway
        generateEnemyInRoom(8, 2, 10); // Finance

    }

    // these methods generate the different Entities in the rooms
    private void generateChestInRoom(int roomX, int roomY) {
        this.world.getRoom(roomX, roomY).placeFeature(new Feature(Resources.CHEST, this::givePlayerRandomLoot));
    }

    private void generateItemInRoom(int roomX, int roomY, byte resource) {
        this.world.getRoom(roomX, roomY).placeFeature(new Feature(resource, this::pickUpItem));
    }

    private void generateEnemyInRoom(int roomX, int roomY, int enemyHp) {
        this.world.getRoom(roomX, roomY).spawnEnemy(new Enemy(Resources.ENEMY, enemyHp, player));
    }

    private void generateBatsInRoom(int roomX, int roomY, int enemyHp) {
        this.world.getRoom(roomX, roomY).spawnEnemy(new Enemy(Resources.BAT, enemyHp, player));
    }

    private void generateMainGhostInRoom(int roomX, int roomY, int enemyHp) {
        this.world.getRoom(roomX, roomY).spawnEnemy(new Enemy(Resources.GHOST_MAIN1, enemyHp, player));
    }

    private void spawnPlayer() {
        if (this.world.getRoom(1, 0).getData().getTileAt(player.x / Tile.SIZE, player.y / Tile.SIZE).getID() != Resources.FLOOR) {
            player.replaceRandomly();
            this.spawnPlayer();
        }
    }

    // used to handle collisions in the rooms
    private void collisions() {
        RoomData roomIn = World.getRoom().getData();

        for (int i = 0; i < roomIn.getSizeX(); i++) {
            for (int j = 0; j < roomIn.getSizeY(); j++) {
                player.handleCollisionWith(roomIn.getTileAt(i, j));

                for (Enemy enemy : World.getRoom().getEnemies()) {
                    enemy.handleCollisionWith(roomIn.getTileAt(i, j));
                }
            }
        }
    }

    // randomly gives the player different items from the chests
    private void givePlayerRandomLoot() {

        switch (MathHelper.randomInt(3)) {
            case 0:
                player.addArmor(MathHelper.randomInt(3, 5));
                se.playSE(6);
                break;
            case 1:
                player.giveGold(MathHelper.randomInt(3, 7));
                se.playSE(6);
                break;
            case 2:
                player.instantHeal(MathHelper.randomInt(2, 5));
                se.playSE(6);
                break;
        }
    }

    // used as a place holder for the generate items
    private void pickUpItem() {
        String text;
        if (Player.playerInventory.size() != Player.inventorySize) {
            text = "You have picked up an item!";
        } else {
            text = "You cannot carry any more items!";
        }
    }

    // used to attack the enemies and for the enemies to attack the player
    private void playerAttacks() {
        player.decreaseTime();
        for (int i = 0; i < World.getRoom().getEnemies().size(); i++) {
            World.getRoom().getEnemies().get(i).move();

            // when the enemy attacks the player
            if (World.getRoom().getEnemies().get(i).intersects(player)) {
                if (World.getRoom().getEnemies().get(i).getID() == Resources.BAT_2 ||
                        World.getRoom().getEnemies().get(i).getID() == Resources.BAT) {
                    se.playSE(13);
                } else {
                    se.playSE(5);
                }
                player.damage(5 - 5 * Player.getArmor() / 100);
            }

            // when the player attacks the enemy
            if (World.getRoom().getEnemies().get(i).intersects(player.getAttackBox())) {
                se.playSE(1);
                if (Player.currentWeapon != null) {
                    World.getRoom().getEnemies().get(i).damage(Player.currentWeapon.getAttackPoints(), player.getFacing());
                } else {
                    World.getRoom().getEnemies().get(i).damage(player.getAttackPoints(), player.getFacing());
                }
                if (World.getRoom().getEnemies().get(i).getHp() <= 0) {
                    World.getRoom().getEnemies().remove(i);
                    player.giveGold(MathHelper.randomInt(2, 5));
                }
            }
        }
    }

    // for opening the master door
    private void openMasterDoor() {
        if (Objects.equals(LevelGenerator.getRoomName(World.getCurrentX(), World.getCurrentY()), "Hallway")) {
            RoomData roomIn = World.getRoom().getData();

            for (int i = 0; i < Player.playerInventory.size(); i++) {
                if (Player.playerInventory.get(i).getID() == Resources.MASTER_KEY &&
                        player.intersects(roomIn.getTileAt(14, 3))) {
                    gameStateManager.stackState(new UseKey(gameStateManager));
                } else if (Player.playerInventory.get(i).getID() == Resources.MASTER_KEY &&
                        player.intersects(roomIn.getTileAt(14, 4))) {
                    gameStateManager.stackState(new UseKey(gameStateManager));
                } else if (Player.playerInventory.get(i).getID() == Resources.MASTER_KEY &&
                        player.intersects(roomIn.getTileAt(14, 5))) {
                    gameStateManager.stackState(new UseKey(gameStateManager));
                }
            }
        }
    }

    // for opening the regular door
    private void openDoor() {
        if (Objects.equals(LevelGenerator.getRoomName(World.getCurrentX(), World.getCurrentY()), "Fellowship Room")) {
            RoomData roomIn = World.getRoom().getData();

            for (int i = 0; i < Player.playerInventory.size(); i++) {
                if (Player.playerInventory.get(i).getID() == Resources.KEY &&
                        player.intersects(roomIn.getTileAt(1, 3))) {
                    gameStateManager.stackState(new UseKey(gameStateManager));
                } else if (Player.playerInventory.get(i).getID() == Resources.KEY &&
                        player.intersects(roomIn.getTileAt(1, 4))) {
                    gameStateManager.stackState(new UseKey(gameStateManager));
                } else if (Player.playerInventory.get(i).getID() == Resources.KEY &&
                        player.intersects(roomIn.getTileAt(1, 5))) {
                    gameStateManager.stackState(new UseKey(gameStateManager));
                }
            }
        }
    }

    // regenerates blocks when the player restarts the game. Used to replace the blocks when doors are opened.
    private void regenerateBlocks() {
        RoomData roomFellowship = World.getFellowshipRoom().getData();
        RoomData roomHallway = World.getHallwayRoom().getData();
        if (roomFellowship.getTileAt(0, 3).getID() == Resources.TILE) {
            roomFellowship.getTileAt(0, 3).setId(Resources.WALL);
            roomFellowship.getTileAt(0, 4).setId(Resources.WALL);
            roomFellowship.getTileAt(0, 5).setId(Resources.WALL);
            roomFellowship.getTileAt(0, 3).setWall(true);
            roomFellowship.getTileAt(0, 4).setWall(true);
            roomFellowship.getTileAt(0, 5).setWall(true);
        } if (roomHallway.getTileAt(15, 3).getID() == Resources.TILE) {
            roomHallway.getTileAt(15,3).setId(Resources.STONE);
            roomHallway.getTileAt(15,4).setId(Resources.STONE);
            roomHallway.getTileAt(15,5).setId(Resources.STONE);
            roomHallway.getTileAt(15, 3).setWall(true);
            roomHallway.getTileAt(15, 4).setWall(true);
            roomHallway.getTileAt(15, 5).setWall(true);
        }
    }
}
