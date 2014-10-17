package com.noahhuppert.java.Wumpus.elements;

import com.noahhuppert.java.Wumpus.WumpusGame;

/**
 * Created by block7 on 10/16/14.
 */
public class ArrowElement extends RoomElement{
    public void handle() {
        WumpusGame.playerAmmo += 1;

        System.out.println("You pick up an arrow, you now have " + WumpusGame.playerAmmo + " arrow(s) left");
    }

    public void printSenses() {
        System.out.println("You see an old arrow");
    }
}
