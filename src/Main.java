import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        ArrayList<ArrayList<Items>> allItemsArrayList = new ArrayList<>();
        ArrayList<OneHanded> oneHandedArrayList = OneHanded.generateOneHandedSwords();
        ArrayList<TwoHanded> twoHandedArrayList = TwoHanded.generateTwoHandedSwords();
        ArrayList<Shields> shieldsArrayLists = Shields.generateShields();
        ArrayList<Bows> bowsArrayList = Bows.generateBows();
        ArrayList<Armors> armorsArrayList = Armors.generateArmors();

        Rooms[][] rooms = Rooms.generateRooms();




    Character hero = new Character(1,"Testing Buddy",100,oneHandedArrayList.get(0),armorsArrayList.get(0),0,0);
        System.out.println("The " + hero.getName() + " is now in " + (hero.getCurrentRoom()+1) + ". room");
        menu();

    }

    public static void menu(){

    }




}
