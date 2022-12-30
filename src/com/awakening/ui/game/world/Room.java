package com.awakening.ui.game.world;

import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.*;
import com.awakening.ui.game.world.generator.RoomData;

import java.awt.*;
import java.util.ArrayList;

public class Room {
    private RoomData data;
    private ArrayList<Feature> features;
    private ArrayList<Enemy> enemies;
    public static boolean itemPickedUp = false;

    public Room(RoomData data) {
        this.data = data;
        this.features = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    // getter for RoomData data
    public RoomData getData() {
        return data;
    }

    public void placeFeature(Feature feature) {
        // x =  The X coordinate of the upper-left corner of the Rectangle.
        // y = The Y coordinate of the upper-left corner of the Rectangle.
        if (data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.FLOOR ||
                data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.GRASS ||
                data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.TILE) {
            //position of the stairs on first floor
            if(feature.getID() == Resources.STAIRS){
                feature.x = 800;
                feature.y = 200;
            }//position of the stairs on second floor
            else if(feature.getID() == Resources.UPPER_STAIRS){
                feature.x = 380;
                feature.y = 530;
            }
            this.features.add(feature);
        } else {
            this.placeFeature(new Feature(feature));
        }
    }

    public void featureInteraction(Player player) {
        for (int i = 0; i < this.features.size(); i++) {
            if (this.features.get(i).intersects(player) && Player.playerInventory.size() <= Player.inventorySize) {
                if (this.features.get(i).getID() == Resources.AXE) {
                    Player.playerInventory.add(Resources.TEXTURES.get(45));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.CAMERA) {
                    Player.playerInventory.add(Resources.TEXTURES.get(49));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.BANDAGES) {
                    Player.playerInventory.add(Resources.TEXTURES.get(46));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.BARBELL) {
                    Player.playerInventory.add(Resources.TEXTURES.get(47));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.BATTERIES) {
                    Player.playerInventory.add(Resources.TEXTURES.get(48));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.CELLPHONE) {
                    Player.playerInventory.add(Resources.TEXTURES.get(50));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.FIRE_EXTINGUISHER) {
                    Player.playerInventory.add(Resources.TEXTURES.get(51));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.FIRST_AID_KIT) {
                    Player.playerInventory.add(Resources.TEXTURES.get(52));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.KEY) {
                    Player.playerInventory.add(Resources.TEXTURES.get(53));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.JOURNAL) {
                    Player.playerInventory.add(Resources.TEXTURES.get(54));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.MASTER_KEY) {
                    Player.playerInventory.add(Resources.TEXTURES.get(55));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.PAPER_CLIP) {
                    Player.playerInventory.add(Resources.TEXTURES.get(56));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.PATIENT_FILE) {
                    Player.playerInventory.add(Resources.TEXTURES.get(57));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.PICTURE) {
                    Player.playerInventory.add(Resources.TEXTURES.get(58));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.PRESS_PASS) {
                    Player.playerInventory.add(Resources.TEXTURES.get(59));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.TYLENOL) {
                    Player.playerInventory.add(Resources.TEXTURES.get(60));
                    itemPickedUp = true;
                }else if (this.features.get(i).getID() == Resources.WOOD_CANE) {
                    Player.playerInventory.add(Resources.TEXTURES.get(61));
                    itemPickedUp = true;
                }
                this.features.remove(i);
            }
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void spawnEnemy(Enemy enemy) {
        if (data.getTileAt(enemy.x / Tile.SIZE, enemy.y / Tile.SIZE).getID() == Resources.FLOOR)
            this.enemies.add(enemy);
        else
            this.spawnEnemy(new Enemy(enemy));
    }

    public void render(Graphics graphics) {
        this.data.render(graphics);
        for (Feature feature : features) {
            feature.render(graphics);
        }
        for (Enemy enemy : enemies) {
            enemy.render(graphics);
        }
    }
}
