package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_WoodCane extends Entity {

    public Obj_WoodCane(int posXinRoom, int posYinRoom) {
        super(Resources.WOOD_CANE, posXinRoom, posYinRoom);
        type = type_weapon;
        this.name = "Wood Cane";
        this.attackPoints = 7;
        this.description = "(" + this.name + ")" + " Damage: " + this.attackPoints +  "\nThis is very well built and easy to \nswing around.";
    }
}
