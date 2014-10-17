package com.noahhuppert.java.Wumpus;
/* com.noahhuppert.java.Wumpus.WumpusMap -- This class represents the entire game map.  The vertices of a dodecahedron are the rooms and the edges
 * are the connections between rooms.
 */

import com.noahhuppert.java.Wumpus.elements.RoomElement;
import com.noahhuppert.java.Wumpus.elements.WumpusElement;

public class WumpusMap {

	public static final int N_ROOMS = 20;
	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	public static final int WEST = 4;
	public static final int N_DIRECTIONS = 4;

	final static String[] DIRECTION_NAMES = {"", "north", "east", "south", "west"};
	final static String[] DIRECTION_SHORT_NAMES = {"", "n", "e", "s", "w"};
	
	WumpusRoom[] rooms;
	
	public WumpusMap() {
        rooms = new WumpusRoom[N_ROOMS + 1];
        rooms[1] = new WumpusRoom(1,0, 5, 6, 2);
        rooms[2] = new WumpusRoom(2, 0, 1, 7, 3);
        rooms[3] = new WumpusRoom(3, 0, 2, 8, 4);
        rooms[4] = new WumpusRoom(4, 0, 3, 9, 5);
        rooms[5] = new WumpusRoom(5, 0, 4, 10, 1);
        rooms[6] =  new WumpusRoom(6, 1, 15, 0, 11);
        rooms[7] =  new WumpusRoom(7, 2, 11, 0, 12);
        rooms[8] =  new WumpusRoom(8, 3, 12, 0, 13);
        rooms[9] =  new WumpusRoom(9, 4, 13, 0, 14);
        rooms[10] =  new WumpusRoom(10, 5, 14, 0, 15);
        rooms[11] =  new WumpusRoom(11, 0, 6, 16, 7);
        rooms[12] =  new WumpusRoom(12, 0, 7, 17, 8);
        rooms[13] =  new WumpusRoom(13, 0, 8, 18, 9);
        rooms[14] =  new WumpusRoom(14, 0, 9, 19, 10);
        rooms[15] =  new WumpusRoom(15, 0, 10, 20, 6);
        rooms[16] =  new WumpusRoom(16, 11, 20, 0, 17);
        rooms[17] =  new WumpusRoom(17, 12, 16, 0, 18);
        rooms[18] =  new WumpusRoom(18, 13, 17, 0, 19);
        rooms[19] =  new WumpusRoom(19, 14, 18, 0, 20);
        rooms[20] =  new WumpusRoom(20, 15, 19, 0, 16);

	}
	
	public static String directionName(int dir) {

		if (dir >= 1 && dir <= 4) 
			return DIRECTION_NAMES[dir];	
		else
			return "ERROR";
	}
	
	public static int directionNumber(String dir) {
		for (int i = 1; i <= N_DIRECTIONS; i++) {
			if (DIRECTION_NAMES[i].equals(dir) ||
				DIRECTION_SHORT_NAMES[i].equals(dir)) {
				return i;
			} 
		}
		return 0;

	}
	
	public WumpusRoom getRoom(int ndx) {
		return rooms[ndx];
	}	
	
	public int randomEmptyRoom() {
		int r = 0;

		do {
			r = (int) (Math.random() * N_ROOMS + 1);
		}	
		while (rooms[r].getElement() != null);
		return r;
	}
	
	public void addElement(RoomElement elem) {
		getRoom(randomEmptyRoom()).setElement(elem);	
	}
	
	public void moveWumpus() {
		for (int i = 1; i <= N_ROOMS; i++) {
			if (rooms[i].getElement() != null && (rooms[i].getElement() instanceof WumpusElement)) {
				// switch these two lines to force wumpus to move
				rooms[i].setElement(null); 
				addElement(new WumpusElement());
				return;
			}
		}
	}
}
