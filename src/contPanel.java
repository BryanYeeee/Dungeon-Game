import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.math.BigDecimal;

public class contPanel extends JLabel {
    public ArrayList<String> txtList;

    public contPanel() {
        super();
        this.txtList = new ArrayList<>();
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setPreferredSize(new Dimension(1000, 50));
        // this.setFont(new Font("Verdana", Font.PLAIN, 15));
        this.setForeground(Color.WHITE);
    }

    public void startCont() {
        Main.gui.fightScene.cont.contOn();
        Main.gui.fightScene.forePanel.removeAll();
        Main.gui.fightScene.forePanel.setLayout(new BorderLayout());
        Main.gui.fightScene.forePanel.add(this, BorderLayout.CENTER);
        Main.gui.fightScene.forePanel.repaint();
        Main.gui.fightScene.forePanel.validate();
        return;
    }

    public void contOn() {
        Main.gui.fightScene.foreIMG.setIcon(new ImageIcon(Main.cl.getResource("Icons/FightIcon/FightOne.png").toString().substring(5)));
        Main.gui.fightScene.cont.setText("SPACE TO CONTINUE");
        Main.gui.mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "SPACE");
        Main.gui.mainPanel.getActionMap().put("SPACE", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
            if (Main.gui.fightScene.defeated && Main.gui.fightScene.cont.txtList.isEmpty()) {
                if (Main.boss.phase == 4) {
                    Bossibility.DoodMoe = false;
                    Bossibility.DoodOcto = false;
                    Bossibility.DoodKoala = false;
                    Main.boss.phase = 0;
                }
                Main.gui.fightScene.cont.contOff();
                GUI.toggleInput(true, false);
                Main.gui.fightScene.foreIMG.setIcon(Main.gui.fightScene.foreOpt);
                GUI.close();
            } else if (Main.gui.fightScene.cont.txtList.isEmpty()) {
                Main.gui.fightScene.forePanel.remove(Main.gui.fightScene.InpPanel);
                Main.gui.fightScene.optionPanel.startOption();
                Main.gui.fightScene.foreIMG.setIcon(Main.gui.fightScene.foreOpt);
                Main.gui.fightScene.cont.contOff();
            } else {
                Main.gui.fightScene.cont.setText(Main.gui.fightScene.cont.txtList.get(0));
                Main.gui.fightScene.cont.txtList.remove(0);
            }
            }
        });
    }

    public void contOff() {
        this.setText("");
        Main.gui.mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke("SPACE"));
        Main.gui.mainPanel.getActionMap().remove("SPACE");
    }

    public void changeTxt(String text) {
        this.setText(text);
        return;
    }

    public void addTxt(String text) {
        this.txtList.add(text);
        return;
    }

}