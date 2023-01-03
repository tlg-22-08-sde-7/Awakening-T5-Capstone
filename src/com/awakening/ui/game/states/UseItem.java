package com.awakening.ui.game.states;

import com.awakening.ui.framework.gamestates.GameState;
import com.awakening.ui.framework.gamestates.GameStateManager;
import com.awakening.ui.framework.gui.WindowManager;
import com.awakening.ui.game.entities.Entity;
import com.awakening.ui.game.entities.Player;
import com.awakening.ui.game.world.World;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UseItem extends GameState {

    private int selected;
    private Inventory inventory = new Inventory(gameStateManager);
    private String[] useItemMenu;
    private static final String QUESTION = "Would you like to use this item?";
    private static final String YES = "YES";
    private static final String NO = "NO";

    protected UseItem(GameStateManager manager) {
        super(manager);
        this.useItemMenu = new String[]{YES, NO};
        this.selected = 0;
    }

    @Override
    protected void loop() {

    }

    @Override
    protected void render(Graphics graphics) {
        World.getRoom().render(graphics);
        int frameX = WindowManager.WIDTH/4 -50;
        int frameY = WindowManager.HEIGHT/3 -30;
        int frameWidth = WindowManager.WIDTH/2;
        int frameHeight = WindowManager.HEIGHT/2 - 100;

        Color c = new Color(0,0,0, 220);
        graphics.setColor(c);
        graphics.fillRoundRect(frameX, frameY, frameWidth, frameHeight, 35, 35);

        c = new Color(255, 255, 255, 220);
        graphics.setColor(c);
        graphics.drawRoundRect(frameX + 5, frameY + 5, frameWidth - 10, frameHeight - 10, 25, 25);

        graphics.setFont(graphics.getFont().deriveFont(40F));
        graphics.setColor(Color.WHITE);
        graphics.drawString(QUESTION, frameX + 20, frameY + 50);
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
                        int itemIndex = Inventory.getItemIndexInSlot();
                        if (itemIndex < Player.playerInventory.size()) {
                            Entity selectedItem = Player.playerInventory.get(itemIndex);
                            Player.setHp(selectedItem.getHealPoints() + Player.getHp());
                            Player.playerInventory.remove(selectedItem);
                            gameStateManager.backToPreviousState();
                        }
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
