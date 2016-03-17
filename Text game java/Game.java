import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Game
{

    private ArrayList<Item> playerItems;
    private Room currentLocation;
    private Room lastLocation;
    private String currentMessage;
    private boolean power;

    //Game locations and items
    private Room wardensOffice;
    private Room securityOffice;
    private Room dinningHall;
    private Room recRoom;
    private Room sleepingQuarters;
    private Room cryogenicLab;
    private Room shelterPurificationRoom;
    private Room generatorRoom;
    private Room greenhouse;
    private Room shelterEntrance;

    private Item keys;
    private Item meal;
    private Item password;
    private Item computer;


    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        // initialise instance variables
        playerItems = new ArrayList<Item>();
        createRooms();
        currentLocation = cryogenicLab;
        lastLocation = null;
        
        power = false;

    }
    // helper method for constructor 
    public void createRooms()
    {


        keys = new Item("Wardens Keys", "Keys to the shelter entrance", 1, false);
        meal = new Item("Preserved Meal", "Preserved Mash Potatoes and Steak", 2, true);
        password = new Item("12 pin Password", "Wardens computer password", 1, false);
        computer = new Item("Secuirty terminal", "Giant high tech security terminal built into the wall", 1000, false);

        dinningHall = new Room(" the Dinning Hall. Old moldy food still sits on the tables. Looks like there are some preserved meals however", null);
        recRoom = new Room(" the Rec Room. Pool tables couches, the bar. Seemed nice enough to spend a century down here", null);
        sleepingQuarters = new Room(" the Sleeping Quarters. Poeple sre Left in a hurry. Belongings have been left behind.", null);
        securityOffice = new Room(" the Security Office. It is filled with hightech computers and moniters.", computer);
        cryogenicLab = new Room("  the Cryogenic Lab. it is full of high tech cryogenic machinery which froze you alive for decades", null);
        shelterEntrance = new Room(" the Shelter Entrance! The gates to freedom! You have made it out.", null);
        generatorRoom = new Room(" the Generator Room. This room is filled with nothing but generators", null);
        wardensOffice = new Room(" the Warden's office. Pretty nice set up he had in here. Too bad he had to go and blow his brains out. You can see the warden slouched dead in his desk chair.", null);
        greenhouse = new Room(" the greenhouse. Nothing but dead crops in here. Nothing edible to salvage", null);
        shelterPurificationRoom = new Room(" the Shelter Purification Room. It is pure stainless steal with steam showers. They tried to keep it sanitary down here", null);

        dinningHall.addNeighbor("southeast", recRoom);
        dinningHall.addNeighbor("east", sleepingQuarters);
        dinningHall.addNeighbor("south", greenhouse);

        sleepingQuarters.addNeighbor("south", recRoom);
        sleepingQuarters.addNeighbor("west", dinningHall);

        greenhouse.addNeighbor("north", dinningHall);
        greenhouse.addNeighbor("east", recRoom);

        recRoom.addNeighbor("north", sleepingQuarters);
        recRoom.addNeighbor("northwest", dinningHall);
        recRoom.addNeighbor("west", greenhouse);
        recRoom.addNeighbor("east", shelterPurificationRoom);
        recRoom.addNeighbor("south", securityOffice);

        securityOffice.addNeighbor("east", wardensOffice);
        securityOffice.addNeighbor("west", generatorRoom);
        securityOffice.addNeighbor("north", recRoom);
        securityOffice.addNeighbor("south", cryogenicLab);

        generatorRoom.addNeighbor("east", securityOffice);

        wardensOffice.addNeighbor("west", securityOffice);
        wardensOffice.addNeighbor("north", shelterPurificationRoom);

        shelterPurificationRoom.addNeighbor("west", recRoom);
        shelterPurificationRoom.addNeighbor("south", wardensOffice);
        shelterPurificationRoom.addNeighbor("east", shelterEntrance);

        shelterEntrance.addNeighbor("west", shelterPurificationRoom);

        cryogenicLab.addNeighbor("north", securityOffice);

        currentLocation = cryogenicLab;
    }
    // to allow movment between rooms
    public void move(String direction){
        Room nextRoom = currentLocation.getNeighbor(direction);
        if ((nextRoom == null) || (nextRoom == wardensOffice) || (nextRoom == shelterEntrance)){
            currentMessage = "You can't go in that direction" + "\n";}
        else {
            currentLocation = nextRoom;
            currentMessage = currentLocation.getLongDescription();

        }

        if (nextRoom == wardensOffice){
             currentMessage = "Door is locked" + "\n";
           }
        if (playerItems.contains(password) && (nextRoom == wardensOffice)){
            currentLocation = wardensOffice;
            currentMessage = "You unlocked the wardensOffice using the 12 pin password" + "\n" + currentLocation.getLongDescription();
           }
        if (nextRoom == shelterEntrance){
             currentMessage = "Door is locked" + "\n";
           }
        if (playerItems.contains(keys) && (nextRoom == shelterEntrance)){
            currentLocation = shelterEntrance;
            currentMessage = "You unlocked the shelter entrance using the keys" + "\n" + currentLocation.getLongDescription();
           }
        
    }

    public void setIntroMessage()
    {
        currentMessage = "Welcome to Text Based Adventure." + "\n" + "Youcan move through the world, collect items and performing actions." + "\n" + "You need to find out how to escape this abandoned shelter!" + "\n";
    }

    // method used by the GUI to show messages
    public String getMessage()
    {
        return currentMessage;
    }

    public void help()
    {
        currentMessage = "You need to find out how to Escape!"  + currentLocation.getLongDescription() + "\n";

    }

    public void show()
    {
        currentMessage = currentLocation.getLongDescription() + "\n";
    }

    // Shows contents of what items the player has
    public void inventory()
    {
        currentMessage = "";
        for(Item itm: playerItems){
            currentMessage = currentMessage + itm.getName() + " " + "\n";
        }
        System.out.println(currentMessage);
    }

    public void eat (String item)
    {
        Item canEat = null;
        for(Item itm: playerItems){
            if(itm.getName().equals(item)){
                canEat = itm;
            }
        }

        if (canEat == null){
            currentMessage = "You don't have that item"+ "\n";
        }
        else{
            if(canEat.isEdible() == true){
                currentMessage = "You ate the item " + canEat.getName() + "!" + "\n";
                playerItems.remove(canEat);
            }
            else if(canEat.isEdible() == false){
                currentMessage = canEat.getName() + " is not edible." + "\n";
            }
        }

    }

    public boolean gameOver()
    {
        if(currentLocation == shelterEntrance){
           return true;
        } 
        else {
            return false;
        }
    }

    // adds an Item object to playerItems and removes it from current room using arrayList
    public void pickup()
    {
        if(currentLocation.hasItem() == false){
            currentMessage = "There is no item in the room to take" + "\n";
        }
        else if(currentLocation.getItem().getWeight() > 50){
            currentMessage = "That item is too heavy to pickup" + "\n";
        }
        else{
            playerItems.add(currentLocation.getItem());
            currentMessage = "You have picked up " + currentLocation.getItem().getName() + "\n";
            currentLocation.removeItem();
        }

    }

    // searches through items
    private Item searchInventory (String name)
    {
        Item sub = null;
        for(Item itm: playerItems){
            if(itm.getName().equals(name)){
                sub = itm;
            }
        }
        return sub;
    }

    //removes item from players items
    public void drop(String item){
        boolean haveItem = false;
        Item holder = null;
        if (currentLocation.hasItem() == true){
            currentMessage = "This room already has an item you can't put one down" + "\n";
        }
        else if(playerItems.size() == 0){
            currentMessage = "You are not holding any items" + "\n";
        }
        else {
            for(Item i: playerItems){
                if(i.getName().equals(item)){
                    holder = i;
                    haveItem = true;
                    currentMessage = "You have dropped " + i.getName() + "." + "\n";
                }
            }
            playerItems.remove(holder);
            currentLocation.addItem(holder);
            if (haveItem == false){
                currentMessage = "You are not holding that item" + "\n";
            }
        }

    }

    //Custom Actions
    // allows the user to return to the previous room
    public void backup()
    {
        if(lastLocation == null){
            currentMessage = "You can't go back from here" + "\n";
        }
        else{
            currentLocation = lastLocation;
            currentMessage = "You are back in " + currentLocation.getDescription() + "\n";
        }
    }

    public void turnOnPower() {
        if (currentLocation == generatorRoom){
            currentMessage = "Ah there is the backup generator switch. The power is now on" + "\n";
            power = true;
        }
        else{
            currentMessage = "There doesn't seem to be a power switch around here." + "\n";
            boolean power = false;
        }
    }

    public void useComputer() {
        if (currentLocation == securityOffice && power == true){
            currentMessage = "After scrounging around on the sercurity terminal you have come across a confidential folder labled password. Upon opening this folder you have discovered a 12 pin password to something"+ "\n";
           
            playerItems.add(password);
        }
        else if (currentLocation != securityOffice){
            currentMessage = "There doesn't seem to be a computer " + "\n";
        }
        else if (power == false){
            currentMessage = "The power needs to be on in order to use the computer" +"\n";
        }
    }

    public void search() {
        if (currentLocation == wardensOffice){
            currentMessage = "Although is was rather disturbing searching the wardens corpse, you found his set of keys." + "\n";
            playerItems.add(keys);
        }
        else {
            currentMessage = " There isn't anything to search here" + "\n";
        }
    }
    
    
}

