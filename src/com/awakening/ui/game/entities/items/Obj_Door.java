package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Door extends Entity {
    private boolean isLocked;

    public Obj_Door(int posXinRoom, int posYinRoom) {
        super(Resources.DOOR, posXinRoom, posYinRoom);
        this.name = "Door";
        this.description = "(" + this.name + ")\nLocked. Would you like to open it?";
        isLocked = true;
    }
}
