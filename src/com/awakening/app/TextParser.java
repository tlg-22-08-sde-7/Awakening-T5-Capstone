package com.awakening.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParser {

    public List<String> parseInput(String input) {
        List<String> result = new ArrayList<>(Arrays.asList(input.toLowerCase().split(" ")));

        if (result.size() == 0) {
            return result;
        }

        String verb;
        String noun;
        List<String> allowedCommands = new ArrayList<>(Arrays.asList("move", "go", "look"));
        List<String> allowedNouns = new ArrayList<>(Arrays.asList("noun"));

        if (result.size() == 1 && "quit".equals(result.get(0))) {
            return result;
        }
        else if (result.size() == 2) {
            verb = result.get(0);
            noun = result.get(1);
//            if (!allowedCommands.contains(verb)) {
//                return new ArrayList<String>();
//            }
        }
        return result;
    }
}
