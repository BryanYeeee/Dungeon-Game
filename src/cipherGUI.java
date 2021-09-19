import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.math.BigDecimal;
    
    public class cipherGUI extends JPanel{
      public static JLabel Instructions;
      public static JPanel Inputs;
      public static int curItem;
      public Cipher curCipher;
      public boolean submitted = false;
			public JTextField answer;
			Timer timer;
			public JLabel time;

        public cipherGUI(Cipher curCipher) { //CANT REMOVE TEXTFIELD FROMBUTTON
					super(new BorderLayout());
					  this.curCipher = curCipher;
            this.submitted = false;
            JLabel name = new JLabel("Cipher");
            System.out.println(curCipher.instruction);
						Instructions = new JLabel(curCipher.instruction);
						Inputs = new JPanel();
						time = new JLabel("TIME LEFT: " + curCipher.dif);

            this.add(name, BorderLayout.NORTH);
            this.add(Instructions, BorderLayout.CENTER);
            time.setHorizontalAlignment(JLabel.CENTER);
						this.add(time, BorderLayout.EAST);
						this.validate();
						

						answer = new JTextField();
						JButton submit = new JButton("Continue");

            answer.setPreferredSize(new Dimension(200,20));
            submit.setPreferredSize(new Dimension(100,20));

            timer = new Timer(1000, new ActionListener() {
							int counter = curCipher.dif;
                @Override
                public void actionPerformed(ActionEvent f) {
                    if (counter > 0) {
											System.out.println(counter + " " + curCipher.dif);
                        counter--;
												time.setText("TIME LEFT: " + counter);
                    } else {
                        timer.stop();
                        		Main.gui.ciphScene.submitted = true;
														Main.gui.ciphScene.Instructions.setText("RAN OUT OF TIME, you know loose health lol");
                    Main.person.health = Main.person.health - curCipher.dif;
                            if(Main.person.health<=0){
                              System.out.println("DDDSDAWDASD");
                              Main.gui.deathScene.die(Main.person.checkpoint, "Death by Cipher, LMAO");
                            }
                    }

                }
            });
            timer.setCoalesce(true);
            timer.start();
						
            submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      System.out.println(Main.gui.ciphScene.submitted);
											if(Main.gui.ciphScene.submitted == false){
												try {
													if(curCipher.hack(Integer.parseInt(answer.getText()))){
                       			Main.gui.ciphScene.submitted = true;
														Main.gui.ciphScene.Instructions.setText("CORRECT, you know make money");
													timer.stop();
                    Main.person.worth = Main.person.worth.add(new BigDecimal(String.valueOf(curCipher.dif)));
                      		}else{
                        		Main.gui.ciphScene.submitted = true;
														Main.gui.ciphScene.Instructions.setText("WRONG, you know loose health lol");
														timer.stop();
                            Main.person.health = (int)(Main.person.health*0.8);
														Main.gui.stats.updateStats();
                            if(Main.person.health<=0){
                              System.out.println("DDDSDAWDASD");
                              Main.gui.deathScene.die(Main.person.checkpoint, "Death by Cipher, LMAO");
                            }
                      		}
												} catch (Exception ex) {
													System.out.println(ex);
												}
											} else {
                      	endCipher();
											}
                    }
            });
						Inputs.add(answer);
						Inputs.add(submit);
						Inputs.setLayout(new FlowLayout());
						this.add(Inputs, BorderLayout.SOUTH);

            this.setBackground(Color.MAGENTA);
						this.setBounds(0,0,700,500);
            validate();
        }


        public static void endCipher() {
            Main.gui.close();
            System.out.println("You've HACKED GOD");
        }
        public static void startCipher(cipherGUI ciphScene){

                    // GUI.mainPanel.removeAll();
                    Main.gui.map.toggleMove(false);
                    Main.gui.gamePanel.add(ciphScene, Integer.valueOf(1));
                    Main.gui.ciphScene.validate();
                    Main.gui.ciphScene.repaint();
                    Main.gui.mainPanel.validate();
                    Main.gui.mainPanel.repaint();
                    Main.gui.frame.validate();
                    Main.gui.frame.repaint();
          
        }
                   
    }
  
