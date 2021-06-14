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

    public boolean isLifeStatus() {
        return lifeStatus;
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

    public Character(int ID, String name, int hitPoints, Weapons weapon, Armors clothing, int currentSet, int currentRoom) {
        this.ID = ID;
        this.name = name;
        this.hitPoints = hitPoints;
        this.weapon = weapon;
        this.clothing = clothing;
        this.currentRoom = currentRoom;
        this.currentSet = currentSet;
        this.lifeStatus = lifeCheck();

    }

    public boolean lifeCheck() {

        if (this.hitPoints <= 0) {
            return false;
        }
        return true;

    }


}

class Hero extends Character {

    public Hero(int ID, String name, int hitPoints, Weapons weapon, Armors clothing, int currentSet, int currentRoom) {

        super(ID, name, hitPoints, weapon, clothing, currentSet, currentRoom);

    }

    public void move(int x) {

    }

}

class Monster extends Character {

    private int loot;

    public Monster(int ID, String name, int hitPoints, Weapons weapon, Armors clothing, int currentSet, int currentRoom, int loot) {

        super(ID, name, hitPoints, weapon, clothing, currentSet, currentRoom);

        this.loot = loot;

    }

    public ArrayList<Monster> generateMonster(ArrayList<Weapons> allWeaponsList){

        ArrayList<Monster> monsterArrayList = new ArrayList<>();
        ArrayList<OneHanded> oneHandedArrayList = OneHanded.generateOneHandedSwords();
        
        monsterArrayList.add(new Monster(1,"Goblin",10,oneHandedArrayList.get(0),)
    }


}

class Townspeople extends Character {

    public Townspeople(int ID, String name, int hitPoints, Weapons weapon, Armors clothing, int currentSet, int currentRoom) {

        super(ID, name, hitPoints, weapon, clothing, currentSet, currentRoom);

    }

}