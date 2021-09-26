import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Slider {
    int movex;
    int movey;
    int counter = 0;
    boolean sliding;
    String dir;
    Timer bigtimer;
    Timer timer;

    public Slider(String way) {
        this.dir = Main.cl.getResource("Icons/TileIcon/SliderIcon"+way+".png").toString().substring(5);
        switch (way) {
            case "w":
                this.movex = -1;
                break;
            case "d":
                this.movey = 1;
                break;
            case "s":
                this.movex = 1;
                break;
            case "a":
                this.movey = -1;
                break;
        }
    }

    public void slide() {

        System.out.println("SLIDE");
        Main.person.move(this.movex, this.movey,Main.currentLvl);

    }


}