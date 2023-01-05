package com.awakening.ui.game.entities;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.framework.utils.MathHelper;

public class Enemy extends Entity{
    private static final long serialVersionUID = 1L;

    private Player target;

    private int hp;

    public Enemy(byte id, int health, Player target) {
        super(id, MathHelper.randomInt(2, 14), MathHelper.randomInt(2, 7));
        type = type_ghost;
        this.target = target;
        this.hp = health;
        // Main Ghost has speed of 3 and all other ghosts have speed of 2
        super.speed = 2;
        if (id == Resources.GHOST_MAIN1){
            super.speed = 3;
        }
    }

    public Enemy(Enemy copy) {
        this(copy.getID(), copy.hp, copy.target);
    }

    @Override
    public void move() {
        super.move();
        float angCoeff = ((float) this.target.y - (float) super.y) / ((float) this.target.x - (float) super.x);
        if(angCoeff < 1 && angCoeff > -1) {
            if(this.target.x < super.x) {
                super.up = false;
                super.down = false;
                super.left = true;
                super.right = false;
            } else {
                super.up = false;
                super.down = false;
                super.left = false;
                super.right = true;
            }
        }
        else if(angCoeff > 1 || angCoeff < -1) {
            if(this.target.y < super.y) {
                super.up = true;
                super.down = false;
                super.left = false;
                super.right = false;
            } else {
                super.up = false;
                super.down = true;
                super.left = false;
                super.right = false;
            }
        }
        else {
            if(this.target.x < super.x) {
                super.left = true;
                super.right = false;
            } else {
                super.left = false;
                super.right = true;
            }
        }
    }

    @Override
    public void setUp(boolean up) {
    }

    @Override
    public void setDown(boolean down) {
    }

    @Override
    public void setLeft(boolean left) {
    }

    @Override
    public void setRight(boolean right) {
    }

    public int getHp() {
        return hp;
    }

    public void damage(int amount, MathHelper.Direction knockback) {
        this.hp -= amount;
        super.x += knockback.dirX * 90;
        super.y += knockback.dirY * 90;
    }
}
