package com.awakening.ui.game.entities;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.framework.utils.MathHelper;
import com.awakening.ui.game.world.Tile;

import java.awt.*;

public class Player  extends Entity{

    private String name;
    private String description;
    private int health;
    private int attack;

    public Player() {
       super(Resources.PLAYER, MathHelper.randomInt(2, 14), MathHelper.randomInt(2, 7));
       health = 100;
       attack = 5;
    }

    public void replaceRandomly() {
        super.x = MathHelper.randomInt(2, 14)* Tile.SIZE;
        super.y = MathHelper.randomInt(2, 7)*Tile.SIZE;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void move (){
        super.move();
        switch(super.facing) {
            case NORTH: super.id = Resources.PLAYER_BACK; break;
            case SOUTH: super.id = Resources.PLAYER; break;
            case WEST: super.id = Resources.PLAYER_LEFT; break;
            case EAST: super.id = Resources.PLAYER_RIGHT; break;
        }
    }

    @Override
    public void render(Graphics graphics){
        if((up || down || left || right) ){ //&& this.attackTime == 0) {
            super.animationDelay++;
            if(super.animationDelay == 70) {
                super.animationDelay = 0;
                super.animationFrame = (byte) (1 - super.animationFrame);
            }
        }
        graphics.drawImage(Resources.TEXTURES.get(id + animationFrame), super.x, super.y, super.width, super.height, null);
    }



}
