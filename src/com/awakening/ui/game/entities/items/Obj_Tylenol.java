package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;
import com.awakening.ui.game.entities.Player;

import static com.awakening.ui.game.states.PlayingState.addMessage;

public class Obj_Tylenol extends Entity {

    public Obj_Tylenol(int posXinRoom, int posYinRoom) {
        super(Resources.TYLENOL, posXinRoom, posYinRoom);
        type = type_heal;
        this.name = "Tylenol";
        this.healPoints = 10;
        this.description = "(" + this.name + ")" + " HP: " + this.healPoints +  "\n\nThis will help me feel better if I get \ninjured.";
    }
    public void use(Entity entity) {
        addMessage("You use the " + getName() + "!\nYour health has recovered " + getHealPoints() + "!");
        Player.setHp(Player.getHp() + getHealPoints());
        if (Player.getHp() > 20) {
            Player.setHp(20);
        }
    }
}
