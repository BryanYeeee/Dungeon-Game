public class Ladder {

    String dir = Main.cl.getResource("Icons/TileIcon/LadderIcon.png").toString().substring(5);
    public Ladder() {
    }
		public void nextLvl() {
				Main.currentLvl++;
				Main.person.setLocation(Main.arlofLevels.get(Main.currentLvl).startx, Main.arlofLevels.get(Main.currentLvl).starty);

				Main.person.inv.clearKeys();
				Main.gui.map.updateMap();
				Main.gui.map.toggleMove(true);
        System.out.println("CAMERA POS: " + (Main.gui.map.y) + " " + (Main.gui.map.x));
      System.out.println("CURLVL: "+ Main.currentLvl);
				Main.gui.scrollToPlayer();
		}
}
