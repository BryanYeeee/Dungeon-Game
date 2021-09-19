import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SwitchDoor {
    ArrayList<SwitchDoor> arlofSDoors = new ArrayList<SwitchDoor>();
		int id = 0;
    public static int paintx = 0;
    public static int painty = 0;
		int x;
		int y;
		String dir = Main.cl.getResource("Icons/TileIcon/sdoor").toString().substring(5);

    public SwitchDoor(int id, int x, int y) {
			this.dir = dir + id + ".png";
			this.id = id;
			this.x = x;
			this.y = y;
    }
}
