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

public class Settings extends GameState {

    // selections for the Settings state
    private String[] settingsMenu;
    private static final String MUSIC = "Music";
    private static final String SE = "SE";
    private static final String BACK = "Back";
    private int selected;

    // Constructor
    public Settings (GameStateManager manager) {
        super(manager);
        this.settingsMenu = new String[]{BACK, MUSIC, SE};
        this.selected = 0;
    }


    @Override
    protected void loop() {

    }

    @Override
    public void render(Graphics graphics) {

        // Background for the current state
        try {
            BufferedImage image = ImageIO.read(new File("resources/pictures/titlescreen.jpg"));
            graphics.drawImage(image, 0, 0, WindowManager.WIDTH, WindowManager.HEIGHT,  null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Window
        int frameX = WindowManager.WIDTH/4;
        int frameY = WindowManager.HEIGHT/6;
        int frameWidth = WindowManager.WIDTH/2;
        int frameHeight = WindowManager.HEIGHT/3;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        // Border for the window
        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        // Font for the window
        graphics.setFont(graphics.getFont().deriveFont(32F));
        for(int i = 0; i < this.settingsMenu.length; i++) {
            if(i == this.selected){
                graphics.setColor(Color.GREEN);
            }
            else{
                graphics.setColor(Color.WHITE);
            }
            if(i == 0) { // Return
                graphics.drawString(this.settingsMenu[i], frameX + 50, frameY + 50);
            }
            else if(i == 1) { // Music
                graphics.drawString(this.settingsMenu[i], frameX + 50, frameY + 100);
            }
            else if(i == 2) { // SE
                graphics.drawString(this.settingsMenu[i], frameX + 50, frameY + 150);
                graphics.setColor(Color.WHITE);
            }
        }
        // Music Bar
        int textX = frameX + 400;
        int textY = frameY + 70;
        graphics.drawRect(textX, textY, 120, 30);
        int volumeWidth = 24 * PlayingState.music.volumeScale;
        graphics.fillRect(textX, textY, volumeWidth, 30);

        //SE Bar
        textY = frameY + 123;
        graphics.drawRect(textX, textY, 120, 30);
        volumeWidth = 24 * PlayingState.se.volumeScale;
        graphics.fillRect(textX, textY, volumeWidth, 30);

    }

    // Keys to be used in the current State
    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (this.selected > 0) {
                    this.selected--;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (this.selected < this.settingsMenu.length - 1) {
                    this.selected++;
                } else {
                    this.selected = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (this.selected == 1 && PlayingState.music.volumeScale < 5) {
                    PlayingState.music.volumeScale++;
                    PlayingState.music.checkVolume();
                    PlayingState.se.playSE(2);
                    break;
                } else if (this.selected == 2 && PlayingState.se.volumeScale < 5) {
                    PlayingState.se.volumeScale++;
                    PlayingState.se.playSE(2);
                    break;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (this.selected == 1 && PlayingState.music.volumeScale > 0) {
                    PlayingState.music.volumeScale--;
                    PlayingState.music.checkVolume();
                    PlayingState.se.playSE(2);
                    break;
                } else if (this.selected == 2 && PlayingState.se.volumeScale > 0) {
                    PlayingState.se.volumeScale--;
                    PlayingState.se.playSE(2);
                    break;
                }
                break;
            case KeyEvent.VK_ENTER:
                switch(this.settingsMenu[selected]) {
                    case BACK:
                        this.gameStateManager.backToPreviousState();
                        break;
                    case MUSIC:
                    case SE:
                        break;
                }
                break;

        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
