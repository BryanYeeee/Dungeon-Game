import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;
import java.awt.image.BufferedImage;

public class invWeapon extends Screen{

	JPanel BGPanel;
	JLabel weapIcon = new JLabel();
	JLabel name = new JLabel();
	JLabel abil1 = new JLabel();
	JLabel abil2 = new JLabel();
	JLabel damage = new JLabel();

	String curWeap;

	JButton changeR;
	JButton changeL;

	public invWeapon(){
		super();
		this.BGPanel = new JPanel();
		this.BGPanel.setOpaque(false);
		this.BGPanel.setLayout(null);
		this.BGPanel.setBounds(0,0,640,384);
		this.add(this.BGPanel, Integer.valueOf(1));

    JLabel backIMG = new JLabel();
		backIMG.setIcon(Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Weapons/WeapBG.png").toString().substring(5), 640,384));
		backIMG.setBounds(0,0,640,384);
		this.add(backIMG, Integer.valueOf(0));

		this.name.setBounds(0,0,640,64);
		this.name.setHorizontalAlignment(JLabel.CENTER);		

		this.weapIcon.setBounds(0,64,640,128);
		this.weapIcon.setHorizontalAlignment(JLabel.CENTER);

		this.abil1.setBounds(8,228,164,108);
		this.abil1.setHorizontalAlignment(JLabel.CENTER);

		this.abil2.setBounds(464,228,164,108);
		this.abil2.setHorizontalAlignment(JLabel.CENTER);

		this.damage.setBounds(172,244,300,92);
		this.damage.setHorizontalAlignment(JLabel.CENTER);
		
		this.curWeap = "sword";
		this.setWeapon(Main.person.inv.sword);
	}

	public void updateScreen(){
		this.BGPanel.removeAll();
		this.BGPanel.setLayout(null);

		this.changeR = makeButton(this.changeR, true);
		this.changeL = makeButton(this.changeL, false);

		this.BGPanel.add(name);
		this.BGPanel.add(weapIcon);
		this.BGPanel.add(abil1);
		this.BGPanel.add(abil2);
		this.BGPanel.add(damage);

		this.BGPanel.add(changeR);
		this.BGPanel.add(changeL);

		this.BGPanel.validate();
		this.BGPanel.repaint();
	}

	public void closeScreen(){
		this.curWeap = "sword";
		this.setWeapon(Main.person.inv.sword);
	}

	public JButton makeButton(JButton button, boolean r){
		button = new JButton();
    button.setOpaque(false);
    button.setContentAreaFilled(false);
    button.setBorderPainted(false);
		button.setBounds(r?560:40,156,40,56);
    button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						switch(Main.gui.invScene.weap.curWeap){
							case "sword":
								Main.gui.invScene.weap.setWeapon(r?Main.person.inv.spell:Main.person.inv.hammer);
								Main.gui.invScene.weap.curWeap = r?"wand":"hammer";
								break;
							case "wand":
								Main.gui.invScene.weap.setWeapon(r?Main.person.inv.hammer:Main.person.inv.sword);
								Main.gui.invScene.weap.curWeap = r?"hammer":"sword";
								break;
							case "hammer":
								Main.gui.invScene.weap.setWeapon(r?Main.person.inv.sword:Main.person.inv.spell);
								Main.gui.invScene.weap.curWeap = r?"sword":"wand";
								break;
						}
						Main.gui.invScene.weap.updateScreen();
				}});
    return button;
	}
	public void setWeapon(Weapon weapon){
		this.name.setText(weapon.name);
		//this.weapIcon.setIcon(Main.setImageSize(weapon.name, 100, 100)) <NEED TO DO LATEr
		this.damage.setText(weapon.damage + " DMG");

		String abil1Name = "", abil2Name = "";
		switch(weapon.basename){
			case "sword":
				abil1Name = "Strike";
				abil2Name = "Block";
				break;
			case "spell":
				abil1Name = "Heal";
				abil2Name = "Burn";
				break;
			case "hammer":
				abil1Name = "Stun";
				abil2Name = "Heavy";
				break;
		}
		this.abil1.setText(abil1Name);
		this.abil1.setIcon(Main.setImageSize(Main.cl.getResource("Icons/AbilIcon/"+abil1Name+"Icon.png").toString().substring(5), 100, 100)); //<100
		this.abil2.setText(abil2Name);
		this.abil2.setIcon(Main.setImageSize(Main.cl.getResource("Icons/AbilIcon/"+abil2Name+"Icon.png").toString().substring(5), 100, 100));  //<100
	}

}