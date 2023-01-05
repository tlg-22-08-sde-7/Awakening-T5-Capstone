package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.Sound;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerSelect extends GameState {

    // selections that can be made
    private String[] playerSelectMenu;
    private static final String SETTINGS = "Settings";
    private static final String QUIT_GAME = "Quit";
    private static final String PLAYER1 = "Lennie";
    private static final String PLAYER2 = "Sandra";
    private static final String PLAYER3 = "Jimmy";
    private static final String PLAYER4 = "Cassidy";
    private int selected;

    // Constructor
    public PlayerSelect(GameStateManager manager) {
        super(manager);
        this.playerSelectMenu = new String[]{ PLAYER1, PLAYER2, PLAYER3, PLAYER4, SETTINGS, QUIT_GAME};
        this.selected = 0;
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {
        // Background of the current State
        try {
            BufferedImage image = ImageIO.read(new File("resources/pictures/titlescreen.jpg"));
            graphics.drawImage(image, 0, 0, WindowManager.WIDTH, WindowManager.HEIGHT,  null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Window of the current State
        int frameX = WindowManager.WIDTH/6;
        int frameY = WindowManager.HEIGHT/6;
        int frameWidth = WindowManager.WIDTH-400;
        int frameHeight = WindowManager.HEIGHT/2;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        // Border of the window
        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        // Font to be used
        graphics.setFont(graphics.getFont().deriveFont(32F));

        // Images of the different players
        graphics.drawImage(Resources.TEXTURES.get(21), frameX + 65, frameHeight -140, 100, 125, null);
        graphics.drawImage(Resources.TEXTURES.get(5), frameX + 245, frameHeight -150, 100, 125, null);
        graphics.drawImage(Resources.TEXTURES.get(29), frameX + 425, frameHeight -150, 100, 125, null);
        graphics.drawImage(Resources.TEXTURES.get(37), frameX + 615, frameHeight -150, 100, 125, null);

        // Selection graphics
        for(int i = 0; i < this.playerSelectMenu.length;i++) {
            if(i == this.selected){
                graphics.setColor(Color.GREEN);
            }
            else{
                graphics.setColor(Color.WHITE);
            }

            if (i == 0) { // player1
                graphics.drawString(this.playerSelectMenu[i], frameX + 65, frameHeight + 30);
            }
            else if (i == 1) {  // player2
                graphics.drawString(this.playerSelectMenu[i], frameX + 245, frameHeight + 30);
            }
            else if (i == 2) {  // player3
                    graphics.drawString(this.playerSelectMenu[i], frameX + 425, frameHeight + 30);
            }
            else if (i == 3){  // player4
                graphics.drawString(this.playerSelectMenu[i], frameX + 615, frameHeight + 30);
            }
            else if(i == 4) { // settings
                graphics.drawString(this.playerSelectMenu[i], frameX + 50, frameY + 50);
            } else { // quit game
                graphics.drawString(this.playerSelectMenu[i], frameWidth + 100, frameY + 50);
            }
        }
    }

    // Keys to be used in the current State
    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (this.selected > 0) {
                    this.selected--;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (this.selected < this.playerSelectMenu.length - 1) {
                    this.selected++;
                } else {
                    this.selected = 0;
                }
                break;
            case KeyEvent.VK_ENTER:
                switch(this.playerSelectMenu[selected]) {
                    case QUIT_GAME:
                        // pop up to ensure player wants to quit the game
                        JFrame window= new JFrame();
                        int resp = JOptionPane.showConfirmDialog( window, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
                        if (resp == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        } else {
                            window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        }
                        break;
                    case SETTINGS:
                        super.gameStateManager.stackState(new Settings(gameStateManager));
                        break;
                    case PLAYER1:
                        super.gameStateManager.stackState(new PlayingState(gameStateManager));
                        PlayingState.music.stopMusic();
                        PlayingState.music.playMusic(10);
                        break;

                    // When different players are selected, changes are made to the Player images
                    case PLAYER2:
                        super.gameStateManager.stackState(new PlayingState(gameStateManager));
                        Player.playerStandDown = Resources.PLAYER;
                        Player.playerStandUp = Resources.PLAYER_BACK;
                        Player.playerStandLeft = Resources.PLAYER_LEFT;
                        Player.playerStandRight = Resources.PLAYER_RIGHT;
                        PlayingState.music.stopMusic();
                        PlayingState.music.playMusic(10);
                        break;
                    case PLAYER3:
                        super.gameStateManager.stackState(new PlayingState(gameStateManager));
                        Player.playerStandDown = Resources.PLAYER_3_STAND_DOWN;
                        Player.playerStandUp = Resources.PLAYER_3_STAND_UP;
                        Player.playerStandLeft = Resources.PLAYER_3_STAND_LEFT;
                        Player.playerStandRight = Resources.PLAYER_3_STAND_RIGHT;
                        PlayingState.music.stopMusic();
                        PlayingState.music.playMusic(10);
                        break;
                    case PLAYER4:
                        super.gameStateManager.stackState(new PlayingState(gameStateManager));
                        Player.playerStandDown = Resources.PLAYER_4_STAND_DOWN;
                        Player.playerStandUp = Resources.PLAYER_4_STAND_UP;
                        Player.playerStandLeft = Resources.PLAYER_4_STAND_LEFT;
                        Player.playerStandRight = Resources.PLAYER_4_STAND_RIGHT;
                        PlayingState.music.stopMusic();
                        PlayingState.music.playMusic(10);
                        break;
                }
                break;

        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
