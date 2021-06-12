public class Item {
    public static void generateItems(){
        ArrayList<Swords> swordsArrayList = new ArrayList<Swords>();
        swordsArrayList.add(new OneHanded("Short sword",2,6));
        swordsArrayList.add(new TwoHanded("Longsword",4,8));

        ArrayList<Staves> stavesArrayList = new ArrayList<Staves>();
        stavesArrayList.add(new Staves("Apprentice Staff",10,4));
        stavesArrayList.add(new Staves("Master Wizard's Staff",20,20));

        ArrayList<Shields> shieldsArrayLists = new ArrayList<Shields>();
        shieldsArrayLists.add(new Shields("Light Steel Shield",10,5));
        shieldsArrayLists.add(new Shields("Heavy Steel Shield",30,10));
        shieldsArrayLists.add(new Shields("Tower Shield",75,25));

        ArrayList<Bows> bowsArrayList = new ArrayList<Bows>();
        bowsArrayList.add(new Bows("Shortbow",20,6));
        bowsArrayList.add(new Bows("Longbow",35,8));
        bowsArrayList.add(new OneHandedCrossbow("Hand Crossbow",35,8));
        bowsArrayList.add(new Bows("Heavy Crossbow",60,10));

        ArrayList<Armors> armorsArrayList = new ArrayList<Armors>();
        armorsArrayList.add(new Armors("Leather Armor",10));
        armorsArrayList.add(new Armors("Chain Armor",25));
        armorsArrayList.add(new Armors("Full Plate Armor",40));

    }
