package com.awakening.app;

import com.apps.util.Prompter;
import com.awakening.app.game.Player;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//Class that will control gameplay
public class Game {

    private static final Prompter prompter = new Prompter(new Scanner(System.in));
    private UI ui = new UI();
    private TextParser textParser = new TextParser();
    private Player player = new Player();


    public Game() {
    }

    public void initGame() {
        boolean gameOver = false;
        boolean gameStart = false;
        String confirmation;

        ui.splashScreen();

        //TODO:  Hency - check with team if ok to remove these commented lines of code
        //String playGame = prompter.prompt("Do you want to play Awakening? [Y/N]").toLowerCase();
        // The purpose her is to capture correct/incorrect responses and manage them accordingly
//        if (!"y".equals(playGame)) {
//            gameOver = true;
//        }

        while (!gameStart){
            String playGame = prompter.prompt("Do you want to play Awakening? [Y/N]").toLowerCase().trim();


            switch (playGame) {
                case ("y"): case("yes"):
                    gameStart = true;
                    break;
                case ("n"): case("no"):
                    confirmation = prompter.prompt("Are you sure? [Y/N]").toLowerCase().trim();
                    if(!"y".equals(confirmation)){
                        break;
                    }
                    gameOver = true;
                    gameStart = true;
                    break;
                default:
                    System.out.println("Invalid input, please provide [Y] for Yes, [N] for No.");
                    System.out.println();
            }
            //This is to add a line, with the intention of spacing out the text fields of U/I and game text
            System.out.println();
        }




        while (!gameOver) {
            ui.displayGameInfo(player);
            ui.displayGamePlayOptions();
            String response = prompter.prompt("What do you want to do?");
            List<String> move = textParser.parseInput(response);
            if ("quit".equals(move.get(0))) {
                confirmation = prompter.prompt("Are you sure? [Y/N]").toLowerCase().trim();
                switch (confirmation){
                    case ("y"): case ("yes"):
                        gameOver = true;
                        break;

                    case ("n"): case("no"):
                        break;

                }
//                if(!"n".equals(confirmation)){
//                gameOver = true;
//                }
            }
            else {
                // something else
            }
        }
    }

    private void startGame() {
    }
}

