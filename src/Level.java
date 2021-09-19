import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level {
    Tile[][] map;
    int levelnum;
    int startx;
    int starty;
    ArrayList<SwitchDoor> arlofSDoors;
    ArrayList<Switch> arlofSwitch;
    ArrayList<Plate> arlofPlate;

  public Level(String[][] map, int levelnum, int playerx, int playery) {
    this.map = new Tile[map.length][map[0].length];
    this.startx = playerx;
    this.starty = playery;
    this.map[playerx][playery] = new Tile("O  ","O", new String[1]);
		this.initMap(map);
    this.levelnum = levelnum;
  }

	public void initMap(String[][] map) {
		// for(int i = 0; i < this.map.length; i++) { //rows
		//     for(int j = 0; j < this.map[0].length; j++) { //collumns
		//         System.out.print("|"+map[i][j] + "|");
		//     }
		//     System.out.println();
		// }
		this.arlofSDoors = new ArrayList<SwitchDoor>();
		this.arlofSwitch = new ArrayList<Switch>();
		this.arlofPlate = new ArrayList<Plate>();
    for(int i = 0; i < map.length; i++) {
      for(int j = 0; j < map[0].length; j++) {
        switch(map[i][j].substring(0,1)){
          case "U":
            this.map[i][j] = new Tile(map[i][j], map[i][j].substring(0,1), map[i][j].substring(1).split(" "));
            break;
          case "O":
						int x = 1/0;
            break;
          case "^":
            this.map[i][j] = new Tile(map[i][j], map[i][j].substring(0,1),new String[]{map[i][j].substring(1,2), map[i][j].substring(2,3), String.valueOf(i), String.valueOf(j)});
            this.arlofSDoors.add((SwitchDoor)this.map[i][j].object);
            if(map[i][j].substring(2,3).equals("O")){
              this.map[i][j] = new Tile(map[i][j], "-  ", new String[1]);		
            }
            break;
          case "G":
            this.map[i][j] = new Tile(map[i][j], map[i][j].substring(0,1), new String[]{String.valueOf(i),String.valueOf(j),map[i][j].substring(1,2)});
            break;
          case "L":
            this.map[i][j] = new Tile(map[i][j], map[i][j].substring(0,1), map[i][j].substring(1).split(""));
            this.arlofSwitch.add((Switch)this.map[i][j].object);
            break;
          case "_":
            this.map[i][j] = new Tile(map[i][j], map[i][j].substring(0,1), map[i][j].substring(1).split(""));
            this.arlofPlate.add((Plate)this.map[i][j].object);
            break;
					case "H":
						this.map[i][j] = new Tile(map,i,j);
						break;
          default:
            this.map[i][j] = new Tile(map[i][j], map[i][j].substring(0,1), map[i][j].substring(1).split(""));
            break;
        }
      }
    }
    Switch.iterate(this.arlofSDoors, this.arlofSwitch);
    Plate.iterate(this.arlofSDoors, this.arlofPlate);

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
