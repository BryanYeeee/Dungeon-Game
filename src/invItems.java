import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;

public class invItems extends Screen{

	JLabel des = new JLabel("");//description of items
	JPanel slots = new JPanel();//inventory slots
	JPanel BGPanel;
	JButton pager;
	JButton pagel;
	int page;
	JLabel backIMG = new JLabel();
	public boolean confirming;

	public invItems(){
		super();
		this.page = 0;
		this.confirming = false;
		this.BGPanel = new JPanel();
		// this.BGPanel.setBackground(Color.YELLOW);
		this.BGPanel.setOpaque(false);
		this.BGPanel.setLayout(null);
		this.BGPanel.setBounds(0,0,640,384);
		this.add(this.BGPanel, Integer.valueOf(1));
		this.pager = makeButton(this.pager, true);
		this.pagel = makeButton(this.pagel, false);

		this.backIMG.setIcon(Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Inv/ItemBGl.png").toString().substring(5),640,384));
		this.backIMG.setBounds(0,0,640,384);
		this.add(backIMG, Integer.valueOf(0));

		this.des.setBounds(16,8,640,104);
    this.des.setVerticalAlignment(JLabel.TOP);
		this.slots.setBounds(92,136,456,185);
		this.slots.setOpaque(false);
		// this.slots.setBackground(Color.PINK);
		this.BGPanel.add(des);
		this.BGPanel.add(slots);
	}

	public void updateScreen(){
    Main.gui.invScene.item.confirming = false;
		this.BGPanel.removeAll();
		this.BGPanel.setLayout(null);
		this.backIMG.setIcon(Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Inv/" +
			(page==0?"ItemBGl.png":
			(page+10==Main.person.inv.invCap?"ItemBGr.png":
			"ItemBGm.png"))).toString().substring(5),640,384));

		this.makeSlots();
		this.BGPanel.add(this.pager);
		this.BGPanel.add(this.pagel);
    this.des.setVerticalAlignment(JLabel.TOP);
		this.BGPanel.add(des);
		this.BGPanel.add(slots);

		this.BGPanel.validate();
		this.BGPanel.repaint();
		this.validate();
		this.repaint();
	}

	public void closeScreen(){
		this.remove(invSlot.confirm);
		this.confirming = false;
		this.page = 0;
	}

	public void makeSlots(){
		this.slots.removeAll();
		GridLayout gl = new GridLayout(2,5);
		gl.setHgap(24);
		gl.setVgap(40);
		this.slots.setLayout(gl);
		for(int i=this.page;i<page+10;i++){
			// System.out.println("made a slot " + col + " " + row);
			invSlot newSlot = new invSlot();
			// JPanel newSlot = new JPanel();

			if(i<Main.person.inv.items.size()){
				newSlot = new invSlot(Main.person.inv.items.get(i));
				this.slots.add(newSlot);
			}else{
				this.slots.add(newSlot);
			}
			newSlot.setOpaque(false);
			// newSlot.setBackground(Color.MAGENTA);
			// newSlot.setBounds(col, row, 64,64);
			// row = (i+1)%9==0 ? row+74 : row;
			// col = (i+1)%9==0 ? 4 : col+71;
		}
	}

	public JButton makeButton(JButton button, boolean r){
		button = new JButton();
    button.setOpaque(false);//shoudl go nex tpage
    button.setContentAreaFilled(false);
    button.setBorderPainted(false);
		button.setBounds(r?580:20,208,40,56);
    button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(((!r)&&page!=0) || (r&&page+10<Main.person.inv.invCap)){
						page += r ? 10 : -10;
					}
						Main.gui.invScene.item.updateScreen();
				}});
    return button;
	}
}