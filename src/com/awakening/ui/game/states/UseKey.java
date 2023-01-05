package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.Sound;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;
import com.awakening.ui.game.entities.Player;
import com.awakening.ui.game.entities.items.Obj_MasterKey;
import com.awakening.ui.game.world.Room;
import com.awakening.ui.game.world.World;
import com.awakening.ui.game.world.generator.LevelGenerator;
import com.awakening.ui.game.world.generator.RoomData;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class UseKey extends GameState {

    // Selections to be used in UseKey
    private int selected;
    private Inventory inventory = new Inventory(gameStateManager);
    private String[] useItemMenu;
    private static final String QUESTION = "Unlock the Door?";
    private static final String YES = "YES";
    private static final String NO = "NO";

    // Constructor
    protected UseKey(GameStateManager manager) {
        super(manager);
        this.useItemMenu = new String[]{YES, NO};
        this.selected = 0;
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {

        // Background of the current State
        World.getRoom().render(graphics);

        // Window
        int frameX = WindowManager.WIDTH/4 -50;
        int frameY = WindowManager.HEIGHT/3 -30;
        int frameWidth = WindowManager.WIDTH/2;
        int frameHeight = WindowManager.HEIGHT/2 - 100;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        // Border for the window
        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        // Font for the window
        graphics.setFont(graphics.getFont().deriveFont(40F));
        graphics.setColor(Color.WHITE);
        graphics.drawString(QUESTION, frameX + 40, frameY + 50);
        for(int i = 0; i < this.useItemMenu.length;i++) {
            if (i == this.selected) {
                graphics.setColor(Color.GREEN);
            } else {
                graphics.setColor(Color.WHITE);
            }
            if (i == 0) { // YES
                graphics.drawString(this.useItemMenu[i], frameX + 170, frameY*2);
            } else if (i == 1) { // NO
                graphics.drawString(this.useItemMenu[i], frameX + 370, frameY*2);
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
                if (this.selected < 2) {
                    this.selected++;
                } else {
                    this.selected = 0;
                }
                break;
            case KeyEvent.VK_ENTER:
                switch(this.useItemMenu[selected]) {
                    case YES:
                        // changes the blocks inside of the rooms to reveal passages for the player
                        // when opening the doors
                        RoomData roomIn = World.getRoom().getData();
                        for(int i = 0; i < Player.playerInventory.size(); i++) {
                            if (Player.playerInventory.get(i).getID() == Resources.KEY &&
                                    Objects.equals(LevelGenerator.getRoomName(World.getCurrentX(),
                                            World.getCurrentY()), "Fellowship Room")) {
                                Player.playerInventory.remove(i);
                                PlayingState.se.playSE(8);
                                roomIn.getTileAt(0, 3).setId(Resources.TILE);
                                roomIn.getTileAt(0, 4).setId(Resources.TILE);
                                roomIn.getTileAt(0, 5).setId(Resources.TILE);
                                roomIn.getTileAt(0, 3).setWall(false);
                                roomIn.getTileAt(0, 4).setWall(false);
                                roomIn.getTileAt(0, 5).setWall(false);
                            }
                            else if (Player.playerInventory.get(i).getID() == Resources.MASTER_KEY &&
                                    Objects.equals(LevelGenerator.getRoomName(World.getCurrentX(),
                                            World.getCurrentY()), "Hallway")) {
//                                Player.playerInventory.remove(i);
                                PlayingState.se.playSE(8);
                                roomIn.getTileAt(15,3).setId(Resources.TILE);
                                roomIn.getTileAt(15,4).setId(Resources.TILE);
                                roomIn.getTileAt(15,5).setId(Resources.TILE);
                                roomIn.getTileAt(15, 3).setWall(false);
                                roomIn.getTileAt(15, 4).setWall(false);
                                roomIn.getTileAt(15, 5).setWall(false);
                            }
                        }
                        PlayingState.addMessage("A room has been revealed on the opposite level!");
                        gameStateManager.backToPreviousState();
                        break;
                    case NO:
                        gameStateManager.backToPreviousState();
                        break;
                }
        }
    }

    @Override
    protected void keyReleased(int keyCode) {

    }
}
