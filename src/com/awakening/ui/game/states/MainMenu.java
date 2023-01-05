package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.Sound;
import com.awakening.ui.framework.gui.WindowManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends GameState {
    private String[] optionsMenu;
    private static final String START_GAME = "Start";
    private static final String QUIT_GAME = "Quit";
    private static final String SHOW_INSTRUCTIONS = "Instructions";
    private static final String SETTINGS = "Settings";
    public static final String INSTRUCTIONS =
            "Choose between 4 different characters and try to survive as long as possible.\n " +
            "You must navigate through the psych ward utilizing the 'W A S D' Keys or the arrow keys.\n"+
            "As you navigate you will encounter ghosts in each room, each room has items that you will need in\n" +
            "order to defeat the ghost and gain access to other rooms.\n" +
            "To attack you can use the Space Bar and/or the \"Q\" key.\n" +
            "You can press the \"M\" key to view the map.\n" +
            "You can also press the \"I\" key to view your inventory.\n" +
            "The \"ESC\" key will bring up the pause menu where you can change the music volume, SFX volume, restart, or quit the game \n" +
            "In order to win you must grab the Master Key, Patient file, Journal and Find your way back to the Front Desk!";
    private int selected;

    public MainMenu(GameStateManager manager) {
        super(manager);
        this.optionsMenu = new String[] {START_GAME, SHOW_INSTRUCTIONS, QUIT_GAME, SETTINGS};
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

        graphics.setFont(new Font("Araial", Font.PLAIN, 25));

        for(int i = 0; i < this.optionsMenu.length;i++) {
            if(i == this.selected){
                graphics.setColor(Color.GREEN);
            }
            else{
                graphics.setColor(Color.WHITE);
            }
            if(i == 0) { //start
                graphics.drawString(this.optionsMenu[i], WindowManager.WIDTH/2-200, 550);
            }
            else if(i == 1){ // show instructions
                graphics.drawString(this.optionsMenu[i], WindowManager.WIDTH/2, 550);
            }
            else if(i == 2) { // quit game
                graphics.drawString(this.optionsMenu[i], WindowManager.WIDTH - 100, 50);
            } else {
                graphics.drawString(this.optionsMenu[i], WindowManager.WIDTH - 100, 100);
            }
        }
    }

    @Override
    protected void keyPressed(int keyCode) {

        switch(keyCode) {
            case KeyEvent.VK_LEFT:
                if(this.selected > 0){
                    this.selected--;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(this.selected < this.optionsMenu.length-1){
                    this.selected++;
                }else{
                    this.selected = 0;
                }
                break;
            case KeyEvent.VK_ENTER:
                switch(this.optionsMenu[selected]) {
                    case START_GAME:
                        super.gameStateManager.stackState(new PlayerSelect(gameStateManager));
                        break;
                    case SHOW_INSTRUCTIONS:
                        super.gameStateManager.stackState(new Instructions(gameStateManager));
                        /*JOptionPane.showMessageDialog(null,  INSTRUCTIONS,
                                "Instructions", JOptionPane.PLAIN_MESSAGE);*/
                        break;
                    case QUIT_GAME:
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
                }
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
