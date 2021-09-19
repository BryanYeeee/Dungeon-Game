import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.math.BigDecimal;

public class buyButton extends JButton{
  public String name;
  public static int itemNumber;
  public double price;
  public int perbuy;
  public int quantity;
  BigDecimal total;

  public ImageIcon buttonUp = Main.setImageSize(Main.cl.getResource("Icons/ShopIcon/button.png").toString().substring(5),152,160);
  public ImageIcon buttonDown = Main.setImageSize(Main.cl.getResource("Icons/ShopIcon/buttonpress.png").toString().substring(5),152,160);
  public ImageIcon buttonImage = buttonUp;
  int itemx = 16;
  int itemy = 0;


  public buyButton(String name, int itemNumber, double price, int perbuy, int quantity){
    total = (new BigDecimal(String.valueOf(price))).multiply(new BigDecimal(String.valueOf(perbuy)));
    this.setPreferredSize(new Dimension(152,160));
    this.setOpaque(false);
    this.setContentAreaFilled(false);
    this.setBorderPainted(false);
    this.setText("<html>" +perbuy+" "+name + " for " + total.doubleValue() + "<br>" + quantity + " left </html>");
    this.name = name;
    this.itemNumber = itemNumber;
    this.price = price;
    this.perbuy = perbuy;
    this.quantity = quantity;
    

    this.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(final MouseEvent e) {
          if(Main.gui.shopScene.curShop.buy(itemNumber)){
            Main.gui.shopScene.stockleft.setText(String.valueOf(Main.gui.shopScene.buttons.get(itemNumber).quantity));
          }
    			// System.out.println(Main.gui.shopScene.buttons.get(0) == this);
          // System.out.println(quantity + " " +  Main.gui.shopScene.buttons.get(0).quantity);

          
          Main.gui.shopScene.cash.setText(String.valueOf(Main.person.worth));


      }
        
        @Override
            public void mousePressed(final MouseEvent e) {
            buttonPos("DOWN");
        }
        @Override
            public void mouseReleased(final MouseEvent mouseEvent) {
            buttonPos("UP");
        }
        @Override
          public void mouseEntered(final MouseEvent mouseEvent) {
          Main.gui.shopScene.stockleft.setText(String.valueOf(Main.gui.shopScene.buttons.get(itemNumber).quantity));
          Main.gui.shopScene.namexperbuy.setText(name+"  X  "+String.valueOf(Main.gui.shopScene.buttons.get(itemNumber).perbuy));

        }
        @Override
         public void mouseExited(final MouseEvent mouseEvent) {
            buttonPos("UP");
            
      }
    });
    
  }

  public void buttonPos(String pos){
    if(pos.equals("UP")){
      this.buttonImage = buttonUp;
      this.itemx = 16;
      this.itemy = 0;
    }
    if(pos.equals("DOWN")){
      this.buttonImage = buttonDown;
      this.itemx = 16;
      this.itemy = 16;
    }
    
    repaint();
  }
  public void paint(Graphics g) {

    super.paint(g); // paint background

    Graphics2D g2D = (Graphics2D) g;
    g2D.drawImage(buttonImage.getImage(),0,0,null);
    g2D.drawImage(Main.setImageSize(Main.cl.getResource("Icons/ItemIcon/"+name+"Icon.png").toString().substring(5),128,128).getImage(),itemx,itemy,null);

    g2D.dispose();

  }
}

