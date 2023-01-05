package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_FireExtinguisher extends Entity {

    public Obj_FireExtinguisher(int posXinRoom, int posYinRoom) {
        super(Resources.FIRE_EXTINGUISHER, posXinRoom, posYinRoom);
        type = type_weapon;
        this.name = "Fire Extinguisher";
        this.attackPoints = 10;
        this.description = "(" + this.name + ")" + " Damage: " + this.attackPoints +  "\nThis is heavy, but also has a \ncharge so it might come in handy.";
    }
}
