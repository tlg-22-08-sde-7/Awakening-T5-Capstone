package framework.gamestates.gui.resources;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Loader {

    public static void load (){
        try {
//            File texturesFolder = new File("res/rooms");
//            for(File imgFile : texturesFolder.listFiles()) {
//                Resources.ROOMS.put(imgFile.getName(), ImageIO.read(imgFile));
//            }
            Resources.TEXTURES.add(Resources.EARTH, ImageIO.read(new File("res/textures/earth.png")));
            Resources.TEXTURES.add(Resources.SAND, ImageIO.read(new File("res/textures/sand.png")));
            Resources.TEXTURES.add(Resources.WALL, ImageIO.read(new File("res/textures/wall.png")));
//            Resources.TEXTURES.add(Resources.TILE, ImageIO.read(new File("res/textures/tile.png")));
            Resources.TEXTURES.add(Resources.GRASS, ImageIO.read(new File("res/textures/grass.png")));
            Resources.TEXTURES.add(Resources.DOWN1, ImageIO.read(new File("res/pictures.player1/down1.png")));
            Resources.TEXTURES.add(Resources.DOWN2, ImageIO.read(new File("res/pictures.player1/down2.png")));
            Resources.TEXTURES.add(Resources.LEFT1, ImageIO.read(new File("res/pictures.player1/left1.png")));
            Resources.TEXTURES.add(Resources.LEFT2, ImageIO.read(new File("res/pictures.player1/left2.png")));
            Resources.TEXTURES.add(Resources.RIGHT1, ImageIO.read(new File("res/pictures.player1/right1.png")));
            Resources.TEXTURES.add(Resources.RIGHT2, ImageIO.read(new File("res/pictures.player1/right2.png")));
            Resources.TEXTURES.add(Resources.UP1, ImageIO.read(new File("res/pictures.player1/up1.png")));
            Resources.TEXTURES.add(Resources.UP2, ImageIO.read(new File("res/pictures.player1/up2.png")));
//            Resources.TEXTURES.add(Resources.STAIRS, ImageIO.read(new File("res/textures/stairs.png")));
//            Resources.TEXTURES.add(Resources.CHEST, ImageIO.read(new File("res/textures/chest.png")));
//            Resources.TEXTURES.add(Resources.ENEMY, ImageIO.read(new File("res/textures/enemy.png")));
//            Resources.TEXTURES.add(Resources.ENEMY_2, ImageIO.read(new File("res/textures/enemy_2.png")));
//            Resources.TEXTURES.add(Resources.ATTACK, ImageIO.read(new File("res/textures/attack.png")));
//            Resources.TEXTURES.add(Resources.HEART, ImageIO.read(new File("res/textures/heart.png")));
//            Resources.TEXTURES.add(Resources.ARMOR, ImageIO.read(new File("res/textures/armor.png")));
//            Resources.TEXTURES.add(Resources.GOLD, ImageIO.read(new File("res/textures/gold.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
