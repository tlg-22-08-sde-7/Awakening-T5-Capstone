package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Journal extends Entity {

    public Obj_Journal(int posXinRoom, int posYinRoom) {
        super(Resources.JOURNAL, posXinRoom, posYinRoom);
        this.name = "Journal";
        this.description = "(" + this.name + ")\nI read it and remember going to\n the hospital to confront the doctor... \nAbout what though?";
    }
}
