import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tile {
    Object object;
    String symbol;
    Random rand = new Random();

    public Tile(String object) {
        this.symbol = object;
        switch (object) {
            case "-":
                this.object = null;
                break;
            case "H":
                this.object = new Wall();
                break;
            case "#":
                this.object = new Ladder();
                break;
            case "$":
                this.object = new Coin();
                break;
            case "@":
                this.object = new Health();
                break;
            case "!":
                this.object = new Sword();
                break;
            case "S1":
                this.symbol = "S";
                this.object = new Shop(Arrays.asList("Strength,10,6", "Health,20,6")); //item, amount, cost
            default:
                this.object = null;
                break;

        }
    }

    public Tile(Player person) {
        this.symbol = "O";
        this.object = person;
    }

    public Tile(String object, int x, int y){//create enemy
        this.symbol = object;
        switch (object) {
            case "8": //skeleton
                this.object = new Enemy(10,50,x,y,rand.nextInt(2) + 3);
                break;
        }

    }



}
