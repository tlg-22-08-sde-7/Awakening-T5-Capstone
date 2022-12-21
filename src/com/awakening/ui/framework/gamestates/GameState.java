package com.awakening.ui.framework.gamestates;

import java.awt.*;

public abstract class GameState {
    protected GameStateManager gameStateManager;

    protected GameState(GameStateManager manager) {
        this.gameStateManager = manager;
    }

    protected abstract void loop();

    protected abstract void render(Graphics graphics);

    protected abstract void keyPressed(int keyCode);

    protected abstract void keyReleased(int keyCode);
}
