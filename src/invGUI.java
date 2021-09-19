import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;

public class invGUI extends JLayeredPane{

  public invItems item = new invItems();
  public invStats stat = new invStats();
	public invBest best = new invBest();
	public invWeapon weap = new invWeapon();
  public invSettings sett = new invSettings();

  public invMenuBar menu;
  public JPanel mainPanel = new JPanel();

  public String menuDir = Main.cl.getResource("Icons/InventoryIcon/Inv/ItemButton.png").toString().substring(5);
  
  public invGUI() {
    this.setLayout(null);
    this.setBounds(128,128,640,464);
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBounds(0,80,640,384);
    mainPanel.setBackground(Color.RED);
    mainPanel.setOpaque(false);
    this.setOpaque(false);
  }

  public void updateInv() {
		this.menu = new invMenuBar();
		this.menu.repaint();
    this.removeAll();
    this.setLayout(null);
    this.add(menu, Integer.valueOf(2));
    this.add(mainPanel, Integer.valueOf(1));
    this.validate();
    this.repaint();
  }	

  public void closeInv() {
    this.item.closeScreen();
    this.stat.closeScreen();
    this.best.closeScreen();
    this.weap.closeScreen();
    this.sett.closeScreen();
  }
				
}