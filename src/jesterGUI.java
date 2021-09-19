import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.math.BigDecimal;
import static javax.swing.BorderFactory.createEmptyBorder;

    public class jesterGUI extends JLayeredPane{
				
        public jesterGUI() {
          // this.setPreferredSize(new Dimension(150,300));
					this.setLayout(null);
					this.setOpaque(false);
					this.setBorder(createEmptyBorder());
					this.validate();
          this.repaint();
        }
				public void updateJesterGUI(int first, int second, ArrayList<Integer> ans) {
					this.removeAll();
					this.setLayout(null);

					JLabel backIMG = new JLabel();
					backIMG.setIcon(new ImageIcon(Main.cl.getResource("Icons/FightIcon/FightQuiz.png").toString().substring(5)));
					backIMG.setBounds(0,0,384,192);
					this.add(backIMG, Integer.valueOf(0));

					JPanel qna = new JPanel();
					qna.setBounds(0,0,384,192);
					qna.setLayout(new GridLayout(5,1));
					System.out.println(ans);
					
					qna.add(new JLabel("what is "+first + "+" + second + "?"));
          qna.add(new JLabel("W: "+ ans.get(0)));
          qna.add(new JLabel("A: "+ ans.get(1)));
          qna.add(new JLabel("S: "+ ans.get(2)));
          qna.add(new JLabel("D: "+ ans.get(3)));

					this.add(qna, Integer.valueOf(1));
					qna.validate();
          qna.repaint();
					this.validate();
          this.repaint();
				}
				public void remove() {
					Main.gui.fightScene.remove(this);
					this.validate();
          this.repaint();
				}

    }