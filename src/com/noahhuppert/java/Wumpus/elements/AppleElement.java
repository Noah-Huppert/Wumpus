package com.noahhuppert.java.Wumpus.elements;

import com.noahhuppert.java.Wumpus.WumpusGame;

/**
 * Created by block7 on 10/16/14.
 */
public class AppleElement extends RoomElement{
    public void handle() {
        WumpusGame.playerHealth += 1;

        System.out.println("You find an apple, you now have " + WumpusGame.playerHealth + " health");
    }

    public void printSenses() {
        System.out.println("You smell something delicious");
    }
}
