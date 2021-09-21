import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Decoration {
    ArrayList<String> arlofnoPasDirs = new ArrayList<>(Arrays.asList("Pots", "Bed1", "Bed2", "Toilet1", "Drawer1"));
    ArrayList<String> noPasRand = new ArrayList<>(Arrays.asList("Pots"));

    ArrayList<String> tallDirs = new ArrayList<>(Arrays.asList("Drawer1", "Toilet1"));

    ArrayList<String> arlofPasDirs = new ArrayList<>(Arrays.asList("Skull", "DogBowl", "OpenDoor"));
    ArrayList<String> PasRand = new ArrayList<>(Arrays.asList("Skull"));
    public String dir = Main.cl.getResource("Icons/Decorations/").toString().substring(5);
    public boolean tall;

    public Decoration(String passable, String index) {
        if (passable.equals(":")) {
            if (index.equals(" ")) {
                this.dir = "/" + this.dir + PasRand.get((new Random()).nextInt(PasRand.size())) + ".png";
            } else {
                this.dir = "/" + this.dir + arlofPasDirs.get(Integer.parseInt(index)) + ".png";
            }
        } else {
            if (index.equals(" ")) {
                this.dir = "/" + this.dir + noPasRand.get((new Random()).nextInt(noPasRand.size())) + ".png";
            } else {
                this.dir = "/" + this.dir + arlofnoPasDirs.get(Integer.parseInt(index)) + ".png";
                if (tallDirs.contains(arlofnoPasDirs.get(Integer.parseInt(index)))) {
                    tall = true;
//                    this.dir = "Icons/Decorations/" + arlofnoPasDirs.get(Integer.parseInt(index)) + ".png";
                }
            }
        }

        System.out.println(index + " " + this.dir);
    }
}
