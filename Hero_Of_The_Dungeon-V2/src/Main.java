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
        Clothing starter_clothing = new Clothing("Monk Plate Armour", 10, 20);

        Bow longbow = new Bow("Longbow", 10, 10, 15, 120);
        Bow shortbow = new Bow("Shortbow", 7, 8, 12, 70);
        Clothing black_armour = new Clothing("Black Armour", 100, 150);

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

        if (heroIndex == 1) {
            newHero = new Aragorn(heroIndex, heroName, 90, basic_sword, starter_clothing, true, 1, 1, default_inventory);
        } else if(heroIndex == 2) {
            newHero = new Warrior(heroIndex, heroName, 120, basic_sword, starter_clothing, true, 1, 1, default_inventory);
        } else {
            newHero = new HokiDoki(heroIndex, heroName, 70, shortbow, starter_clothing, true, 1, 1, default_inventory);
        }

        System.out.printf("Your character is successfully created!%n%n");




        boolean IN_ROOM = true;

        int roomID, toMove;
        String inputMove;
        Room currentRoom;
        while(IN_ROOM) {

            do {
                System.out.printf("------ Level %d - Room %02d ------\nPlayer: %s, Hero %s, %dHP, %s, %s%n", newHero.getCurrentLevel(), newHero.getCurrentRoom(), heroName, heroes[heroIndex-1], newHero.getHitPoints(), newHero.getWeapon().getName(), newHero.getClothing().getName());

                roomID = newHero.getCurrentRoom();
                currentRoom = game1.getCurrentRoom(roomID);
                currentRoom.printRoomInfo();

                System.out.printf("Your ACTION: ");

                inputMove = scanner.next();
                int attackedMonsterIndex = java.lang.Character.getNumericValue(inputMove.charAt(inputMove.length() - 1));

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
                } else if (inputMove.equals("a1") && currentRoom.getMonsters().get(attackedMonsterIndex - 1).isLifeStatus()){

                    Monster attackedMonster = currentRoom.getMonsters().get(0);
                    int resultOfFight = newHero.fight(attackedMonster);

                    if(resultOfFight == -1) {
                        break;
                    } else if(resultOfFight == 1) {
                        attackedMonster.setName("Monster " + (attackedMonster.getID() + 1) + " (DEAD)");

                        // Update the hero's rescued townspeople stat
                    } else {
                        System.out.println("Nothing happened!");
                    }





                } else {
                    System.out.println("Please check your ACTION!");
                    toMove = currentRoom.getID();
                }

            } while(!inputMove.equals("d1") && !inputMove.equals("d2") && !inputMove.equals("up") && !inputMove.equals("down"));



        }







        //Hero kadir = new Hero(1, "Kadir", 100, longbow, armour, true, 1, 1, inventory);
    }
}
