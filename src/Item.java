import java.util.ArrayList;



    interface Items {
    }

    class Armors implements Items {
        String name;
        int additionalHealth;

        public static ArrayList<Armors> generateArmors() {
            ArrayList<Armors> armorsArrayList = new ArrayList<Armors>();

            armorsArrayList.add(new Armors("Leather Armor", 10));
            armorsArrayList.add(new Armors("Reinforced Tunic", 20));
            armorsArrayList.add(new Armors("Hardened Wooden Armor", 30));
            armorsArrayList.add(new Armors("Rusty Chain Armor",40));
            armorsArrayList.add(new Armors("Magical Leaf Armor",50));
            armorsArrayList.add(new Armors("Spidersilk Bodysuit",60));
            armorsArrayList.add(new Armors("Chain Armor", 70));
            armorsArrayList.add(new Armors("Rusty Full Plate Armor",80));
            armorsArrayList.add(new Armors("Full Plate Armor", 90));
            armorsArrayList.add(new Armors("Hellknight Plate Armor", 100));


            return armorsArrayList;


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
    }

    class OneHanded extends Swords {

        public OneHanded(String name, int range, int damage) {
            super(name, range, damage);
        }

        public static ArrayList<OneHanded> generateOneHandedSwords(){

            ArrayList<OneHanded> oneHandedArrayList = new ArrayList<>();
            oneHandedArrayList.add(new OneHanded("Dagger", 1, 3));
            oneHandedArrayList.add(new OneHanded("Shortsword", 2, 6));
            oneHandedArrayList.add(new OneHanded("Light Hammer", 2, 8));
            oneHandedArrayList.add(new OneHanded("Mace", 2, 10));
            oneHandedArrayList.add(new OneHanded("Handaxe", 2, 12));
            oneHandedArrayList.add(new OneHanded("Club", 5, 14));
            oneHandedArrayList.add(new OneHanded("Morningstar", 5, 16));
            oneHandedArrayList.add(new OneHanded("Battleaxe", 6, 18));
            oneHandedArrayList.add(new OneHanded("Longsword", 4, 20));
            oneHandedArrayList.add(new OneHanded("Meteor Hammer", 6, 25));

            return oneHandedArrayList;
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
            bowsArrayList.add(new Bows("Composite Shortbow", 25, 8));
            bowsArrayList.add(new Bows("Composite Longbow", 40, 10));
            bowsArrayList.add(new Bows("Magical Wooden Bow", 50, 12));
            bowsArrayList.add(new Bows("Double-string Shortbow", 40, 10));
            bowsArrayList.add(new Bows("Double-string Longbow", 50, 14));
            bowsArrayList.add(new Bows("Light Crossbow", 60, 16));
            bowsArrayList.add(new Bows("Heavy Crossbow", 80, 20));
             return bowsArrayList;
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
            shieldsArrayList.add(new Shields("Rusty Light Steel Shield", 10, 5));
            shieldsArrayList.add(new Shields("Light Steel Shield", 20, 10));
            shieldsArrayList.add(new Shields("Hardened Wooden Shield", 30, 15));
            shieldsArrayList.add(new Shields("Rusty Heavy Steel Shield", 40, 20));
            shieldsArrayList.add(new Shields("Heavy Steel Shield", 50, 25));
            shieldsArrayList.add(new Shields("Heavy Hardened Wooden Shield", 60, 30));
            shieldsArrayList.add(new Shields("Snarlshield, Steel", 70, 35));
            shieldsArrayList.add(new Shields("Snarlshield, Hardened Wooden", 80, 40));
            shieldsArrayList.add(new Shields("Tower Shield", 90, 45));
            shieldsArrayList.add(new Shields("Hellknight Shield", 100, 50));

            return shieldsArrayList;
        }

        public int getBlockChance(){
            return blockChance;
        }

    }

