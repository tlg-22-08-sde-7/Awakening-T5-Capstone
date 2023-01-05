package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Player;
import com.awakening.ui.game.world.World;
import com.awakening.ui.game.world.generator.LevelGenerator;
import com.awakening.ui.game.world.generator.RoomData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class NoKey extends GameState {

    // Selections to be made in the state
    private int selected;
    private String[] noKeyMenu;
    private static final String STATEMENT = "You need the right key!";
    private static final String BACK = "Back";

    // Constructor
    protected NoKey(GameStateManager manager) {
        super(manager);
        this.noKeyMenu = new String[]{BACK};
        this.selected = 0;
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {

        // Render the current room in the background
        World.getRoom().render(graphics);

        // Window
        int frameX = WindowManager.WIDTH/4 -50;
        int frameY = WindowManager.HEIGHT/3 -30;
        int frameWidth = WindowManager.WIDTH/2;
        int frameHeight = WindowManager.HEIGHT/2 - 100;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        // Border of the Window
        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        // Font for the Window
        graphics.setFont(graphics.getFont().deriveFont(40F));
        graphics.setColor(Color.WHITE);
        graphics.drawString(STATEMENT, frameX + 40, frameY + 50);
        for(int i = 0; i < this.noKeyMenu.length; i++) {
            if (i == this.selected) {
                graphics.setColor(Color.GREEN);
            } else {
                graphics.setColor(Color.WHITE);
            }
            if (i == 0) { // BACK
                graphics.drawString(this.noKeyMenu[i], frameX + 100, frameY*2);
            }
        }
    }

    // Keys to be used in the current State
    @Override
    protected void keyPressed(int keyCode) {
        switch(this.noKeyMenu[selected]) {
            case BACK:
                gameStateManager.backToPreviousState();
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
