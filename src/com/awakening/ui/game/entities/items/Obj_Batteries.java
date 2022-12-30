package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Batteries extends Entity {

    public Obj_Batteries(int posXinRoom, int posYinRoom) {
        super(Resources.BATTERIES, posXinRoom, posYinRoom);
        this.name = "Batteries";
        this.description = "(" + this.name + ")\nOld batteries... \nHope they have some juice left.";
    }
}
