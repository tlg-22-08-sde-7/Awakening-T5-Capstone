package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_WoodCane extends Entity {

    public Obj_WoodCane(int posXinRoom, int posYinRoom) {
        super(Resources.WOOD_CANE, posXinRoom, posYinRoom);
        this.name = "Wood Cane";
        this.description = "(" + this.name + ")\nThis is very well built and easy to \nswing around.";
        this.attackPoints = 7;
    }
}
