import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;
import java.util.Map;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import static javax.swing.BorderFactory.createEmptyBorder;

public class invBest extends Screen{

	JPanel BGPanel;
	JScrollPane scroll;
	JPanel listBG;
	invEnemy enemy;

	public invBest(){
		super();
		this.BGPanel = new JPanel();
		this.BGPanel.setOpaque(false);
		this.BGPanel.setLayout(null);
		this.BGPanel.setBounds(0,0,640,320);
		this.add(this.BGPanel, Integer.valueOf(1));

    JLabel backIMG = new JLabel();
		backIMG.setIcon(Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Bestiary/BestBG.png").toString().substring(5), 384,640));
		backIMG.setBounds(0,0,640,384);
		this.add(backIMG, Integer.valueOf(0));

		this.listBG = new JPanel();
		// this.listBG.setBackground(Color.BLUE);
		this.listBG.setLayout(null);
		this.listBG.setPreferredSize(new Dimension(192,56*(Main.person.bestiary.size()+1)));
		this.scroll = new JScrollPane(this.listBG);
		this.scroll.getVerticalScrollBar().setUI(new scrollBest());
    this.scroll.getViewport().setOpaque(false);
    this.scroll.setOpaque(false);
    this.scroll.getVerticalScrollBar().setPreferredSize(new Dimension(48,304));
    this.scroll.setBorder(createEmptyBorder());
		this.scroll.setBounds(32,32,256,304);

		this.enemy = new invEnemy();
	}

	public void updateScreen(){
		this.BGPanel.removeAll();
		this.BGPanel.setLayout(null);
		this.createOptions();

		this.BGPanel.add(this.scroll);
		this.BGPanel.add(this.enemy);
		this.BGPanel.validate();
		this.BGPanel.repaint();
	}

	public void closeScreen(){
	}

	public void createOptions(){
		this.listBG.removeAll();
		JPanel list = new JPanel();
		// list.setBackground(Color.RED);s
		list.setBounds(16,16,192,56*Main.person.bestiary.size());
		list.setLayout(new GridLayout(Main.person.bestiary.size(),1));
		((GridLayout)list.getLayout()).setVgap(8);
		Iterator it = Main.person.bestiary.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        // System.out.println(pair.getKey() + " = " + pair.getValue());
				 JButton enemyBut = new JButton();
        if((Boolean)pair.getValue()){
					enemyBut.setText((String)pair.getKey());
    			enemyBut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
          	System.out.println((String)pair.getKey());
						Main.gui.invScene.best.enemy.setEnemy(new Enemy((String)pair.getKey()));
						Main.gui.invScene.best.updateScreen();
					}});
				 }
				 list.add(enemyBut);
			   list.validate();
    }
		this.listBG.add(list);
	}

}