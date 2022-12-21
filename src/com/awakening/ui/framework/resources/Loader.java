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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
