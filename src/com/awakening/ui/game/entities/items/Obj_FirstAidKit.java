package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_FirstAidKit extends Entity {

    public Obj_FirstAidKit(int posXinRoom, int posYinRoom) {
        super(Resources.FIRST_AID_KIT, posXinRoom, posYinRoom);
        this.name = "First Aid Kit";
        this.description = "(" + this.name + ")\nThis has everything I need \nto ensure I can fix up any wounds.";
        this.healPoints = 30;
    }
}
