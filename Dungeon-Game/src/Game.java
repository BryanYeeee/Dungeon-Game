import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    public static void main(String args[]) {
        ArrayList<Level> arlofLevels = new ArrayList<Level>();
        int currentLvl = -1;
        Scanner sc = new Scanner(System.in);
        Player person = new Player(10,10,0);

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
        arlofLevels.add(new Level(lvlSword, 3, person, 4, 2, ""));

        while(true) {
            if(person.atLadder){
                currentLvl++;
                person.setLocation(arlofLevels.get(currentLvl).startx, arlofLevels.get(currentLvl).starty);
                System.out.println("{" + arlofLevels.get(currentLvl).text + "}");
                person.atLadder = false;
            }
            System.out.println("Level: " + arlofLevels.get(currentLvl).levelnum);
            System.out.println("[Health: " + person.health + "|Strength: " + person.strength + "|Money: " + person.worth + "]");

            arlofLevels.get(currentLvl).outputMap();
            String move = sc.nextLine();
            person.move(move, arlofLevels.get(currentLvl), true);
            System.out.println();
        }

    }
}
