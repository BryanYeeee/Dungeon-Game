import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

    public class mapGUI extends JPanel {

        Timer timer;
        tileGUI[][] Panels;
        int xVelocity = 1;
        int yVelocity = 1;
        int x = Main.person.x*128;
        int y = Main.person.y*128;
				moveAction plleft;
				moveAction plright;
				moveAction pldown;
				moveAction plup;
        
				public Boulder curBould;

        public mapGUI() {
          this.updateMap();       
        }
        public void toggleMove(boolean on) {
          if (on) {
						plright.turnOn();
						plleft.turnOn();
						plup.turnOn();
						pldown.turnOn();
          } else if (!on) {//make it easier to read
						plright.turnOff();
						plleft.turnOff();
						plup.turnOff();
						pldown.turnOff();					
          }
				}
        public void updateMap() { //remove all tiles and then add updated ones
				//System.out.println((Main.person.x*100-1 00) + " " + (Main.person.y*100-100));

				// scrollBar.getHorizontalScrollBar().setValue(Math.min((Main.arlofLevels.get(Main.currentLvl).map[0].length - Main.person.y)*100, Main.person.y*100-300));
        // scrollBar.getVerticalScrollBar().setValue(Math.min((Main.arlofLevels.get(Main.currentLvl).map.length - Main.person.y)*100, Main.person.x*100-200));

        // System.out.println(Math.min((Main.arlofLevels.get(Main.currentLvl).map.length - Main.person.y)*100, Main.person.x*100-300));
        // System.out.println(Math.min((Main.arlofLevels.get(Main.currentLvl).map[0].length - Main.person.y)*100, Main.person.y*100-200));
            this.x = Main.person.x*128;
            this.y = Main.person.y*128;

        
            this.removeAll();
            this.setLayout(new GridLayout(Main.arlofLevels.get(Main.currentLvl).map.length,Main.arlofLevels.get(Main.currentLvl).map[0].length));
						
						System.out.println(Main.currentLvl + " THIS IS THE CURRET LEVEL THAT IS BEING UPDATED");
            // this.setLayout(new GridLayout(5,5));

            this.Panels =  new tileGUI[Main.arlofLevels.get(Main.currentLvl).map.length][Main.arlofLevels.get(Main.currentLvl).map[0].length];
            for (int i = 0; i < Main.arlofLevels.get(Main.currentLvl).map.length; i++) {
                for (int j = 0; j < Main.arlofLevels.get(Main.currentLvl).map[0].length; j++) {
                    tileGUI Tile = new tileGUI(new Tile("-  ","-", new String[1]), 0, 0);
                    Tile.setPreferredSize(new Dimension(128, 128));
                    Tile.setMaximumSize(new Dimension(128, 128));
                    try{
                        Tile = new tileGUI(Main.arlofLevels.get(Main.currentLvl).map[i][j], i, j);

                    }catch(IndexOutOfBoundsException e){
                        Tile = new tileGUI(new Tile("-  ","-", new String[1]), i, j);
                    }
                    this.Panels[i][j]=Tile;
                    this.add(Tile);

                }
                this.setPreferredSize(new Dimension(Main.arlofLevels.get(Main.currentLvl).map[0].length*128,Main.arlofLevels.get(Main.currentLvl).map.length*128));
                this.setSize(new Dimension(Main.arlofLevels.get(Main.currentLvl).map[0].length*128,Main.arlofLevels.get(Main.currentLvl).map.length*128));

								//System.out.println("MAP SIZE " + this.size() + " " + new Dimension(Main.arlofLevels.get(Main.currentLvl).map.length*100,Main.arlofLevels.get(Main.currentLvl).map[0].length*100));
								
                validate();
                repaint();
            }
        }
    //     public BufferedImage createImage(JPanel panel, int width, int height) {
    //     BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    //     Graphics2D g = bi.createGraphics();
    //     panel.setSize(width, height); // or panel.getPreferedSize()
    //     layoutComponent(panel);
    //     panel.print(g);
    //     g.dispose();
    //     return bi;
    // }

    // private void layoutComponent(Component component) {
    //     synchronized (component.getTreeLock()) {
    //         component.doLayout();

    //         if (component instanceof Container) {
    //             for (Component child : ((Container) component).getComponents()) {
    //                 layoutComponent(child);
    //             }
    //         }
    //     }
    // }
    public void paint(Graphics g) {

      super.paint(g); // paint background

      Graphics2D g2D = (Graphics2D) g;
      //g2D.drawImage(backgroundImage, 0, 0, null);

      Image tileImage = (Main.AutoSize(Main.person.playericon)).getImage();
      g2D.drawImage(tileImage,y,x,null);
      
      if(curBould != null) {
        System.out.println(this.curBould.my+""+this.curBould.mx);
        g2D.drawImage(Main.AutoSize(this.curBould.dir).getImage(),this.curBould.my,this.curBould.mx,null);
      }
      g2D.dispose();

    }
    
}
