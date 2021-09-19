import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Plate {
    String dir = Main.cl.getResource("Icons/TileIcon/PlateIcon.png").toString().substring(5);
    ArrayList<SwitchDoor> arlofSDoors = new ArrayList<SwitchDoor>();
		int id = 0;

    public Plate(int id) {
			this.id = id;
    }
		public void add(ArrayList<SwitchDoor> arlofSDoors) {
      this.arlofSDoors = arlofSDoors;
		}
		public void use(){
			System.out.println("SWTICH DOORS" + this.arlofSDoors);
			for(int i = 0; i < this.arlofSDoors.size(); i++) {
				// switch(Main.arlofLevels.get(Main.currentLvl).map[this.arlofSDoors.get(i).x][this.arlofSDoors.get(i).y].symbol){
				// 	case "^":
						Main.arlofLevels.get(Main.currentLvl).map[this.arlofSDoors.get(i).x][this.arlofSDoors.get(i).y] = new Tile("-  ","-", new String[1]);
						// break;
					// default:
					// 	Main.arlofLevels.get(Main.currentLvl).map[this.arlofSDoors.get(i).x][this.arlofSDoors.get(i).y].object = this.arlofSDoors.get(i);
					// 	Main.arlofLevels.get(Main.currentLvl).map[this.arlofSDoors.get(i).x][this.arlofSDoors.get(i).y].symbol = "^";
					// 	Main.arlofLevels.get(Main.currentLvl).map[this.arlofSDoors.get(i).x][this.arlofSDoors.get(i).y].dir = "sdoor" + this.id + ".png";
					// 	break;
				// }
        Main.gui.map.updateMap();
			}
		}

		public static void iterate(ArrayList<SwitchDoor> lvlSDoors, ArrayList<Plate> arlofPlate) {
			for(int j = 0; j < arlofPlate.size(); j++) {
				ArrayList<SwitchDoor> arlofSDoors = new ArrayList<SwitchDoor>();
				for(int i = 0; i < lvlSDoors.size(); i++){
					if(lvlSDoors.get(i).id == arlofPlate.get(j).id) {
						arlofSDoors.add(lvlSDoors.get(i));
					}
				}
				arlofPlate.get(j).add(arlofSDoors);
			}
		}
}
