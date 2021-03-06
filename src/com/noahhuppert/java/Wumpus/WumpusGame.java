package com.noahhuppert.java.Wumpus;/* class com.noahhuppert.java.Wumpus.WumpusGame -- main game class; provides main loop and some utilities.  */

/*
Features I added
    Bats
    Ammo for arrows
    Pick-up-able arrows
        When you shoot, if you miss. You can go to that room and pick up the arrow
    Added health points
    Added start health
    Added start arrows
    Made sure the user enters correct input
        So if they type "shoot" it will prompt them to enter a direction
    Gems
        Used to upgrade stats
    Pits
    Apples
        + 1 health
    Wumpus Detector
        If you smell the wumpus you can use the wumpus detector(if you have one) to find out which room
    Gave the Wumpus HP
    Rouge Like system
        The player has 2 stats
            Starting Arrows
                The amount of arrows you start with
            Starting Health
                The ammount of health you start with
        After the player dies they can choose to upgrade those stats
        They do this by using gems that they pick up
        Every time the user upgrades a stat that stat will cost one more to upgrade next time
 */

import com.noahhuppert.java.Wumpus.elements.*;

import java.io.*;

public class WumpusGame {

	public static WumpusMap map;
	public static boolean gameActive = true;
	
	public static int currentRoomIndex = 1;

    /* Player properties */
    public static final int PLAYER_AMMO_DEFAULT = 3;
    public static final int PLAYER_HEALTH_DEFAULT = 3;

    public static int PLAYER_AMMO_STARTING = PLAYER_AMMO_DEFAULT;
    public static int PLAYER_HEALTH_STARTING = PLAYER_HEALTH_DEFAULT;

    public static int playerAmmo = PLAYER_AMMO_DEFAULT;
    public static int playerHealth = PLAYER_HEALTH_DEFAULT;
    public static boolean hasWumpusDetector = false;
    public static int playerGems = 0;

	
	// special i/o method required because Eclipse does not provide a Console object
	private static String readLine(String prompt) {
	        String line = null;
	        Console c = System.console();
	        if (c != null) {
	             line = c.readLine(prompt);
	        } else {
	            System.out.print(prompt);
	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	            try {
	                 line = bufferedReader.readLine();
	            } catch (IOException e) { 
	                //Ignore    
	            }
	        }
	        return line;
	 }

	
	public static void shootArrow(String input) {
        try{
            String direction = input.substring(6);  // direction should be input after "shoot " part

            int dirNum = WumpusMap.directionNumber(direction);
            if (dirNum == 0) {
                System.out.println("Huh?");   // bad shoot direction
            } else if(playerAmmo == 0){
                System.out.println("You have no ammo");
            } else {
                WumpusRoom room = currentRoom();
                WumpusRoom targetRoom = room.roomInDirection(dirNum);
                if (targetRoom != null) {
                    if (targetRoom.getElement() != null && (targetRoom.getElement() instanceof WumpusElement)) {
                        System.out.println("You shoot the wumpus.  Victory!!");
                        gameActive = false;
                    } else {
                        System.out.println("You missed and scared the wumpus.");
                        map.moveWumpus();
                    }

                    playerAmmo -= 1;

                    System.out.println("You have " + playerAmmo + " arrow(s) left");
                } else {
                    System.out.println("Can't fire that way.");
                }

                if(gameActive){
                    ArrowElement element = null;

                    if(!(targetRoom.getElement() instanceof ArrowElement)){
                        element = new ArrowElement();
                    } else{
                        element = (ArrowElement) targetRoom.getElement();
                    }

                    element.ammountArrows += 1;

                    targetRoom.setElement(element);
                }
            }
        } catch(StringIndexOutOfBoundsException e){
            System.out.println("Please provide a direction to shoot");
        }
	}
	
	public static WumpusRoom currentRoom() {
		return map.getRoom(currentRoomIndex);
	}

    public static void upgradePowers(){
        String input = readLine("Would you like to upgrade your powers?(y/n)");

        while(!input.equals("n")){
            System.out.println("You have " + playerGems + " gems\nChoose one of the following things to upgrade(Or \"n\" to exit)");

            int startingHealthCost = PLAYER_HEALTH_DEFAULT == PLAYER_HEALTH_STARTING ? 1 : PLAYER_HEALTH_STARTING - PLAYER_HEALTH_DEFAULT;
            int startingAmmoCost = PLAYER_AMMO_DEFAULT == PLAYER_AMMO_STARTING ? 1 : PLAYER_AMMO_STARTING - PLAYER_AMMO_DEFAULT;

            System.out.println("1) Upgrade starting health to " + (PLAYER_HEALTH_STARTING + 1) + " (" + startingHealthCost + " gems)");
            System.out.println("2) Upgrade starting ammo to " + (PLAYER_AMMO_STARTING + 1) + " (" + startingAmmoCost + " gems)");

            String choice = readLine("> ");

            if(choice.equals("1")){
                if(playerGems >= startingHealthCost){
                    PLAYER_HEALTH_STARTING += 1;
                    playerGems -= 1;
                    System.out.println("Your starting health is now " + PLAYER_HEALTH_STARTING);
                } else{
                    System.out.println("You do not have enough gems");
                }
            } else if(choice.equals("2")){
                if(playerGems >=  startingAmmoCost){
                    PLAYER_AMMO_STARTING += 1;
                    playerGems -= 1;
                    System.out.println("Your starting ammo is now " + PLAYER_AMMO_STARTING);
                } else{
                    System.out.println("You do not have enough gems");
                }
            } else if(choice.equals("n")){
                input = "n";
            } else{
                System.out.println("You must choose a valid choice");
            }
        }
    }

