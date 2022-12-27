package com.awakening.ui.game.world;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.*;
import com.awakening.ui.game.world.generator.RoomData;

import java.awt.*;
import java.util.ArrayList;

public class Room {
    private RoomData data;
    private ArrayList<Feature> features;
    private ArrayList<Enemy> enemies;

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
                data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.TILE)
            this.features.add(feature);
        else
            this.placeFeature(new Feature(feature));
    }

    public void featureInteraction1(Player1 player) {
        for (int i = 0; i < this.features.size(); i++) {
            if (this.features.get(i).intersects(player))
                this.features.remove(i);
        }
    }

    public void featureInteraction2(Player2 player) {
        for (int i = 0; i < this.features.size(); i++) {
            if (this.features.get(i).intersects(player))
                this.features.remove(i);
        }
    }
    public void featureInteraction3(Player3 player) {
        for (int i = 0; i < this.features.size(); i++) {
            if (this.features.get(i).intersects(player))
                this.features.remove(i);
        }
    }
    public void featureInteraction4(Player4 player) {
        for (int i = 0; i < this.features.size(); i++) {
            if (this.features.get(i).intersects(player))
                this.features.remove(i);
        }
    }


    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void spawnEnemy(Enemy enemy, Player1 player) {
        if (data.getTileAt(enemy.x / Tile.SIZE, enemy.y / Tile.SIZE).getID() == Resources.FLOOR)
            this.enemies.add(enemy);
        else
            this.spawnEnemy(new Enemy(enemy, player), player);
    }
    public void spawnEnemy(Enemy enemy, Player2 player) {
        if (data.getTileAt(enemy.x / Tile.SIZE, enemy.y / Tile.SIZE).getID() == Resources.FLOOR)
            this.enemies.add(enemy);
        else
            this.spawnEnemy(new Enemy(enemy, player), player);
    }
    public void spawnEnemy(Enemy enemy, Player3 player) {
        if (data.getTileAt(enemy.x / Tile.SIZE, enemy.y / Tile.SIZE).getID() == Resources.FLOOR)
            this.enemies.add(enemy);
        else
            this.spawnEnemy(new Enemy(enemy, player), player);
    }
    public void spawnEnemy(Enemy enemy, Player4 player) {
        if (data.getTileAt(enemy.x / Tile.SIZE, enemy.y / Tile.SIZE).getID() == Resources.FLOOR)
            this.enemies.add(enemy);
        else
            this.spawnEnemy(new Enemy(enemy, player), player);
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
