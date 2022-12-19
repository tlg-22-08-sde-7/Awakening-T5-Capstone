package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.game.entities.Player;
import com.awakening.ui.game.world.generator.LevelGenerator;

import java.awt.*;

class PlayingState extends GameState {

    private LevelGenerator generator;
    private Player player;

    public PlayingState(GameStateManager manager) {
        super(manager);
        generator = new LevelGenerator();
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {

    }

    @Override
    protected void keyPressed(int keyCode) {

    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
