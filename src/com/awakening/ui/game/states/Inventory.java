package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;

public class Inventory extends GameState {
    public static Image currentPlayerSprite;
    public static Image[] playerInventory;
    private int selected;
    private int slotCol = 0;
    private int slotRow = 0;

    protected Inventory(GameStateManager manager) {
        super(manager);
        playerInventory = new Image[]{Resources.TEXTURES.get(Resources.AXE), Resources.TEXTURES.get(Resources.BANDAGES)};
        this.selected = 0;
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {
        World.getRoom().render(graphics);

        int frameX = WindowManager.WIDTH/4;
        int frameY = WindowManager.HEIGHT/3;
        int frameWidth = WindowManager.WIDTH/2 - 43;
        int frameHeight = WindowManager.HEIGHT/2 - 50;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;

        int cursorX = slotXstart + (90 * slotCol);
        int cursorY = slotYstart + (90 * slotRow);
        int cursorWidth = 70;
        int cursorHeight = 70;

        graphics.setColor(Color.WHITE);
        graphics.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        for(int i = 0; i < playerInventory.length;i++) {

            if(i == 0) { // continue
                graphics.drawImage(playerInventory[i], slotXstart + 5, slotYstart + 5, 60, 60, null);
            }
            else if(i == 1){ // show instructions
                graphics.drawImage(playerInventory[i], slotXstart + 95, slotYstart + 5, 60, 60, null);
            }
            else if(i == 2) { // settings game
                graphics.drawImage(playerInventory[i], frameX + 50, frameY + 150, 50, 50, null);
            }
            else if (i == 3) {  // restart game
                graphics.drawImage(playerInventory[i], frameX + 50, frameY + 200, 50, 50, null);
            } else {  // quit game
                graphics.drawImage(playerInventory[i], frameX + 50, frameY + 250, 50, 50, null);
            }
        }
        frameX = WindowManager.WIDTH/4;
        frameY = WindowManager.HEIGHT-625;
        frameWidth = WindowManager.WIDTH/2 - 43;
        frameHeight = WindowManager.HEIGHT/4;

        c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);
    }

    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (this.slotRow != 0) {
                    this.slotRow--;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (this.slotRow != 2) {
                    this.slotRow++;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (this.slotCol != 0) {
                    this.slotCol--;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (this.slotCol != 5) {
                    this.slotCol++;
                }
                break;
            case KeyEvent.VK_I:
            case KeyEvent.VK_ESCAPE:
                gameStateManager.backToPreviousState();
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_I:
            case KeyEvent.VK_ESCAPE:

                break;
        }
    }
}
