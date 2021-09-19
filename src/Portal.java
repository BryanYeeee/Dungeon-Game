public class Portal {

    String dir = Main.cl.getResource("Icons/TileIcon/PortalIcon.png").toString().substring(5);
		public int level;
		boolean reset;
    public Portal(int level, String reset) {
			this.level = level;
			this.reset = reset.equals("r") ? true:false;
			this.dir = reset.equals("r") ? "r"+this.dir : this.dir; 
    }

		public void reset() {
			Main.currentLvl = this.level;
			if(reset) {
				Main.arlofLevels.set(Main.currentLvl, (new LevelList()).arlofLevels.get(Main.currentLvl));
			}
      Main.person.setLocation(Main.arlofLevels.get(Main.currentLvl).startx, Main.arlofLevels.get(Main.currentLvl).starty);
      Main.gui.map.updateMap();
      Main.gui.scrollToPlayer();

		}
}
