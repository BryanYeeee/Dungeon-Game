import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;import java.util.Arrays;
    
public class shopGUI extends JLayeredPane{
//public static JScrollPane scrollBar;
public JPanel shopButtons;
public JPanel shopPrice;
public JLabel cash;
public static int curItem;
public Shop curShop;
public ArrayList<buyButton> buttons;

public JLabel namexperbuy;
public JLabel stockleft;



shopGUI(Shop curShop) {
  this.setLayout(null);
  this.curShop = curShop;
  JLabel name = new JLabel("Sshop");
  //this.add(name, BorderLayout.NORTH);
  JButton exit = new JButton("IMPOOR");

  GridLayout glayout = new GridLayout(1,/*curShop.items.size()*/3);
  glayout.setHgap(80);
  shopButtons = new JPanel();
  shopButtons.setLayout(glayout);
  shopButtons.setBounds(128,376,640,152);
  shopButtons.setOpaque(false);
  //scrollBar  = new JScrollPane(shopButtons);
  this.add(shopButtons, Integer.valueOf(1));

  shopPrice = new JPanel();
  shopPrice.setLayout(glayout);
  shopPrice.setBounds(128,560,640,56);
  shopPrice.setOpaque(false);
  this.add(shopPrice, Integer.valueOf(1));


  cash = new JLabel(String.valueOf(Main.person.worth));
  cash.setBounds(360,234,256,70);
  this.add(cash,Integer.valueOf(1));


  stockleft = new JLabel();
  namexperbuy = new JLabel();
  stockleft.setBounds(312,80,264,72);
  namexperbuy.setBounds(72,24,728,56);
  stockleft.setHorizontalAlignment(SwingConstants.CENTER);
  stockleft.setVerticalAlignment(SwingConstants.CENTER);
  namexperbuy.setHorizontalAlignment(SwingConstants.CENTER);
  namexperbuy.setVerticalAlignment(SwingConstants.CENTER);
  this.add(stockleft,Integer.valueOf(1));
  this.add(namexperbuy,Integer.valueOf(1));

  JLabel backIMG = new JLabel();
  backIMG.setIcon(Main.setImageSize(Main.cl.getResource("Icons/ShopIcon/shopideas.png").toString().substring(5),896, 640));
  backIMG.setBounds(0,0,896,640);
  this.add(backIMG, Integer.valueOf(0));

  buttons = new ArrayList<buyButton>();

  // Iterator it = curShop.items.entrySet().iterator();
  // while (it.hasNext()) {
  // 	Map.Entry<ShopItem, Integer> pair = (Map.Entry<ShopItem, Integer>)it.next();
  // 	System.out.println("[ " + ((ShopItem)(pair.getKey()).name) + " " + pair.getValue());
  // }
  for(curItem=0;curItem<3/*curShop.items.size()*/;curItem++){
      
    Item item = ((Item)curShop.items.keySet().toArray()[curItem]);
    int perbuy = (int)((int[])(curShop.items.values().toArray()[curItem]))[0];
    int quantity = (int)((int[])(curShop.items.values().toArray()[curItem]))[1];

    buttons.add(new buyButton(item.name, curItem, item.price, perbuy, quantity));
    shopButtons.add(buttons.get(curItem));

    JLayeredPane temp = new JLayeredPane();
    temp.setPreferredSize(new Dimension(160,56));
    JLabel priceIMG = new JLabel(Main.setImageSize(Main.cl.getResource("Icons/ShopIcon/moneyshotsmall.png").toString().substring(5),160,56));
    priceIMG.setBounds(0,0,160,56);
    temp.add(priceIMG, Integer.valueOf(0));
    JLabel number = new JLabel(String.valueOf(item.price));
    number.setBounds(70,10,90,46);
    temp.add(number, Integer.valueOf(1));

    shopPrice.add(temp);

    }
  exit.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      System.out.println(Main.gui.shopScene.curItem
      );
      Main.gui.shopScene.endShop();
    }
  });
  this.add(exit);
  this.setBounds(0,0,896,640);
  repaint();
  validate();

  Main.gui.toggleInput(false,true);
}


  public static void endShop() {
      GUI.close();
      System.out.println("You've BUY STUFF");
  }
  public static void startShop(shopGUI shopScene){
              
    // GUI.mainPanel.removeAll();
    Main.gui.map.toggleMove(false);
    Main.gui.gamePanel.add(shopScene, Integer.valueOf(1));
    Main.gui.shopScene.validate();
    Main.gui.shopScene.repaint();
    Main.gui.mainPanel.validate();
    Main.gui.mainPanel.repaint();
    Main.gui.frame.validate();
    Main.gui.frame.repaint();
    
  }
                   
}
  
