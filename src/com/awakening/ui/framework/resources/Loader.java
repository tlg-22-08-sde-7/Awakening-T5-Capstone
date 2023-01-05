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
            //Loading images to be used in the game using ImageIO
            Resources.TEXTURES.add(Resources.FLOOR, ImageIO.read(new File("resources/pictures/textures/floor.png")));
            Resources.TEXTURES.add(Resources.STONE, ImageIO.read(new File("resources/pictures/textures/stone.png")));
            Resources.TEXTURES.add(Resources.WALL, ImageIO.read(new File("resources/pictures/textures/wall.png")));
            Resources.TEXTURES.add(Resources.TILE, ImageIO.read(new File("resources/pictures/textures/tile.png")));
            Resources.TEXTURES.add(Resources.GRASS, ImageIO.read(new File("resources/pictures/textures/wood.png")));
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
            Resources.TEXTURES.add(Resources.AXE, ImageIO.read(new File("resources/pictures/axe.png")));
            Resources.TEXTURES.add(Resources.BANDAGES, ImageIO.read(new File("resources/pictures/bandages.png")));
            Resources.TEXTURES.add(Resources.BARBELL, ImageIO.read(new File("resources/pictures/barbell.png")));
            Resources.TEXTURES.add(Resources.BATTERIES, ImageIO.read(new File("resources/pictures/batteries.png")));
            Resources.TEXTURES.add(Resources.CAMERA, ImageIO.read(new File("resources/pictures/camera.png")));
            Resources.TEXTURES.add(Resources.CELLPHONE, ImageIO.read(new File("resources/pictures/cellphone.png")));
            Resources.TEXTURES.add(Resources.FIRE_EXTINGUISHER, ImageIO.read(new File("resources/pictures/fireextinguisher.png")));
            Resources.TEXTURES.add(Resources.FIRST_AID_KIT, ImageIO.read(new File("resources/pictures/firstaidkit.png")));
            Resources.TEXTURES.add(Resources.KEY, ImageIO.read(new File("resources/pictures/key.png")));
            Resources.TEXTURES.add(Resources.JOURNAL, ImageIO.read(new File("resources/pictures/journal.png")));
            Resources.TEXTURES.add(Resources.MASTER_KEY, ImageIO.read(new File("resources/pictures/masterkey.png")));
            Resources.TEXTURES.add(Resources.PAPER_CLIP, ImageIO.read(new File("resources/pictures/paperclip.png")));
            Resources.TEXTURES.add(Resources.PATIENT_FILE, ImageIO.read(new File("resources/pictures/patientfile.png")));
            Resources.TEXTURES.add(Resources.PICTURE, ImageIO.read(new File("resources/pictures/picture.png")));
            Resources.TEXTURES.add(Resources.PRESS_PASS, ImageIO.read(new File("resources/pictures/presspass.png")));
            Resources.TEXTURES.add(Resources.TYLENOL, ImageIO.read(new File("resources/pictures/tylenol.png")));
            Resources.TEXTURES.add(Resources.WOOD_CANE, ImageIO.read(new File("resources/pictures/woodcane.png")));
            Resources.TEXTURES.add(Resources.UPPER_STAIRS, ImageIO.read(new File("resources/pictures/textures/stairs.png")));
            Resources.TEXTURES.add(Resources.DOOR, ImageIO.read(new File("resources/pictures/door.png")));
            Resources.TEXTURES.add(Resources.MASTER_DOOR, ImageIO.read(new File("resources/pictures/masterdoor.png")));
            Resources.TEXTURES.add(Resources.HOSPITAL_BED, ImageIO.read(new File("resources/pictures/textures/bed.png")));
            Resources.TEXTURES.add(Resources.BAT, ImageIO.read(new File("resources/pictures/textures/enemy.png")));
            Resources.TEXTURES.add(Resources.BAT_2, ImageIO.read(new File("resources/pictures/textures/enemy_2.png")));
            Resources.TEXTURES.add(Resources.GHOST_MAIN1, ImageIO.read(new File("resources/pictures.ghost/ghostMain1.png")));
            Resources.TEXTURES.add(Resources.GHOST_MAIN2, ImageIO.read(new File("resources/pictures.ghost/ghostMain2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
