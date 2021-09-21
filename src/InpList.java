import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.math.BigDecimal;
import java.awt.image.RescaleOp;
import java.io.*;
import javax.imageio.ImageIO;

import static javax.swing.BorderFactory.createEmptyBorder;

public class InpList extends JPanel {
    ArrayList<String> Inputs = new ArrayList<>();
    public int tileLeft;
    public Timer timer;
    public int fadingperc;
    public int[] swapPos = new int[]{0, 0};
    ArrayList<String> jestDis = new ArrayList<>();

    public Image swapinp = new ImageIcon(Main.cl.getResource("Icons/InpTilesIcon/SwapInp.png").toString().substring(5)).getImage();

    public InpList() {
        super(new GridLayout());
        // this.setBackground(Color.blue);
        this.setOpaque(false);
        this.setBorder(createEmptyBorder());
    }

    public void generate(int size) {
        tileLeft = size;
        Random rand = new Random();
        this.Inputs.clear();
        for (int i = 0; i < size; i++) {
            Inputs.add(Main.gui.fightScene.victim.possibleKeys[rand.nextInt(Main.gui.fightScene.victim.possibleKeys.length)]);
            this.jestDis.add("?");
        }
        System.out.println("New Input List " + Inputs);
        updateInp();
    }

    public void updateInp() {
        this.removeAll();
        Ebility.updateInputList(Main.gui.fightScene.victim.name, this.Inputs, this.jestDis);
        this.validate();
        this.repaint();
    }

    public void startInp() {
        Main.gui.fightScene.foreIMG.setIcon(new ImageIcon(Main.cl.getResource("Icons/InpTilesIcon/FightInp.png").toString().substring(5)));
        this.jestDis = new ArrayList<>();
        Main.gui.fightScene.forePanel.removeAll();
        Main.gui.fightScene.forePanel.setLayout(null);
        Main.gui.fightScene.inpOn(true);
        Main.gui.fightScene.forePanel.validate();
        Main.gui.fightScene.forePanel.repaint();

        if (Main.gui.fightScene.curAbility instanceof Strike) {
            Main.gui.fightScene.InpPanel.generate(100);
        } else {
            Main.gui.fightScene.InpPanel.generate(Main.gui.fightScene.victim.inpSize);
        }

        JScrollPane scrollBar = new JScrollPane(Main.gui.fightScene.InpPanel);
        scrollBar.setBounds(0, 15, 896, 100);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.getViewport().setOpaque(false);
        scrollBar.setOpaque(false);
        scrollBar.setBorder(createEmptyBorder());
        Main.gui.fightScene.forePanel.add(scrollBar);
        Main.gui.fightScene.forePanel.validate();
        Main.gui.fightScene.time.setText(String.valueOf(Main.gui.fightScene.victim.timeLeft));
        Main.gui.fightScene.InpPanel.timer = new Timer(10, new ActionListener() {
            int counter = Main.gui.fightScene.victim.timeLeft;
            int timePast = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                timePast = Ebility.incrTime(Main.gui.fightScene.victim.name, timePast);

                counter -= timePast % 1000 == 0 ? 1 : 0;
                Main.gui.fightScene.time.setText(String.valueOf(counter));

                if (counter == 0 && timePast % 1000 == 0) {
                    Main.gui.fightScene.InpPanel.timer.stop();
                    // Main.gui.fightScene.stunActive[2] = 3;
                    Main.gui.fightScene.dealDMG(false);
                }
            }
        });
        Ebility.mathInp(Main.gui.fightScene.victim.name);
        Main.gui.fightScene.InpPanel.timer.setCoalesce(true);
        Main.gui.fightScene.InpPanel.timer.start();
        System.out.println(Main.gui.fightScene.InpPanel.timer);
    }

    public void paint(Graphics g) {
        super.paint(g); // paint background
        Graphics2D g2D = (Graphics2D) g;

        if (swapPos[0] > 0 && swapPos[1] > 0) {
            g2D.drawImage(swapinp, swapPos[0] * 100, 0, null);
            g2D.drawImage(swapinp, swapPos[1] * 100, 0, null);
        }

        // System.out.println(this.fading + " AnD also " + this.fadingperc);
        RescaleOp rop = new RescaleOp(new float[]{1f, 1f, 1f, this.fadingperc * 0.01f}, new float[4], null);
        try {
            if (Bossibility.DoodMoe && Main.boss.phase >= 3) {
                RescaleOp ropR = new RescaleOp(new float[]{1f, 1f, 1f, 1 - this.fadingperc * 0.01f}, new float[4], null);
                g2D.drawImage(ImageIO.read(new File(Main.cl.getResource("Icons/InpTilesIcon/GlassesL.png").toString().substring(5))), rop, 0, 0);
                g2D.drawImage(ImageIO.read(new File(Main.cl.getResource("Icons/InpTilesIcon/GlassesR.png").toString().substring(5))), ropR, 400, 0);
            } else {
                g2D.drawImage(ImageIO.read(new File(Main.cl.getResource("Icons/InpTilesIcon/Glasses.png").toString().substring(5))), rop, 0, 0);
            }
        } catch (Exception e) {
            System.out.println("THIS SHOULDNT EXISET");
        }

        g2D.dispose();
    }

}