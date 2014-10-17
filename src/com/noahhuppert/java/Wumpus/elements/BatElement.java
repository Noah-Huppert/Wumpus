package com.noahhuppert.java.Wumpus.elements;

import com.noahhuppert.java.Wumpus.WumpusGame;

/**
 * Created by block7 on 10/16/14.
 */
public class BatElement extends RoomElement {
    public void handle() {
        int emptyRoom = WumpusGame.map.randomEmptyRoom();
        System.out.println("You walk into a room of bats. They Lift you up and put you in room " + emptyRoom);

        WumpusGame.currentRoomIndex = emptyRoom;
    }

    public void printSenses() {
        System.out.println("You hear the flutter of wings");
    }
}
