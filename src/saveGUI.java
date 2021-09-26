import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class saveGUI extends JPanel{

  JLabel backIMG;
	int savenum;
	
  public saveGUI() {
    
    this.setBounds(0,0,896,640);
    this.setBackground(Color.CYAN);
    this.setLayout(null);

    backIMG = new JLabel(Main.setImageSize(Main.cl.getResource("Icons/saveGUI.png").toString().substring(5),896,640));
    backIMG.setBounds(0,0,896,640);
    this.add(backIMG);

    for(savenum=0;savenum<3;savenum++){
      JButton button = new JButton(String.valueOf(savenum));
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
      button.setBounds(148,244+savenum*68,604,64);
      button.setPreferredSize(new Dimension(604,64));
			System.out.println(savenum+1);
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
          Main.saveGame(Integer.valueOf(((JButton)e.getSource()).getText().toString())+1);
          Main.gui.close();
        
      }});
      this.add(button);
    }
    
    Main.gui.frame.getRootPane().getActionMap().put("escape", Main.gui.escapeMAP);

    this.validate();
    this.repaint();
  }
  public void openSave(){
    System.out.println("OEPNES SVEA SVAEV SA VEVS VAEV");
    Main.gui.map.toggleMove(false);
    Main.gui.gamePanel.add(Main.gui.saveScene, Integer.valueOf(1));
    Main.gui.gamePanel.validate();
    Main.gui.gamePanel.repaint();
    Main.gui.mainPanel.validate();
    Main.gui.mainPanel.repaint();
		this.validate();
		this.repaint();
    }

}