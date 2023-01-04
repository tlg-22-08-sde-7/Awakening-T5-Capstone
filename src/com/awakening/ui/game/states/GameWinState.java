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

public class GameWinState extends GameState {
    private String[] optionsMenu;
    private static final String QUIT_GAME = "Quit";
    private static final String RESTART_GAME = "Restart Game";
    private int selected;

    public GameWinState(GameStateManager manager) {
        super(manager);
        this.optionsMenu = new String[]{ RESTART_GAME, QUIT_GAME};
        this.selected = 0;
    }


    @Override
    protected void loop() {

    }

    @Override
    public void render(Graphics graphics) {

        World.getRoom().render(graphics);
        int frameX = WindowManager.WIDTH/4;
        int frameY = WindowManager.HEIGHT/6;
        int frameWidth = WindowManager.WIDTH/2;
        int frameHeight = WindowManager.HEIGHT/2;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        graphics.setFont(graphics.getFont().deriveFont(40F));
        graphics.drawString("You Win!", frameX + 225, frameY + 100);

        graphics.setFont(graphics.getFont().deriveFont(32F));


        for(int i = 0; i < this.optionsMenu.length;i++) {
            if(i == this.selected){
                graphics.setColor(Color.GREEN);
            }
            else{
                graphics.setColor(Color.WHITE);
            }

            if (i == 0) {  // restart game
                graphics.drawString(this.optionsMenu[i], frameX + 50, frameY + 200);
            } else {  // quit game
                graphics.drawString(this.optionsMenu[i], frameX + 50, frameY + 250);
            }
        }
    }

    @Override
    protected void keyPressed(int keyCode) {

    }

    @Override
    protected void keyReleased(int keyCode) {
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
            case KeyEvent.VK_ENTER:
                switch(this.optionsMenu[selected]) {

                    case QUIT_GAME:
                        JFrame window= new JFrame();
                        int resp = JOptionPane.showConfirmDialog( window, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
                        if (resp == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        } else {
                            window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        }
                        break;
                    case RESTART_GAME:
                        Player.playerInventory.clear();
                        gameStateManager.backToPreviousState();
                        gameStateManager.backToPreviousState();
                        PlayingState.music.stopMusic();
                        PlayingState.music.playMusic(0);
                        break;
                }
                break;

        }
    }
}
