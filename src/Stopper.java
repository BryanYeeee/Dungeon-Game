import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Stopper {

  Timer timer;
  int counter = 0;
  String dir = Main.cl.getResource("Icons/TileIcon/StopperIcon.png").toString().substring(5);
    public Stopper() {

    }
    public void tostopper(int intix, int intiy){
      
      Main.gui.map.repaint();
      timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent f) {
                  System.out.println("x"+Main.gui.map.x+"y"+Main.gui.map.y+"  "+ counter);
                    if (counter < 100) {
                        Main.gui.map.x = Main.gui.map.x + Main.gui.map.xVelocity * intix;
                        Main.gui.map.y = Main.gui.map.y + Main.gui.map.yVelocity * intiy;
                        GUI.scrollToPlayer();
                        Main.gui.map.repaint();
                        counter++;
                    } else {
                        timer.stop();
                        counter = 0;
                        Main.person.ActuallyMove(intix,intiy);
                        Main.gui.map.x = Main.person.x*100;
                        Main.gui.map.y = Main.person.y*100;
                        Main.gui.map.repaint();
                    }

                }
            });
            timer.setCoalesce(true);
            timer.start();
            Main.gui.map.toggleMove(true);

            Main.gui.afterMove();
      
      //sliding = true;
    }

    
}
