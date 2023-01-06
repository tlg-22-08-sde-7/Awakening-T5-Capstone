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

    // String for the Paragraph and the Back selection
    private int selected;
    private String[] instructionButton;
    private static final String INSTRUCTIONS_PARAGRAPH =
            "Choose between 4 different characters and try to survive as long as possible.\n" +
                    "          * You must navigate through the psych ward utilizing the\n               'W A S D' Keys or the arrow keys.\n"+
                    "          * As you navigate you will encounter ghosts in each room,\n               each room has items that you will need in order to\n" +
                    "               defeat the ghost and gain access to other rooms.\n" +
                    "          * To attack you can use the Space Bar and/or the \"Q\" key.\n" +
                    "          * You can press the \"M\" key to view the map, \"P\" to pause.\n" +
                    "          * You can also press the \"I\" key to view your inventory.\n" +
                    "          * Hit \"ENTER\" in the inventory to equip weapons or use items to heal\n" +
                    "          * The \"ESC\" key will bring up the pause menu, where you can change:\n               the music volume, SFX volume, restart, or quit the game \n" +
                    "          * The \"ENTER\" key will open doors if you have the right key.\n" +
                    "          * In order to win you must grab the Master Key, Patient file,\n               Journal and find your way back to the Front Desk!";;
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

        // Render the titlescreen image in the background
        try {
            BufferedImage image = ImageIO.read(new File("resources/pictures/titlescreen.jpg"));
            graphics.drawImage(image, 0, 0, WindowManager.WIDTH, WindowManager.HEIGHT,  null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set the size, location, and color of the window
        int frameX = 50;
        int frameY = 50;
        int frameWidth = WindowManager.WIDTH-100;
        int frameHeight = WindowManager.HEIGHT-100;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        // set the border of the current window
        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        // Font for the Instructions
        graphics.setFont(graphics.getFont().deriveFont(25F));
        graphics.setColor(Color.WHITE);
        int textY = frameY + 100;
        for (String line : INSTRUCTIONS_PARAGRAPH.split("\n")) { // split the lines after each "/n"
            graphics.drawString(line, frameX + 35, textY);
            textY += 30;

        }

        // Font for the Back selection
        for(int i = 0; i < this.instructionButton.length; i++) {
            if (i == this.selected) {
                graphics.setColor(Color.GREEN);
            } else {
                graphics.setColor(Color.WHITE);
            }
            if (i == 0) { // BACK
                graphics.drawString(this.instructionButton[i], frameX + 50, frameY + 50);
            }
        }
    }

    // Keys to be used in the current State
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