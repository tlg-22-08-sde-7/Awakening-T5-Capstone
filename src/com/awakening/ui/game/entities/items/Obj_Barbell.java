package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_Barbell extends Entity {

    public Obj_Barbell(int posXinRoom, int posYinRoom) {
        super(Resources.BARBELL, posXinRoom, posYinRoom);
        this.name = "Barbell";
        this.description = "(" + this.name + ")\nIt's a good thing I am pretty strong. \nI could probably throw this at something.";
        this.attackPoints = 5;
    }
}
