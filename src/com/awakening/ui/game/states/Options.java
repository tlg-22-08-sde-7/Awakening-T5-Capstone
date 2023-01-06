package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.Sound;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.game.entities.Player;
import com.awakening.ui.game.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Options extends GameState {
    private String[] optionsMenu;
    private static final String CONTINUE_GAME = "Back To The Game";
    private static final String QUIT_GAME = "Quit";
    private static final String SHOW_INSTRUCTIONS = "Instructions";
    private static final String MUSIC = "Music";
    private static final String SE = "SE";
    private static final String RESTART_GAME = "Restart Game";
    private static final String MAP = "Map";
    private int selected;

    public Options(GameStateManager manager) {
        super(manager);
        this.optionsMenu = new String[]{CONTINUE_GAME, SHOW_INSTRUCTIONS,MAP, MUSIC, SE , RESTART_GAME, QUIT_GAME};
        this.selected = 0;
    }


    @Override
    protected void loop() {

    }

    @Override
    public void render(Graphics graphics) {

        // Background of the current state
        World.getRoom().render(graphics);

        // Window to be displayed
        int frameX = WindowManager.WIDTH/4;
        int frameY = WindowManager.HEIGHT/6;
        int frameWidth = WindowManager.WIDTH/2;
        int frameHeight = WindowManager.HEIGHT/2+50;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        // Border of the window
        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        // Font for the window
        graphics.setFont(graphics.getFont().deriveFont(32F));
        for(int i = 0; i < this.optionsMenu.length;i++) {
            if(i == this.selected){
                graphics.setColor(Color.GREEN);
            }
            else{
                graphics.setColor(Color.WHITE);
            }
            if(i == 0) { // continue
                graphics.drawString(this.optionsMenu[i], frameX + 50, frameY + 50);
            }
            else if(i == 1){ // show instructions
                graphics.drawString(this.optionsMenu[i], frameX + 50, frameY + 100);
            }
            else if (i == 2){ // map
                graphics.drawString(this.optionsMenu[i], frameX + 50, frameY + 150);
            }
            else if(i == 3) { // Music
                graphics.drawString(this.optionsMenu[i], frameX + 50, frameY + 200);
            }
            else if(i == 4) { // SE
                graphics.drawString(this.optionsMenu[i], frameX + 50, frameY + 250);
            }
            else if (i == 5) {  // restart game
                graphics.drawString(this.optionsMenu[i], frameX + 50, frameY + 300);
            } else if (i == 6) {  // quit game
                graphics.drawString(this.optionsMenu[i], frameX + 50, frameY + 350);
                graphics.setColor(Color.WHITE);
            }
        }
        // Music Bar
        int textX = frameX + 450;
        int textY = frameY + 170;
        graphics.drawRect(textX, textY, 120, 30);
        int volumeWidth = 24 * PlayingState.music.volumeScale;
        graphics.fillRect(textX, textY, volumeWidth, 30);

        //SE Bar
        textY = frameY + 223;
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
                if (this.selected < this.optionsMenu.length - 1) {
                    this.selected++;
                } else {
                    this.selected = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (this.selected == 3 && PlayingState.music.volumeScale < 5) {
                    PlayingState.music.volumeScale++;
                    PlayingState.music.checkVolume();
                    PlayingState.se.playSE(2);
                    break;
                } else if (this.selected == 4 && PlayingState.se.volumeScale < 5) {
                    PlayingState.se.volumeScale++;
                    PlayingState.se.playSE(2);
                    break;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (this.selected == 3 && PlayingState.music.volumeScale > 0) {
                    PlayingState.music.volumeScale--;
                    PlayingState.music.checkVolume();
                    PlayingState.se.playSE(2);
                    break;
                } else if (this.selected == 4 && PlayingState.se.volumeScale > 0) {
                    PlayingState.se.volumeScale--;
                    PlayingState.se.playSE(2);
                    break;
                }
                break;
            case KeyEvent.VK_ENTER:
                switch(this.optionsMenu[selected]) {
                    case CONTINUE_GAME:
                        this.gameStateManager.backToPreviousState();
                        break;
                    case SHOW_INSTRUCTIONS:
                        super.gameStateManager.stackState(new Instructions(gameStateManager));
                        break;
                    case MAP:
                        super.gameStateManager.stackState(new Map(gameStateManager));
                        break;
                    case QUIT_GAME:
                        // Pop up frame to ensure you want to quit
                        JFrame window= new JFrame();
                        int resp = JOptionPane.showConfirmDialog( window, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
                        if (resp == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        } else {
                            window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        }
                        break;
                    case MUSIC:
                    case SE:
                        break;
                    case RESTART_GAME:
                        // Pop up to ensure you want to restart the game
                        window= new JFrame();
                        resp = JOptionPane.showConfirmDialog( window, "Are you sure you want to restart?\n " +
                                "You will lose all of your progress.", "Restart?", JOptionPane.YES_NO_OPTION);
                        if (resp == JOptionPane.YES_OPTION) {
                            Player.playerInventory.clear();
                            Player.currentWeapon = null;
                            super.gameStateManager.backToPreviousState();
                            super.gameStateManager.backToPreviousState();
                            PlayingState.music.stopMusic();
                            PlayingState.music.playMusic(0);
                        } else {
                            window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        }
                        break;
                }
                break;

        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
