package com.awakening.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class UI {
    public void displayGameInfo() {
        System.out.println("You wake up on the floor in a dark place. \n" +
                "You feel disoriented  and  cannot recall how you got here. \n" +
                "As you look around, you see a few rays light filtering through an opening. \n");
        System.out.println("You find a camera on the ground with a note.\n" +
                "\"Use the camera to defend yourself. \n" +
                "It seems to affect them.\"\n" +
                "   -J.S.\n");
        System.out.println("Not wanting to be here any longer,\n" +
                "you get up and start trying to find a way out.\n");
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
    public static void splashScreen() throws IOException {
        String welcome = Files.readString(Path.of("resources/ASCII/banner.txt"));
        System.out.println(welcome);
    }

    public static void main(String[] args) throws IOException {
        UI ui = new UI();
        ui.splashScreen();
        ui.displayGameInfo();
    }
}