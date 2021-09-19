import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;



public class deathGUI extends JPanel{
				
        public deathGUI(int checkpoint, String deathmsg) {
          this.setBackground(Color.BLACK);
          this.setBounds(0,0, 700, 500);


          this.add(new JLabel(deathmsg));
          JButton button = new JButton();
          button.setText("ok");
          button.addActionListener(new ActionListener() {
          		@Override
          		public void actionPerformed(ActionEvent actionEvent) {
              	Main.startGame();
								Main.gui.close();
								Main.gui.frame.remove(Main.gui.mainPanel);
				        Main.gui.initGUI();	
								
          	}
        	});
          this.add(button);

        }

				public static void die(int checkpoint, String deathmsg){
					Main.gui.deathScene = new deathGUI(checkpoint, deathmsg);
          Main.gui.map.toggleMove(false);
            Main.gui.gamePanel.removeAll();
						Main.gui.gamePanel.add(Main.gui.deathScene, Integer.valueOf(1));
						Main.gui.deathScene.validate();
						Main.gui.deathScene.repaint();
						Main.gui.mainPanel.validate();
						Main.gui.mainPanel.repaint();
						Main.gui.frame.validate();
						Main.gui.frame.repaint();
				}

    }