package com.awakening.app;

import com.apps.util.Prompter;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//Class that will control gameplay
public class Game {

    private static final Prompter prompter = new Prompter(new Scanner(System.in));
    private UI ui = new UI();
    private TextParser textParser = new TextParser();


    public Game() {
    }

    public void initGame() throws IOException {
        boolean gameOver = false;

        ui.splashScreen();


        String playGame = prompter.prompt("Do you want to play Awakening? [Y/N]").toLowerCase();

        if (!"y".equals(playGame)) {
            gameOver = true;
        }
        //                System.out.println();
//
//                switch (playGame) {
//                    case ("Y"):
//                        startGame();
//                        gameOver = true;
//                        break;
//                    case ("N"):
//                        quitGame();
//                        gameOver = true;
//                        break;
//                    default:
//                        System.out.println("Invalid input, please provide [Y] for Yes, [N] for No.");
//                        System.out.println();
//                }

        while (!gameOver) {
            ui.displayGameInfo();
            String response = prompter.prompt("What do you want to do?");
            List<String> move = textParser.parseInput(response);
            if ("quit".equals(move.indexOf(0))) {
                gameOver = true;
            }
            else {
                // something else
            }
        }

    }

    private void startGame() {
    }
}