    public static void addBasicElements(){

        map = new WumpusMap();

        /* Add elements */
        map.addElement(new WumpusElement());

        map.addElement(new PitElement());
        map.addElement(new PitElement());

        map.addElement(new BatElement());

        map.addElement(new AppleElement());
        map.addElement(new AppleElement());

        map.addElement(new GemElement());
        map.addElement(new GemElement());
        map.addElement(new GemElement());

        map.addElement(new WumpusDetector());

        currentRoomIndex = map.randomEmptyRoom();
    }

	public static void main(String[] args) {

        addBasicElements();
		
		do {
            map.getRoom(currentRoomIndex).printInfo();
            String userInput = readLine("Type your command(Type \"help\" for help)\n> ");
            int direction = 0;
            if (userInput.startsWith("shoot")) {
                shootArrow(userInput);
            } else if ((direction = WumpusMap.directionNumber(userInput)) != 0) {
                WumpusRoom nowRoom = map.getRoom(currentRoomIndex);
                WumpusRoom targetRoom = nowRoom.roomInDirection(direction);
                if (targetRoom != null) {
                    currentRoomIndex = targetRoom.getIndex();
                    targetRoom.handleElement();
                } else {
                    System.out.println("You can't move in that direction.");
                }
            } else if (userInput.equals("bye")) {
                gameActive = false;
            } else if(userInput.equals("detect")){
                String wumpusDirection = "none";

                if(currentRoom().roomInDirection(WumpusMap.NORTH) != null && currentRoom().roomInDirection(WumpusMap.NORTH).getElement() instanceof WumpusElement){
                    wumpusDirection = "north";
                } else if(currentRoom().roomInDirection(WumpusMap.EAST) != null && currentRoom().roomInDirection(WumpusMap.EAST).getElement() instanceof WumpusElement){
                    wumpusDirection = "east";
                } else if(currentRoom().roomInDirection(WumpusMap.SOUTH) != null && currentRoom().roomInDirection(WumpusMap.SOUTH).getElement() instanceof WumpusElement){
                    wumpusDirection = "south";
                } else if(currentRoom().roomInDirection(WumpusMap.WEST) != null && currentRoom().roomInDirection(WumpusMap.WEST).getElement() instanceof WumpusElement){
                    wumpusDirection = "west";
                }

                System.out.println("The wumpus is in the room to your " + wumpusDirection);
                hasWumpusDetector = false;

                System.out.println("You have consumed your wumpus detector");

                map.addElement(new WumpusDetector());
            } else if(userInput.equals("help")){
                System.out.println("    Directions");

                System.out.println("        North");
                System.out.println("        East");
                System.out.println("        South");
                System.out.println("        West");
                System.out.println("        n");
                System.out.println("        e");
                System.out.println("        s");
                System.out.println("        w");

                System.out.println("shoot [direction]           Shoots an array into the room in that direction");
                System.out.println("detect                      Use your Wumpus Detector");

                System.out.println("\nRouge like system");
                System.out.println("    With each death you will loose all you items, but keep your characters stats");
                System.out.println("    You can upgrade each stat with Gems which you will find in the level\n");
            } else if (userInput.equals("DEBUG DIE")) {
                playerHealth = 0;
            } else if(userInput.equals("DEBUG ADD HEALTH")){
                playerHealth += 1;
            } else if(userInput.equals("DEBUG REMOVE HEALTH")){
                playerHealth -= 1;
            } else if (userInput.equals("DEBUG ADD GEM")){
                playerGems += 1;
            } else if(userInput.equals("DEBUG REMOVE GEM")){
                playerGems -= 1;
            } else {
				System.out.println("Command not understood.");
			}

            if(playerHealth <= 0){
                String wantsToContinue = readLine("You lost!\nWould you like to keep playing(y/n)");

                if(wantsToContinue.equals("n")){
                    gameActive = false;
                } else{
                    playerAmmo = PLAYER_AMMO_STARTING;
                    playerHealth = PLAYER_AMMO_STARTING;
                    hasWumpusDetector = false;

                    addBasicElements();

                    upgradePowers();

                    System.out.println("********** New round **********");
                }
            }
		} while (gameActive);
		System.out.println("GAME OVER");
	}

}
