import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        // Set the level and room count constants
        final int m = 3;
        final int n = 4;

        // Available heroes to pick
        String[] heroes = { "Aragorn", "Warrior", "HokiDoki" };

        // Create game and generate rooms/levels
        Game game1 = new Game(m, n);

        // Set the defaults
        ArrayList<Item> default_inventory = new ArrayList<Item>();

        // Load the Items
        Punch punch = new Punch("Punch", 0, 1, 1, 5);
        Sword basic_sword = new Sword("Basic Sword", 5, 3, 15, 10);
        Axe basic_axe = new Axe("Basic Axe", 5, 5, 20, 7);
        Clothing starter_clothing = new Clothing("Monk Plate Armour", 10, 20, 50);

        Bow longbow = new Bow("Longbow", 10, 10, 15, 120);
        Bow shortbow = new Bow("Shortbow", 7, 8, 12, 70);
        Clothing black_armour = new Clothing("Black Armour", 100, 150, 75);



        System.out.println("------------------------------------------------");
        System.out.println("--- Welcome to the Hero Of The Dungeon Game! ---");
        System.out.println("------------------------------------------------");

        // Pick hero
        int heroIndex;
        String heroName;

        Hero newHero;

        for (int i = 0; i < heroes.length; i++) {
            System.out.printf("%d: (%s)%n", i + 1, heroes[i]);
        }
        System.out.printf("Pick your hero type: ");
        heroIndex = scanner.nextInt();

        System.out.printf("The name of your hero: ");
        heroName = scanner.next();

        default_inventory.add(longbow);
        default_inventory.add(shortbow);
        default_inventory.add(black_armour);

        if (heroIndex == 1) {
            newHero = new Aragorn(heroIndex, heroName, 130, basic_axe, starter_clothing, true, 1, 1, default_inventory);
        } else if(heroIndex == 2) {
            newHero = new Warrior(heroIndex, heroName, 100, basic_sword, starter_clothing, true, 1, 1, default_inventory);
        } else {
            newHero = new HokiDoki(heroIndex, heroName, 80, shortbow, starter_clothing, true, 1, 1, default_inventory);
        }


        System.out.printf("Your character is successfully created!%n%n");





        boolean IN_ROOM = true;

        int roomID, toMove;
        String inputMove;
        Room currentRoom;
        int resultOfFight = 0;

        while(IN_ROOM) {

            do {
                System.out.printf("------ Level %d - Room %02d ------\nPlayer: %s, Hero %s, %dHP, %s, %s%n", newHero.getCurrentLevel(), newHero.getCurrentRoom(), heroName, heroes[heroIndex-1], newHero.getHitPoints(), newHero.getWeapon().getName(), newHero.getClothing().getName());

                roomID = newHero.getCurrentRoom();
                currentRoom = game1.getCurrentRoom(roomID);
                currentRoom.printRoomInfo();
                System.out.println("List Inventory (in)");
                System.out.println("Loot Items (lt)");

                System.out.printf("Your ACTION: ");

                inputMove = scanner.next();

                char commandLetter = inputMove.charAt(0);

                if (inputMove.equals("d1") && (currentRoom.getDoor1() != null)) {
                    toMove = currentRoom.getID() - 1;
                    newHero.moveHero(toMove);
                } else if (inputMove.equals("d2") && (currentRoom.getDoor2() != null)){
                    toMove = currentRoom.getID() + 1;
                    newHero.moveHero(toMove);
                } else if (inputMove.equals("up") && (currentRoom.getStairs() != null)) {
                    toMove = currentRoom.getStairs().getID();
                    newHero.setCurrentLevel(newHero.getCurrentLevel() + 1);
                    newHero.moveHero(toMove);
                } else if (inputMove.equals("down") && (currentRoom.getDownstairs() != null)){
                    toMove = currentRoom.getDownstairs().getID();
                    newHero.setCurrentLevel(newHero.getCurrentLevel() - 1);
                    newHero.moveHero(toMove);
                } else if (commandLetter == 'a') {
                    String attackedMonsterIndexSTR = inputMove.substring(1);
                    int attackedMonsterIndex = Integer.parseInt(attackedMonsterIndexSTR);

                    try {
                        Monster attackedMonster = currentRoom.getMonsters().get(attackedMonsterIndex - 1);
                        resultOfFight = newHero.fight(attackedMonster);

                        if(resultOfFight == -1) {
                            IN_ROOM = false;
                            break;
                        } else if(resultOfFight == 1) {
                            attackedMonster.setName("Monster " + (attackedMonster.getID() + 1) + " (DEAD)");

                            ArrayList<Item> allLootCollection = new ArrayList<Item>();
                            allLootCollection.addAll(currentRoom.getItemLoot());
                            allLootCollection.addAll(attackedMonster.getInventory());

                            currentRoom.setItemLoot(allLootCollection);

                            // Update the hero's rescued townspeople stat

                        } else {
                            System.out.println("Nothing happened!");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("That monster is not EXIST! Please enter valid input.");
                        continue;
                    }

                } else if(inputMove.equals("in")) {
                    System.out.println("List of items - Pick up item (iX)");
                    System.out.println("---------------");
                    newHero.listInventory();

                } else if (commandLetter == 'i') {

                    String equippedItemIndexSTR = inputMove.substring(1);
                    int equippedItemIndex = Integer.parseInt(equippedItemIndexSTR);

                    try {

                        Item equippedItem = newHero.getInventory().get(equippedItemIndex - 1);
                        newHero.equip(equippedItem, equippedItemIndex - 1);

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("That slot in your inventory is EMPTY!");
                        continue;
                    }

                }  else if (inputMove.equals("lt")) {

                    currentRoom.displayLootableItems();

                } else {
                    System.out.println("Please check your ACTION!");
                    toMove = currentRoom.getID();
                }

            } while(!inputMove.equals("d1") && !inputMove.equals("d2") && !inputMove.equals("up") && !inputMove.equals("down") && resultOfFight != -1);



        }







        //Hero kadir = new Hero(1, "Kadir", 100, longbow, armour, true, 1, 1, inventory);
    }
}
