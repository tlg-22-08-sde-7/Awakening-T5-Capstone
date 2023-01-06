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

    // Constructor
    public Map(GameStateManager manager) {
        super(manager);
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
    }

    // Keys to be used in the current State
    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_M:
            case KeyEvent.VK_ESCAPE:
                gameStateManager.backToPreviousState();
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
