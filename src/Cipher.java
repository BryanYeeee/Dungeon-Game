public abstract class Cipher {
		int dif;
    int key;
    String instruction;
		String dir = Main.cl.getResource("Icons/TileIcon/CipherIcon.png").toString().substring(5);

    public Cipher(int dif) { 
			this.dif = dif;
    }

    public abstract boolean hack(int answer);
}
