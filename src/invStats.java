import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;
import java.awt.image.BufferedImage;

public class invStats extends Screen{

	JPanel BGPanel;
	JPanel playerPanel;
	JLabel HP;
	JLabel MANA;
	JLabel WORTH;
	JLabel STR;
	JLabel LVL;
  JLabel NAME;

	public invStats(){
		super();
		this.BGPanel = new JPanel();
		this.BGPanel.setOpaque(false);
		this.BGPanel.setLayout(null);
		this.BGPanel.setBounds(0,0,640,384);
		this.add(this.BGPanel, Integer.valueOf(1));

    JLabel backIMG = new JLabel();
		backIMG.setIcon(Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Stats/StatBG.png").toString().substring(5), 640,384));
		backIMG.setBounds(0,0,640,384);
		this.add(backIMG, Integer.valueOf(0));

		this.playerPanel = new JPanel();
		this.playerPanel.setBounds(64,72,120,120);
		this.playerPanel.setBackground(Color.PINK);
	}

	public void updateScreen(){
		this.BGPanel.removeAll();
		this.BGPanel.setLayout(null);

		this.HP = makeStat("HP: " + Main.person.health + " / " + Main.person.maxhp,480,78);
    	this.WORTH = makeStat("WALLET: " + Main.person.worth.toString(),480,110);
		this.STR = makeStat("STRENGTH: " + Main.person.strength,480,140);
		this.MANA = makeStat("MANA",480,172);
    	this.NAME = makeStat("ZANE AL-HAMWY",304,76);

		this.BGPanel.add(this.playerPanel);
		this.BGPanel.add(this.HP);
		this.BGPanel.add(this.STR);
		this.BGPanel.add(this.WORTH);
		this.BGPanel.add(this.MANA);
    this.BGPanel.add(this.NAME);

		this.BGPanel.validate();
		this.BGPanel.repaint();
	}

	public void closeScreen(){
	}

	public JLabel makeStat(String txt, int x, int y){
		JLabel newLabel = new JLabel(txt);
    newLabel.setVerticalAlignment(JLabel.TOP);
		newLabel.setBounds(x, y, 318, 36);
		return newLabel;
	}
  public void paint(Graphics g) {

    super.paint(g); // paint background

    Graphics2D g2D = (Graphics2D) g;
    //g2D.drawImage(backgroundImage, 0, 0, null);

    
    ImageIcon deck = Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Stats/StatMesh.png").toString().substring(5),576,96);


    BufferedImage sandfilling = Main.crop(Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Stats/FillSand.png").toString().substring(5), 544,16),0,0,16,(Main.currentLvl+1)*24);
    BufferedImage labfilling = Main.crop(Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Stats/FillLab.png").toString().substring(5),(Main.currentLvl+1-10)*6,16),0,0,16,544);
    BufferedImage castlefilling = Main.crop(Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Stats/FillCastle.png").toString().substring(5),(Main.currentLvl+1-20)*6,16),0,0,16,544);

    g2D.drawImage(sandfilling,44,270,null);
    g2D.drawImage(deck.getImage(),32,258,null);
    
  }

}