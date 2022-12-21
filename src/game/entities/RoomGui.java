package game.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import framework.gamestates.gui.resources.Resources;
import game.entities.generator.RoomData;

public class RoomGui {
    private RoomData data;
    private ArrayList<Feature> features;
//    private ArrayList<Enemy> enemies;

    public RoomGui(RoomData data) {
        this.data = data;
        this.features = new ArrayList<>();
//        this.enemies = new ArrayList<>();
    }

    public RoomData getData() {
        return data;
    }

    public void placeFeature(Feature feature) {
        if(data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.EARTH ||
                data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.GRASS ||
                data.getTileAt(feature.x / Tile.SIZE, feature.y / Tile.SIZE).getID() == Resources.TILE)
            this.features.add(feature);
        else
            this.placeFeature(new Feature(feature));
    }

    public void featureInteraction(Player player) {
        for(int i=0;i<this.features.size();i++) {
            if(this.features.get(i).intersects(player))
                this.features.remove(i);
        }
    }

//    public ArrayList<Enemy> getEnemies() {
//        return enemies;
//    }

//    public void spawnEnemy(Enemy enemy) {
//        if(data.getTileAt(enemy.x / Tile.SIZE, enemy.y / Tile.SIZE).getID() == Resources.FLOOR)
//            this.enemies.add(enemy);
//        else
//            this.spawnEnemy(new Enemy(enemy));
//    }

    public void render(Graphics graphics) {
        this.data.render(graphics);
        for(Feature feature : features) {
            feature.render(graphics);
        }
//        for(Enemy enemy : enemies) {
//            enemy.render(graphics);
//        }
    }
}