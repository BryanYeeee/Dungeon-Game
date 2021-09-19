import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Decoration {
	ArrayList<String> arlofDirs = new ArrayList<>(Arrays.asList("Skull","Pots"));
  public String dir = Main.cl.getResource("Icons/Decorations").toString().substring(5);

    public Decoration(String index) {
      this.dir = index.equals(" ") ? this.dir+arlofDirs.get((new Random()).nextInt(arlofDirs.size()))+".png" : this.dir+arlofDirs.get(Integer.parseInt(index))+".png";
    }
}
