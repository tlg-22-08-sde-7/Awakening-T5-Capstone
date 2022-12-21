package com.awakening.ui.game.world;

import com.awakening.ui.framework.resources.Resources;
import com.awakening.ui.framework.utils.FunctionalInterface;
import com.awakening.ui.framework.utils.MathHelper;

import java.awt.*;

public class Feature extends Tile{
    private static final long serialVersionUID = 1L;

    private FunctionalInterface action;

    public Feature(byte id, FunctionalInterface action) {
        super(id, MathHelper.randomInt(2, 14), MathHelper.randomInt(2, 7), false);
        this.action = action;
    }

    public Feature(Feature copy) {
        this(copy.getID(), copy.action);
    }

    @Override
    public boolean intersects(Rectangle r) {
        if(super.intersects(r)) {
            this.action.action();
            return true;
        }
        return false;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(Resources.TEXTURES.get(super.getID()), super.x, super.y, super.width, super.height, null);
    }
}
