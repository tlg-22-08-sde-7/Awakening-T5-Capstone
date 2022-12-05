package com.awakening.app;

import com.awakening.app.game.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class UI {
    public void displayGameInfo(Player player) {
        System.out.println("You are in the basement.");
        System.out.println("Your items are [camera, batteries]");
        System.out.println("You can go [East, North, South]");
    }
    public void displayGamePlayOptions() {
        System.out.println("Your gameplay options are:\n" +
                "'map' to display an ascii map of the game\n" +
                "'look' to examine the room's contents,\n" +
                "'move (north, south, east, west)' to move to a room *if a room is in that direction,\n" +
                "'get' an item,\n" +
                "'use' an item,\n" +
                "'quit' to exit the game\n");

    }
    public static void splashScreen() {
        String welcome = null;
        try {
            welcome = Files.readString(Path.of("resources/ASCII/banner.txt"));
            System.out.println(welcome);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}