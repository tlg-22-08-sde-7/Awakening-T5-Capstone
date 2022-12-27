package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.Sound;
import com.awakening.ui.framework.gui.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Options extends GameState {
    private String[] optionsMenu;
    private static final String CONTINUE_GAME = "Back To The Game";
    private static final String QUIT_GAME = "Quit";
    private static final String SHOW_INSTRUCTIONS = "Instructions";
    private static final String SETTINGS = "Settings";
    private static final String RESTART_GAME = "Restart Game";
    private int selected;

    public Options(GameStateManager manager) {
        super(manager);
        this.optionsMenu = new String[]{CONTINUE_GAME, SHOW_INSTRUCTIONS, SETTINGS, RESTART_GAME, QUIT_GAME};
        this.selected = 0;
    }


    @Override
    protected void loop() {

    }

    @Override
    public void render(Graphics graphics) {

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
            else if(i == 2) { // settings game
                graphics.drawString(this.optionsMenu[i], frameX + 50, frameY + 150);
            }
            else if (i == 3) {  // restart game
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
                    case CONTINUE_GAME:
                        this.gameStateManager.backToPreviousState();
                        break;
                    case SHOW_INSTRUCTIONS:
                        JOptionPane.showMessageDialog(null, "These are your instructions",
                                "Instructions", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case QUIT_GAME:
                        JFrame window= new JFrame();
                        int resp = JOptionPane.showConfirmDialog( window, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
                        if (resp == JOptionPane.YES_OPTION) {
                            //window.dispose();
                            System.exit(0);
                        } else {
                            window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        }
                        break;
                    case SETTINGS:
                        window = new JFrame();
                        JPanel settingsWindow = new JPanel();
                        settingsWindow.add(Sound.volumeControl());
                        settingsWindow.setVisible(true);
                        int optionPane = JOptionPane.showConfirmDialog
                                (window, settingsWindow, "Settings", JOptionPane.OK_CANCEL_OPTION);

                        if (optionPane == JOptionPane.OK_OPTION) {
                            window.setDefaultCloseOperation(window.DO_NOTHING_ON_CLOSE);
                        } else {
                            window.setDefaultCloseOperation(window.DISPOSE_ON_CLOSE);
                        }
                        break;
                    case RESTART_GAME:
                        window= new JFrame();
                        resp = JOptionPane.showConfirmDialog( window, "Are you sure you want to restart?\n " +
                                "You will lose all of your progress.", "Restart?", JOptionPane.YES_NO_OPTION);
                        if (resp == JOptionPane.YES_OPTION) {
                            super.gameStateManager.stackState(new PlayerSelect(gameStateManager));
                            Sound.stopMusic();
                            Sound.playMusic(10);
                        } else {
                            window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        }
                        break;
                }
                break;

        }
    }
}
