package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Bandages extends Entity {

    public Obj_Bandages(int posXinRoom, int posYinRoom) {
        super(Resources.BANDAGES, posXinRoom, posYinRoom);
        this.name = "Bandages";
        this.description = "(" + this.name + ")\nOld Bandages... \nHope I won't have to use these.";
        this.healPoints = 20;
    }
}
