import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class statGUI extends JPanel{
        public JPanel helppanel = new JPanel();

        public statGUI() {
            this.setLayout(new GridLayout(1,0));
						this.setBounds(0,0,500,100);
            this.setPreferredSize(new Dimension(500,30));
            this.setBackground(Color.BLUE);
            this.helppanel.setLayout(new GridLayout(0,4));
            validate();
        }
        public void updateStats(){
          this.removeAll();
          this.helppanel.removeAll();
          this.helppanel.setLayout(new GridLayout(0,4));
            this.setLayout(new GridLayout(1,0));
					System.out.println("stat heaklth " + Main.person.health);

          this.helppanel.add(new JLabel(("Level: " + Main.arlofLevels.get(Main.currentLvl).levelnum)));
          this.helppanel.add(new JLabel("Health: " + Main.person.health +"/"+ Main.person.maxhp));
          this.helppanel.add(new JLabel("Strength: " + Main.person.strength));
          this.helppanel.add(new JLabel("Money: " + Main.person.worth));

          // this.add(new JLabel("{ " + Main.arlofLevels.get(Main.currentLvl).text + " }"));
          this.add(this.helppanel);
          // this.add(new JLabel("Items: " + Main.person.inv.outputInv()));

          this.validate();
          this.repaint();
          
          //  "<html> { " + Main.arlofLevels.get(Main.currentLvl).text + " } <br> Level: " + Main.arlofLevels.get(Main.currentLvl).levelnum + " <br> [Health: " + Main.person.health + "|Strength: " + Main.person.strength + "|Money: " + Main.person.worth + "] <br> --" + Main.person.inv.items + "--</html>"

        }

    }