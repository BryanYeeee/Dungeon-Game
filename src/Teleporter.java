public class Teleporter {
  int x;
  int y;
		String dir = Main.cl.getResource("Icons/TileIcon/TeleporterIcon.png").toString().substring(5);
    public Teleporter(int x, int y) {
      this.x = x;
      this.y = y;
    }
}