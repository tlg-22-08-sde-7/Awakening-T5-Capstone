package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.WindowManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Instructions extends GameState {

    private int selected;
    private String[] instructionButton;
    private static final String STATEMENT = "Instructions";
    private static final String BACK = "Back";

    protected Instructions(GameStateManager manager) {
        super(manager);
        this.instructionButton = new String[]{BACK};
        this.selected = 0;
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {
        try {
            BufferedImage image = ImageIO.read(new File("resources/pictures/titlescreen.jpg"));
            graphics.drawImage(image, 0, 0, WindowManager.WIDTH, WindowManager.HEIGHT,  null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int frameX = 50;
        int frameY = 50;
        int frameWidth = WindowManager.WIDTH-100;
        int frameHeight = WindowManager.HEIGHT-100;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        graphics.setFont(graphics.getFont().deriveFont(30F));
        graphics.setColor(Color.WHITE);
        graphics.drawString(STATEMENT, frameX + 40, frameY + 50);
        for(int i = 0; i < this.instructionButton.length; i++) {
            if (i == this.selected) {
                graphics.setColor(Color.GREEN);
            } else {
                graphics.setColor(Color.WHITE);
            }
            if (i == 0) { // BACK
                graphics.drawString(this.instructionButton[i], frameX + 100, frameY + 100);
            }
        }
    }

    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ENTER:
                gameStateManager.backToPreviousState();
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}