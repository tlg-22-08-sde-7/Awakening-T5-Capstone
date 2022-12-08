package com.awakening.app;

import com.awakening.app.game.Player;
import com.awakening.app.game.Room;
import com.awakening.app.TextParser;
import org.w3c.dom.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class UI {
    public void displayGameInfo(Player player) {
        System.out.println("You are in the " + player.getCurrentRoom().getName() + ".");
        System.out.println("Your items are " + player.getInventory());
        System.out.println("You can go South: " + player.getCurrentRoom().getDirections().getSouth());
    }
    public void displayGamePlayOptions() {
        System.out.println("Your gameplay options are:\n" +
                "A two two word command is expected: 'verb + noun'\n" +
                "Verb: [go, get, look, use, quit]\n" +
                "Noun: [north, south, east, west, map]\n");

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

    public void loadRooms(String locations) {
        
    }

    public static void main(String[] args) {
        UI ui = new UI();


        ui.loadRooms("Basement");
    }

}