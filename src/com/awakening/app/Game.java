package com.awakening.app;
import com.apps.util.Prompter;
import java.util.Scanner;

//Class that will control gameplay
public class Game {

    private static final Prompter prompter = new Prompter(new Scanner(System.in));

    public Game(){
    }

    public void initGame(){

            String level = "1";
            boolean quit = false;
            while (!quit) {
                String selectLevel = prompter.prompt("Do you want to play Awakening? [Y/N]");
                System.out.println();
                switch (selectLevel) {
                    case ("Y"):
                        startGame();
                        quit = true;
                        break;
                    case ("N"):
                        quitGame();
                        quit = true;
                        break;
                    default:
                        System.out.println("Invalid input, please provide [Y] for Yes, [N] for No.");
                        System.out.println();
                }
            }
        }

    private void quitGame() {
    }

    private void startGame() {
    }
}

