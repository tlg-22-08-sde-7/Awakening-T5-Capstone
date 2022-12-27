package com.awakening.ui.game.entities;

import com.awakening.ui.framework.utils.MathHelper;

public class Enemy extends Entity{
    private static final long serialVersionUID = 1L;

    private Player1 target1;
    private Player2 target2;
    private Player3 target3;
    private Player4 target4;

    private int hp;

    public Enemy(byte id, int health, Player1 target) {
        super(id, MathHelper.randomInt(2, 14), MathHelper.randomInt(2, 7));
        this.target1 = target;
        super.speed = 2;
        this.hp = health;
    }
    public Enemy(byte id, int health, Player2 target) {
        super(id, MathHelper.randomInt(2, 14), MathHelper.randomInt(2, 7));
        this.target2 = target;
        super.speed = 2;
        this.hp = health;
    }
    public Enemy(byte id, int health, Player3 target) {
        super(id, MathHelper.randomInt(2, 14), MathHelper.randomInt(2, 7));
        this.target3 = target;
        super.speed = 2;
        this.hp = health;
    }
    public Enemy(byte id, int health, Player4 target) {
        super(id, MathHelper.randomInt(2, 14), MathHelper.randomInt(2, 7));
        this.target4 = target;
        super.speed = 2;
        this.hp = health;
    }

    public Enemy(Enemy copy, Player1 target1) {
        this(copy.getID(), copy.hp, copy.target1);
    }
    public Enemy(Enemy copy, Player2 target2) {
        this(copy.getID(), copy.hp, copy.target2);
    }
    public Enemy(Enemy copy, Player3 target3) {
        this(copy.getID(), copy.hp, copy.target3);
    }
    public Enemy(Enemy copy, Player4 target4) {
        this(copy.getID(), copy.hp, copy.target4);
    }
    @Override
    public void move() {
    }

    public void move1() {
        super.move();
        float angCoeff = ((float) this.target1.y - (float) super.y) / ((float) this.target1.x - (float) super.x);
        if(angCoeff < 1 && angCoeff > -1) {
            if(this.target1.x < super.x) {
                super.up = false;
                super.down = false;
                super.left = true;
                super.right = false;
            } else {
                super.up = false;
                super.down = false;
                super.left = false;
                super.right = true;
            }
        }
        else if(angCoeff > 1 || angCoeff < -1) {
            if(this.target1.y < super.y) {
                super.up = true;
                super.down = false;
                super.left = false;
                super.right = false;
            } else {
                super.up = false;
                super.down = true;
                super.left = false;
                super.right = false;
            }
        }
        else {
            if(this.target1.x < super.x) {
                super.left = true;
                super.right = false;
            } else {
                super.left = false;
                super.right = true;
            }
        }
    }
    public void move2() {
        super.move();
        float angCoeff = ((float) this.target2.y - (float) super.y) / ((float) this.target2.x - (float) super.x);
        if(angCoeff < 1 && angCoeff > -1) {
            if(this.target2.x < super.x) {
                super.up = false;
                super.down = false;
                super.left = true;
                super.right = false;
            } else {
                super.up = false;
                super.down = false;
                super.left = false;
                super.right = true;
            }
        }
        else if(angCoeff > 1 || angCoeff < -1) {
            if(this.target2.y < super.y) {
                super.up = true;
                super.down = false;
                super.left = false;
                super.right = false;
            } else {
                super.up = false;
                super.down = true;
                super.left = false;
                super.right = false;
            }
        }
        else {
            if(this.target2.x < super.x) {
                super.left = true;
                super.right = false;
            } else {
                super.left = false;
                super.right = true;
            }
        }
    }
    public void move3() {
        super.move();
        float angCoeff = ((float) this.target3.y - (float) super.y) / ((float) this.target3.x - (float) super.x);
        if(angCoeff < 1 && angCoeff > -1) {
            if(this.target3.x < super.x) {
                super.up = false;
                super.down = false;
                super.left = true;
                super.right = false;
            } else {
                super.up = false;
                super.down = false;
                super.left = false;
                super.right = true;
            }
        }
        else if(angCoeff > 1 || angCoeff < -1) {
            if(this.target3.y < super.y) {
                super.up = true;
                super.down = false;
                super.left = false;
                super.right = false;
            } else {
                super.up = false;
                super.down = true;
                super.left = false;
                super.right = false;
            }
        }
        else {
            if(this.target3.x < super.x) {
                super.left = true;
                super.right = false;
            } else {
                super.left = false;
                super.right = true;
            }
        }
    }
    public void move4() {
        super.move();
        float angCoeff = ((float) this.target4.y - (float) super.y) / ((float) this.target4.x - (float) super.x);
        if(angCoeff < 1 && angCoeff > -1) {
            if(this.target4.x < super.x) {
                super.up = false;
                super.down = false;
                super.left = true;
                super.right = false;
            } else {
                super.up = false;
                super.down = false;
                super.left = false;
                super.right = true;
            }
        }
        else if(angCoeff > 1 || angCoeff < -1) {
            if(this.target4.y < super.y) {
                super.up = true;
                super.down = false;
                super.left = false;
                super.right = false;
            } else {
                super.up = false;
                super.down = true;
                super.left = false;
                super.right = false;
            }
        }
        else {
            if(this.target4.x < super.x) {
                super.left = true;
                super.right = false;
            } else {
                super.left = false;
                super.right = true;
            }
        }
    }

    @Override
    public void setUp(boolean up) {
        return;
    }

    @Override
    public void setDown(boolean down) {
        return;
    }

    @Override
    public void setLeft(boolean left) {
        return;
    }

    @Override
    public void setRight(boolean right) {
        return;
    }

    public int getHp() {
        return hp;
    }

    public void damage(int amount, MathHelper.Direction knockback) {
        this.hp -= amount;
        super.x += knockback.dirX * 90;
        super.y += knockback.dirY * 90;
    }
}
