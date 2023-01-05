package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.Sound;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.game.entities.Entity;
import com.awakening.ui.game.entities.Player;
import com.awakening.ui.game.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

public class Inventory extends GameState {

    // int and image used in the Inventory State
    public static Image currentSpriteImage;
    private final int selected;
    private static int slotCol = 0;
    private static int slotRow = 0;

    // Constructor
    protected Inventory(GameStateManager manager) {
        super(manager);
        this.selected = 0;
    }

    @Override
    protected void loop() {
    }

    @Override
    protected void render(Graphics graphics) {

        // Render the current room in the background
        World.getRoom().render(graphics);

        String currentHP = String.valueOf(Player.getHp());
        String currentArmor = String.valueOf(Player.getArmor());
        String currentGold = String.valueOf(Player.getGold());

        // Inventory Window
        int frameX = WindowManager.WIDTH/4;
        int frameY = WindowManager.HEIGHT/3 -30;
        int frameWidth = WindowManager.WIDTH/2 - 43;
        int frameHeight = WindowManager.HEIGHT/2 - 50;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        // Cursor to select items
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;

        int cursorX = slotXstart + (90 * slotCol);
        int cursorY = slotYstart + (90 * slotRow);
        int cursorWidth = 70;
        int cursorHeight = 70;

        // slots for the items
        int slotX = slotXstart;
        int slotY = slotYstart;

        graphics.setColor(Color.WHITE);
        graphics.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // changes the color of the item slot when a weapon is selected
        for(int i = 0; i < Player.playerInventory.size(); i++) {
            if (Player.playerInventory.get(i) == Player.currentWeapon) {
                graphics.setColor(new Color(240, 190, 90));
                graphics.fillRoundRect(slotX, slotY, 70, 70, 10, 10);
            }

            // draws the image of the items in slots
            graphics.drawImage(Resources.TEXTURES.get(Player.playerInventory.get(i).getID()), slotX + 5, slotY + 5, 60, 60, null);

            slotX += cursorWidth + 20;

            // makes the items move to a different slot when the size in the slot is more than 6
            if (i == 5 || i == 11) {
                slotX = slotXstart;
                slotY += cursorHeight + 20;
            }
        }


        // Upper Window to display Player Info
        frameX = WindowManager.WIDTH/4;
        frameY = WindowManager.HEIGHT-655;
        frameWidth = WindowManager.WIDTH/2 - 43;
        frameHeight = WindowManager.HEIGHT/4;

        c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.setFont(graphics.getFont().deriveFont(40F));
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);
        graphics.drawImage(Resources.TEXTURES.get(Player.playerStandDown), frameX + 20, frameY + 40, 75, 75, null);
        graphics.drawImage(Resources.TEXTURES.get(Resources.HEART), frameX + 100, frameY + 40, 50, 50, null);
        graphics.drawString(currentHP, frameX + 180, frameY + 80);
        graphics.drawImage(Resources.TEXTURES.get(Resources.ARMOR), frameX + 250, frameY + 40, 50, 50, null);
        graphics.drawString(currentArmor, frameX + 330, frameY + 80);
        graphics.drawImage(Resources.TEXTURES.get(Resources.GOLD), frameX + 400, frameY + 40, 50, 50, null);
        graphics.drawString(currentGold, frameX + 480, frameY + 80);

        // Lower Window to display item information
        frameX = WindowManager.WIDTH/4;
        frameY = WindowManager.HEIGHT-187;
        frameWidth = WindowManager.WIDTH/2 - 43;
        frameHeight = WindowManager.HEIGHT/4;

        c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.setFont(graphics.getFont().deriveFont(40F));
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        // text for descriptions of items
        int textX = frameX + 10;
        int textY = frameY + 40;
        graphics.setFont(graphics.getFont().deriveFont(28F));
        int itemIndex = getItemIndexInSlot();

        if (itemIndex < Player.playerInventory.size()) {
            for (String line: Player.playerInventory.get(itemIndex).getDescription().split("\n")) {
                graphics.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    // Keys to be used in the current State
    @Override
    protected void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (slotRow != 0) {
                    slotRow--;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (slotRow != 2) {
                    slotRow++;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (slotCol != 0) {
                    slotCol--;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (slotCol != 5) {
                    slotCol++;
                }
                break;
            case KeyEvent.VK_I:
            case KeyEvent.VK_ESCAPE:
                gameStateManager.backToPreviousState();
                break;
            case KeyEvent.VK_ENTER:
                selectItem();
                break;
        }
    }

    @Override
    protected void keyReleased(int keyCode) {
    }

    //gets the current item that is in the slot
    public static int getItemIndexInSlot() {
        return slotCol + (slotRow*6);
    }

    // used to select the items that are in each slot.
    public void selectItem() {

        int itemIndex = getItemIndexInSlot();

        if (itemIndex < Player.playerInventory.size()) {
            Entity selectedItem = Player.playerInventory.get(itemIndex);

            // ensures that you can only select weapons to use
            if (Objects.equals(selectedItem.getName(), "Axe")||Objects.equals(selectedItem.getName(), "Barbell")
                    ||Objects.equals(selectedItem.getName(), "Wood Cane")
                    ||Objects.equals(selectedItem.getName(), "Fire Extinguisher")
                    ||Objects.equals(selectedItem.getName(), "Camera")
                    ||Objects.equals(selectedItem.getName(), "Cellphone")) {
                Player.currentWeapon = selectedItem;
                PlayingState.player.setAttackPoints(selectedItem.getAttackPoints());
            }
            // ensures that you can only use healing items to heal the player
            else if (Objects.equals(selectedItem.getName(), "First Aid Kit")
                    ||Objects.equals(selectedItem.getName(), "Bandages")
                    ||Objects.equals(selectedItem.getName(), "Tylenol")) {
                selectedItem.use(selectedItem);
                Player.playerInventory.remove(itemIndex);
                gameStateManager.backToPreviousState();
            } else {

                // if selection is not a healing item or a weapon, gives an error sound
                PlayingState.se.playSE(2);
            }
        }
    }

}
