import java.util.ArrayList;
import java.util.Arrays;

public class Rooms {




   public static Rooms[][] generateRooms(){
       int randomset = 0;
       int randomroom = 0;
       boolean looper = true;
       while (looper = true) {
           if (randomset == 0 || randomroom == 0) {
               randomset = (int) (Math.random() * 4);
               randomroom = (int) (Math.random() * 4);

           } else {
               break;
           }
       }
       System.out.println("There are " + randomset + " sets and " + randomroom + " rooms in the dungeon now.");
       Rooms[][] rooms = new Rooms[randomset][randomroom];



        return rooms;
    }
	
}