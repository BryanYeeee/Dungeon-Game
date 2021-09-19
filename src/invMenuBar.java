import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;

public class invMenuBar extends JPanel{

	public JButton statBut = new JButton("stat"); 
	public JButton itemBut = new JButton("item");
	public JButton bestBut = new JButton("best");
	public JButton weapBut = new JButton("weap");
  public JButton settBut = new JButton("sett");
	public JButton dBut = new JButton("6	");
//defualyt items probably ".//Icons//InventoryIcon//InvMenuBar.png").toString().substring(5)

	public invMenuBar() {
		this.setBounds(0,0,640,80);
		this.setBackground(Color.GREEN);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		((FlowLayout)this.getLayout()).setHgap(0);
    this.setOpaque(false);

    this.itemBut = makeButton(this.itemBut, Main.gui.invScene.item,Main.cl.getResource("Icons/InventoryIcon/Inv/ItemButton.png").toString().substring(5));
		this.add(this.itemBut);

    this.statBut = makeButton(this.statBut, Main.gui.invScene.stat,Main.cl.getResource("Icons/InventoryIcon/Stats/StatButton.png").toString().substring(5));
		this.add(this.statBut);

    this.bestBut = makeButton(this.bestBut, Main.gui.invScene.best,Main.cl.getResource("Icons/InventoryIcon/Bestiary/BestButton.png").toString().substring(5));
		this.add(this.bestBut);

   	this.weapBut = makeButton(this.weapBut, Main.gui.invScene.weap,Main.cl.getResource("Icons/InventoryIcon/Weapons/WeapButton.png").toString().substring(5));
		this.add(this.weapBut);
    this.settBut = makeButton(this.settBut, Main.gui.invScene.sett,Main.cl.getResource("Icons/InventoryIcon/Settings/SettButton.png").toString().substring(5));
		this.add(this.settBut);

	}
  public JButton makeButton(JButton button, Screen screen, String dir){
    button.setOpaque(false);
    button.setContentAreaFilled(false);
    button.setBorderPainted(false);
    button.setPreferredSize(new Dimension(104,80));
    button.setMaximumSize(new Dimension(104,80));
    button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
          Main.gui.invScene.menuDir = dir;
					screen.addScreen();
				}});
    return button;
  }
  public void paint(Graphics g) {

		super.paint(g); // paint background
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(Main.setImageSize(Main.gui.invScene.menuDir, 80, 640).getImage(),0,0,null);
  
  }
}