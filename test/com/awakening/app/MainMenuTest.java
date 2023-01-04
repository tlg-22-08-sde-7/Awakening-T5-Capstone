package com.awakening.app;

import com.awakening.ui.game.states.MainMenu;
import org.junit.Assert;
import org.junit.Test;

public class MainMenuTest{




    @Test
    public void getInstructions(){
        String instructions = "Choose between 4 different characters and try to survive as long as possible.\n " +
                "You must navigate through the psych ward utilizing the 'W A S D' Keys or the arrow keys.\n"+
                "As you navigate you will encounter ghosts in each room, each room has items that you will need in\n" +
                "order to defeat the ghost and gain access to other rooms.\n" +
                "To attack you can use the Space Bar and/or the \"Q\" key.\n" +
                "You can press the \"M\" key to view the map.\n" +
                "You can also press the \"I\" key to view your inventory.\n" +
                "The \"ESC\" key will bring up the pause menu where you can change the music volume, SFX volume, restart, or quit the game \n" +
                "In order to win you must grab the Master Key, Patient file, Journal and Find your way back to the Front Desk!";

        Assert.assertEquals(instructions, MainMenu.INSTRUCTIONS);
    }
}