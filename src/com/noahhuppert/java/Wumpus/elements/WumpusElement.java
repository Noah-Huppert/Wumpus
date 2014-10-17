package com.noahhuppert.java.Wumpus.elements;

import com.noahhuppert.java.Wumpus.WumpusGame;
import com.noahhuppert.java.Wumpus.elements.RoomElement;

public class WumpusElement extends RoomElement {
	public void handle() {
        WumpusGame.playerHealth -= 2;
        String healthMessage = WumpusGame.playerHealth <= 0 ? "" : ", You now have " + WumpusGame.playerHealth + " health";

		System.out.println("You attacked by the wumpus" + healthMessage);
	}
	
	public void printSenses() {
		System.out.println("You smell a wumpus.");
	}
}
