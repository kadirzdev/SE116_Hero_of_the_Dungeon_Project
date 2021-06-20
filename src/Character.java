import javax.swing.text.MutableAttributeSet;
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
    private Shields   shield;
    private int bottlesofhealingpotions;

    public int getBottlesofhealingpotions() {
        return bottlesofhealingpotions;
    }

    public void setBottlesofhealingpotions(int bottlesofhealingpotions) {
        this.bottlesofhealingpotions = bottlesofhealingpotions;
    }

    public Shields getShield() {
        return shield;
    }

    public void setShield(Shields shield) {
        this.shield = shield;
    }

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
    int previousRoom;
    int previousSet;
    String heroType;

    public Hero(int ID, String name, int hitPoints, Weapons weapon, Armors clothing, int currentSet, int currentRoom, int currentLevel, String heroType) {

        super(ID, name, hitPoints, weapon, clothing, currentSet, currentRoom, currentLevel);
        this.heroType = heroType;

    }

    public String getHeroType() {
        return this.heroType;
    }

    public void attack(Monster monster, Hero hero, Rooms[][] rooms) {

        int heroDamage = (int)(Math.random() * getWeapon().damage);
        int monsterDamage = (int)(Math.random() * monster.getWeapon().damage);
        System.out.println("You have dealt " + heroDamage + " damage." + " The monster fights back and deals " + monsterDamage + " damage.");
        if (hero.getShield() != null){
            int chance = (int)(Math.random() * (hero.getShield().getBlockChance()));
            if (chance > hero.getShield().getBlockChance()  || chance <= 100){
                System.out.println("you blocked the damage!");
                monsterDamage = 0;
            }
        }
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
        if (savedTownspeople == 0){
            System.out.println("...");
            System.out.println("I mean...");
            System.out.println("Really...");
            System.out.println("You really thought you can win the game without saving anyone?");
            System.err.println("No.");
            System.err.println("You LOSE!");
        }
    }



    public void inventory(ArrayList<Weapons> weaponsInventory, ArrayList<Armors> armorsInventory) {


        System.out.println("Firstly, type the item list name. Then, write its list number.");
        System.out.println("Example:");
        System.out.println("weapon");
        System.out.println("1");
        System.out.println();
        System.out.println("Weapons: ");
        int j = 0;
            for (int i = 0; i < weaponsInventory.size(); i++){
                if (weaponsInventory == null){
                    System.out.println("Nothing.");
                }else {

                    System.out.println((j + 1) + ") " + weaponsInventory.get(i).name);
                    j++;
                }
            }
        System.out.println("Armors:");
        int k = 0;
            for (Armors i : armorsInventory) {
                if (armorsInventory == null) {
                    System.out.println("Nothing.");
                } else {

                    System.out.println((k + 1) + ") " + i.name);
                    k++;
                }
            }

        }

    public void weaponschoices(){
        System.out.println("choose a number.");
        System.out.println("1) Equip / Unequip");
        System.out.println("2) Description");
    }

    public void lootMenu(Rooms[][] rooms) {
        System.out.println("Take " + rooms[getCurrentSet()][getCurrentRoom()].monster.getWeapon().name + " (take1)");
        System.out.println("Take " + rooms[getCurrentSet()][getCurrentRoom()].monster.getClothing().name + " (take2)");
        System.out.println("Take the healing potion (take3)");
    }
}

class Monster extends Character {

    private int loot;
    private int currentHitPoints;
    private boolean isDead;
    private Shields shield;

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

    public Monster(int ID, String name, int hitPoints, Weapons weapon, Armors clothing, int currentSet, int currentRoom, int loot, int currentHitPoints, int currentLevel, Shields shield) {

        super(ID, name, hitPoints, weapon, clothing, currentSet, currentRoom, currentLevel);

        this.currentHitPoints = currentHitPoints;
        this.loot = loot;
        this.shield = shield;

    }

    public static ArrayList<Monster> generateMonster() {

        ArrayList<Monster> monsterArrayList = new ArrayList<>();

        ArrayList<OneHanded> oneHandedArrayList = OneHanded.generateOneHandedSwords();
        ArrayList<Armors> armorsArrayList = Armors.generateArmors();

        monsterArrayList.add(new Monster(1, "Goblin", 10, oneHandedArrayList.get(0), armorsArrayList.get(0), 0, 0, 5,10,1,null));

        return monsterArrayList;
    }


}

class Townspeople{
        int howMany;
}


