package com.awakening.ui.game.entities;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.framework.utils.MathHelper;
import com.awakening.ui.game.world.Tile;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity {

    private static final long serialVersionUID = 1L;
    public static Entity currentWeapon = null;
    public static Entity currentItem;

    private static int hp;
    private int attackPoints;
    private int maxHp;
    private byte regenDelay;
    private static int armor;
    private static int gold;
    public static byte playerStandDown = Resources.PLAYER_1_STAND_DOWN;
    public static byte playerStandUp = Resources.PLAYER_1_STAND_UP;
    public static byte playerStandLeft = Resources.PLAYER_1_STAND_LEFT;
    public static byte playerStandRight = Resources.PLAYER_1_STAND_RIGHT;
    private byte attackTime;
    private byte damageTime;
    // Player's current location
    private String playerLoc;
    public static ArrayList<Entity> playerInventory = new ArrayList<>();

    public static final int inventorySize = 18;

    public Player() {
        super(playerStandDown, MathHelper.randomInt(2, 14), MathHelper.randomInt(2, 7));
        type = type_player;
        hp = 20;
        this.maxHp = 20;
        this.regenDelay = 0; // regenerate player's health every 120 milisec
        this.armor = 0;
        this.gold = 0;
        this.attackTime = 0;
        this.attackPoints = 3;
        this.damageTime = 0;
        this.playerLoc = "Basement";

    }

    public void replaceRandomly() {
        super.x = MathHelper.randomInt(2, 14)* Tile.SIZE;
        super.y = MathHelper.randomInt(2, 7)*Tile.SIZE;
    }

    public static int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void instantHeal(int amount) {
        this.hp += amount;
        if(this.hp > this.maxHp) this.hp = this.maxHp;
    }

    // Regenerate player's health every 120 miliseconds
    public void regenerateHealth() {
        if(this.hp < this.maxHp) this.regenDelay++;
        else this.regenDelay = 0;

        if(this.regenDelay == 120) {
            this.hp++;
            this.regenDelay = 0;
        }
    }

    public static int getArmor() {
        return armor;
    }

    public void addArmor(int amount) {
        this.armor += amount;
        if(this.armor > 75) this.armor = 75;
    }

    public static int getGold() {
        return gold;
    }

    public void giveGold(int amount) {
        this.gold += amount;
    }

    @Override
    public void move() {
        if(this.attackTime == 0) {
            super.move();
            switch(super.facing) {
                case NORTH: super.id = playerStandUp; break;
                case SOUTH: super.id = playerStandDown; break;
                case WEST: super.id = playerStandLeft; break;
                case EAST: super.id = playerStandRight; break;
            }
        }
    }

    public void decreaseTime() {
        if(this.attackTime > 0) this.attackTime--;
        if(this.damageTime > 0) this.damageTime--;
    }

    public void attack() {
        if(this.attackTime == 0) this.attackTime = 25;
    }

    public Rectangle getAttackBox() {
        if(this.attackTime <= 20 && this.attackTime >= 15) {
            switch(super.facing) {
                case NORTH:
                    return new Rectangle(super.x, super.y - super.height, super.width, super.height);
                case SOUTH:
                    return new Rectangle(super.x, super.y + super.height, super.width, super.height);
                case WEST:
                    return new Rectangle(super.x - super.width, super.y, super.width, super.height);
                case EAST:
                    return new Rectangle(super.x + super.width, super.y, super.width, super.height);
                default:
                    break;
            }
        }
        return new Rectangle(0, 0, 0, 0);
    }

    // Render player on screen
    @Override
    public void render(Graphics graphics) {
        if((up || down || left || right) && this.attackTime == 0) {
            super.animationDelay++;
            if(super.animationDelay == 70) {
                super.animationDelay = 0;
                super.animationFrame = (byte) (1 - super.animationFrame);
            }
        }
        graphics.drawImage(Resources.TEXTURES.get(id + animationFrame), super.x, super.y, super.width, super.height, null);
    }

    public void damage(int amount) {
        if(this.damageTime == 0) {
            this.hp -= amount;
            this.damageTime = 50;
        }
    }
    public String getPlayerLoc() {
        return playerLoc;
    }

    public void setPlayerLoc(String playerLoc) {
        this.playerLoc = playerLoc;
    }


    public static ArrayList<Entity> getPlayerInventory() {
        return playerInventory;
    }

    public static void setHp(int hp) {
        Player.hp = hp;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
}
