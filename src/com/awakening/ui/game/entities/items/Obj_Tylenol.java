package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Tylenol extends Entity {

    public Obj_Tylenol(int posXinRoom, int posYinRoom) {
        super(Resources.TYLENOL, posXinRoom, posYinRoom);
        this.name = "Bandages";
        this.description = "(" + this.name + ")\nThis will help me feel better if I get \ninjured.";
        this.healPoints = 20;
    }
}
