import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class loadGUI extends JPanel{

  JLabel backIMG;
	int loadnum;
	
  public loadGUI() {
    
    this.setPreferredSize(new Dimension(896,640));
    this.setBackground(Color.CYAN);
    this.setLayout(null);

    backIMG = new JLabel(Main.setImageSize(Main.cl.getResource("Icons/loadGUI.png").toString().substring(5),640,896));
    backIMG.setBounds(0,0,896,640);
    this.add(backIMG);

    for(loadnum=0;loadnum<3;loadnum++){
      JButton button = new JButton();
      button.setBounds(148,244+loadnum*68,604,64);
      button.setPreferredSize(new Dimension(604,64));
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
          Main.loadGame(loadnum+1);
        
      }});
      this.add(button);
    }

    Main.gui.frame.getRootPane().getActionMap().put("escape", Main.gui.escapeSTART);

    this.validate();
    this.repaint();
  }

}