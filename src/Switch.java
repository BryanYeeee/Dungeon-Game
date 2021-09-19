import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Switch {
    // String dir = "SwitchIcon.png";
    ArrayList<SwitchDoor> arlofSDoors = new ArrayList<SwitchDoor>();
    int id = 0;
    public static int paintx = 0;
    public static int painty = 0;
    String dir;

    public Switch(int id) {
        this.dir = Main.cl.getResource("Icons/TileIcon/switch"+id+".png").toString().substring(5);
        this.id = id;
    }

    public void add(ArrayList<SwitchDoor> arlofSDoors) {
        this.arlofSDoors = arlofSDoors;
    }

    public void use() {
        System.out.println("SWTICH DOORS" + this.arlofSDoors);
        for (int i = 0; i < this.arlofSDoors.size(); i++) {
            switch (Main.arlofLevels.get(Main.currentLvl).map[this.arlofSDoors.get(i).x][this.arlofSDoors.get(i).y].symbol) {
                case "^":
                    Main.arlofLevels.get(Main.currentLvl).map[this.arlofSDoors.get(i).x][this.arlofSDoors.get(i).y] = new Tile("-  ", "-", new String[1]);
                    break;
                default:
                    Main.arlofLevels.get(Main.currentLvl).map[this.arlofSDoors.get(i).x][this.arlofSDoors.get(i).y].object = this.arlofSDoors.get(i);
                    Main.arlofLevels.get(Main.currentLvl).map[this.arlofSDoors.get(i).x][this.arlofSDoors.get(i).y].symbol = "^";
                    Main.arlofLevels.get(Main.currentLvl).map[this.arlofSDoors.get(i).x][this.arlofSDoors.get(i).y].dir = Main.cl.getResource("sdoor" + this.id + ".png").toString().substring(5);
                    break;
            }
            Main.gui.map.updateMap();
        }
    }

    public static void iterate(ArrayList<SwitchDoor> lvlSDoors, ArrayList<Switch> arlofSwitch) {
        for (int j = 0; j < arlofSwitch.size(); j++) {
            ArrayList<SwitchDoor> arlofSDoors = new ArrayList<SwitchDoor>();
            for (int i = 0; i < lvlSDoors.size(); i++) {
                if (lvlSDoors.get(i).id == arlofSwitch.get(j).id) {
                    arlofSDoors.add(lvlSDoors.get(i));
                }
            }
            arlofSwitch.get(j).add(arlofSDoors);
        }
    }
}
