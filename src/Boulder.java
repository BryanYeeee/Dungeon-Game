import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Boulder {
    String dir = Main.cl.getResource("Icons/TileIcon/BoulderIcon.png").toString().substring(5);
    int spawnx;
    int spawny;
    int x;
    int y;
    int mx;
    int my;
    Timer timer;
    boolean done;
    boolean killable;

    public Boulder(int spawnx, int spawny, String killable) {
        this.spawnx = spawnx;
        this.spawny = spawny;
        this.x = spawnx;
        this.y = spawny;
        this.mx = spawnx * 128;
        this.my = spawny * 128;
        this.done = false;
        this.killable = killable.equals("k") ? true : false;
        this.dir = killable.equals("k") ? Main.cl.getResource("Icons/TileIcon/kBoulderIcon.png").toString().substring(5) : this.dir;
    }

    public boolean create(Level level, int movex, int movey) {
        if (this.done) {
            return false;
        }
        if (!Main.person.boulPush) {
            System.out.println("KILLEABLE " + this.killable);
            if (!this.killable) {
                return false;
            }
            level.map[Main.person.x + movex][Main.person.y + movey].symbol = "B";
        } else if ((level.map[Main.person.x + movex * 2][Main.person.y + movey * 2].object != null && !(level.map[Main.person.x + movex * 2][Main.person.y + movey * 2].object instanceof Plate))) {
            return false;
        } else {
            Main.gui.map.curBould = this;
            Main.arlofLevels.get(Main.currentLvl).map[this.x][this.y].dir = ".png";
        }
        return true;
    }

    public boolean push(Level level, int movex, int movey) {

        this.x = Main.person.x + movex * 2;
        this.y = Main.person.y + movey * 2;
        if (level.map[Main.person.x + movex * 2][Main.person.y + movey * 2].object instanceof Plate) {
            this.done = true;
            ((Plate) level.map[Main.person.x + movex * 2][Main.person.y + movey * 2].object).use();

            System.out.println("USED PLATE");
        }
        System.out.println("BOUL:DER PUHSIHED");
        level.map[Main.person.x + movex * 2][Main.person.y + movey * 2] = level.map[Main.person.x + movex][Main.person.y + movey];
        level.map[Main.person.x + movex][Main.person.y + movey] = new Tile("-  ", "-", new String[1]);
        // Main.gui.boulGUI.startBoulder(Main.gui.boulGUI, x, y, movex, movey);
        return true;


        // return false;
    }

    public void fight(Level level, int movex, int movey) {
        level.map[this.spawnx][this.spawny] = level.map[Main.person.x + movex][Main.person.y + movey];
        if (!(this.spawnx == Main.person.x + movex) && !(this.spawny == Main.person.x + movey)) {
            level.map[this.x + movex][this.y + movey] = new Tile("-  ", "-", new String[1]);
        }
        level.map[this.spawnx][this.spawny].symbol = "G";
        this.x = this.spawnx;
        this.y = this.spawny;
    }
}
