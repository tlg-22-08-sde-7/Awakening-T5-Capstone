package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_PressPass extends Entity {

    public Obj_PressPass(int posXinRoom, int posYinRoom) {
        super(Resources.PRESS_PASS, posXinRoom, posYinRoom);
        this.name = "Press Pass";
        this.description = "(" + this.name + ")\nThis belonged to someone. This is me? \nI'm this person? I need to get out of here.";
    }
}
