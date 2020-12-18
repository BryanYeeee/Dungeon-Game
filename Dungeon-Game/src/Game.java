import java.util.Scanner;

public class Game {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Player player = new Player(10, 100);
        String[][] level1map = {{"-", "-", "-", "-", "-"},
                                {"-", "-", "-", "-", "-"},
                                {"-", "-", "O", "-", "-"},
                                {"-", "-", "-", "-", "-"},
                                {"-", "-", "-", "-", "-"}};
        Level lvl1 = new Level(level1map, 1, player);
        lvl1.outputMap();
        while(1==1) {
            String move = sc.nextLine();
            player.move(move, lvl1, true);
            lvl1.outputMap();
        }

    }
}
