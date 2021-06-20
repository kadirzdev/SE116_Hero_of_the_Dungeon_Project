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
        String[] heroes = { "Aragorn" };

        // Create game and generate rooms/levels
        Game game1 = new Game(m, n);

        // Set the defaults
        ArrayList<Item> default_inventory = new ArrayList<Item>();

        // Load the Items
        Weapon punch = new Punch("Punch", 0, 1, 1, 5);
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
            newHero = new Aragorn(heroIndex, heroName, 100, punch, starter_clothing, true, 1, 1, default_inventory);
        } else {
            newHero = new Aragorn(heroIndex, heroName, 100, punch, starter_clothing, true, 1, 1, default_inventory);
        }

        System.out.printf("Your character is successfully created!%n%n");

        System.out.printf("Level %d, Room %02d\nHero %s, %dHP, %s, %s%n", newHero.getCurrentLevel(), newHero.getCurrentRoom(), heroes[heroIndex - 1], newHero.getHitPoints(), newHero.getWeapon().getName(), newHero.getClothing().getName());

        int roomID = newHero.getCurrentRoom();

        Room currentRoom = game1.getCurrentRoom(roomID);


        currentRoom.printRoomInfo();

        //Hero kadir = new Hero(1, "Kadir", 100, longbow, armour, true, 1, 1, inventory);
    }
}
