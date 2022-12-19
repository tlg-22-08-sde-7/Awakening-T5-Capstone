package com.awakening.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.awakening.app.game.*;
import com.awakening.app.game.Item;
import com.awakening.app.game.Player;
import com.awakening.app.game.Room;
import com.awakening.app.game.RoomMap;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


//Class that will control gameplay
public class Game {

    public static RoomMap world;
    public static RoomMap.RoomLayout roomLayout = new RoomMap.RoomLayout();
    public static List<Item.ItemsSetup> roomItems;
    public static Player player = new Player();
    public static Player.PlayerLayout currentPlayer;
    public static NPC npc = new NPC();
    public static GameText gameText = new GameText();

    private static final Prompter prompter = new Prompter(new Scanner(System.in));
    private List<String> approvedItems = new ArrayList<>(Arrays.asList("camera", "cellphone", "key", "journal", "press-pass",
            "batteries", "file", "bandages", "bandages", "paper-clip", "picture", "fire-extinguisher",
            "axe", "first-aid-kit", "barbell", "wood-cane", "master-key", "tylenol", "records-key"));
    private UI ui = new UI();
    private TextParser textParser = new TextParser();
    private List<Room> rooms = new ArrayList<>();
    boolean gameOver = false;

    public Game() {
    }

    public void initGame() {
        boolean gameStart = false;
        String confirmation;

        ui.splashScreen();

        while (!gameStart) {
            String playGame = prompter.prompt("Do you want to play Awakening? [Y/N]").toLowerCase().trim();


            switch (playGame) {
                case ("y"):
                case ("yes"):
                    System.out.println();
                    generateWorld();
                    playerSelect();
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
                    System.out.println(TextParser.RED + "Invalid input, please provide [Y] for Yes, [N] for No." + TextParser.RESET);
                    System.out.println();
            }
            //This is to add a line, with the intention of spacing out the text fields of U/I and game text
            System.out.println();
        }

        while (!gameOver) {
            ui.clearConsole();

            ui.displayGameInfo(player, currentPlayer);
            gameText.roomText(player);

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
            } else if ("help".equals(move.get(0))) {
                ui.displayGamePlayOptions();
            } else {
                executeCommand(move);
            }
            gameStateCheck();

        }
    }
    private Player.PlayerLayout playerSelect() {
        System.out.println("PLAYER SELECT: Choose from one of the following characters:");
        System.out.println();
        System.out.println(player.toString());
        String namePrompt = prompter.prompt("\n>", "(?i)lennie|sandra|jimmy|cassidy",
                "Please select a valid character!").toLowerCase(Locale.ROOT).trim();
        String name = namePrompt.toLowerCase().trim();
        switch (name) {
            case "lennie":
                return currentPlayer = player.getPlayer1();
            case "sandra":
                return currentPlayer = player.getPlayer2();
            case "jimmy":
                return currentPlayer = player.getPlayer3();
            case "cassidy":
                return currentPlayer = player.getPlayer4();
        }
        return null;
    }

    private void gameStateCheck() {
        if (player.getCurrentRoom() != world.getRoom("Front Desk")) {
            return;
        }
        if (player.printInventory().contains("master-key") && player.printInventory().contains("file")) {
            gameOver = true;
            printGameWon();
        }
    }

    private void printGameWon() {
        String endText = null;
        try{
            endText = Files.readString(Path.of("resources/ASCII/gameEnd.txt"));
            System.out.println(endText);
        }catch (IOException e){
            e.printStackTrace();
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
            case "get":
                if (approvedItems.contains(noun)) {
                    pickUp(noun);
                    System.out.println("You have successfully added " + noun + " to your inventory!");
                    Console.pause(1500);
                } else {
                    System.out.println(TextParser.RED + "Invalid command" + TextParser.RESET);
                    Console.pause(1500);
                }
                break;
            default:
                System.out.println(TextParser.RED + "Invalid command" + TextParser.RESET);
                Console.pause(1500);
        }
    }

    private void move(String direction) {
        RoomMap.RoomLayout currentRoom = player.getCurrentRoom();
        RoomMap.RoomLayout nextRoom = world.getRoom(currentRoom.getDirections().get(direction));
        if (nextRoom == null) {
            System.out.println(TextParser.RED + "You can't go that way!" + TextParser.RESET);
        } else if (nextRoom.isLocked()) {
            String input = prompter.prompt("Would you like to unlock the door? (yes or no)\n>", "(?i)yes|no", "Please enter yes or no!");
            if (input.equals("yes") && player.printInventory().contains("master-key")) {
                System.out.println(TextParser.GREEN + "You have unlocked something!" + TextParser.RESET);
                Console.pause(1500);
                player.setCurrentRoom(nextRoom);
            } else if (input.equals("yes") && player.printInventory().contains("key") &&
                    nextRoom.getName().contains("Stairs")) {
                System.out.println(TextParser.GREEN + "You have unlocked something!" + TextParser.RESET);
                Console.pause(1500);
                player.setCurrentRoom(nextRoom);
            } else if (input.equals("yes") && player.printInventory().contains("key") &&
                    nextRoom.getName().contains("Janitor Closet")) {
                System.out.println(TextParser.GREEN + "You have unlocked something!" + TextParser.RESET);
                Console.pause(1500);
                player.setCurrentRoom(nextRoom);
            } else if (input.equals("yes") && player.printInventory().contains("records-key") &&
                    nextRoom.getName().contains("Filing Cabinet")) {
                System.out.println(TextParser.GREEN + "You have unlocked something!" + TextParser.RESET);
                Console.pause(1500);
                player.setCurrentRoom(nextRoom);
            } else if (input.equals("yes") && player.printInventory().contains("press-pass") &&
                    nextRoom.getName().contains("Patient Room")) {
                System.out.println(TextParser.GREEN + "You have unlocked something!" + TextParser.RESET);
                Console.pause(1500);
                player.setCurrentRoom(nextRoom);
            } else if (input.equals("no")) {
                player.setCurrentRoom(currentRoom);
            }
            else {
                System.out.println(TextParser.RED + "You can't go that way! You don't have the key!" + TextParser.RESET);
                Console.pause(1500);
            }
        }
        else {
            player.setCurrentRoom(nextRoom);
        }
    }

    private void look(String noun) {
        RoomMap.RoomLayout currentRoom = player.getCurrentRoom();

        if (noun.equals("ghost")) {
            String npcName = currentRoom.getNpcName().toString();
            if (npcName == null) {
                System.out.println("There is no ghost in this room!");
                Console.pause(1500);
                return;
            }
            String ghostDesc = "";
            String npcGhost = npc.getGhost(npcName);
            ghostDesc += npcGhost + "\n";
            System.out.println(ui.wrapFrame(ghostDesc));
            prompter.prompt("Hit enter to continue...");
        } else if (noun.equals("map")) {
            ui.displayMap();
        } else if (approvedItems.contains(noun) && currentRoom.getItems().contains(noun)) {
            String itemDesc = "";
            Item.ItemsSetup item = findItem(noun);
            assert item != null;
            itemDesc = item.getDescription();
            System.out.println(itemDesc);
        } else if (approvedItems.contains(noun) && player.printInventory().contains(noun)) {
            String itemDesc = "";
            Item.ItemsSetup item = findItem(noun);
            assert item != null;
            itemDesc = item.getDescription();
            System.out.println(itemDesc);
        } else {
            System.out.println(TextParser.RED + "Invalid command" + TextParser.RESET);
            Console.pause(1500);
        }
    }

    private void pickUp(String noun) {
        RoomMap.RoomLayout currentRoom = player.getCurrentRoom();
        List itemList = player.getCurrentRoom().getItems();

        int index;
        Item.ItemsSetup item = findItem(noun);

        if (item == null) {
            System.out.println(noun + " is not in " + currentRoom);
        } else if (itemList.contains(noun)) {
            player.addToInventory(item);
            for (int i = 0; i < itemList.size(); i++) {
                if (noun.equals(itemList.get(i))) {
                    index = i;
                    //Remove item from room
                    player.getCurrentRoom().getItems().remove(index);
                }
            }
        } else {
            System.out.println(TextParser.RED + "Invalid command" + TextParser.RESET);
            Console.pause(1500);
        }
    }


    private Item.ItemsSetup findItem(String noun) {
        for (Item.ItemsSetup roomItem : roomItems) {
            if (noun.equals(roomItem.getName())) {
                return roomItem;
            }
        }
        return null;
    }

    private void generateWorld() {
        loadRoomDescriptions();
        loadPlayer();
        try (Reader reader = new FileReader("resources/JSON/roomsListNew.json")) {
            world = new Gson().fromJson(reader, RoomMap.class);
            player.setCurrentRoom(world.getBasement());
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadNPC();
    }

    private void loadNPC() {
        try (Reader reader = new FileReader("resources/JSON/NPC.json")) {
            npc = new Gson().fromJson(reader, NPC.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        generateItems();
    }

    private void generateItems() {
        Item item;
        try (Reader reader = new FileReader("resources/JSON/Items.json")) {
            item = new Gson().fromJson(reader, Item.class);
            roomItems = item.loadItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadPlayer() {
        try (Reader reader = new FileReader("resources/JSON/Player.json")) {
            player = new Gson().fromJson(reader, Player.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadRoomDescriptions() {
        try (Reader reader = new FileReader("resources/JSON/GameText.json")) {
            gameText = new Gson().fromJson(reader, GameText.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startGame() {
    }
}

