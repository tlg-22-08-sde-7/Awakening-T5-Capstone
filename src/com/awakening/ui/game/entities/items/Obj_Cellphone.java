package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Cellphone extends Entity {

    public Obj_Cellphone(int posXinRoom, int posYinRoom) {
        super(Resources.CELLPHONE, posXinRoom, posYinRoom);
        type = type_weapon;
        this.name = "Cellphone";
        this.description = "(" + this.name + ")\nDead phone, needs charge.";
        attackPoints = 5;
    }
}
