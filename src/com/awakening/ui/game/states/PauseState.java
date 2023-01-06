package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.game.world.Tile;
import com.awakening.ui.game.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseState extends GameState {

    private final String paused = "P A U S E D";

    protected PauseState(GameStateManager manager) {
        super(manager);
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {

        // displays background for current state
        World.getRoom().render(graphics);

        int frameX = WindowManager.WIDTH/3 + 30;
        int frameY = WindowManager.HEIGHT/2;

        graphics.setColor(Color.BLACK);
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 50f));
        graphics.drawString(paused, frameX + 3, frameY + 3);
        graphics.setColor(Color.WHITE);
        graphics.setFont(graphics.getFont().deriveFont(Font.BOLD, 50f));
        graphics.drawString(paused, frameX, frameY);
    }

    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_P:
            case KeyEvent.VK_ESCAPE:
                gameStateManager.backToPreviousState();
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
