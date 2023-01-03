package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayingState extends GameState {
    private LevelGenerator generator;
    private World world;
    private Player player;
    public static ArrayList<String> message = new ArrayList<>();
    public static ArrayList<Integer> messageCounter = new ArrayList<>();

    public PlayingState(GameStateManager manager) {
        super(manager);
        generator = new LevelGenerator();
        player = new Player();

        this.generator.intializeGridForRooms();
        this.generator.generateFloor();
        this.world = new World(this.generator.getRoomsData());

        generateLevel(1);
        generateItemsAndEnemies();
    }

    @Override
    protected void loop() {
        this.player.move();
        this.world.changeRoom(player);
        // Room names set up
        String roomName = this.generator.getRoomName(this.world.getCurrentX(), this.world.getCurrentY());
        this.player.setPlayerLoc(roomName);
        this.collisions();

        checkWinLose();

        this.world.getRoom().featureInteraction(player);

        this.player.regenerateHealth();
        this.playerAttacks();
    }

    /*
        If the player has a master key & patient file, and located in Front Desk, the player escapes and wins the game
     */
    private void checkWinLose() {
        //Game Lose Scenario -> if the player's hp is < 0, the player loses
        if (this.player.getHp() <= 0) {
            super.gameStateManager.stackState(new GameLoseState(gameStateManager));
        }
        //Game Win Scenario -> check if the master key && patient file is already in the inventory
        boolean masteryKeyFound = false;
        boolean patientFileFound = false;

        for (int i = 0; i < this.player.getPlayerInventory().size(); i++) {
            //if( this.player.getPlayerInventory().contains())
            Entity e = this.player.getPlayerInventory().get(i);
            if (!patientFileFound && this.player.getPlayerInventory().get(i).getID() == Resources.PATIENT_FILE) {
                patientFileFound = true;
            }
            if (!masteryKeyFound && this.player.getPlayerInventory().get(i).getID() == Resources.MASTER_KEY) {
                masteryKeyFound = true;
            }
            if (masteryKeyFound
                    && patientFileFound
                    && this.player.getPlayerLoc().equalsIgnoreCase("Front Desk")) {
                super.gameStateManager.stackState(new GameWinState(gameStateManager));
                break;
            }
        }
    }

    @Override
    protected void render(Graphics graphics) {
        this.world.getRoom().render(graphics);
        this.player.render(graphics);
        graphics.drawImage(Resources.TEXTURES.get(Resources.ATTACK), this.player.getAttackBox().x, this.player.getAttackBox().y, this.player.getAttackBox().width, this.player.getAttackBox().height, null);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("arial", Font.PLAIN, 15));
        graphics.drawImage(Resources.TEXTURES.get(Resources.HEART), 0, 0, Tile.SIZE * 2 / 3, Tile.SIZE * 2 / 3, null);
        graphics.drawString(this.player.getHp() + "/" + this.player.getMaxHp(), Tile.SIZE * 2 / 3 + 1, 20);
        graphics.drawImage(Resources.TEXTURES.get(Resources.ARMOR), 80, 0, Tile.SIZE * 2 / 3, Tile.SIZE * 2 / 3, null);
        graphics.drawString(this.player.getArmor() + "", Tile.SIZE * 2 / 3 + 85, 20);
        graphics.drawImage(Resources.TEXTURES.get(Resources.GOLD), 160, 0, Tile.SIZE * 2 / 3, Tile.SIZE * 2 / 3, null);
        graphics.drawString(this.player.getGold() + "", Tile.SIZE * 2 / 3 + 165, 20);

        // render player's current location/ rooms name
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 20f));
        graphics.drawString(this.player.getPlayerLoc(), 5, Tile.SIZE + 10);

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
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 50f));

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

    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                this.player.setUp(true);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.player.setLeft(true);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.player.setDown(true);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.player.setRight(true);
                break;
            case KeyEvent.VK_Q:
            case KeyEvent.VK_SPACE:
                this.player.attack();
                break;
            case KeyEvent.VK_ESCAPE:
                super.gameStateManager.stackState(new Options(gameStateManager));
                break;
            case KeyEvent.VK_I:
                super.gameStateManager.stackState(new Inventory(gameStateManager));
                break;
        }

    }

    @Override
    protected void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                this.player.setUp(false);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.player.setLeft(false);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.player.setDown(false);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.player.setRight(false);
                break;
            case KeyEvent.VK_ESCAPE:
                break;
            case KeyEvent.VK_I:
                break;
        }
    }

    private void generateLevel(int floorNum) {
        if (floorNum == 2) { //second floor
            this.world.setCurrentX(7);
            this.world.setCurrentY(2);
            // stairs to First Floor
            this.world.getRoom(7, 2).placeFeature(new Feature(Resources.UPPER_STAIRS, () -> generateLevel(3)));
        } else if (floorNum == 1) { //first floor
            this.world.getRoom(2, 2).placeFeature(new Feature(Resources.STAIRS, () -> generateLevel(2)));
        } else {
            this.world.setCurrentX(2);
            this.world.setCurrentY(2);
            this.world.getRoom(2, 2).placeFeature(new Feature(Resources.STAIRS, () -> generateLevel(2)));
        }

        this.spawnPlayer();
    }

    private void generateItemsAndEnemies() {
        //first floor
        //place items in the rooms per the requirement
        generateChestInRoom(1, 0);
        generateItemInRoom(1, 0, Resources.CAMERA);
        generateItemInRoom(1, 0, Resources.CELLPHONE);
        generateChestInRoom(1, 2);
        generateChestInRoom(0, 2);
        generateItemInRoom(0, 2, Resources.PAPER_CLIP);
        generateItemInRoom(0, 2, Resources.BATTERIES);
        generateChestInRoom(1, 2);
        generateItemInRoom(1, 2, Resources.BANDAGES);
        generateChestInRoom(2, 1);
        generateItemInRoom(1, 3, Resources.KEY);
        generateItemInRoom(3, 2, Resources.PATIENT_FILE);

        //place enemies in the room per the requirement
        generateEnemyInRoom(1, 0, 5);
        generateEnemyInRoom(1, 2, 5);
        generateEnemyInRoom(0, 2, 5);
        generateEnemyInRoom(1, 2, 5);
        generateEnemyInRoom(2, 2, 5);
        generateEnemyInRoom(2, 1, 5);

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
        generateChestInRoom(7, 2);
        generateItemInRoom(7, 2, Resources.AXE);
        generateItemInRoom(7, 2, Resources.FIRE_EXTINGUISHER);
        generateChestInRoom(8, 2);
        generateItemInRoom(8, 2, Resources.TYLENOL);
        generateItemInRoom(8, 2, Resources.JOURNAL);

        //place enemies in the room per the requirement
        generateEnemyInRoom(6, 1, 5);
        generateEnemyInRoom(6, 2, 5);
        generateEnemyInRoom(6, 3, 5);
        generateEnemyInRoom(5, 2, 5);
        generateEnemyInRoom(7, 2, 5);
        generateEnemyInRoom(8, 2, 5);
    }

    private void generateChestInRoom(int roomX, int roomY) {
        this.world.getRoom(roomX, roomY).placeFeature(new Feature(Resources.CHEST, this::givePlayerRandomLoot));
    }

    private void generateItemInRoom(int roomX, int roomY, byte resource) {
        this.world.getRoom(roomX, roomY).placeFeature(new Feature(resource, this::pickUpItem));
    }

    private void generateEnemyInRoom(int roomX, int roomY, int enemyHp) {
        this.world.getRoom(roomX, roomY).spawnEnemy(new Enemy(Resources.ENEMY, enemyHp, this.player));
    }

    private void spawnPlayer() {
        if (this.world.getRoom(1, 0).getData().getTileAt(player.x / Tile.SIZE, player.y / Tile.SIZE).getID() != Resources.FLOOR) {
            this.player.replaceRandomly();
            this.spawnPlayer();
        }
    }

    private void collisions() {
        RoomData roomIn = this.world.getRoom().getData();

        for (int i = 0; i < roomIn.getSizeX(); i++) {
            for (int j = 0; j < roomIn.getSizeY(); j++) {
                this.player.handleCollisionWith(roomIn.getTileAt(i, j));

                for (Enemy enemy : this.world.getRoom().getEnemies()) {
                    enemy.handleCollisionWith(roomIn.getTileAt(i, j));
                }
            }
        }
    }

    private void givePlayerRandomLoot() {

        switch (MathHelper.randomInt(3)) {
            case 0:
                this.player.addArmor(MathHelper.randomInt(3, 5));
                break;
            case 1:
                this.player.giveGold(MathHelper.randomInt(3, 7));
                break;
            case 2:
                this.player.instantHeal(MathHelper.randomInt(2, 5));
                break;
        }
    }

    private void pickUpItem() {
        String text;
        if (Player.playerInventory.size() != Player.inventorySize) {
            text = "You have picked up an item!";
        } else {
            text = "You cannot carry any more items!";
        }
    }

    private void playerAttacks() {
        this.player.decreaseTime();
        for (int i = 0; i < this.world.getRoom().getEnemies().size(); i++) {
            this.world.getRoom().getEnemies().get(i).move();

            if (this.world.getRoom().getEnemies().get(i).intersects(this.player)) {
                this.player.damage(5 - 5 * this.player.getArmor() / 100);
            }

            if (this.world.getRoom().getEnemies().get(i).intersects(this.player.getAttackBox())) {
                this.world.getRoom().getEnemies().get(i).damage(3, this.player.getFacing());
                if (this.world.getRoom().getEnemies().get(i).getHp() <= 0) {
                    this.world.getRoom().getEnemies().remove(i);
                    this.player.giveGold(MathHelper.randomInt(2, 5));
                }
            }
        }
    }
}
