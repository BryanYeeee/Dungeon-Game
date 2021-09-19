import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class invEnemy extends JPanel{
	JPanel enemyDisplay;
	JLabel sword;
	JLabel wand;
	JLabel hammer;
	JLabel abil;

  ImageIcon weapWeak;
  ImageIcon weapRes;
  ImageIcon weapImmune;
  ImageIcon weapNone;

  ImageIcon bestSword;
  ImageIcon bestSpell;
  ImageIcon bestHammer;

	public invEnemy(){
		weapWeak = Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Bestiary/WeakWeap.png").toString().substring(5),20,20);
		weapRes = Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Bestiary/ResWeap.png").toString().substring(5),20,20);
		weapImmune = Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Bestiary/ImmuneWeap.png").toString().substring(5),20,20);
		weapNone = Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Bestiary/NoneWeap.png").toString().substring(5),20,20);

		bestSword = Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Bestiary/BestSword.png").toString().substring(5), 36,36);
		bestSpell = Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Bestiary/BestSpell.png").toString().substring(5), 36,36);
		bestHammer = Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Bestiary/BestHammer.png").toString().substring(5), 36,36);
		this.setLayout(null);
		this.setBounds(308,36,296,304);
		this.setOpaque(false);

		enemyDisplay = new JPanel();
		enemyDisplay.setBounds(20,20,272,160);
		enemyDisplay.setOpaque(false);

		sword = new JLabel();
		sword.setIcon(bestSword);
		sword.setBounds(20,104,36,36);
		wand = new JLabel();
		wand.setIcon(bestSpell);
		wand.setBounds(100,104,36,36);
		hammer = new JLabel();
		hammer.setIcon(bestHammer);
		hammer.setBounds(180,104,36,36);

		this.abil = new JLabel();
		this.abil.setBounds(16,184,272,96);
		this.abil.setVerticalAlignment(JLabel.TOP);

		this.add(enemyDisplay);
	}

	public void setEnemy(Enemy enemy){
		this.enemyDisplay.removeAll();
		this.enemyDisplay.setLayout(null);
		this.abil.setText("<html>"+enemy.abilities+"</html>");

		JLabel enemyIcon = new JLabel();
		enemyIcon.setIcon(Main.setImageSize(enemy.dir,96,96));
		enemyIcon.setBounds(88,0,96,96);

		ImageIcon icon = enemy.name.equals("Dood")? weapImmune : weapNone;

		System.out.println(enemy.weak + " " + enemy.resist);
		JLabel swordWeak = new JLabel();
		swordWeak.setBounds(56,112,20,20);
		swordWeak.setIcon(icon);
		JLabel wandWeak = new JLabel();
		wandWeak.setBounds(136,112,20,20);
		wandWeak.setIcon(icon);
		JLabel hammerWeak = new JLabel();
		hammerWeak.setBounds(216,112,20,20);
		hammerWeak.setIcon(icon);

		icon  = weapWeak;
		switch(enemy.weak){
			case "sword":
				swordWeak.setIcon(icon);
				break;
			case "spell":
				wandWeak.setIcon(icon);
				break;
			case "hammer":
				hammerWeak.setIcon(icon);
				break;
		}
		icon = enemy.resist.contains("/")?weapImmune:weapRes;
		switch(enemy.resist){
			case "sword":
			case "/sword":
				swordWeak.setIcon(icon);
				break;
			case "spell":
			case "/spell":
				wandWeak.setIcon(icon);
				break;
			case "hammer":
			case "/hammer":
				hammerWeak.setIcon(icon);
				break;
		}

		this.enemyDisplay.add(enemyIcon);
		this.add(this.abil);
		this.enemyDisplay.add(this.sword);
		this.enemyDisplay.add(this.wand);
		this.enemyDisplay.add(this.hammer);
		this.enemyDisplay.add(swordWeak);
		this.enemyDisplay.add(wandWeak);
		this.enemyDisplay.add(hammerWeak);


	}


}