import java.util.ArrayList;

public class Rooms {
    public boolean hasaSideDoor;
    Monster monster;
    int howManyDoors;
    boolean hasaLadder;
    int howManytownsPeople = (int)Math.random()*40;


    private int randomRoom;
    private int randomSet;

    public int getRandomRoom() {
        return randomRoom;
    }

    public void setRandomRoom(int randomRoom) {
        this.randomRoom = randomRoom;
    }

    public int getRandomSet() {
        return randomSet;
    }

    public void setRandomSet(int randomSet) {
        this.randomSet = randomSet;
    }

    public void generateMonsterInTheRoom() {
        ArrayList<Monster> monsterArrayList = Monster.generateMonster();
        monster = monsterArrayList.get(0);

    }

    public void perceive(Hero hero){
        int random= (int) (Math.random());


        System.out.println("The hero sees the following:");
        if (hasaLadder = true){
            System.out.println("a ladder that goes upwards,");
        }if (howManyDoors == 1) {
            System.out.println("a door (door1) ,");
        }else if (howManyDoors == 2){
            if (random == 0){
                System.out.println("another door to the left (door2),");
            }else if (random == 1){
                System.out.println("another door to the right (door2) ,");
            }

        }
        if (howManytownsPeople != 0){
            System.out.println(howManytownsPeople + "townspeople,");
        }
        System.out.println("a " + monster.getName() + ".");
        System.out.println("-----------------------");
        if (howManyDoors == 1) {
            System.out.println("Enter the door (enterdoor1)");
        } else if (howManyDoors == 2){
            System.out.println("Enter the door (enterdoor1)");
            System.out.println("Second door (enterdoor2)");
        }
        System.out.println("Attack monster (attackmonster1)");
        System.out.println("Check life status (hp)");
        System.out.println("Check monster's life status (monsterhp)");
        if (hasaLadder){
            System.out.println("Climb ladder (ladder)");
        }
    }


}




	
