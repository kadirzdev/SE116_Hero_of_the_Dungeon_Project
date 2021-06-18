import java.util.ArrayList;
import java.util.spi.AbstractResourceBundleProvider;

// Invetory Sistemi Eksik11
// İtem giyme çıkarma eksik
// Canavardan item düşme olayı gg
//Oda arası geçişler eksik
//Oda içine item, köylü ve canavar ekleme eksik



public class Character {

    // Character Props
    private int     ID;
    private String  name;
    private int     hitPoints;
    private Weapons weapon;
    private Armors  clothing;
    private boolean lifeStatus;
    private int     currentSet;
    private int     currentRoom;
    private int     currentLevel;

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
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

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }

    public Armors getClothing() {
        return clothing;
    }

    public void setClothing(Armors clothing) {
        this.clothing = clothing;
    }

    public void isLifeStatus() {
        System.out.println("Your HP is " + hitPoints);
    }

    public void setLifeStatus(boolean lifeStatus) {
        this.lifeStatus = lifeStatus;
    }

    public int getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet(int currentSet) {
        this.currentSet = currentSet;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Character(int ID, String name, int hitPoints, Weapons weapon, Armors clothing, int currentSet, int currentRoom, int currentLevel) {
        this.ID = ID;
        this.name = name;
        this.hitPoints = hitPoints;
        this.weapon = weapon;
        this.clothing = clothing;
        this.currentRoom = currentRoom;
        this.currentSet = currentSet;
        this.lifeStatus = lifeCheck();
        this.currentLevel = currentLevel;

    }

    public boolean lifeCheck() {

        if (this.hitPoints <= 0) {
            return false;
        }
        return true;

    }


    public void attack(Monster monster, Hero hero) {
        System.out.println();
    }
}

class Hero extends Character {
    int savedTownspeople;

    public Hero(int ID, String name, int hitPoints, Weapons weapon, Armors clothing, int currentSet, int currentRoom, int currentLevel) {

        super(ID, name, hitPoints, weapon, clothing, currentSet, currentRoom, currentLevel);

    }

    public void move(int x) {

    }

    public void attack(Monster monster, Hero hero, Rooms[][] rooms) {

        int heroDamage = (int)(Math.random() * getWeapon().damage);
        int monsterDamage = (int)(Math.random() * monster.getWeapon().damage);
        System.out.println("You have dealt " + heroDamage + " damage." + " The monster fights back and deals " + monsterDamage + " damage.");
        monster.setCurrentHitPoints(monster.getCurrentHitPoints()-heroDamage);
        hero.setHitPoints(hero.getHitPoints()-monsterDamage);

        if (monster.getCurrentHitPoints() <= 0){
            System.out.println("The monster is dead.");
            monster.setDead(true);
            System.out.println("You saved " + rooms[getCurrentSet()][getCurrentRoom()].howManytownsPeople + " townspeople from this room.");

            savedTownspeople = savedTownspeople + rooms[getCurrentSet()][getCurrentRoom()].howManytownsPeople;
        }



    }

    public void useDoor(Rooms[][] rooms) {
        setCurrentSet(getCurrentSet()+1);
        setCurrentRoom(0);
    }

    public int useLadder() {
        System.out.println("Climbing...");
        setCurrentLevel(getCurrentLevel()+1);
        setCurrentRoom(0);
        setCurrentSet(0);


        System.out.println("You are now in " + getCurrentLevel() + ". level.");

        if (getCurrentLevel() == 16){
            youWin();
            return 16;
        }
        return getCurrentLevel();
    }

    private void youWin() {
        System.out.println("Congratulations!!!!!!!");
        System.out.println("Townspeople saved: " + savedTownspeople);
    }
}

class Monster extends Character {

    private int loot;
    private int currentHitPoints;
    private boolean isDead;

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getLoot() {
        return loot;
    }

    public void setLoot(int loot) {
        this.loot = loot;
    }

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int currentHitPoints) {
        this.currentHitPoints = currentHitPoints;
    }

    public Monster(int ID, String name, int hitPoints, Weapons weapon, Armors clothing, int currentSet, int currentRoom, int loot, int currentHitPoints, int currentLevel) {

        super(ID, name, hitPoints, weapon, clothing, currentSet, currentRoom, currentLevel);

        this.currentHitPoints = currentHitPoints;
        this.loot = loot;

    }

    public static ArrayList<Monster> generateMonster() {

        ArrayList<Monster> monsterArrayList = new ArrayList<>();

        ArrayList<OneHanded> oneHandedArrayList = OneHanded.generateOneHandedSwords();
        ArrayList<Armors> armorsArrayList = Armors.generateArmors();

        monsterArrayList.add(new Monster(1, "Goblin", 10, oneHandedArrayList.get(0), armorsArrayList.get(0), 0, 0, 5,10,1));

        return monsterArrayList;
    }


}

class Townspeople{
        int howMany;
}


