import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;


public class invSlot extends JPanel {
		Item item;
		static JPanel confirm = new JPanel();
		String option;

   	public invSlot(Item item) {
				this.item = item;
        this.setOpaque(false);
        // this.add(new JLabel(item.name));//TEMP THING BEFORE ITEMS ICONS
				// JLabel imgLabel = new JLabel();
				// imgLabel.setIcon(Main.setImageSize(item.dir, 72, 72));
				// this.add(imgLabel);

        this.addMouseListener(new MouseListener() {
          @Override
          public void mouseClicked(final MouseEvent e) {
            if(!Main.gui.invScene.item.confirming){
              if(SwingUtilities.isRightMouseButton(e)){
                initConfirm("del");
              }else if(SwingUtilities.isLeftMouseButton(e)){
                initConfirm("use");
              }
            }
          }
          @Override
          public void mousePressed(final MouseEvent e) {
          }
          @Override
          public void mouseReleased(final MouseEvent mouseEvent) {
          }
          @Override
          public void mouseEntered(final MouseEvent mouseEvent) {
						System.out.println("ENTER");
            Main.gui.invScene.item.des.setText("<html><br>TOOL TIP: <br>" + item.toolTip + "</html>");
          }
          @Override
          public void mouseExited(final MouseEvent mouseEvent) {
						System.out.println("LEFT");
            Main.gui.invScene.item.des.setText("");
          }
        });
    }
   	public invSlot() { // empty item
        this.add(new JLabel("-"));
		}

		public void initConfirm(String option) {
      Main.gui.invScene.item.confirming = true;
			this.option = option;
			if(this.item instanceof Key || this.item instanceof Weapon || this.item instanceof Note) {
				this.option = "none";
			}
        System.out.println(option);
				
				Main.gui.invScene.item.add(confirm, Integer.valueOf(2));
				confirm.removeAll();
				// confirm.setLayout(new BorderLayout());
				confirm.setLayout(null);
        confirm.setBorder(new EtchedBorder());
        confirm.setBackground(Color.pink);
        confirm.setBounds(106,48,424,224);

        JButton yes = new JButton("YES");
        JButton no = new JButton("NO");
				JLabel midText = new JLabel("ILLEGAL ACTION");
				midText.setBounds(0,64,424,64);
				midText.setHorizontalAlignment(JLabel.CENTER);
				yes.setBounds(53,130,106,64);
				no.setBounds(265,130,106,64);
       	confirm.add(no);
				if(this.option.equals("none") || (this.item instanceof RockPass && this.option.equals("del"))){
					no.setText("OK");
					confirm.add(midText);
				} else {
       		confirm.add(yes);
					JLabel confirmText = new JLabel("ARE YOU SURE YOU WANT TO DO THIS?");
					confirmText.setBounds(0,0,424,64);
					confirmText.setHorizontalAlignment(JLabel.CENTER);
					confirm.add(confirmText);
					midText.setText(this.option + " " + item.name);
					confirm.add(midText);

					  yes.addActionListener(new ActionListener() {
          		@Override
          		public void actionPerformed(ActionEvent actionEvent) {
								if(option.equals("use")){
             			item.use();
                } else if(option.equals("del")){
      						Main.person.inv.removeItem(item.index);
								}
              	Main.gui.invScene.item.remove(confirm);
         				Main.gui.invScene.item.updateScreen();
          	}
        	});
				}
        no.addActionListener(new ActionListener() {
        	@Override
         	public void actionPerformed(ActionEvent actionEvent) {
             Main.gui.invScene.item.remove(confirm);
          		Main.gui.invScene.item.updateScreen();
         	}
        });
				confirm.validate();
				confirm.repaint();
		}
		
		public void paint(Graphics g) {
        super.paint(g); // paint background
        Graphics2D g2D = (Graphics2D) g;

				Image slotimg = (Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Inv/ItemIcon.png").toString().substring(5),72,72)).getImage();
				g2D.drawImage(slotimg,0,0,null);

				if(this.item != null) {
					Image itemimg = (Main.setImageSize(this.item.dir,56,56)).getImage();
					g2D.drawImage(itemimg,8,8,null);
				}
        g2D.dispose();
    }
}
                
                    


