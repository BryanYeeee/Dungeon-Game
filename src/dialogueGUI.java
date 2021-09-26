import javax.swing.*;
import java.awt.*;

public class dialogueGUI extends JLayeredPane{
    public Npc currentNPC;
    public dialogueGUI(Npc newNPC){
        this.currentNPC = newNPC;
        this.setLayout(null);
        this.setBounds(0,512,896,128);
        JLabel backIMG = new JLabel();
        backIMG.setIcon(Main.setImageSize(Main.cl.getResource("Icons/diagBG.png").toString().substring(5), 896,128));
        backIMG.setBounds(0,0,896,128);
        this.add(backIMG, Integer.valueOf(0));//
    }

    public static void startDiag(dialogueGUI diagScene){

        // GUI.mainPanel.removeAll();
        Main.gui.map.toggleMove(false);
        Main.gui.gamePanel.add(diagScene, Integer.valueOf(1));
        Main.gui.diagScene.validate();
        Main.gui.diagScene.repaint();
        Main.gui.mainPanel.validate();
        Main.gui.mainPanel.repaint();
        Main.gui.frame.validate();
        Main.gui.frame.repaint();

    }
}
