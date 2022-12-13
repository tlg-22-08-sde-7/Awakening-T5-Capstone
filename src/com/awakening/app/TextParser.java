package com.awakening.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {
    private List<String> allowedCommands = new ArrayList<>(Arrays.asList("go", "get", "look", "quit"));

    public List<String> getAllowedCommands() {
        return allowedCommands;
    }

    public List<String> getAllowedNouns() {
        return allowedNouns;
    }

    private List<String> allowedNouns = new ArrayList<>(Arrays.asList("map", "north", "south", "east", "west", "camera"));

    public List<String> parseInput(String input) {
        List<String> result = new ArrayList<>(Arrays.asList(input.toLowerCase().trim().split(" ")));


        String verb = result.get(0);
        String noun;

        if (result.size() == 1 && "help".equals(verb)) {
            return result;
        }
        if (result.size() == 1 && "quit".equals(verb)) {
            return result;
        } else if (result.size() != 2) {
            System.out.println("\033[31mCommand not recognized. Only two word commands are recognized.\033[0m\n" +
                    "First word must be a verb from the following list: " + allowedCommands +
                    "\nSecond word must be a noun from the following list: " + allowedNouns);
            result.set(0, "\033[31minvalid\033[0m");
            return result;
        }
        if (!allowedCommands.contains(verb) || !allowedNouns.contains(result.get(1))) {
            System.out.print("\033[31mInvalid input, please try again. Type 'help' for a list of commands. \033[0m\n");
//            System.out.println("Invalid input, please try again. Type 'help' for a list of commands.\n");
        }
        if (!allowedCommands.contains(verb)) {
            System.out.println("allowed commands are " + allowedCommands);
            result.set(0, "\033[31minvalid\033[0m");

        }
        if (!allowedNouns.contains(result.get(1))) {
            System.out.println("allowed nouns are " + allowedNouns);
            result.set(0, "\033[31minvalid\033[0m");
        }
        return result;
    }
}
