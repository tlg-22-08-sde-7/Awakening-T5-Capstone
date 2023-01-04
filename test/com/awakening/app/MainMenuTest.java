package com.awakening.app;

import com.awakening.ui.game.states.MainMenu;
import org.junit.Assert;
import org.junit.Test;

public class MainMenuTest{




    @Test
    public void getInstructions(){
        String instructions = "You must navigate through the psych ward utilizing the 'W A S D' Keys or the arrow keys.\n"+
                "As you navigate you will encounter ghosts in each room, each room has items that you will need in\n" +
                "order to defeat the ghost and gain access to other rooms.\n" +
                "To attack you can use the Space Bar and/or the \"Q\" key.\n" +
                "In order to win you must grab the master key and ESCAPE!";

        Assert.assertEquals(instructions, MainMenu.INSTRUCTIONS);
    }
}