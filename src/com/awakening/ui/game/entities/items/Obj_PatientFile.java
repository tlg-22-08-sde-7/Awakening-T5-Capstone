package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;

public class Obj_PatientFile extends Entity {

    public Obj_PatientFile(int posXinRoom, int posYinRoom) {
        super(Resources.PATIENT_FILE, posXinRoom, posYinRoom);
        this.name = "Patient File";
        this.description = "(" + this.name + ")\nYou read the file... Patient is Jessica Smith. \nHeld for mental health examination. Room 103. Door code is ####. \nThe rest is illegible. Maybe her room will have an answer?";
    }
}
