import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class startingGUI extends JPanel{

  JLabel backIMG;
  public JButton startGame = startButtons(new JButton()); 
	public JButton loadGame = startButtons(new JButton());
	public JButton configureGame = startButtons(new JButton());
	public JButton quitGame = startButtons(new JButton());

  public startingGUI() {
    
    this.setPreferredSize(new Dimension(896,640));
    this.setBackground(Color.CYAN);
    this.setLayout(null);

    backIMG = new JLabel(Main.setImageSize(Main.cl.getResource("Icons/startingGUI.png").toString().substring(5),640,896));
    backIMG.setBounds(0,0,896,640);
    this.add(backIMG);

    finishButton();

    this.validate();
    this.repaint();
  }
  public JButton startButtons(JButton button){
    button.setOpaque(false);
    button.setContentAreaFilled(false);
    button.setBorderPainted(false);
    button.setPreferredSize(new Dimension(172,76));
    button.setMaximumSize(new Dimension(172,76));

    return button;

  }
  public void finishButton(){
    
    
  startGame.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      Main.startGame();
      Main.gui.frame.remove(Main.gui.mainPanel);
      Main.gui.initGUI();
      Main.gui.close();
    }});
  loadGame.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      Main.gui.changeScreen("load");
    }});
  configureGame.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      System.out.println("NO CONFIG");
    }});
  quitGame.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      Main.gui.frame.setVisible(false); 
      Main.gui.frame.dispose(); 
    }});

    
  startGame.setBounds(60,488,172,76);
  loadGame.setBounds(264,488,172,76);
  configureGame.setBounds(468,488,172,76);
  quitGame.setBounds(672,488,172,76);

  this.add(startGame);
  this.add(loadGame);
  this.add(configureGame);
  this.add(quitGame);
  

  }
}