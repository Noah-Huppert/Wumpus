package com.noahhuppert.java.Wumpus.elements;

import com.noahhuppert.java.Wumpus.WumpusGame;

/**
 * Created by block7 on 10/16/14.
 */
public class PitElement extends RoomElement {
    public void handle() {
        System.out.println("You fall down a pit");
        WumpusGame.playerHealth = 0;
    }

    public void printSenses() {
        System.out.println("You feel a cold breeze");
    }
}
