package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;
import com.awakening.ui.game.entities.Player;

import static com.awakening.ui.game.states.PlayingState.addMessage;

public class Obj_Bandages extends Entity {

    public Obj_Bandages(int posXinRoom, int posYinRoom) {
        super(Resources.BANDAGES, posXinRoom, posYinRoom);
        type = type_heal;
        this.name = "Bandages";
        this.healPoints = 10;
        this.description = "(" + this.name + ")" + " HP: " + this.healPoints +  "\nOld Bandages... \nHope I won't have to use these.";
    }

    public void use(Entity entity) {
        addMessage("You use the " + getName() + "!\nYour health has recovered " + getHealPoints() + "!");
        Player.setHp(Player.getHp() + getHealPoints());
        if (Player.getHp() > 20) {
            Player.setHp(20);
        }
    }
}
