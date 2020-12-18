public class Tile {
    Object object;
    String symbol;

    public Tile(String object) {
        this.symbol = object;
        switch (object) {
            case "-":
                this.object = null;
                break;
        }
    }
    public Tile(Player player) {
        this.object = player;
        this.symbol = "O";
    }
}
