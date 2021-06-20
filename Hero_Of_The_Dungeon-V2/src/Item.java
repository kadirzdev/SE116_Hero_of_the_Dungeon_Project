abstract public class Item {

    private String name;
    private int weight;
    private int value;

    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class Clothing extends Item {

    public Clothing(String name, int weight, int value) {
        super(name, weight, value);
    }
}


abstract class Weapon extends Item {

    private int damage;
    private int range;

    public Weapon(String name, int weight, int value, int damage, int range) {
        super(name, weight, value);
        this.damage = damage;
        this.range = range;
    }

}

class Punch extends Weapon {

    public Punch(String name, int weight, int value, int damage, int range) {
        super(name, weight, value, damage, range);
    }
}

class Sword extends Weapon {

    public Sword(String name, int weight, int value, int damage, int range) {
        super(name, weight, value, damage, range);
    }
}

class Axe extends Weapon {

    public Axe(String name, int weight, int value, int damage, int range) {
        super(name, weight, value, damage, range);
    }

}

class Bow extends Weapon {

    public Bow(String name, int weight, int value, int damage, int range) {
        super(name, weight, value, damage, range);
    }

}