import java.util.ArrayList;
import java.util.Random;

public class Game {

    private int totalLevels;
    private int roomsInEachLevel;
    private Room[][] game;


    public Game(int totalLevels, int roomsInEachLevel) {
        this.totalLevels = totalLevels;
        this.roomsInEachLevel = roomsInEachLevel;

        this.game = new Room[totalLevels][roomsInEachLevel];
        this.generateRooms();
        this.generateLevelSystem();
        this.fillMobs();
    }

    public Room[][] getGame() {
        return game;
    }

    public void setGame(Room[][] game) {
        this.game = game;
    }

    public void generateRooms() {
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        ArrayList<Townsperson> townspeople = new ArrayList<Townsperson>();
        ArrayList<Item> itemLoot = new ArrayList<Item>();

        for (int i = 1; i <= this.totalLevels; i++) {
            for (int j = 1; j <= this.roomsInEachLevel; j++) {
                int roomID = i * this.totalLevels + i + j - 4;
                this.game[i - 1][j - 1] = new Room(roomID, i, monsters, townspeople, null, null, null, null);
            }
        }

        for (int i = 0; i < this.totalLevels; i++) {
            for (int j = 0; j < this.roomsInEachLevel; j++) {
                Room nextRoom, prevRoom;

                prevRoom = (j == 0) ? null : this.game[i][j - 1];
                nextRoom = (j == 3) ? null : this.game[i][j + 1];

                this.game[i][j].setDoor1(prevRoom);
                this.game[i][j].setDoor2(nextRoom);

            }
        }

    }

    public void fillMobs() {

        for (int i = 0; i < this.totalLevels; i++) {

            for (int j = 0; j < this.roomsInEachLevel; j++) {
                game[i][j].createMobs();
            }

        }

    }

    public void generateLevelSystem() {
        Random rand = new Random();

        // Get random rooms from each level
        Room level1_room = this.game[0][rand.nextInt(this.totalLevels)];
        Room level1to2_room = this.game[1][rand.nextInt(this.totalLevels)];

        Room level2to3_room = this.game[1][rand.nextInt(this.totalLevels)];
        Room level3_room = this.game[2][rand.nextInt(this.totalLevels)];

        // Do the connection between the levels
        level1_room.setStairs(level1to2_room);
        level2to3_room.setStairs(level3_room);

        // Do the connection for lower levels
        level1to2_room.setDownstairs(level1_room);
        level3_room.setDownstairs(level1to2_room);
    }

    public Room getCurrentRoom(int roomID) {
        Room currentRoom;

        switch (roomID) {
            case 2:
                currentRoom = this.game[0][1];
                break;
            case 3:
                currentRoom = this.game[0][2];
                break;
            case 4:
                currentRoom = this.game[0][3];
                break;
            case 5:
                currentRoom = this.game[1][0];
                break;
            case 6:
                currentRoom = this.game[1][1];
                break;
            case 7:
                currentRoom = this.game[1][2];
                break;
            case 8:
                currentRoom = this.game[1][3];
                break;
            case 9:
                currentRoom = this.game[2][0];
                break;
            case 10:
                currentRoom = this.game[2][1];
                break;
            case 11:
                currentRoom = this.game[2][2];
                break;
            case 12:
                currentRoom = this.game[2][3];
                break;
            default:
                currentRoom = this.game[0][0];
                break;
        }

        return currentRoom;

    }

    public void printRooms() {
        for (int i = 1; i <= this.totalLevels; i++) {
            for (int j = 1; j <= this.roomsInEachLevel; j++) {
                this.game[i - 1][j - 1].printer();
            }
        }
    }

}
