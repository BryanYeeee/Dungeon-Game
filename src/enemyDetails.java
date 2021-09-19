import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.math.BigDecimal;
import static javax.swing.BorderFactory.createEmptyBorder;

    public class enemyDetails extends JPanel{
			JLabel abilityLabel;
			JLabel nameTag;
			JLabel statusLabel;

        public enemyDetails(String abilities, String name) {
					this.setBounds(508,0,388,446);
					this.setOpaque(false);
					this.setBorder(createEmptyBorder());

					this.nameTag = new JLabel(name);
					this.nameTag.setHorizontalAlignment(SwingConstants.CENTER);
					this.nameTag.setForeground(Color.WHITE);
					this.nameTag.setBounds(132,384,256,62);

					this.statusLabel = new JLabel("");	
					this.statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
					this.statusLabel.setForeground(Color.WHITE);
					this.statusLabel.setBounds(20,10,99,85);
									
					this.updateDetails(abilities, name);
        }
				public void updateDetails(String abilities, String name) {
					this.removeAll();
					this.setLayout(null);
					// this.statusLabel.setText();
					for(int i = 0; i < Main.gui.fightScene.victim.status.keySet().size(); i++){
						this.statusLabel.setText(this.statusLabel.getText() + "<br>" + (new ArrayList<>(Main.gui.fightScene.victim.status.keySet())).get(i));
					}
					this.statusLabel.setText("<html>"+this.statusLabel.getText()+"</html>");
          this.nameTag.setText(name);
					this.add(this.nameTag);          
					this.add(this.statusLabel);
					this.validate();
					this.repaint();
				}

    }