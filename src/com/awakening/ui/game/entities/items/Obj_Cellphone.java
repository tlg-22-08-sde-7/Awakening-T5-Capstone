package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Cellphone extends Entity {

    public Obj_Cellphone(int posXinRoom, int posYinRoom) {
        super(Resources.CELLPHONE, posXinRoom, posYinRoom);
        type = type_weapon;
        this.name = "Cellphone";
        this.attackPoints = 5;
        this.description = "(" + this.name + ")" + " Damage: " + this.attackPoints +  "\nDead phone, needs charge.";
    }
}
