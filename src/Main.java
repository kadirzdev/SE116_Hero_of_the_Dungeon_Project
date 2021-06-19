import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        ArrayList<OneHanded> oneHandedArrayList = OneHanded.generateOneHandedSwords();
        ArrayList<Shields> shieldsArrayLists = Shields.generateShields();
        ArrayList<Bows> bowsArrayList = Bows.generateBows();
        ArrayList<Armors> armorsArrayList = Armors.generateArmors();
        Rooms[][] rooms = generateRooms();

        ArrayList<Weapons> weop = new ArrayList<>();





        Hero hero = new Hero(1,"Testing Buddy",100,oneHandedArrayList.get(0),armorsArrayList.get(0),0,0,1);
        System.out.println("The " + hero.getName() + " is now in " + (hero.getCurrentRoom()+1) + ". room");
        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);

        boolean looper = true;
        while(looper) {

            String choose = input.next();
            switch (choose) {

                case "attackmonster1":
                    if (rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.isDead()){
                        System.out.println("The monster is already dead.");
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }else if(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster == null){
                        System.out.println("There's no monster in this room.");
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }else {
                        hero.attack(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster, hero, rooms);
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }
                    if (hero.getHitPoints() <= 0){
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
                    if(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster == null){
                        System.out.println("There's no monster in this room.");
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }else {
                        System.out.println("Monster's HP: " + rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getCurrentHitPoints());
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }
                    break;

                case "enterdoor1":
                    enterNextRoom(hero,rooms);
                    rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    break;

                case "enterdoor2":
                    if (rooms[hero.getCurrentSet()][hero.getCurrentRoom()].howManyDoors != 2){
                        System.out.println("There's no second door.");
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }else {
                        hero.useDoor(rooms);
                        Arrays.fill(rooms,null);
                        rooms = generateRooms();
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }
                    break;

                case "ladder":
                    int winnerNumber = hero.useLadder();
                    if (winnerNumber == 16){
                        looper = false;

                    }else {
                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    }

                    break;

                case "goback":
                   goBack(hero,rooms);
                    rooms[hero.getCurrentSet()][hero.getCurrentRoom()].perceive(hero);
                    break;

                case "take":
                    boolean overencumber = true;
                    while(overencumber){
                        for (int i = 0; i < rooms[hero.getCurrentSet()][hero.getCurrentRoom()].weaponsloot.size(); i++) {
                           if (hero.carryweight < 20) {
                                   hero.weaponsArrayList.add(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].weaponsloot.get(i));
                                   hero.carryweight++;
                                   overencumber = false;
                           }else {
                               System.out.println("You don't have space in your inventory. You need to drop some items.");
                           }
                        }
                    }

                case "inventory":
                    hero.inventory();
                    boolean innerlooper = true;
                    while (innerlooper){
                        String weaponcataloguechoice = input.nextLine();
                        switch (weaponcataloguechoice){

                            case "weapon":
                                System.out.println("Weapons catalogue chosen.");
                                int secondcataloguechoice = input.nextInt();
                                System.out.println(hero.weaponsArrayList.get(secondcataloguechoice).name + " is chosen.");
                                hero.weaponschoices();
                                int thirdcataloguechoice = input.nextInt();
                                switch (thirdcataloguechoice){
                                    case 1:
                                        if (hero.getWeapon() != null){
                                            System.out.println("You unequipped" + hero.getWeapon() + " and equipped " + hero.weaponsArrayList.get(secondcataloguechoice).name + ".");
                                            hero.setWeapon(hero.weaponsArrayList.get(secondcataloguechoice));
                                            innerlooper = false;
                                        }else if (hero.getWeapon() == hero.weaponsArrayList.get(secondcataloguechoice)){
                                            System.out.println("You unequipped" + hero.weaponsArrayList.get(secondcataloguechoice).name + ".");
                                            hero.setWeapon(new OneHanded("Fists",1,1));
                                            innerlooper = false;
                                        }else{
                                            System.out.println("You unequipped" + hero.weaponsArrayList.get(secondcataloguechoice).name + ".");
                                            innerlooper = false;
                                        }

                                    case 2:
                                        System.out.println("You dropped the weapon.");
                                        rooms[hero.getCurrentSet()][hero.getCurrentRoom()].weaponsloot.add(hero.weaponsArrayList.remove(secondcataloguechoice));
                                        hero.weaponsArrayList.remove(secondcataloguechoice);
                                        innerlooper = false;
                                        break;
                                }
                                break;
                        }
                    }
                case "loot":
                    System.out.println("In the remains, you found" +rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getWeapon().name +", and" + rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getClothing().name + "." );
                    int whattypeofloot = (int)(Math.random() * 3);

                    hero.lootMenu(rooms);
                    String lootchosin = input.next();
                    switch (lootchosin){
                        case "loot1":
                            if (hero.carryweight < 20) {
                                System.out.println(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getWeapon().name + " is now in your inventory.");
                                hero.weaponsArrayList.add(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getWeapon());
                                hero.carryweight++;
                            }else {
                                System.out.println("You don't have space in your inventory. You need to drop some items.");
                            }


                            break;
                        case "loot2":
                            if (hero.carryweight < 20) {
                                System.out.println(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getClothing().name + " is now in your inventory.");
                                hero.armorsArrayList.add(rooms[hero.getCurrentSet()][hero.getCurrentRoom()].monster.getClothing());
                                hero.carryweight++;
                            }else {
                                System.out.println("You don't have space in your inventory. You need to drop some items.");
                            }
                            break;

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

    public static Rooms[][] generateRooms(){
        int randomset = 0;
        int randomroom = 0;

        int randomsetforladder = 10;
        int randomroomforladder = 10;

        boolean looper = true;
        boolean looperforladder = true;
        while (looper) {
            if (randomset <= 2 || randomroom <= 2 ) {
                randomset = (int) (Math.random() * 5);
                randomroom = (int) (Math.random() * 5);
            } else {
                System.out.println("There are " + randomset + " sets and " + randomroom + " rooms in this level.");
                looper = false;
            }
        }

            while (looperforladder) {
                if (randomsetforladder >= randomset || randomroomforladder >= randomroom || randomsetforladder == 0 || randomroomforladder == 0 || randomsetforladder == randomset - 1) {
                    randomsetforladder = (int)(Math.random() * 4 - 1 );
                    randomroomforladder = (int)(Math.random() * 4 - 1);

                }else {
                    System.out.println("The ladder is in " + randomsetforladder + ". set and " + randomroomforladder + ". room.");
                    looperforladder = false;
                }
        }
        Rooms[][] rooms = new Rooms[randomset][randomroom];

        for (int i = 0; i < rooms.length; i++){
            for (int j = 0; j < rooms[i].length; j++){
                rooms[i][j] = new Rooms();
                rooms[i][j].generateMonsterInTheRoom();
            }
        }
        for (int i = 0; i < rooms.length; i++){
            for (int j = 0; j < rooms[i].length; j++){
                rooms[i][j].hasLadder = false;
                rooms[i][j].howManyDoors = 1;
            }
        }
        rooms[randomsetforladder-1][randomroomforladder-1].hasLadder = true;
        rooms[randomsetforladder-1][randomroomforladder-1].howManyDoors = 2;
        rooms[randomset-1][randomroom-1].howManyDoors = 0;
        return rooms;
    }

    public static void enterNextRoom(Hero hero, Rooms[][] rooms){
        hero.previousRoom = hero.getCurrentRoom();
        hero.previousSet = hero.getCurrentSet();

        if (hero.getCurrentRoom() == rooms[hero.getCurrentSet()].length-1){
            hero.setCurrentSet(hero.getCurrentSet()+1);
            hero.setCurrentRoom(0);
        }else if (hero.getCurrentSet() == rooms.length && hero.getCurrentRoom()-1 == rooms[hero.getCurrentSet()].length-1){
            System.out.println("There is no door.");
        }else {
            hero.setCurrentRoom(hero.getCurrentRoom() + 1);
        }
    }







}
