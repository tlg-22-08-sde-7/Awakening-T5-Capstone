package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Axe extends Entity {

    public Obj_Axe(int posXinRoom, int posYinRoom) {
        super(Resources.AXE, posXinRoom, posYinRoom);
        this.name = "Axe";
        this.description = "(" + this.name + ")\nThe blade is dull, \nbut it can generate a lot of force.";
        this.attackPoints = 10;
    }
}
