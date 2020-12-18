public class Level {
    Tile[][] map;
    int levelnum;

    public Level(String[][] map, int levelnum, Player player) {
        this.map = new Tile[map.length][map[0].length];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j].equals("O")) {
                    this.map[i][j] = new Tile(player);
                    player.setLocation(i,j);
                } else {
                    this.map[i][j] = new Tile(map[i][j]);
                }
            }
        }
        this.levelnum = levelnum;
    }

    public void outputMap() {
        System.out.println("Level: " + levelnum);
        for(int i = 0; i < this.map.length; i++) { //rows
            for(int j = 0; j < this.map[0].length; j++) { //collumns
                    System.out.print(this.map[i][j].symbol + " ");
            }
            System.out.println();
        }
    }
}
