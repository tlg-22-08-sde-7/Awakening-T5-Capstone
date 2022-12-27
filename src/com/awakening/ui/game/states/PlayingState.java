package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.Sound;
import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.framework.utils.MathHelper;
import com.awakening.ui.game.entities.Enemy;
import com.awakening.ui.game.entities.Player;
import com.awakening.ui.game.world.Feature;
import com.awakening.ui.game.world.Tile;
import com.awakening.ui.game.world.World;
import com.awakening.ui.game.world.generator.LevelGenerator;
import com.awakening.ui.game.world.generator.RoomData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class PlayingState extends GameState {

    private LevelGenerator generator;
    private World world;
    private Player player;

    public PlayingState(GameStateManager manager) {
        super(manager);
        generator = new LevelGenerator();
        player = new Player();
        generateLevel();
    }

    @Override
    protected void loop() {
        this.player.move();
        this.world.changeRoom(player);
        // Room names set up
        String roomName = this.generator.getRoomName(this.world.getCurrentX(), this.world.getCurrentY());
        this.player.setPlayerLoc(roomName);
        this.collisions();

        this.world.getRoom().featureInteraction(player);


        this.player.regenerateHealth();
        this.playerAttacks();
    }

    @Override
    protected void render(Graphics graphics) {
        this.world.getRoom().render(graphics);
        this.player.render(graphics);
        graphics.drawImage(Resources.TEXTURES.get(Resources.ATTACK), this.player.getAttackBox().x, this.player.getAttackBox().y, this.player.getAttackBox().width, this.player.getAttackBox().height, null);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("arial", Font.PLAIN, 15));
        graphics.drawImage(Resources.TEXTURES.get(Resources.HEART), 0, 0, Tile.SIZE*2/3, Tile.SIZE*2/3, null);
        graphics.drawString(this.player.getHp()+"/"+this.player.getMaxHp(), Tile.SIZE*2/3+5, 20);
        graphics.drawImage(Resources.TEXTURES.get(Resources.ARMOR), 80, 0, Tile.SIZE*2/3, Tile.SIZE*2/3, null);
        graphics.drawString(this.player.getArmor()+"", Tile.SIZE*2/3+85, 20);
        graphics.drawImage(Resources.TEXTURES.get(Resources.GOLD), 160, 0, Tile.SIZE*2/3, Tile.SIZE*2/3, null);
        graphics.drawString(this.player.getGold()+"", Tile.SIZE*2/3+165, 20);
        // render player's current location/ rooms name
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 20f));
        graphics.drawString(this.player.getPlayerLoc(), 5, Tile.SIZE + 10);
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
                super.gameStateManager.stackState(new Options(gameStateManager));
                break;
        }
    }

    private void generateLevel() {
        this.generator.intializeGridForRooms();
        this.generator.generate();
        this.world = new World(this.generator.getRoomsData());

        // TODO: stairs to second floor
        // this.world.getRoomRandom().placeFeature(new Feature(Resources.STAIRS, this::generateLevel));

        //place items in the rooms per the requirement
        this.world.getRoom(0,2).placeFeature(new Feature(Resources.CHEST, this::givePlayerRandomLoot));
        this.world.getRoom(1,0).placeFeature(new Feature(Resources.CHEST, this::givePlayerRandomLoot));
        this.world.getRoom(1,1).placeFeature(new Feature(Resources.CHEST, this::givePlayerRandomLoot));
        this.world.getRoom(1,2).placeFeature(new Feature(Resources.CHEST, this::givePlayerRandomLoot));
        this.world.getRoom(1,3).placeFeature(new Feature(Resources.CHEST, this::givePlayerRandomLoot));
        this.world.getRoom(2,2).placeFeature(new Feature(Resources.CHEST, this::givePlayerRandomLoot));
        this.world.getRoom(3,2).placeFeature(new Feature(Resources.CHEST, this::givePlayerRandomLoot));

        //place enemies in the room per the requirement
        this.world.getRoom(0,2).spawnEnemy(new Enemy(Resources.ENEMY, 5, this.player));
        this.world.getRoom(1,0).spawnEnemy(new Enemy(Resources.ENEMY, 5, this.player));
        this.world.getRoom(1,1).spawnEnemy(new Enemy(Resources.ENEMY, 5, this.player));
        this.world.getRoom(1,2).spawnEnemy(new Enemy(Resources.ENEMY, 5, this.player));
        this.world.getRoom(1,3).spawnEnemy(new Enemy(Resources.ENEMY, 5, this.player));
        this.world.getRoom(2,2).spawnEnemy(new Enemy(Resources.ENEMY, 5, this.player));
        this.world.getRoom(3,2).spawnEnemy(new Enemy(Resources.ENEMY, 5, this.player));

        this.spawnPlayer();
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

                for(Enemy enemy : this.world.getRoom().getEnemies()) {
                    enemy.handleCollisionWith(roomIn.getTileAt(i, j));
                }
            }
        }
    }


    private void givePlayerRandomLoot() {
    
        switch(MathHelper.randomInt(3)) {
            case 0: this.player.addArmor(MathHelper.randomInt(3, 5)); break;
            case 1: this.player.giveGold(MathHelper.randomInt(3, 7)); break;
            case 2: this.player.instantHeal(MathHelper.randomInt(2, 5)); break;
        }
    }

    private void playerAttacks() {

        if (this.player.getHp() <=0 ){
            JFrame window= new JFrame();
            int resp = JOptionPane.showConfirmDialog( window, "You died. Restart the game?", "G  A  M  E  O  V  E  R", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                super.gameStateManager.stackState(new MainMenu(gameStateManager));
            } else {
                System.exit(0);
            }
        }

        this.player.decreaseTime();
        for(int i=0;i<this.world.getRoom().getEnemies().size();i++) {
            this.world.getRoom().getEnemies().get(i).move();

            if(this.world.getRoom().getEnemies().get(i).intersects(this.player)) {
                this.player.damage(5 -  5*this.player.getArmor()/100);
            }

            if(this.world.getRoom().getEnemies().get(i).intersects(this.player.getAttackBox())) {
                this.world.getRoom().getEnemies().get(i).damage(3, this.player.getFacing());
                if(this.world.getRoom().getEnemies().get(i).getHp() <= 0) {
                    this.world.getRoom().getEnemies().remove(i);
                    this.player.giveGold(MathHelper.randomInt(2, 5));
                }
            }
        }
    }

}
