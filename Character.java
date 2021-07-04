import java.util.ArrayList;

interface HeroUI {
    int fight(Monster attackedMonster);
    void equip(Item willEquip, int willEquippedIndex);
    void listInventory();
}

abstract public class Character {

    private int      ID;
    private String   name;
    private int      hitPoints;
    private Weapon   weapon;
    private Clothing clothing;
    private boolean  lifeStatus;
    private int      currentRoom;
    private int      currentLevel;

    private ArrayList<Item> inventory;

    public Character(int ID, String name, int hitPoints, Weapon weapon, Clothing clothing, boolean lifeStatus, int currentRoom, int currentLevel, ArrayList<Item> inventory) {
        this.ID = ID;
        this.name = name;
        this.hitPoints = hitPoints;
        this.weapon = weapon;
        this.clothing = clothing;
        this.lifeStatus = lifeStatus;
        this.currentRoom = currentRoom;
        this.currentLevel = currentLevel;
        this.inventory = new ArrayList<Item>();
        this.inventory = inventory;

        this.hitPoints += this.clothing.getBonusHP();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
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

    public void equip() {}
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

class Hero extends Character implements HeroUI {
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

    public void moveHero(int x){
        this.setCurrentRoom(x);

    }

    public int fight(Monster attackedMonster) {
        // Get HP & damage of the Hero
        int heroHP = this.getHitPoints();
        int heroDMG = this.getWeapon().getDamage();

        // Get HP & damage of the Monster
        int monsterHP = attackedMonster.getHitPoints();
        int monsterDMG = attackedMonster.getWeapon().getDamage();

        // Do the fight
        monsterHP -= heroDMG;
        attackedMonster.setHitPoints(monsterHP);

        if (monsterHP <= 0) {
            System.out.println("HERO YOU KILLED ME!!!");
            attackedMonster.setLifeStatus(false);
            return 1;
        }

        heroHP -= monsterDMG;
        this.setHitPoints(heroHP);

        if(heroHP <= 0) {
            System.out.println("YOU DIED!!! ---- GAME OVER! :(");
            this.setLifeStatus(false);
            return -1;
        }

        return 0;

    }

    public void equip(Item willEquip, int willEquippedIndex) {
        if (willEquip instanceof Weapon) {

            Weapon tempWeapon;
            tempWeapon = this.getWeapon();
            this.setWeapon((Weapon) willEquip);
            this.getInventory().remove(willEquippedIndex);
            this.getInventory().add(willEquippedIndex, tempWeapon);

        } else if (willEquip instanceof Clothing) {

            Clothing tempClothing;
            tempClothing = this.getClothing();

            this.setClothing((Clothing) willEquip);

            this.getInventory().remove(willEquippedIndex);
            this.getInventory().add(willEquippedIndex, tempClothing);

            this.setClothing((Clothing) willEquip);
            this.setHitPoints(this.getHitPoints() + ((Clothing) willEquip).getBonusHP() - tempClothing.getBonusHP());
        }
    }

    public void listInventory() {
        int index = 0;
        for (Item curItem : this.getInventory()) {
            curItem.printItemInfo(index);
            index++;
        }
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

class Warrior extends Hero {
    private final String characterName = "Warrior";

    public Warrior(int ID, String name, int hitPoints, Weapon weapon, Clothing clothing, boolean lifeStatus, int currentRoom, int currentLevel, ArrayList<Item> inventory) {
        super(ID, name, hitPoints, weapon, clothing, lifeStatus, currentRoom, currentLevel, inventory);
    }

    public String getCharacterName() {
        return characterName;
    }
}

class HokiDoki extends Hero {
    private final String characterName = "HokiDoki";

    public HokiDoki(int ID, String name, int hitPoints, Weapon weapon, Clothing clothing, boolean lifeStatus, int currentRoom, int currentLevel, ArrayList<Item> inventory) {
        super(ID, name, hitPoints, weapon, clothing, lifeStatus, currentRoom, currentLevel, inventory);
    }

    public String getCharacterName() {
        return characterName;
    }
}