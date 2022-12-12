package com.awakening.app;

import com.apps.util.Prompter;
import com.awakening.app.game.*;
import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.w3c.dom.Text;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//Class that will control gameplay
public class Game {

    public static RoomMap world;
    public static Player player = new Player();
    public static NPC npc = new NPC();
    private static final Prompter prompter = new Prompter(new Scanner(System.in));
    private UI ui = new UI();
    private TextParser textParser = new TextParser();
    private List<Room> rooms = new ArrayList<>();

    public Game() {
    }

    public void initGame() {
        boolean gameOver = false;
        boolean gameStart = false;
        String confirmation;

        ui.splashScreen();


        while (!gameStart) {
            String playGame = prompter.prompt("Do you want to play Awakening? [Y/N]").toLowerCase().trim();


            switch (playGame) {
                case ("y"):
                case ("yes"):
                    System.out.println();
                    ui.displayGamePlayOptions();
                    gameStart = true;
                    break;
                case ("n"):
                case ("no"):
                    confirmation = prompter.prompt("Are you sure? [Y/N]").toLowerCase().trim();
                    if (!"y".equals(confirmation)) {
                        break;
                    }
                    gameOver = true;
                    gameStart = true;
                    break;
                default:
                    System.out.println(TextParser.RED+"Invalid input, please provide [Y] for Yes, [N] for No."+TextParser.RESET);
                    System.out.println();
            }
            //This is to add a line, with the intention of spacing out the text fields of U/I and game text
            System.out.println();
        }

        generateWorld();

        while (!gameOver) {
            ui.displayGameInfo(player);
//            ui.displayGamePlayOptions();
            String response = prompter.prompt("What do you want to do?\n");
            List<String> move = textParser.parseInput(response);
            while ("invalid".equals(move.get(0))) {
                response = prompter.prompt("What do you want to do?\n");
                move = textParser.parseInput(response);
            }

            if ("quit".equals(move.get(0))) {
                confirmation = prompter.prompt("Are you sure? [Y/N]").toLowerCase().trim();
                switch (confirmation) {
                    case ("y"):
                    case ("yes"):
                        gameOver = true;
                        break;

                    case ("n"):
                    case ("no"):
                        break;
                }
            }
            else if ("help".equals(move.get(0))) {
                ui.displayGamePlayOptions();
            }
            else {
                executeCommand(move);
            }
        }
    }

    private void executeCommand(List<String> move) {
        // execute command based on verb
        String verb = move.get(0);
        String noun = move.get(1);
        switch (verb) {
            case "go":
                move(noun);
                break;
            case "quit":
                System.out.println("Thanks for playing!");
                break;
            case "look":
                look(noun);
                break;
            default:
                System.out.println(TextParser.RED + "Invalid command" + TextParser.RESET);
        }
    }

    private void move(String direction) {
        RoomMap.RoomLayout currentRoom = player.getCurrentRoom();
        RoomMap.RoomLayout nextRoom = world.getRoom(currentRoom.getDirections().get(direction));
        if (nextRoom == null) {
            System.out.println("You can't go that way");
        } else {
            player.setCurrentRoom(nextRoom);
        }
    }

    private void look(String noun) {
        RoomMap.RoomLayout currentRoom = player.getCurrentRoom();
        String npcName = currentRoom.getNpcName().toString();


        if (noun.equals("ghost")) {
            if (npcName == null){
                System.out.println("There is no ghost in this room");
                return;
            }
            String ghostDesc = "";
            String npcGhost = npc.getGhost(npcName);
            ghostDesc+= npcGhost +"\n";
            System.out.println(ui.wrapFrame(ghostDesc));

        }
        else if(noun.equals("item")) {
            System.out.println("print list of items here");
        }

    }


    private void generateWorld() {
        try (Reader reader = new FileReader("resources/JSON/roomsListNew.json")) {
            world = new Gson().fromJson(reader, RoomMap.class);
            player.setCurrentRoom(world.getBasement());
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadNPC();
    }

    private void loadNPC(){
        try (Reader reader = new FileReader("resources/JSON/NPC.json")) {
            npc = new Gson().fromJson(reader, NPC.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startGame() {
    }
}

