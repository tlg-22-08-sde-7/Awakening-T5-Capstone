package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Key extends Entity {

    public Obj_Key(int posXinRoom, int posYinRoom) {
        super(Resources.KEY, posXinRoom, posYinRoom);
        this.name = "Key";
        this.description = "(" + this.name + ")\nA key for opening something";
    }
}
