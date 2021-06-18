import java.util.ArrayList;

public class Item {
}

    interface Items {
        void equip();

        void unequip();
    }

    class Armors implements Items {
        String name;
        int additionalHealth;

        public static ArrayList<Armors> generateArmors() {
            ArrayList<Armors> armorsArrayList = new ArrayList<Armors>();

            armorsArrayList.add(new Armors("Leather Armor", 10));
            armorsArrayList.add(new Armors("Chain Armor", 25));
            armorsArrayList.add(new Armors("Full Plate Armor", 40));
            return armorsArrayList;


        }

        @Override
        public void equip() {

        }

        @Override
        public void unequip() {

        }

        public Armors(String name, int additionalHealth) {
            this.name = name;
            this.additionalHealth = additionalHealth;
        }
    }


    abstract class Weapons implements Items {
        String name;
        int range;
        int damage;

        public Weapons(String name, int range, int damage) {
            this.name = name;
            this.range = range;
            this.damage = damage;
        }




    }

    class Swords extends Weapons implements Items {

        public Swords(String name, int range, int damage) {

            super(name, range, damage);
        }

        @Override
        public void equip() {

        }

        @Override
        public void unequip() {

        }

        public void block() {
        }

        @Override
        public String toString() {
            return "";
        }


    }

    class OneHanded extends Swords {

        public OneHanded(String name, int range, int damage) {
            super(name, range, damage);
        }

        public static ArrayList<OneHanded> generateOneHandedSwords(){

            ArrayList<OneHanded> oneHandedArrayList = new ArrayList<>();
            oneHandedArrayList.add(new OneHanded("Short sword", 2, 6));
            return oneHandedArrayList;
        }

        @Override
        public void block() {
            //depends if s/he has a shield or not.
        }


    }

    class TwoHanded extends Swords {

        public TwoHanded(String name, int range, int damage) {
            super(name, range, damage);
        }

        public static ArrayList<TwoHanded> generateTwoHandedSwords() {

            ArrayList<TwoHanded> twoHandedArrayList = new ArrayList<>();
            twoHandedArrayList.add(new TwoHanded("Longsword",5,10));
            return twoHandedArrayList;
        }
    }


    class Bows extends Weapons implements Items {

        public Bows(String name, int range, int damage) {
            super(name, range, damage);
        }

        public static ArrayList<Bows> generateBows() {
            ArrayList<Bows> bowsArrayList = new ArrayList<Bows>();

            bowsArrayList.add(new Bows("Shortbow", 20, 6));
            bowsArrayList.add(new Bows("Longbow", 35, 8));
            bowsArrayList.add(new OneHandedCrossbow("Hand Crossbow", 35, 8));
            bowsArrayList.add(new Bows("Heavy Crossbow", 60, 10));
             return bowsArrayList;
        }

        @Override
        public void equip() {

        }

        @Override
        public void unequip() {

        }
    }

    class OneHandedCrossbow extends Bows {

        public OneHandedCrossbow(String name, int range, int damage) {
            super(name, range, damage);
        }
    }

    class Shields implements Items {

        String name;
        int additionalHealth;
        int blockChance;

        public Shields(String name, int additionalHealth, int blockChance) {
            this.name = name;
            this.additionalHealth = additionalHealth;
            this.blockChance = blockChance;
        }

        public static ArrayList<Shields> generateShields() {
            ArrayList<Shields> shieldsArrayList = new ArrayList<>();

            shieldsArrayList.add(new Shields("Light Steel Shield", 10, 5));
            shieldsArrayList.add(new Shields("Heavy Steel Shield", 30, 10));
            shieldsArrayList.add(new Shields("Tower Shield", 75, 25));

            return shieldsArrayList;
        }

        void block() {

        }

        @Override
        public void equip() {

        }

        @Override
        public void unequip() {

        }
    }

