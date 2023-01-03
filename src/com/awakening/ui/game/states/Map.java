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
    public Map(GameStateManager manager) {
        super(manager);
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {
        World.getRoom().render(graphics);
        int frameX = WindowManager.WIDTH / 8;
        int frameY = WindowManager.HEIGHT / 8;
        try {
            BufferedImage image = ImageIO.read(new File("resources/pictures/map.png"));
            graphics.drawImage(image, frameX - 25, frameY + 25, 944, 450, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_M:
                gameStateManager.backToPreviousState();
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_M:
                break;
        }
    }
}
