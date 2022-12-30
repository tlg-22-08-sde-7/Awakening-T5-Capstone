package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Camera extends Entity {

    public Obj_Camera(int posXinRoom, int posYinRoom) {
        super(Resources.CAMERA, posXinRoom, posYinRoom);
        this.name = "Camera";
        this.description = "(" + this.name + ")\nMy camera. No pictures on it. \nI use its light to see around me.";
        this.attackPoints = 5;
    }
}
