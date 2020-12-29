public class Tile {
    Object object;
    String symbol;

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
        }
    }

    public Tile(Player person) {
        this.symbol = "O";
        this.object = person;
    }
}
