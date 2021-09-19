public class Door {
    int id;
    public static int paintx = 0;
    public static int painty = 0;
		String dir = Main.cl.getResource("Icons/TileIcon/door.png").toString().substring(5);
    public Door(int id) {
			// this.dir = dir + id + ".png";
        this.id = id;
    }
}
