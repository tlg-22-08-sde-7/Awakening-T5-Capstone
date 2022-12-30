package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_MasterKey extends Entity {

    public Obj_MasterKey(int posXinRoom, int posYinRoom) {
        super(Resources.MASTER_KEY, posXinRoom, posYinRoom);
        this.name = "Master Key";
        this.description = "(" + this.name + ")\nThis says Master on it. I think it will \nopen up all of the rooms in this place....";
    }
}
