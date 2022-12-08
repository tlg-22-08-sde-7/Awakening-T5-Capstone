package com.awakening.app.game;

public class NPC {
    private Ghost1 ghost1;
    private Ghost2 ghost2;
    private Ghost3 ghost3;
    private Ghost4 ghost4;

    public Ghost1 getGhost1() {
        return ghost1;
    }

    public void setGhost1(Ghost1 ghost1) {
        this.ghost1 = ghost1;
    }

    public Ghost2 getGhost2() {
        return ghost2;
    }

    public void setGhost2(Ghost2 ghost2) {
        this.ghost2 = ghost2;
    }

    public Ghost3 getGhost3() {
        return ghost3;
    }

    public void setGhost3(Ghost3 ghost3) {
        this.ghost3 = ghost3;
    }

    public Ghost4 getGhost4() {
        return ghost4;
    }

    public void setGhost4(Ghost4 ghost4) {
        this.ghost4 = ghost4;
    }

    public static class NPCLayout {
       private String name;
       private String description;
       private int health;
       private int attack;

       public NPCLayout(){}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public int getAttack() {
            return attack;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }
    }

    public static class Ghost1 extends NPCLayout{
        public Ghost1() {
        }
    }

    public static class Ghost2 extends NPCLayout{
        public Ghost2() {
        }
    }

    public static class Ghost3 extends NPCLayout{
        public Ghost3() {
        }
    }

    public static class Ghost4 extends NPCLayout{
        public Ghost4() {
        }
    }
}
