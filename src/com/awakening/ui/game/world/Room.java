package com.awakening.ui.game.world;

import com.awakening.app.game.Item;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.*;
import com.awakening.ui.game.entities.items.*;
import com.awakening.ui.game.states.PlayingState;
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
            this.features.add(feature);
        } else {
            placeFeature(new Feature(feature));
        }
    }

    public void featureInteraction(Player player) {
        for (int i = 0; i < this.features.size(); i++) {
            if (this.features.get(i).intersects(player)) {
                if (this.features.get(i).getID() == Resources.AXE) {
                    Obj_Axe axe = new Obj_Axe(0,0);
                    Player.playerInventory.add(axe);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up an " + axe.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.CAMERA) {
                    Obj_Camera camera = new Obj_Camera(0,0);
                    Player.playerInventory.add(camera);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + camera.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.BANDAGES) {
                    Obj_Bandages bandages = new Obj_Bandages(0,0);
                    Player.playerInventory.add(bandages);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up some " + bandages.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.BARBELL) {
                    Obj_Barbell barbell = new Obj_Barbell(0,0);
                    Player.playerInventory.add(barbell);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + barbell.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.BATTERIES) {
                    Obj_Batteries batteries = new Obj_Batteries(0,0);
                    Player.playerInventory.add(batteries);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up some " + batteries.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.CELLPHONE) {
                    Obj_Cellphone cellphone = new Obj_Cellphone(0,0);
                    Player.playerInventory.add(cellphone);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + cellphone.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.FIRE_EXTINGUISHER) {
                    Obj_FireExtinguisher fireExtinguisher = new Obj_FireExtinguisher(0,0);
                    Player.playerInventory.add(fireExtinguisher);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + fireExtinguisher.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.FIRST_AID_KIT) {
                    Obj_FirstAidKit firstAidKit = new Obj_FirstAidKit(0,0);
                    Player.playerInventory.add(firstAidKit);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + firstAidKit.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.KEY) {
                    Obj_Key key = new Obj_Key(0,0);
                    Player.playerInventory.add(key);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + key.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.JOURNAL) {
                    Obj_Journal journal = new Obj_Journal(0,0);
                    Player.playerInventory.add(journal);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + journal.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.MASTER_KEY) {
                    Obj_MasterKey masterKey = new Obj_MasterKey(0,0);
                    Player.playerInventory.add(masterKey);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + masterKey.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.PAPER_CLIP) {
                    Obj_PaperClip paperClip = new Obj_PaperClip(0,0);
                    Player.playerInventory.add(paperClip);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + paperClip.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.PATIENT_FILE) {
                    Obj_PatientFile patientFile = new Obj_PatientFile(0,0);
                    Player.playerInventory.add(patientFile);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + patientFile.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.PRESS_PASS) {
                    Obj_PressPass pressPass = new Obj_PressPass(0,0);
                    Player.playerInventory.add(pressPass);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up a " + pressPass.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.TYLENOL) {
                    Obj_Tylenol tylenol = new Obj_Tylenol(0,0);
                    Player.playerInventory.add(tylenol);
                    itemPickedUp = true;
                    PlayingState.addMessage("You have picked up some " + tylenol.getName() + "!");
                }else if (this.features.get(i).getID() == Resources.WOOD_CANE) {
                    Obj_WoodCane woodCane = new Obj_WoodCane(0,0);
                    Player.playerInventory.add(woodCane);
                    itemPickedUp = true;
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
