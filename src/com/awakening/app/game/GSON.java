package com.awakening.app.game;

// Java Program to Create an Employee object with all
// Attributes and generate a GSON out of it

// Importing input output classes
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.io.*;
import java.util.*;

// Main class
public class GSON {

    // Main driver method
    public static void main(String[] args)
    {

        // Creating an object of Gson class
        Gson gson = new Gson();

        //Create a location using JSON
        Gson roomGson = new GsonBuilder().setPrettyPrinting().create();
        Room rm = createRoomObject();



        String json = gson.toJson(rm);

        //create the json output file
        try (FileWriter writer = new FileWriter("resources/JSON/rooms.json")) {
            gson.toJson(rm, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create an array of JSON objects

        List<Room> rooms = new ArrayList<>();

        //read JSON from a file
        try(Reader reader = new FileReader("resources/JSON/rooms.json")){

            Room room = gson.fromJson(reader, Room.class);
//          for(Room room : rm2) {
//              System.out.println(room.getName());
//              System.out.println(room.getDescription());
//          }

            System.out.println(room);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Create the room object
    private static Room createRoomObject() {
        Room room = new Room();
        room.setName("Basement");
        room.setDescription("Place where the player spawns");
        room.setItems(Arrays.asList("Camera","Cellphone"));
        HashMap<String, String> directions = new HashMap() {{
            put("South","Morgue");
        }};
        room.setDirections(directions);
        return room;
    }
}


