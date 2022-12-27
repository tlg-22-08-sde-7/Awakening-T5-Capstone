package com.awakening.ui.framework.resources;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Loader {

    public static void load (){
        try {
            File texturesFolder = new File("resources/pictures/rooms");
            for(File imgFile : texturesFolder.listFiles()) {
                Resources.ROOMS.put(imgFile.getName(), ImageIO.read(imgFile));
            }
            Resources.TEXTURES.add(Resources.FLOOR, ImageIO.read(new File("resources/pictures/textures/floor.png")));
            Resources.TEXTURES.add(Resources.STONE, ImageIO.read(new File("resources/pictures/textures/stone.png")));
            Resources.TEXTURES.add(Resources.WALL, ImageIO.read(new File("resources/pictures/textures/wall.png")));
            Resources.TEXTURES.add(Resources.TILE, ImageIO.read(new File("resources/pictures/textures/tile.png")));
            Resources.TEXTURES.add(Resources.GRASS, ImageIO.read(new File("resources/pictures/textures/grass.png")));
            Resources.TEXTURES.add(Resources.PLAYER, ImageIO.read(new File("resources/pictures/player2/p2standdown.png")));
            Resources.TEXTURES.add(Resources.PLAYER_2, ImageIO.read(new File("resources/pictures/player2/p2walkdown.png")));
            Resources.TEXTURES.add(Resources.PLAYER_LEFT, ImageIO.read(new File("resources/pictures/player2/p2standleft.png")));
            Resources.TEXTURES.add(Resources.PLAYER_LEFT_2, ImageIO.read(new File("resources/pictures/player2/p2walkleft.png")));
            Resources.TEXTURES.add(Resources.PLAYER_RIGHT, ImageIO.read(new File("resources/pictures/player2/p2standright.png")));
            Resources.TEXTURES.add(Resources.PLAYER_RIGHT_2, ImageIO.read(new File("resources/pictures/player2/p2walkright.png")));
            Resources.TEXTURES.add(Resources.PLAYER_BACK, ImageIO.read(new File("resources/pictures/player2/p2standup.png")));
            Resources.TEXTURES.add(Resources.PLAYER_BACK_2, ImageIO.read(new File("resources/pictures/player2/p2walkup.png")));
            Resources.TEXTURES.add(Resources.STAIRS, ImageIO.read(new File("resources/pictures/textures/stairs.png")));
            Resources.TEXTURES.add(Resources.CHEST, ImageIO.read(new File("resources/pictures/textures/chest.png")));
            Resources.TEXTURES.add(Resources.ENEMY, ImageIO.read(new File("resources/pictures.ghost/ghost3.png")));
            Resources.TEXTURES.add(Resources.ENEMY_2, ImageIO.read(new File("resources/pictures.ghost/ghost4.png")));
            Resources.TEXTURES.add(Resources.ATTACK, ImageIO.read(new File("resources/pictures/textures/attack.png")));
            Resources.TEXTURES.add(Resources.HEART, ImageIO.read(new File("resources/pictures/textures/heart.png")));
            Resources.TEXTURES.add(Resources.ARMOR, ImageIO.read(new File("resources/pictures/textures/armor.png")));
            Resources.TEXTURES.add(Resources.GOLD, ImageIO.read(new File("resources/pictures/textures/gold.png")));
            Resources.TEXTURES.add(Resources.PLAYER_1_STAND_DOWN, ImageIO.read(new File("resources/pictures/player1/down1.png")));
            Resources.TEXTURES.add(Resources.PLAYER_1_WALK_DOWN, ImageIO.read(new File("resources/pictures/player1/down2.png")));
            Resources.TEXTURES.add(Resources.PLAYER_1_STAND_LEFT, ImageIO.read(new File("resources/pictures/player1/left1.png")));
            Resources.TEXTURES.add(Resources.PLAYER_1_WALK_LEFT, ImageIO.read(new File("resources/pictures/player1/left2.png")));
            Resources.TEXTURES.add(Resources.PLAYER_1_STAND_RIGHT, ImageIO.read(new File("resources/pictures/player1/right1.png")));
            Resources.TEXTURES.add(Resources.PLAYER_1_WALK_RIGHT, ImageIO.read(new File("resources/pictures/player1/right2.png")));
            Resources.TEXTURES.add(Resources.PLAYER_1_STAND_UP, ImageIO.read(new File("resources/pictures/player1/up1.png")));
            Resources.TEXTURES.add(Resources.PLAYER_1_WALK_UP, ImageIO.read(new File("resources/pictures/player1/up2.png")));
            Resources.TEXTURES.add(Resources.PLAYER_3_STAND_DOWN, ImageIO.read(new File("resources/pictures/player3/p3standdown.png")));
            Resources.TEXTURES.add(Resources.PLAYER_3_WALK_DOWN, ImageIO.read(new File("resources/pictures/player3/p3walkdown.png")));
            Resources.TEXTURES.add(Resources.PLAYER_3_STAND_LEFT, ImageIO.read(new File("resources/pictures/player3/p3standleft.png")));
            Resources.TEXTURES.add(Resources.PLAYER_3_WALK_LEFT, ImageIO.read(new File("resources/pictures/player3/p3walkleft.png")));
            Resources.TEXTURES.add(Resources.PLAYER_3_STAND_RIGHT, ImageIO.read(new File("resources/pictures/player3/p3standright.png")));
            Resources.TEXTURES.add(Resources.PLAYER_3_WALK_RIGHT, ImageIO.read(new File("resources/pictures/player3/p3walkright.png")));
            Resources.TEXTURES.add(Resources.PLAYER_3_STAND_UP, ImageIO.read(new File("resources/pictures/player3/p3standup.png")));
            Resources.TEXTURES.add(Resources.PLAYER_3_WALK_UP, ImageIO.read(new File("resources/pictures/player3/p3walkup.png")));
            Resources.TEXTURES.add(Resources.PLAYER_4_STAND_DOWN, ImageIO.read(new File("resources/pictures/player4/p4standdown.png")));
            Resources.TEXTURES.add(Resources.PLAYER_4_WALK_DOWN, ImageIO.read(new File("resources/pictures/player4/p4walkdown.png")));
            Resources.TEXTURES.add(Resources.PLAYER_4_STAND_LEFT, ImageIO.read(new File("resources/pictures/player4/p4standleft.png")));
            Resources.TEXTURES.add(Resources.PLAYER_4_WALK_LEFT, ImageIO.read(new File("resources/pictures/player4/p4walkleft.png")));
            Resources.TEXTURES.add(Resources.PLAYER_4_STAND_RIGHT, ImageIO.read(new File("resources/pictures/player4/p4standright.png")));
            Resources.TEXTURES.add(Resources.PLAYER_4_WALK_RIGHT, ImageIO.read(new File("resources/pictures/player4/p4walkright.png")));
            Resources.TEXTURES.add(Resources.PLAYER_4_STAND_UP, ImageIO.read(new File("resources/pictures/player4/p4standup.png")));
            Resources.TEXTURES.add(Resources.PLAYER_4_WALK_UP, ImageIO.read(new File("resources/pictures/player4/p4walkup.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
