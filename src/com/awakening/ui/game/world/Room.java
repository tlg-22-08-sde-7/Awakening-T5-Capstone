package com.awakening.ui.game.world;

import com.awakening.app.game.Item;
import com.awakening.ui.framework.gui.Sound;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.*;
import com.awakening.ui.game.entities.items.*;
import com.awakening.ui.game.states.PlayingState;
import com.awakening.ui.game.world.generator.RoomData;

import java.awt.*;
import java.util.ArrayList;

public class Room {
    private RoomData data; // RoomData object
    private ArrayList<Feature> features; // ArrayList of features
    private ArrayList<Enemy> enemies; // ArrayList of enemies
    public static boolean itemPickedUp = false; // Boolean to check if an item has been picked up
    public static Obj_MasterKey masterKey; // Master key object
    public static Obj_Key key; // Key object

    public Room(RoomData data) {
        this.data = data;
        this.features = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    // getter for RoomData data
    public RoomData getData() {
        return data;
    }

    // placeFeature method Takes in a Feature object
    // Adds the feature to the features ArrayList
    // Sets the feature's room to this room
    // Sets the feature's x and y position to the feature's x and y position in the room
    // Sets the feature's bounds to the feature's x and y position and the feature's width and height
    // Returns the feature
    // This method is used to place a feature in the room
    public void placeFeature(Feature feature) {
        // x =  The X coordinate of the upper-left corner of the Rectangle.
        // y = The Y coordinate of the upper-left corner of the Rectangle.
        if (data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.FLOOR ||
                data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.GRASS ||
                data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.TILE) {
            //position of the stairs on first floor
            if(feature.getID() == Resources.STAIRS){
                itemLocation(feature, 800, 200);
            }//position of the stairs on second floor
            else if(feature.getID() == Resources.UPPER_STAIRS){
                itemLocation(feature, 380, 530);
            } else if (feature.getID() == Resources.MASTER_DOOR) {
                feature.x = WindowManager.WIDTH - Tile.SIZE;
                feature.y = WindowManager.HEIGHT - 450;
                feature.height = Tile.SIZE * 3;
            } else if (feature.getId() == Resources.DOOR) {
                feature.x = 0;
                feature.y = WindowManager.HEIGHT - 450;
                feature.height = Tile.SIZE * 3;
            } else if (feature.getId() == Resources.KEY) {
                itemLocation(feature, 400, WindowManager.HEIGHT - (Tile.SIZE*3));
            } else if (feature.getId() == Resources.MASTER_KEY) {
                itemLocation(feature, Tile.SIZE, Tile.SIZE);
            } else if (feature.getId() == Resources.TYLENOL) {
                itemLocation(feature, WindowManager.WIDTH -(Tile.SIZE*2), Tile.SIZE);
            } else if (feature.getId() == Resources.WOOD_CANE) {
                itemLocation(feature, WindowManager.WIDTH/2, WindowManager.HEIGHT-(Tile.SIZE*4));
            } else if (feature.getId() == Resources.PRESS_PASS) {
                itemLocation(feature, Tile.SIZE*4, Tile.SIZE*3);
            } else if (feature.getId() == Resources.PATIENT_FILE) {
                itemLocation(feature, WindowManager.WIDTH -(Tile.SIZE*2), 400);
            } else if (feature.getId() == Resources.PAPER_CLIP) {
                itemLocation(feature, Tile.SIZE, Tile.SIZE);
            } else if (feature.getId() == Resources.JOURNAL) {
                itemLocation(feature, WindowManager.WIDTH -(Tile.SIZE*2), WindowManager.HEIGHT-(Tile.SIZE*2));
            } else if (feature.getId() == Resources.FIRST_AID_KIT) {
                itemLocation(feature, Tile.SIZE*12, WindowManager.HEIGHT -(Tile.SIZE*3));
            } else if (feature.getId() == Resources.BATTERIES) {
                itemLocation(feature, Tile.SIZE, WindowManager.HEIGHT-(Tile.SIZE*2));
            } else if (feature.getId() == Resources.FIRE_EXTINGUISHER) {
                itemLocation(feature, 600, 400);
            } else if (feature.getId() == Resources.BARBELL) {
                itemLocation(feature, 400, WindowManager.HEIGHT - (Tile.SIZE*3));
            } else if (feature.getId() == Resources.CELLPHONE) {
                itemLocation(feature, WindowManager.WIDTH -(Tile.SIZE*2), 400);
            } else if (feature.getId() == Resources.CAMERA) {
                itemLocation(feature, 300, Tile.SIZE*3);
            } else if (feature.getId() == Resources.AXE) {
                itemLocation(feature, 900, 350);
            } else if (feature.getId() == Resources.BANDAGES) {
                itemLocation(feature, WindowManager.WIDTH/2, WindowManager.HEIGHT-(Tile.SIZE*4));
            }
            this.features.add(feature);
        } else {
            this.placeFeature(new Feature(feature));

        }
    }

    private void itemLocation(Feature feature, int x, int y) {
        feature.x = x;
        feature.y = y;
    }

    // featureInteraction method
    // takes in a player object
    // for each feature in the features ArrayList
    // if the player's bounds intersects the feature's bounds
    // player interacts with the feature and adds the feature to their inventory
    public void featureInteraction(Player player) {
        for (int i = 0; i < this.features.size(); i++) {
            if (this.features.get(i).intersects(player)) {
                if (this.features.get(i).getID() == Resources.AXE) {
                    Obj_Axe axe = new Obj_Axe(0,0);
                    Player.playerInventory.add(axe); // add axe to player's inventory
                    itemPickedUp = true; // itemPickedUp is set to true
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up an " + axe.getName() + "!");// add message to the message log
                }else if (this.features.get(i).getID() == Resources.CAMERA) {
                    Obj_Camera camera = new Obj_Camera(0,0);
                    Player.playerInventory.add(camera);
                    itemPickedUp = true;
                    PlayingState.se.playSE(3);
                    PlayingState.addMessage("You have picked up a " + camera.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.BANDAGES) {
                    Obj_Bandages bandages = new Obj_Bandages(0,0);
                    Player.playerInventory.add(bandages);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up some " + bandages.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.BARBELL) {
                    Obj_Barbell barbell = new Obj_Barbell(0,0);
                    Player.playerInventory.add(barbell);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up a " + barbell.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.BATTERIES) {
                    Obj_Batteries batteries = new Obj_Batteries(0,0);
                    Player.playerInventory.add(batteries);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up some " + batteries.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.CELLPHONE) {
                    Obj_Cellphone cellphone = new Obj_Cellphone(0,0);
                    Player.playerInventory.add(cellphone);
                    itemPickedUp = true;
                    PlayingState.se.playSE(3);
                    PlayingState.addMessage("You have picked up a " + cellphone.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.FIRE_EXTINGUISHER) {
                    Obj_FireExtinguisher fireExtinguisher = new Obj_FireExtinguisher(0,0);
                    Player.playerInventory.add(fireExtinguisher);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up a " + fireExtinguisher.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.FIRST_AID_KIT) {
                    Obj_FirstAidKit firstAidKit = new Obj_FirstAidKit(0,0);
                    Player.playerInventory.add(firstAidKit);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up a " + firstAidKit.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.KEY) {
                    key = new Obj_Key(0,0);
                    Player.playerInventory.add(key);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up a " + key.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.JOURNAL) {
                    Obj_Journal journal = new Obj_Journal(0,0);
                    Player.playerInventory.add(journal);
                    itemPickedUp = true;
                    PlayingState.se.playSE(4);
                    PlayingState.addMessage("You have picked up a " + journal.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.MASTER_KEY) {
                    masterKey = new Obj_MasterKey(0,0);
                    Player.playerInventory.add(masterKey);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up a " + masterKey.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.PAPER_CLIP) {
                    Obj_PaperClip paperClip = new Obj_PaperClip(0,0);
                    Player.playerInventory.add(paperClip);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up a " + paperClip.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.PATIENT_FILE) {
                    Obj_PatientFile patientFile = new Obj_PatientFile(0,0);
                    Player.playerInventory.add(patientFile);
                    itemPickedUp = true;
                    PlayingState.se.playSE(4);
                    PlayingState.addMessage("You have picked up a " + patientFile.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.PRESS_PASS) {
                    Obj_PressPass pressPass = new Obj_PressPass(0,0);
                    Player.playerInventory.add(pressPass);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up a " + pressPass.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.TYLENOL) {
                    Obj_Tylenol tylenol = new Obj_Tylenol(0,0);
                    Player.playerInventory.add(tylenol);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up some " + tylenol.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.WOOD_CANE) {
                    Obj_WoodCane woodCane = new Obj_WoodCane(0,0);
                    Player.playerInventory.add(woodCane);
                    itemPickedUp = true;
                    PlayingState.se.playSE(6);
                    PlayingState.addMessage("You have picked up a " + woodCane.getName() + "!");
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
