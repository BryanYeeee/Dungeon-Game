import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    public static void main(String args[]) {
        ArrayList<Level> arlofLevels = new ArrayList<Level>();
        int currentLvl = 3;//+2
        Scanner sc = new Scanner(System.in);
        Player person = new Player(10,100,0);

        String[][] lvlMove = {  {"-", "-", "-", "H", "#"},
                {"-", "H", "-", "H", "-"},
                {"-", "H", "-", "H", "-"},
                {"-", "H", "-", "H", "-"},
                {"O", "H", "-", "-", "-"} };
        arlofLevels.add(new Level(lvlMove, 1, person, 4, 0, "WASD for Movement"));


        String[][] lvlItems = { {"-", "$", "$", "$", "-"},
                {"-", "-", "$", "$", "-"},
                {"O", "-", "H", "H", "#"},
                {"-", "-", "@", "@", "-"},
                {"-", "@", "@", "@", "-"} };
        arlofLevels.add(new Level(lvlItems, 2, person, 2, 0, "Pickup Items, $ for Coins, @ for Health"));

        String[][] lvlSword = { {"H", "H", "#", "H", "H"},
                {"H", "H", "-", "H", "H"},
                {"H", "-", "!", "-", "H"},
                {"H", "-", "-", "-", "H"},
                {"H", "-", "O", "-", "H"} };
        arlofLevels.add(new Level(lvlSword, 3, person, 4, 2, "Don't Go Wandering Alone, Take this Sword with you, or I will come with you myself (!)"));

        String[][] lvlSkeleton = { {"O", "-", "-", "-", "-"},
                {"-", "-", "-", "H", "-"},
                {"H", "H", "H", "H", "8"},
                {"@", "@", "-", "-", "-"},
                {"#", "@", "-", "-", "-"}};
        arlofLevels.add(new Level(lvlSkeleton, 4, person, 0, 0, "Fight The Skeleton! (8)"));

        String[][] lvlShop = { {"H", "H", "H", "H", "H"},
                {"H", "S1", "-", "#", "H"},
                {"H", "-", "-", "-", "H"},
                {"H", "O", "-", "-", "H"},
                {"H", "H", "H", "H", "H"}};
        arlofLevels.add(new Level(lvlShop, 5, person, 3, 1, "Spend your money on items in the SHOP"));

        // ADD SHOP WITH S EQUAL SHOP KEEPER, SHOP CLASS AND BUY ITEMS

        while(true) {
            if(!person.isAlive) { //dead
                break;
            } else if(person.atLadder){ //next level
                currentLvl++;
                person.setLocation(arlofLevels.get(currentLvl).startx, arlofLevels.get(currentLvl).starty);
                person.atLadder = false;
            }
            System.out.println("{" + arlofLevels.get(currentLvl).text + "}");
            System.out.println("Level: " + arlofLevels.get(currentLvl).levelnum);
            System.out.println("[Health: " + person.health + "|Strength: " + person.strength + "|Money: " + person.worth + "]");

            arlofLevels.get(currentLvl).outputMap();
            String move = sc.nextLine();
            person.move(move, arlofLevels.get(currentLvl), true);
            System.out.println();
        }

    }
}
