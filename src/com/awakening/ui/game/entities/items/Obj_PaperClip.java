package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_PaperClip extends Entity {

    public Obj_PaperClip(int posXinRoom, int posYinRoom) {
        super(Resources.PAPER_CLIP, posXinRoom, posYinRoom);
        this.name = "Paper Clip";
        this.description = "(" + this.name + ")\nPaper clip. All I need to open simple locks... \nHow do I know this?";
    }
}
