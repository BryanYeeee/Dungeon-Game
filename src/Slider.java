import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void eachslide() {

        if (Main.person.move(movex, movey, Main.arlofLevels.get(Main.currentLvl), false)) {
            Main.gui.map.toggleMove(false);
            System.out.println(Main.person.x + " " + Main.person.y);
            //sliding = false;
            timer = new Timer(1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent f) {
                    System.out.println(counter);
                    if (counter < 100) {
                        Main.gui.map.x = Main.gui.map.x + Main.gui.map.xVelocity * movex;
                        Main.gui.map.y = Main.gui.map.y + Main.gui.map.yVelocity * movey;
                        GUI.scrollToPlayer();
                        Main.gui.map.repaint();
                        counter++;
                    } else {
                        timer.stop();
                        counter = 0;
                        Main.person.move(movex, movey, Main.arlofLevels.get(Main.currentLvl), true);
                        Main.gui.map.x = Main.person.x * 100;
                        Main.gui.map.y = Main.person.y * 100;
                        Main.gui.map.repaint();
                        eachslide();

                    }

                }
            });
            timer.setCoalesce(true);
            timer.start();

            Main.gui.afterMove();
        } else {
            Main.gui.map.repaint();
        }


    }

    public void slide(int intix, int intiy) {
        Main.gui.map.toggleMove(false);
        Main.gui.map.repaint();
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                System.out.println("x" + Main.gui.map.x + "y" + Main.gui.map.y + "  " + counter);
                if (counter < 100) {
                    Main.gui.map.x = Main.gui.map.x + Main.gui.map.xVelocity * intix;
                    Main.gui.map.y = Main.gui.map.y + Main.gui.map.yVelocity * intiy;
                    GUI.scrollToPlayer();
                    Main.gui.map.repaint();
                    counter++;
                } else {
                    timer.stop();
                    counter = 0;
                    Main.person.ActuallyMove(intix, intiy);
                    Main.gui.map.x = Main.person.x * 100;
                    Main.gui.map.y = Main.person.y * 100;
                    Main.gui.map.repaint();
                    eachslide();
                }

            }
        });
        timer.setCoalesce(true);
        timer.start();

        Main.gui.afterMove();
        Main.gui.map.toggleMove(false);
        //sliding = true;
    }


}