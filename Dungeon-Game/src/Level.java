public class Level {
    Tile[][] map;
    int levelnum;
    int startx;
    int starty;
    String text;

    public Level(String[][] map, int levelnum, Player person, int playerx, int playery, String text) {
        this.text = text;
        this.map = new Tile[map.length][map[0].length];
        this.startx = playerx;
        this.starty = playery;
        this.map[playerx][playery] = new Tile(person);
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(!map[i][j].equals("O")){
                    this.map[i][j] = new Tile(map[i][j]);
                }
            }
        }
        this.levelnum = levelnum;
    }

    public void outputMap() {
        for(int i = 0; i < this.map.length; i++) { //rows
            for(int j = 0; j < this.map[0].length; j++) { //collumns
                System.out.print(this.map[i][j].symbol + " ");
            }
            System.out.println();
        }
    }
}
