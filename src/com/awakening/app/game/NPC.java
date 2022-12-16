package com.awakening.app.game;

public class NPC {
    private Ghost1 ghost1;
    private Ghost2 ghost2;
    private Ghost3 ghost3;
    private Ghost4 ghost4;
    private Ghost5 ghost5;
    private Ghost6 ghost6;
    private Ghost7 ghost7;
    private Ghost8 ghost8;
    private Ghost9 ghost9;

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

    public Ghost5 getGhost5() {
        return ghost5;
    }

    public void setGhost5(Ghost5 ghost5) {
        this.ghost5 = ghost5;
    }

    public Ghost6 getGhost6() {
        return ghost6;
    }

    public void setGhost6(Ghost6 ghost6) {
        this.ghost6 = ghost6;
    }

    public Ghost7 getGhost7() {
        return ghost7;
    }

    public void setGhost7(Ghost7 ghost7) {
        this.ghost7 = ghost7;
    }

    public Ghost8 getGhost8() {
        return ghost8;
    }

    public void setGhost8(Ghost8 ghost8) {
        this.ghost8 = ghost8;
    }

    public Ghost9 getGhost9() {
        return ghost9;
    }

    public void setGhost9(Ghost9 ghost9) {
        this.ghost9 = ghost9;
    }

    public String getGhost(String ghostName){
        String ghost = null;

        if (ghostName.equals(getGhost1().getName())) {
            ghost = getGhost1().getDescription();
        }
        else if (ghostName.equals(getGhost2().getName())){
            ghost = getGhost2().getDescription();
        }
        else if (ghostName.equals(getGhost3().getName())) {
            ghost = getGhost3().getDescription();
        }
        else if (ghostName.equals(getGhost4().getName())) {
            ghost = getGhost4().getDescription();
        }
        else if (ghostName.equals(getGhost5().getName())) {
            ghost = getGhost5().getDescription();
        }
        else if (ghostName.equals(getGhost6().getName())) {
            ghost = getGhost6().getDescription();
        }
        else if (ghostName.equals(getGhost7().getName())) {
            ghost = getGhost7().getDescription();
        }
        else if (ghostName.equals(getGhost8().getName())) {
            ghost = getGhost8().getDescription();
        }
        else if (ghostName.equals(getGhost9().getName())) {
            ghost = getGhost9().getDescription();
        }
        else{
            ghost = "Name not found";
        }
        return ghost;

    }

    public static class NPCLayout {
       private String name;
       private String description;
       private int health;
       private int attack;

        public NPCLayout(String name, String description, int health, int attack) {
            this.name = name;
            this.description = description;
            this.health = health;
            this.attack = attack;
        }

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

        public Ghost1(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }

    public static class Ghost2 extends NPCLayout{

        public Ghost2(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }

    public static class Ghost3 extends NPCLayout{

        public Ghost3(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }

    public static class Ghost4 extends NPCLayout{

        public Ghost4(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }

    public static class Ghost5 extends NPCLayout{

        public Ghost5(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }

    public static class Ghost6 extends NPCLayout{

        public Ghost6(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }

    public static class Ghost7 extends NPCLayout{

        public Ghost7(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }

    public static class Ghost8 extends NPCLayout{

        public Ghost8(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }

    public static class Ghost9 extends NPCLayout{

        public Ghost9(String name, String description, int health, int attack) {
            super(name, description, health, attack);
        }
    }
}
