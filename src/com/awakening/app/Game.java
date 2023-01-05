package com.awakening.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.awakening.app.game.*;
import com.awakening.app.game.Item;
import com.awakening.app.game.Player;
import com.awakening.app.game.Room;
import com.awakening.app.game.RoomMap;
import com.google.gson.Gson;

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
    public static NPC.NPCLayout currentNPC;
    public static GameText gameText = new GameText();

    private static final Prompter prompter = new Prompter(new Scanner(System.in));
    private List<String> approvedItems = new ArrayList<>(Arrays.asList("camera", "cellphone", "key", "journal", "press-pass",
            "batteries", "file", "bandages", "bandages", "paper-clip", "picture", "fire-extinguisher",
            "axe", "first-aid-kit", "barbell", "wood-cane", "master-key", "tylenol", "records-key"));
    private List<String> fightNouns = new ArrayList<>(Arrays.asList("barbell", "camera", "wood-cane",
            "first-aid-kit", "cellphone", "bandages", "fire-extinguisher", "axe", "fist"));
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

        gameCycle();
    }

    private void gameCycle() {
        String confirmation;
        while (!gameOver) {
            ui.clearConsole();

            if (player.getCurrentRoom().getNpcName() != null) {
                runningIntoAGhost();
            } else {
                ui.displayGameInfo(player, currentPlayer);
                gameText.roomText(player);
            }

            String response = prompter.prompt("What do you want to do?\n>");
            List<String> move = textParser.parseInput(response);
            while ("invalid".equals(move.get(0))) {
                response = prompter.prompt("What do you want to do?\n>");
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

    //method to select the player at the beginning of the game
    private void playerSelect() {
        System.out.println("PLAYER SELECT: Choose from one of the following characters:");
        System.out.println();
        System.out.println(player.toString());
        String namePrompt = prompter.prompt("\n>", "(?i)lennie|sandra|jimmy|cassidy",
                "Please select a valid character!").toLowerCase(Locale.ROOT).trim();
        String name = namePrompt.toLowerCase().trim();
        switch (name) {
            case "lennie":
                currentPlayer = player.getPlayer1();
                return;
            case "sandra":
                currentPlayer = player.getPlayer2();
                return;
            case "jimmy":
                currentPlayer = player.getPlayer3();
                return;
            case "cassidy":
                currentPlayer = player.getPlayer4();
        }
    }

    private void gameStateCheck() {
        if (currentPlayer.getHealth() < 1) {
            System.out.println("You have been killed by the " + currentNPC.getName() + "!");
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
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
            System.out.println("Thanks for Playing!");
            System.exit(0);
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
            case "use":
                if (fightNouns.contains(noun)) {
                    useItem(noun);
                } else {
                    System.out.println(TextParser.RED + "Invalid command" + TextParser.RESET);
                }
                Console.pause(1500);
                break;
            case "look":
                look(noun);
                break;
            case "get":
                if (approvedItems.contains(noun)) {
                    pickUp(noun);
                    System.out.println(TextParser.GREEN + "You have successfully added " + noun + " to your inventory!" + TextParser.RESET);
                } else {
                    System.out.println(TextParser.RED + "Invalid command" + TextParser.RESET);
                }
                Console.pause(1500);
                break;
            default:
                System.out.println(TextParser.RED + "Invalid command" + TextParser.RESET);
                Console.pause(1500);
        }
    }

    //method for player to move between the different rooms
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

    //method to look at various objects in the rooms, including items in player inventory, and the map
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

    //method to pick up the items in the rooms
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

    //method to use items for attack and healing
    private void useItem(String noun) {
        Item.ItemsSetup item = inventoryItem(noun);

        if (item == null) {
            System.out.println("Item is not in you inventory!");
        } else if (player.getInventory().contains(item)) {
            if (item.getHealPoints() > 0) {
                currentPlayer.setHealth(currentPlayer.getHealth() + item.getHealPoints());
                System.out.println("You have used " + item.getName() + " to heal " + item.getHealPoints() + " HP.");
                System.out.println(item.getName() + " is now removed from inventory.");
                player.getInventory().remove(item);
                Console.pause(1500);
            } else if (item.getAttackPoints() > 0) {
                currentNPC.setHealth(currentNPC.getHealth() - (item.getAttackPoints() + currentPlayer.getAttack()));
                System.out.println("You have used " + item.getName() + " to attack the " + currentNPC.getName());
                System.out.println(item.getName() + " has been destroyed during use and must be discarded!");
                player.getInventory().remove(item);
                Console.pause(1500);
            }
        }
    }

    private Item.ItemsSetup inventoryItem(String noun) {
        for (Item.ItemsSetup item : player.getInventory()) {
            if (noun.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    private Item.ItemsSetup findItem(String noun) {
        for (Item.ItemsSetup roomItem : roomItems) {
            if (noun.equals(roomItem.getName())) {
                return roomItem;
            }
        }
        return null;
    }

    private void runningIntoAGhost() {
        ui.displayGameInfo(player, currentPlayer);
        gameText.roomText(player);
        getGhostInRoom();

        if (player.getCurrentRoom().getNpcName() != null && currentNPC.getHealth() > 0) {
            System.out.println("You are in a fight with a ghost!");
            System.out.println(currentPlayer.getName() + " | HP: " + currentPlayer.getHealth());
            System.out.println(currentNPC.getName() + " | HP: " + currentNPC.getHealth());

            String input = prompter.prompt("What would you like to do? (use + fist or item)\n>");
            List<String> fightCommands = textParser.fightParseInput(input);
            String verb = fightCommands.get(0);

            if ("quit".equals(fightCommands.get(0))) {
                String confirmation = prompter.prompt("Are you sure? [Y/N]").toLowerCase().trim();
                switch (confirmation) {
                    case ("y"):
                    case ("yes"):
                        gameOver = true;
                        System.out.println("You have left the game....");
                        System.exit(0);
                        break;
                    case ("n"):
                    case ("no"):
                        runningIntoAGhost();
                }
            } else if ("help".equals(fightCommands.get(0))) {
                ui.displayGamePlayOptions();
                runningIntoAGhost();
            }

            String noun = fightCommands.get(1);
            while ("invalid".equals(verb)) {
                Console.pause(1500);
                runningIntoAGhost();
            }
            Item.ItemsSetup item = inventoryItem(noun);
            if (verb.equals("use")) {
                if (item != null && noun.equals(item.getName()) && item.getHealPoints() > 0) {
                    useItem(noun);
                    runningIntoAGhost();
                }
                else if (item != null && noun.equals(item.getName())) {
                    currentNPC.setHealth(currentNPC.getHealth() - item.getAttackPoints());
                    playerAttack();
                    npcAttack();
                    useItem(noun);
                    gameStateCheck();
                    if (currentNPC.getHealth() < 1) {
                        System.out.println("You have defeated the ghost!");
                        Console.pause(1000);
                        gameCycle();
                    } else {
                        runningIntoAGhost();
                    }
                } else if (noun.equals("fist")){
                    playerAttack();
                    npcAttack();
                    gameStateCheck();
                    if (currentNPC.getHealth() < 1) {
                        System.out.println("You have defeated the ghost!");
                        Console.pause(1000);
                        gameCycle();
                    } else {
                        runningIntoAGhost();
                    }
                } else {
                    System.out.println(TextParser.RED + "Invalid Command!" + TextParser.RESET);
                    Console.pause(1000);
                    runningIntoAGhost();
                }
            }
        }
    }
    private void playerAttack() {
        currentNPC.setHealth(currentNPC.getHealth() - currentPlayer.getAttack());
    }
    private void npcAttack() {
        currentPlayer.setHealth(currentPlayer.getHealth() - currentNPC.getAttack());
    }
    public void getGhostInRoom() {
        if (Objects.equals(player.getCurrentRoom().getName(), "Morgue")) {
            currentNPC = npc.getGhost1();
        } else if (Objects.equals(player.getCurrentRoom().getName(), "Emergency Room")) {
            currentNPC = npc.getGhost2();
        }else if (Objects.equals(player.getCurrentRoom().getName(), "Patient Room")) {
            currentNPC = npc.getGhost4();
        }else if (Objects.equals(player.getCurrentRoom().getName(), "Front Desk")) {
            currentNPC = npc.getGhost3();
        }else if (Objects.equals(player.getCurrentRoom().getName(), "Pharmacy")) {
            currentNPC = npc.getGhost6();
        }else if (Objects.equals(player.getCurrentRoom().getName(), "Fellowship Room")) {
            currentNPC = npc.getGhost7();
        }else if (Objects.equals(player.getCurrentRoom().getName(), "Finance")) {
            currentNPC = npc.getGhost9();
        }else if (Objects.equals(player.getCurrentRoom().getName(), "Stairs")) {
            currentNPC = npc.getGhost8();
        }else if (Objects.equals(player.getCurrentRoom().getName(), "Janitor Closet")) {
            currentNPC = npc.getGhost5();
        }
    }

    //JSON integration into the game

    //Generate the rooms in the map
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

    //load the NPCs into the game
    private void loadNPC() {
        try (Reader reader = new FileReader("resources/JSON/NPC.json")) {
            npc = new Gson().fromJson(reader, NPC.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        generateItems();
    }

    //load the items into the game
    private void generateItems() {
        Item item;
        try (Reader reader = new FileReader("resources/JSON/Items.json")) {
            item = new Gson().fromJson(reader, Item.class);
            roomItems = item.loadItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load the player into the game
    private void loadPlayer() {
        try (Reader reader = new FileReader("resources/JSON/Player.json")) {
            player = new Gson().fromJson(reader, Player.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load the descriptions of the rooms into the map
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

