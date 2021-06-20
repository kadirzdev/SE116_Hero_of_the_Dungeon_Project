import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] heroTypes = {"Warrior", "Aragorn"};

        ArrayList<OneHanded> oneHandedArrayList = OneHanded.generateOneHandedSwords();
        ArrayList<Shields> shieldsArrayLists = Shields.generateShields();
        ArrayList<Bows> bowsArrayList = Bows.generateBows();
        ArrayList<Armors> armorsArrayList = Armors.generateArmors();

        ArrayList<Armors> armorsloot = new ArrayList<>();
        ArrayList<Weapons> weaponsloot = new ArrayList<>();
        ArrayList<Shields> shieldsloot = new ArrayList<>();
        ArrayList<Items> itemsloot = new ArrayList<>();
        Rooms[][] rooms = generateRooms();

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                rooms[i][j].itemsloot = new ArrayList<>();
                rooms[i][j].armorsloot = new ArrayList<>();
                rooms[i][j].weaponsloot = new ArrayList<>();
                rooms[i][j].shieldsloot = new ArrayList<>();
            }
        }


        ArrayList<Weapons> weop = new ArrayList<>();


        ArrayList<Armors> armorsInventory = new ArrayList<>();
        ArrayList<Weapons> weaponsInventory = new ArrayList<>();
        ArrayList<HealingPotion> potionInventory = new ArrayList<>();

        System.out.println("----- CREATE YOUR HERO -----");
        for (int i = 0; i < heroTypes.length; i++) {
            System.out.printf("%d - %s%n", i+1, heroTypes[i]);
        }
        System.out.printf("Pick your hero type: ");
        int heroType = input.nextInt();

        System.out.printf("Type your hero name: ");
        String heroName = input.next();





        Hero hero = new Hero(1, heroName, 100, oneHandedArrayList.get(0), armorsArrayList.get(0), 0, 0, 1, heroTypes[heroType-1]);
        armorsInventory.add(hero.getClothing());
        weaponsInventory.add(hero.getWeapon());
        System.out.println("Hero " + hero.getHeroType() + ", " + hero.getHitPoints() + "HP " + hero.getWeapon().getName() + ", " + hero.getClothing().getName());
        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);

        boolean looper = true;
        while (looper) {

            String choose = input.next();
            switch (choose) {

                case "attackmonster1":
                    if (rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.isDead()) {
                        System.out.println("The monster is already dead.");
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    } else if (rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster == null) {
                        System.out.println("There's no monster in this room.");
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    } else {
                        hero.attack(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster, hero, rooms);
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }
                    if (hero.getHitPoints() <= 0) {
                        System.out.println("You are dead.");
                        looper = false;
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }
                    break;
                case "hp":
                    hero.isLifeStatus();
                    rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);

                    break;
                case "monsterhp":
                    if (rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster == null) {
                        System.out.println("There's no monster in this room.");
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    } else {
                        System.out.println("Monster's HP: " + rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getCurrentHitPoints());
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }
                    break;

                case "enterdoor1":
                    enterNextRoom(hero, rooms);
                    rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    break;

                case "enterdoor2":
                    if (rooms[hero.getCurrentSet()][hero.getCurrentRoom()].howManyDoors != 2) {
                        System.out.println("There's no second door.");
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    } else {
                        hero.useDoor(rooms);
                        Arrays.fill(rooms, null);
                        rooms = generateRooms();
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }
                    break;

                case "ladder":
                    int winnerNumber = hero.useLadder();
                    if (winnerNumber == 16) {
                        looper = false;

                    } else {
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }

                    break;

                case "goback":
                    goBack(hero, rooms);
                    rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    break;

                case "take":
                    for (int i = 0; i < rooms[hero.getCurrentSet()][hero.getCurrentRoom()].weaponsloot.size(); i++) {
                        System.out.println(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].weaponsloot.get(i).name + "is now in your inventory.");
                        weaponsInventory.add(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].weaponsloot.get(i));
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].weaponsloot.remove(i);
                    }
                    break;
                case "inventory":
                    hero.inventory(weaponsInventory, armorsInventory);
                    boolean innerlooper = true;
                    while (innerlooper) {
                        System.out.println("");
                        String weaponcataloguechoice = input.nextLine();
                        switch (weaponcataloguechoice) {

                            case "weapon":
                                System.out.println("Weapons catalogue chosen.");
                                System.out.println("Type which one would you like to choose.");
                                int secondcataloguechoice = input.nextInt();
                                System.out.println(weaponsInventory.get(secondcataloguechoice - 1).name + " is chosen.");
                                hero.weaponschoices();
                                int thirdcataloguechoice = input.nextInt();
                                switch (thirdcataloguechoice) {
                                    case 1:
                                        if (hero.getWeapon() == weaponsInventory.get(secondcataloguechoice - 1)) {
                                            System.out.println("You unequipped " + weaponsInventory.get(secondcataloguechoice - 1).name + ".");
                                            hero.setWeapon(new OneHanded("Fists", 1, 1));
                                            rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                                            innerlooper = false;
                                        } else if (hero.getWeapon().name != null) {
                                            System.out.println("You unequipped " + hero.getWeapon().name + " and equipped " + weaponsInventory.get(secondcataloguechoice - 1).name + ".");
                                            hero.setWeapon(weaponsInventory.get(secondcataloguechoice - 1));
                                            rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                                            innerlooper = false;
                                        } else {
                                            System.out.println("You equipped" + weaponsInventory.get(secondcataloguechoice - 1).name + ".");
                                            rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                                            innerlooper = false;
                                        }
                                        break;

                                    case 2:
                                        System.out.println(hero.getWeapon());
                                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                                }
                                break;
                            case "armor":
                                System.out.println("Armors catalogue chosen.");
                                System.out.println("Type which one would you like to choose.");
                                int secondcataloguechoice2 = input.nextInt();
                                System.out.println(armorsInventory.get(secondcataloguechoice2 - 1).name + " is chosen.");
                                hero.weaponschoices();
                                int thirdcataloguechoice2 = input.nextInt();
                                switch (thirdcataloguechoice2) {
                                    case 1:
                                        if (hero.getClothing() == armorsInventory.get(secondcataloguechoice2 - 1)) {
                                            System.out.println("You unequipped " + armorsInventory.get(secondcataloguechoice2 - 1).name + ".");
                                            rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                                            innerlooper = false;
                                        } else if (hero.getClothing().name != null) {
                                            System.out.println("You unequipped " + hero.getClothing().name + " and equipped " + armorsInventory.get(secondcataloguechoice2 - 1).name + ".");
                                            hero.setClothing(armorsInventory.get(secondcataloguechoice2 - 1));
                                            rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                                            innerlooper = false;
                                        } else {
                                            System.out.println("You equipped" + armorsInventory.get(secondcataloguechoice2 - 1).name + ".");
                                            rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                                            innerlooper = false;
                                        }
                                        break;
                                }
                                break;
                        }

                    }
                    break;
                case "loot":
                    System.out.println("In the remains, you found " + rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getWeapon().name + ", and " + rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getClothing().name + ".");
                    int whattypeofloot = (int) (Math.random() * 3);
                    System.out.println("You also found a healing potion.");

                    hero.lootMenu(rooms);
                    String lootchosin = input.next();
                    switch (lootchosin) {
                        case "take1":
                            System.out.println(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getWeapon().name + " is now in your inventory.");
                            weaponsInventory.add(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getWeapon());
                            rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);


                            break;
                        case "take2":

                            System.out.println(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getClothing().name + " is now in your inventory.");
                            armorsInventory.add(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getClothing());
                            rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                            break;

                        case "take3":
                            System.out.println("Healing potion is now in your inventory.");
                            hero.setBottlesofhealingpotions(hero.getBottlesofhealingpotions() + 1);
                            rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                            break;

                    }

                case "heal":
                    if (hero.getBottlesofhealingpotions() == 0){
                        System.out.println("You don't have any healing potions.");
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }else {
                        HealingPotion.heal(hero);
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);

                    }
            }


        }
    }


    private static void goBack(Hero hero, Rooms[][] rooms) {

        if (hero.getCurrentLevel() == 0 && hero.getCurrentSet() == 0) {
            System.out.println("There's no door in the back.");
        } else {
            hero.setCurrentSet(hero.previousSet);
            hero.setCurrentRoom(hero.previousRoom);
            System.out.println("Hero is now " + hero.previousSet + ". set and " + hero.previousRoom + ". room");

        }
    }

    public static Rooms[][] generateRooms() {
        int randomset = 0;
        int randomroom = 0;

        int randomsetforladder = 10;
        int randomroomforladder = 10;

        boolean looper = true;
        boolean looperforladder = true;
        while (looper) {
            if (randomset <= 2 || randomroom <= 2) {
                randomset = (int) (Math.random() * 5);
                randomroom = (int) (Math.random() * 5);
            } else {
                System.out.println("There are " + randomset + " sets and " + randomroom + " rooms in this level.");
                looper = false;
            }
        }

        while (looperforladder) {
            if (randomsetforladder >= randomset || randomroomforladder >= randomroom || randomsetforladder == 0 || randomroomforladder == 0 || randomsetforladder == randomset - 1) {
                randomsetforladder = (int) (Math.random() * 4 - 1);
                randomroomforladder = (int) (Math.random() * 4 - 1);

            } else {
                System.out.println("The ladder is in " + randomsetforladder + ". set and " + randomroomforladder + ". room.");
                looperforladder = false;
            }
        }
        Rooms[][] rooms = new Rooms[randomset][randomroom];

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                rooms[i][j] = new Rooms();
                rooms[i][j].generateMonsterInTheRoom();

            }
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                rooms[i][j].hasLadder = false;
                rooms[i][j].howManyDoors = 1;
            }
        }
        rooms[randomsetforladder][randomroomforladder].hasLadder = true;
        rooms[randomsetforladder][randomroomforladder].howManyDoors = 2;
        rooms[randomset - 1][randomroom - 1].howManyDoors = 0;
        return rooms;
    }

    public static void enterNextRoom(Hero hero, Rooms[][] rooms) {
        hero.previousRoom = hero.getCurrentRoom();
        hero.previousSet = hero.getCurrentSet();

        if (hero.getCurrentRoom() == rooms[hero.getCurrentSet()].length - 1) {
            hero.setCurrentSet(hero.getCurrentSet() + 1);
            hero.setCurrentRoom(0);
        } else if (hero.getCurrentSet() == rooms.length && hero.getCurrentRoom() - 1 == rooms[hero.getCurrentSet()].length - 1) {
            System.out.println("There is no door.");
        } else {
            hero.setCurrentRoom(hero.getCurrentRoom() + 1);
        }
    }
}









