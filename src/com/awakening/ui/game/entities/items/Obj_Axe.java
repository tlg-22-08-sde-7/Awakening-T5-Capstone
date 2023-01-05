package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Axe extends Entity {

    public Obj_Axe(int posXinRoom, int posYinRoom) {
        super(Resources.AXE, posXinRoom, posYinRoom);
        type = type_weapon;
        this.name = "Axe";
        this.attackPoints = 10;
        this.description = "(" + this.name + ")" + " Damage: " + this.attackPoints +  "\nThe blade is dull, \nbut it can generate a lot of force.";

    }

}
