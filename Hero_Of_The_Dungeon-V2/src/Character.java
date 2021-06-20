import java.util.ArrayList;

public class Character {

    private int      ID;
    private String   name;
    private int      hitPoints;
    private Weapon   weapon;
    private Clothing clothing;
    private boolean  lifeStatus;
    private int      currentRoom;
    private int      currentLevel;

    private ArrayList<Item> inventory = new ArrayList<Item>();

    public Character(int ID, String name, int hitPoints, Weapon weapon, Clothing clothing, boolean lifeStatus, int currentRoom, int currentLevel, ArrayList<Item> inventory) {
        this.ID = ID;
        this.name = name;
        this.hitPoints = hitPoints;
        this.weapon = weapon;
        this.clothing = clothing;
        this.lifeStatus = lifeStatus;
        this.currentRoom = currentRoom;
        this.currentLevel = currentLevel;
        this.inventory = inventory;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Clothing getClothing() {
        return clothing;
    }

    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
    }

    public boolean isLifeStatus() {
        return lifeStatus;
    }

    public void setLifeStatus(boolean lifeStatus) {
        this.lifeStatus = lifeStatus;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

}

class Monster extends Character {
    public Monster(int ID, String name, int hitPoints, Weapon weapon, Clothing clothing, boolean lifeStatus, int currentRoom, int currentLevel, ArrayList<Item> inventory) {
        super(ID, name, hitPoints, weapon, clothing, lifeStatus, currentRoom, currentLevel, inventory);
    }
}

class Townsperson extends Character {
    public Townsperson(int ID, String name, int hitPoints, Weapon weapon, Clothing clothing, boolean lifeStatus, int currentRoom, int currentLevel, ArrayList<Item> inventory) {
        super(ID, name, hitPoints, weapon, clothing, lifeStatus, currentRoom, currentLevel, inventory);
    }
}

class Hero extends Character {
    private int numOfPeopleSaved = 0;
    private ArrayList<Monster> nearMonsters = new ArrayList<Monster>();
    private ArrayList<Townsperson> nearTownspeople = new ArrayList<Townsperson>();

    public Hero(int ID, String name, int hitPoints, Weapon weapon, Clothing clothing, boolean lifeStatus, int currentRoom, int currentLevel, ArrayList<Item> inventory) {
        super(ID, name, hitPoints, weapon, clothing, lifeStatus, currentRoom, currentLevel, inventory);
    }

    public int getNumOfPeopleSaved() {return numOfPeopleSaved;}

    public void setNumOfPeopleSaved(int numOfPeopleSaved) {
        this.numOfPeopleSaved = numOfPeopleSaved;
    }

    public ArrayList<Monster> getNearMonsters() {
        return nearMonsters;
    }

    public void setNearMonsters(ArrayList<Monster> nearMonsters) {
        this.nearMonsters = nearMonsters;
    }

    public ArrayList<Townsperson> getNearTownspeople() {
        return nearTownspeople;
    }

    public void setNearTownspeople(ArrayList<Townsperson> nearTownspeople) {
        this.nearTownspeople = nearTownspeople;
    }

    public boolean availableMonsters(ArrayList<Monster> nearMonsters) {
        if (nearMonsters.isEmpty()) {
            return true;
        }
        return false;
    }
}

class Aragorn extends Hero {
    private final String characterName = "Aragorn";

    public Aragorn(int ID, String name, int hitPoints, Weapon weapon, Clothing clothing, boolean lifeStatus, int currentRoom, int currentLevel, ArrayList<Item> inventory) {
        super(ID, name, hitPoints, weapon, clothing, lifeStatus, currentRoom, currentLevel, inventory);
    }

    public String getCharacterName() {
        return characterName;
    }
}