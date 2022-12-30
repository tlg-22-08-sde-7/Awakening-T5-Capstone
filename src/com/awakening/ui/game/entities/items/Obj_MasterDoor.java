package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_MasterDoor extends Entity {

    public Obj_MasterDoor( int posXinRoom, int posYinRoom) {
        super(Resources.MASTER_DOOR, posXinRoom, posYinRoom);
        this.name = "Master Door";
        this.description = "(" + this.name + ")\nLocked. Would you like to open it?";
    }
}
