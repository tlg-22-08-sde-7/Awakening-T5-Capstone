package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;
import com.awakening.ui.game.entities.Player;
import com.awakening.ui.game.states.PlayingState;

import static com.awakening.ui.game.states.PlayingState.addMessage;

public class Obj_Batteries extends Entity {

    public Obj_Batteries(int posXinRoom, int posYinRoom) {
        super(Resources.BATTERIES, posXinRoom, posYinRoom);
        type = type_charge;
        chargePoints = 50;
        this.name = "Batteries";
        this.description = "(" + this.name + ")\nOld batteries... \nHope they have some juice left.";
    }
    public void use(Entity entity) {
        addMessage("You use the " + getName() + "!\nYour camera is charged by " + getHealPoints() + "!");
        Player.setHp(Player.getHp() + getHealPoints());
        if (PlayingState.player.getAttackPoints() > 20) {
            Player.setHp(20);
        }
    }
}
