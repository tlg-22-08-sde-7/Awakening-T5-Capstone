package com.awakening.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {

    public List<String> parseInput(String input) {
        List<String> result = new ArrayList<>(Arrays.asList(input.toLowerCase().split(" ")));


        String verb = result.get(0);
        String noun;
        List<String> allowedCommands = new ArrayList<>(Arrays.asList("go", "get", "look", "quit"));
        List<String> allowedNouns = new ArrayList<>(Arrays.asList("map", "north", "south", "east", "west", "game"));

        if (result.size() == 1 && "quit".equals(verb)) {
            return result;
        } else if (result.size() != 2) {
            System.out.println("Command not recognized. Only two word commands are recognized.\n" +
                    "First word must be a verb from the following list: " + allowedCommands +
                    "\nSecond word must be a noun from the following list: " + allowedNouns);
            result.set(0, "invalid");
            return result;
        }
        if (!allowedCommands.contains(verb)) {
            System.out.println("allowed commands are " + allowedCommands);
            result.set(0, "invalid");

        }
        if (!allowedNouns.contains(result.get(1))) {
            System.out.println("allowed nouns are " + allowedNouns);
            result.set(0, "invalid");
        }
        return result;
    }
}
