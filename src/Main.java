import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        ArrayList<OneHanded> oneHandedArrayList = OneHanded.generateOneHandedSwords();
        ArrayList<TwoHanded> twoHandedArrayList = TwoHanded.generateTwoHandedSwords();
        ArrayList<Shields> shieldsArrayLists = Shields.generateShields();
        ArrayList<Bows> bowsArrayList = Bows.generateBows();
        ArrayList<Armors> armorsArrayList = Armors.generateArmors();
        Rooms[][] rooms = generateRooms();




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
            }
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

                looper = false;
            }
        }

            while (looperforladder) {
                if (randomsetforladder >= randomset || randomroomforladder >= randomroom || randomsetforladder == 0 || randomroomforladder == 0 || randomsetforladder == randomset - 1) {
                    randomsetforladder = (int)(Math.random() * 4 - 1 );
                    randomroomforladder = (int)(Math.random() * 4 - 1);

                }else {

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
        rooms[randomsetforladder-1][randomroomforladder-1].hasaLadder = true;
        rooms[randomsetforladder-1][randomroomforladder-1].howManyDoors = 2;
        rooms[randomset-1][randomroom-1].howManyDoors = 0;
        return rooms;
    }

    public static void enterNextRoom(Hero hero, Rooms[][] rooms){
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
