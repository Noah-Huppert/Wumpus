package com.noahhuppert.java.Wumpus.elements;

import com.noahhuppert.java.Wumpus.WumpusGame;

/**
 * Created by block7 on 10/16/14.
 */
public class GemElement extends RoomElement{
    public void handle() {
        WumpusGame.playerGems += 1;

        System.out.println("You find a gem, you now have " + WumpusGame.playerGems + " gems");

        WumpusGame.map.getRoom(WumpusGame.currentRoomIndex).myElement = null;
    }

    public void printSenses() {
        System.out.println("You see a shinny object");
    }
}
