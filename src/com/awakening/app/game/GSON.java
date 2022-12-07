package com.awakening.app.game;

// Java Program to Create an Employee object with all
// Attributes and generate a GSON out of it

// Importing input output classes
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.*;

// Main class
public class GSON {

    // Main driver method
    public static void main(String[] args)
    {

        //create an array of JSON objects
        //read JSON from a file
        try(Reader reader = new FileReader("resources/JSON/roomsList.json")){

            ArrayList<Room> roomList=  new Gson().fromJson(reader, new TypeToken<List<Room>>() {}.getType());
            roomList.forEach(System.out::println);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}


