import java.util.ArrayList;

// Invetory Sistemi Eksik
// İtem giyme çıkarma eksik
// Canavardan item düşme olayı eksik


public class Character {

    // Character Props
    private int     ID;
    private String  name;
    private int     hitPoints;
    private int     weapon;
    private int     clothing;
    private boolean lifeStatus;

    // Character Location
    protected int location;

    public Character(int ID, String name, int hitPoints, int weapon, int clothing, int location) {
        this.ID = ID;
        this.name = name;
        this.hitPoints = hitPoints;
        this.weapon = weapon;
        this.clothing = clothing;
        this.location = location;
        this.lifeStatus = lifeCheck();

    }

    public boolean lifeCheck() {

        if (this.hitPoints <= 0) {
            return false;
        }
        return true;

    }


}

class Hero extends Character {

    public Hero(int ID, String name, int hitPoints, int weapon, int clothing, int location) {

        super(ID, name, hitPoints, weapon, clothing, location);

    }

    public void move(int x) {
        this.location = x;
    };

}

class Monster extends Character {

    private int loot;

    public Monster(int ID, String name, int hitPoints, int weapon, int clothing, int location, int loot) {

        super(ID, name, hitPoints, weapon, clothing, location);

        this.loot = loot;

    }


}

class Townspeople extends Character {

    public Townspeople(int ID, String name, int hitPoints, int weapon, int clothing, int location) {

        super(ID, name, hitPoints, weapon, clothing, location);

    }

}