import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OmniSlider {
  int counter = 0;
  boolean sliding;
  String dir = Main.cl.getResource("Icons/TileIcon/OmniSlider.png").toString().substring(5);
  Timer bigtimer;
  Timer timer;
  
    public OmniSlider() {
			

    }
    public void eachslide(int movex, int movey){

      if(Main.person.move(movex, movey, Main.arlofLevels.get(Main.currentLvl), false)){
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
                        Main.gui.map.x = Main.person.x*100;
                        Main.gui.map.y = Main.person.y*100;
                        Main.gui.map.repaint();
                        eachslide(movex, movey);
                        
                    }

                }
            });
            timer.setCoalesce(true);
            timer.start();

            Main.gui.afterMove();
      }else{
        Main.gui.map.repaint();
      }

    
    
    }
    
    public void slide(int intix, int intiy, int movex, int movey){
      Main.gui.map.toggleMove(false);
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
                        eachslide(movex,movey);
                    }

                }
            });
            timer.setCoalesce(true);
            timer.start();

            Main.gui.afterMove();
      Main.gui.map.toggleMove(false);
    }

    
}