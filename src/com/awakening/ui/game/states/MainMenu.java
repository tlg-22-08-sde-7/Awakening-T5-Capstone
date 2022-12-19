package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
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
    private int selected;

    public MainMenu(GameStateManager manager) {
        super(manager);
        this.optionsMenu = new String[] {START_GAME, SHOW_INSTRUCTIONS, QUIT_GAME};
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
            else{ // quit game
                graphics.drawString(this.optionsMenu[i], WindowManager.WIDTH - 100, 50);
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
                        super.gameStateManager.stackState(new PlayingState(gameStateManager));
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
                }
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
