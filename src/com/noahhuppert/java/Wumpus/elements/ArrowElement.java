package com.noahhuppert.java.Wumpus.elements;

import com.noahhuppert.java.Wumpus.WumpusGame;

/**
 * Created by block7 on 10/16/14.
 */
public class ArrowElement extends RoomElement{
    public int ammountArrows = 0;

    public void handle() {
        WumpusGame.playerAmmo += ammountArrows;

        System.out.println("You pick up " + ammountArrows + " arrow(s), you now have " + WumpusGame.playerAmmo + " arrow(s) left");

        WumpusGame.map.getRoom(WumpusGame.currentRoomIndex).myElement = null;
    }

    public void printSenses() {
        System.out.println("You see an old arrow(s)");
    }
}
