import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    public static void main(String args[]) {
        ArrayList<Level> arlofLevels = new ArrayList<Level>();
        int currentLvl = 5;//+2
        Scanner sc = new Scanner(System.in);
        Player person = new Player(10,100,0);

        String[][] lvlMove = {  {" - ", " - ", " - ", " H ", " # "},
                {" - ", " H ", " - ", " H ", " - "},
                {" - ", " H ", " - ", " H ", " - "},
                {" - ", " H ", " - ", " H ", " - "},
                {" O ", " H ", " - ", " - ", " - "} };
        arlofLevels.add(new Level(lvlMove, 1, person, 4, 0, "WASD for Movement"));


        String[][] lvlItems = { {" - ", " $ ", " $ ", " $ ", " - "},
                {" - ", " - ", " $ ", " $ ", " - "},
                {" O ", " - ", " H ", " H ", " # "},
                {" - ", " - ", " @ ", " @ ", " - "},
                {" - ", " @ ", " @ ", " @ ", " - "} };
        arlofLevels.add(new Level(lvlItems, 2, person, 2, 0, "Pickup Items, $ for Coins, @ for Health"));

        String[][] lvlSword = { {" H ", " H ", " # ", " H ", " H "},
                {" H ", " H ", " - ", " H ", " H "},
                {" H ", " - ", " ! ", " - ", " H "},
                {" H ", " - ", " - ", " - ", " H "},
                {" H ", " - ", " O ", " - ", " H "} };
        arlofLevels.add(new Level(lvlSword, 3, person, 4, 2, "Don't Go Wandering Alone, Take this Sword with you, or I will come with you myself (!)"));

        String[][] lvlSkeleton = { {" O ", " - ", " - ", " - ", " - "},
                {" - ", " - ", " - ", " H ", " - "},
                {" H ", " H ", " H ", " H ", " 8 "},
                {" @ ", " @ ", " - ", " - ", " - "},
                {" # ", " @ ", " - ", " - ", " - "}};
        arlofLevels.add(new Level(lvlSkeleton, 4, person, 0, 0, "Fight The Skeleton! (8)"));

        String[][] lvlShop = { {" H ", " H ", " H ", " H ", " H "},
                {" H ", " S1", " - ", " # ", " H " },
                {" H ", " - ", " - ", " - ", " H "},
                {" H ", " O ", " - ", " - ", " H "},
                {" H ", " H ", " H ", " H ", " H "}};
        arlofLevels.add(new Level(lvlShop, 5, person, 3, 1, "Spend your money on items in the SHOP"));

        String[][] lvlKeyDoor = { {" H ", " H ", " H ", " H ", " H ", " H ", " H ", " H "},
                {" H ", " @ ", "<2>", " @ ", " H ", " - ", " $ ", " H "},
                {" H ", " - ", " - ", " - ", "|2|", " - ", " # ", " H "},
                {" H ", " - ", " - ", " - ", " H ", " - ", " $ ", " H "},
                {" H ", "|1|", " H ", " H ", " H ", " H ", " H ", " H "},
                {" H ", " - ", " - ", " O ", " H ", " $ ", " $ ", " H "},
                {" H ", "<1>", " - ", " - ", "|2|", " $ ", " $ ", " H "},
                {" H ", " H ", " H ", " H ", " H ", " H ", " H ", " H "}};
        arlofLevels.add(new Level(lvlKeyDoor, 6, person, 5, 3, "Use the keys to open the matching door"));

        String[][] lvlPzl1 = {
                {" H ", " H ", " H ", " H ", " - ", " H ", " H ", " H ", " H "},
                {" H ", " # ", " @ ", "|2|", " - ", " - ", " H ", "<3>", " H "},
                {" H ", " @ ", " @ ", " H ", " - ", " 8 4", " H ", "|2|", " H "},
                {" H ", " H ", " H ", " H ", "|3|", " H ", " H ", " O ", " H "},
                {" H ", " 8 ", " - ", " - ", " - ", " - ", "|1|", " - ", " H "},
                {" H ", " H ", "|1|", " H ", " H ", " H ", " H ", " - ", " H "},
                {" H ", " - ", " - ", " H ", " $ ", " @ ", " H ", " - ", " H "},
                {" H ", " 8 2", " - ", "|4|", " @ ", " $ ", " H ", " 8 1", " H "},
                {" H ", " H ", " H ", " H ", " H ", " H ", " H ", " H ", " H "},
        };
        arlofLevels.add(new Level(lvlPzl1, 7, person, 3, 7, "Enemies Can Drop Keys"));

// ADD FIGHT SYSTEM

        while(true) {
            if(!person.isAlive) { //dead
                System.out.println("YOU DIED");
                person.inv.clearAll(); restart levels
                currentLvl = -1;
                person.setLocation(arlofLevels.get(currentLvl).startx, arlofLevels.get(currentLvl).starty);
                person.atLadder = false;
                break;
            } else if(person.atLadder){ //next level
                person.inv.clearAll();
                currentLvl++;
                person.setLocation(arlofLevels.get(currentLvl).startx, arlofLevels.get(currentLvl).starty);
                person.atLadder = false;
            }
//            System.out.println("-- " + arlofLevels.get(currentLvl).arlofKeys + " --");
            System.out.println("{" + arlofLevels.get(currentLvl).text + "}");
            System.out.println("Level: " + arlofLevels.get(currentLvl).levelnum);
            System.out.println("[Health: " + person.health + "|Strength: " + person.strength + "|Money: " + person.worth + "]");
            System.out.println("[-"+person.inv.items+"-]");

            arlofLevels.get(currentLvl).outputMap();
            String move = sc.nextLine();
            person.move(move, arlofLevels.get(currentLvl), true);
            System.out.println();
        }


    }
}
