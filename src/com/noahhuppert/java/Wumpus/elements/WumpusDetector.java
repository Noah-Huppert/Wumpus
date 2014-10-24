package com.noahhuppert.java.Wumpus.elements;

import com.noahhuppert.java.Wumpus.WumpusGame;
import com.noahhuppert.java.Wumpus.WumpusMap;

/**
 * Created by block7 on 10/21/14.
 */
public class WumpusDetector extends RoomElement{
    public void handle() {
        WumpusGame.hasWumpusDetector = true;

        System.out.println("You find a Wumpus Detector, type \"detect\" to use");

        System.out.println("    What is a Wumpus Detector?");
        System.out.println("        A Wumpus Detector is a one time use item that tells you which direction the wumpus is in");
        System.out.println("        Due to technical limitations, the Wumpus Detector only works if you are next to the room the wumpus is in.");

        WumpusGame.map.getRoom(WumpusGame.currentRoomIndex).myElement = null;
    }

    public void printSenses() {
        System.out.println("You hear a beeping noise");
    }
}
