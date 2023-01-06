package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.game.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Map extends GameState {
    private int selected;
    // back selection
    private String[] backButton;
    private static final String BACK = "Back";

    // Constructor
    public Map(GameStateManager manager) {
        super(manager);
        this.backButton = new String[]{BACK};
        this.selected = 0;
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {

        // Background image for the map
        World.getRoom().render(graphics);

        // window for the map
        int frameX = WindowManager.WIDTH / 8;
        int frameY = WindowManager.HEIGHT / 8;

        // displaying the map image in the frame
        try {
            BufferedImage image = ImageIO.read(new File("resources/pictures/map.png"));
            graphics.drawImage(image, frameX - 25, frameY + 25, 944, 450, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Font for the Back selection
        for(int i = 0; i < this.backButton.length; i++) {
            if (i == this.selected) {
                graphics.setColor(Color.GREEN);
            } else {
                graphics.setColor(Color.WHITE);
            }
            if (i == 0) { // BACK
                graphics.drawString(this.backButton[i], frameX + 10, frameY + 50);
            }
        }
    }


    // Keys to be used in the current State
    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_M:
            case KeyEvent.VK_ENTER:
                gameStateManager.backToPreviousState();
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
