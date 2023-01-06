package com.awakening.ui.game.entities.items;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;
import com.awakening.ui.game.entities.Player;

import static com.awakening.ui.game.states.PlayingState.addMessage;

public class Obj_FirstAidKit extends Entity {

    public Obj_FirstAidKit(int posXinRoom, int posYinRoom) {
        super(Resources.FIRST_AID_KIT, posXinRoom, posYinRoom);
        type = type_heal;
        this.name = "First Aid Kit";
        this.healPoints = 15;
        this.description = "(" + this.name + ")" + " HP: " + this.healPoints +  "\nThis has everything I need \nto ensure I can fix up any wounds.";
    }
    public void use(Entity entity) {
        addMessage("You use the " + getName() + "!\nYour health has recovered " + getHealPoints() + "!");
        Player.setHp(Player.getHp() + getHealPoints());
        if (Player.getHp() > 20) {
            Player.setHp(20);
        }
    }
}
