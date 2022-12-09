package com.awakening.app;

import com.awakening.app.game.Player;
import com.awakening.app.game.Room;
import com.awakening.app.TextParser;
import org.w3c.dom.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


class UI {
    private TextParser textParser = new TextParser();
    public void displayGameInfo(Player player) {
        System.out.println("You are in the " + player.getCurrentRoom().getName() + ".");
        System.out.println("Your items are " + player.getInventory());
        // display exits with room names
        String exits = "Exits: \n";
        for (String direction : player.getCurrentRoom().getDirections().keySet()) {
            exits += direction + ": " + player.getCurrentRoom().getDirections().get(direction) + "\n";
        }

        System.out.println(exits);
    }
    public void displayGamePlayOptions() {
        System.out.println("Your gameplay options are:\n" +
                "A two word command is expected: 'Verb + Noun'\n" +
                "Verb:" + textParser.getAllowedCommands() +
                "\nNoun:" + textParser.getAllowedNouns());

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