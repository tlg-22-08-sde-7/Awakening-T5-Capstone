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
        String infoText = "";
        infoText += "You are in the " + player.getCurrentRoom().getName() + ".\n";
        infoText += "Your items are " + player.getInventory() + ".\n";
        // display exits with room names
        infoText += "Exits : " + player.getCurrentRoom().getDirections().keySet() + ".\n";
        System.out.println(wrapFrame(infoText));
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

    public String wrapFrame(String text) {
        // wrap text in ascci frame
        String[] lines = text.split("\\n");
        int longestLine = 0;
        for (String line : lines) {
            if (line.length() > longestLine) {
                longestLine = line.length();
            }
        }

        String frame = "";
        String top = "╔";
        String textBody = "";
        String bottom = "╚";
        for (int i = 0; i < longestLine + 2; i++) {
            top += "═";
            bottom += "═";
        }
        top += "╗\n";
        bottom += "╝\n";

        for (String line : lines) {
            int lineLength = line.length();
            int spaces = longestLine - lineLength;
            for (int i = 0; i < spaces; i++) {
                line += " ";
            }
            textBody += "║ " + line + " ║\n";
        }

        frame = top + textBody + bottom;

        return frame;
    }
}