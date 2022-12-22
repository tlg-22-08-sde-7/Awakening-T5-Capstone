package com.awakening.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Allowed Commands
    private List<String> allowedCommands = new ArrayList<>(Arrays.asList("go", "get", "look", "quit", "use", "help"));
    private List<String> allowedFightCommands = new ArrayList<>(Arrays.asList("help", "quit", "use"));

    public List<String> getAllowedCommands() {
        return allowedCommands;
    }

    public List<String> getAllowedNouns() {
        return allowedNouns;
    }

    public List<String> getFightNouns() {
        return fightNouns;
    }

    // method to print array of nouns to string and in color
    public String displayAllowedNouns() {
        return BLUE+allowedNouns.toString()+RESET;
    }
    // method to print array of commands to string and in color
    public String displayAllowedCommands() {
        return GREEN+allowedCommands.toString()+RESET;
    }
    private List<String> fightNouns = new ArrayList<>(Arrays.asList("barbell", "camera", "wood-cane",
            "first-aid-kit", "cellphone", "bandages", "fire-extinguisher", "axe", "fist"));

    private List<String> allowedNouns = new ArrayList<>(Arrays.asList("map", "north",
            "south", "east", "west", "ghost", "item", "camera","cellphone","key","journal","batteries",
            "file","bandages","press-pass","paper-clip", "picture","fire-extinguisher",
            "axe","first-aid-kit","barbell","wood-cane","master-key","tylenol", "records-key"));




    public List<String> parseInput(String input) {

        List<String> result = new ArrayList<>(Arrays.asList(input.strip().toLowerCase().trim().split(" ")));


        String verb = result.get(0);
        String noun;


        if (result.size() == 1 && "help".equals(verb)) {
            return result;
        }
        if (result.size() == 1 && "quit".equals(verb)) {
            return result;

        } else if (result.size() != 2) {
            System.out.println(RED + "Command not recognized. Only two word commands are recognized.\n" + RESET); //+
//                    "First word must be a verb from the following list: " + displayAllowedCommands() +
//                    "\nSecond word must be a noun from the following list: " + displayAllowedNouns());
            result.set(0, "invalid");
            return result;
        }
        if (!allowedCommands.contains(verb) || !allowedNouns.contains(result.get(1))) {
            System.out.print(RED + "Invalid input, please try again. Type 'help' for a list of commands.\n" + RESET);
//            System.out.println("Invalid input, please try again. Type 'help' for a list of commands.\n");
        }
        if (!allowedCommands.contains(verb)) {
            System.out.println(RED + "First word in command not recognized" + RESET);
            result.set(0, "invalid");

        }
        if (!allowedNouns.contains(result.get(1))) {
            System.out.println(RED + "Second word in command not recognized" + RESET);
            result.set(0, "invalid");
        }

        return result;
    }
    public List<String> fightParseInput(String input) {

        List<String> result = new ArrayList<>(Arrays.asList(input.strip().toLowerCase().trim().split(" ")));


        String verb = result.get(0);
        String noun;


        if (result.size() == 1 && "help".equals(verb)) {
            return result;
        }
        if (result.size() == 1 && "quit".equals(verb)) {
            return result;

        } else if (result.size() != 2) {
            System.out.println(RED + "Command not recognized. Only two word commands are recognized.\n" + RESET); //+
//                    "First word must be a verb from the following list: " + displayAllowedCommands() +
//                    "\nSecond word must be a noun from the following list: " + displayAllowedNouns());
            result.set(0, "invalid");
            return result;
        }
        if (!allowedFightCommands.contains(verb) || !fightNouns.contains(result.get(1))) {
            System.out.print(RED + "Invalid input, please try again. Type 'help' for a list of commands.\n" + RESET);
//            System.out.println("Invalid input, please try again. Type 'help' for a list of commands.\n");
        }
        if (!allowedFightCommands.contains(verb)) {
            System.out.println(RED + "First word in command not recognized" + RESET);
            result.set(0, "invalid");

        }
        if (!fightNouns.contains(result.get(1))) {
            System.out.println(RED + "Second word in command not recognized" + RESET);
            result.set(0, "invalid");
        }

        return result;
    }
}
