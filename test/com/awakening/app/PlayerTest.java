package com.awakening.app;

import com.awakening.ui.game.entities.Player;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class PlayerTest {

    private static Player player;

    @Before
    public void setUp(){
        player = new Player();
    }

    @Test
    public void getHealth(){
        Assert.assertEquals(30, player.getMaxHp());
    }

    @Test
    public void getArmorValue(){
        Assert.assertEquals(0, player.getArmor());
    }
}
