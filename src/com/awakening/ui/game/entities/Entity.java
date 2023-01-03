package com.awakening.ui.game.entities;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.framework.utils.MathHelper;
import com.awakening.ui.game.world.Tile;

import java.awt.*;

import static com.awakening.ui.game.states.PlayingState.addMessage;

public class Entity extends Rectangle {
    private static final long serialVersionUID = 1L; // serialization for threading
    protected String name;
    protected String description;
    protected int healPoints;
    protected static int attackPoints;
    protected static int chargePoints;
    public static int type;
    public static final int type_player = 0;
    public static final int type_npc = 1;
    public static final int type_ghost = 2;
    public static final int type_weapon = 3;
    public static final int type_heal = 4;
    public static final int type_charge = 5;

    protected byte id;

    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;

    protected int speed; // How fast we want player or enemie to move

    protected MathHelper.Direction facing;

    protected byte animationFrame;
    protected byte animationDelay;

    public Entity(byte id, int posXinRoom, int posYinRoom) {
        super(posXinRoom * Tile.SIZE, posYinRoom * Tile.SIZE, Tile.SIZE, Tile.SIZE);
        this.id = id;
        up = false;
        down = false;
        left = false;
        right = false;
        speed = 7;
        facing = MathHelper.Direction.SOUTH;
    }

    public void move() {
        if (up) {
            super.y -= speed;
            facing = MathHelper.Direction.NORTH;
        }
        if (down) {
            super.y += speed;
            facing = MathHelper.Direction.SOUTH;
        }
        if (left) {
            super.x -= speed;
            facing = MathHelper.Direction.WEST;
        }
        if (right) {
            super.x += speed;
            facing = MathHelper.Direction.EAST;
        }
    }

    public void render(Graphics graphics) {
        if (up || down || left || right) {
            this.animationDelay++;
            if (this.animationDelay == 70) {
                this.animationDelay = 0;
                this.animationFrame = (byte) (1 - this.animationFrame);
            }
        }
        graphics.drawImage(Resources.TEXTURES.get(id + animationFrame), super.x, super.y, super.width, super.height, null);
    }

    public void handleCollisionWith(Tile tile) {
        Rectangle intersection = this.intersection(tile);
        if (intersection.isEmpty() || !tile.isWall())
            return;

        if (intersection.width > intersection.height) {
            if (this.y < tile.y)
                this.y = tile.y - this.height;
            else
                this.y = tile.y + this.height;
        } else {
            if (this.x < tile.x)
                this.x = tile.x - this.width;
            else
                this.x = tile.x + this.width;
        }
    }

    public void use(Entity entity) {
    }

    public byte getID() {
        return id;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setCenterX(int x) {
        super.x = x - super.width / 2;
    }

    public void setCenterY(int y) {
        super.y = y - super.height / 2;
    }

    public MathHelper.Direction getFacing() {
        return facing;
    }

    public int getHealPoints() {
        return healPoints;
    }

    public void setHealPoints(int healPoints) {
        this.healPoints = healPoints;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static int getAttackPoints() {
        return attackPoints;
    }

    public static void setAttackPoints(int attackPoints) {
        Entity.attackPoints = attackPoints;
    }

    public static int getType() {
        return type;
    }

    public static void setType(int type) {
        Entity.type = type;
    }

}
