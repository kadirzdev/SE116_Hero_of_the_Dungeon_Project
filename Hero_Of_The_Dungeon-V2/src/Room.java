import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Room {
    private int ID;
    private int level;
    private int numOfMonsters;
    private int numOfTownspeople;

    private ArrayList<Monster> monsters;
    private ArrayList<Townsperson> townspeople;

    private Room door1 = null;
    private Room door2 = null;
    private Room stairs = null;
    private Room downstairs = null;

    private ArrayList<Item> itemLoot = new ArrayList<Item>();

    public Room(int ID, int level, ArrayList<Monster> monsters, ArrayList<Townsperson> townspeople, Room door1, Room door2, Room stairs, Room downstairs) {
        this.ID = ID;
        this.level = level;
        this.monsters = monsters;
        this.townspeople = townspeople;
        this.door1 = door1;
        this.door2 = door2;
        this.stairs = stairs;
        this.downstairs = downstairs;
        this.monsters = new ArrayList<Monster>();
        this.townspeople = new ArrayList<Townsperson>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumOfMonsters() {
        return numOfMonsters;
    }

    public void setNumOfMonsters(int numOfMonsters) {
        this.numOfMonsters = numOfMonsters;
    }

    public int getNumOfTownspeople() {
        return numOfTownspeople;
    }

    public void setNumOfTownspeople(int numOfTownspeople) {
        this.numOfTownspeople = numOfTownspeople;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public ArrayList<Townsperson> getTownspeople() {
        return townspeople;
    }

    public void setTownspeople(ArrayList<Townsperson> townspeople) {
        this.townspeople = townspeople;
    }

    public Room getDoor1() {
        return door1;
    }

    public void setDoor1(Room door1) {
        this.door1 = door1;
    }

    public Room getDoor2() {
        return door2;
    }

    public void setDoor2(Room door2) {
        this.door2 = door2;
    }

    public Room getStairs() {
        return stairs;
    }

    public void setStairs(Room stairs) {
        this.stairs = stairs;
    }

    public Room getDownstairs() {
        return downstairs;
    }

    public void setDownstairs(Room downstairs) {
        this.downstairs = downstairs;
    }

    public ArrayList<Item> getItemLoot() {
        return itemLoot;
    }

    public void setItemLoot(ArrayList<Item> itemLoot) {
        this.itemLoot = itemLoot;
    }

    public void createMobs() {

        int min = 1;
        int max = 3;

        int monster_num = (int)Math.floor(Math.random()*(max-min+1)+min);
        int townsperson_num = 1;

        // Monster Items
        ArrayList<Item> monsterInventory = new ArrayList<Item>();

        Sword monsterLoot1 = new Sword("Glory Sword", 5, 10, 10, 10);
        Sword monsterLoot2 = new Sword("Big Sword", 7, 15, 15, 10);
        Sword monsterLoot3 = new Sword("Sun Sword", 10, 20, 20, 10);
        Axe monsterLoot4 = new Axe("Big Axe", 13, 10, 17, 7);
        Axe monsterLoot5 = new Axe("Thor Axe", 15, 15, 23, 7);
        Bow monsterLoot6 = new Bow("Bowi Mowi Bow", 7, 20, 25, 100);
        Bow monsterLoot7 = new Bow("S400 Bow", 7, 20, 30, 150);
        Clothing monsterLoot8 = new Clothing("Death's Plate Armour", 5, 20, 20);
        Clothing monsterLoot9 = new Clothing("Lion's Plate Armour", 8, 40, 30);
        Clothing monsterLoot10 = new Clothing("Dragon's Plate Armour", 12, 50, 40);
        Clothing monsterLoot11 = new Clothing("Dragon God's Armour", 18, 70, 60);
        Clothing monsterLoot12 = new Clothing("Black Steel Armour", 25, 100, 100);


        Sword monsterWeapon = new Sword("Glory Monster Sword", 5, 10, 5, 20);
        Clothing monsterClothing = new Clothing("Shiny Monster Armour", 10, 20, 5);

        for (int i = 0; i < monster_num; i++) {
            this.monsters.add(
                    new Monster(i, "Monster " + (i + 1),
                            this.level * 20,
                            monsterWeapon,
                            monsterClothing,
                            true, this.ID, this.level,
                            monsterInventory)
            );
        }

    }

    public void printer() {
        System.out.printf("Room ID: %d, Room level %d ", this.ID, this.level);

        try {
            System.out.printf("Prev room: %d ", this.door1.getID());
        } catch (NullPointerException e) {
            System.out.printf("Prev room: null ");
        }

        try {
            System.out.printf("Next room: %d ", this.door2.getID());
        } catch (NullPointerException e) {
            System.out.printf("Next room: null ");
        }

        try {
            System.out.printf("stairs: %d ", this.stairs.getID());
        } catch (NullPointerException e) {
            System.out.printf("stairs: null ");
        }



        System.out.printf("%n");

    }

    public void printRoomInfo() {
        if (this.door1 != null) {
            System.out.printf("Door 1 (d1)%n");
        }

        if (this.door2 != null) {
            System.out.printf("Door 2 (d2)%n");
        }

        if (this.stairs != null) {
            System.out.printf("Stairs (up)%n");
        }

        if (this.downstairs != null) {
            System.out.printf("Downstairs (down)%n");
        }

        if (!this.monsters.isEmpty()) {
            for (int i = 0; i < this.monsters.size(); i++) {

                if (this.monsters.get(i).isLifeStatus()) {
                    System.out.printf("Attack " + this.monsters.get(i).getName() + " --> (a%d)%n", i + 1);
                } else {
                    System.out.printf("Attack " + this.monsters.get(i).getName() + " %n", i + 1);
                }

            }
        }

    }

}