import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;
import java.math.BigDecimal;

public class fightPanel extends JLayeredPane {
    public InpList InpPanel;
    public JPanel forePanel;
    public fightOption optionPanel;
    public enemyDetails detailsPanel;
    public JLabel enemy = new JLabel();
    public JPanel statPanel = new JPanel();
    public JLabel time = new JLabel();
    public HashMap<String, String> actHm = new HashMap<String, String>();
    public int totalErrors = 0;
    public int errors = 0;
    public boolean defeated;
    public contPanel cont = new contPanel();
    public ArrayList<inpAction> arlofActions = new ArrayList<>();
    public jesterGUI quiztime = new jesterGUI();

    public JLabel foreIMG = new JLabel();
    public ImageIcon foreOpt;
    public static Enemy victim;
    public Ability curAbility;

    public fightPanel(Enemy victim) {
        this.setLayout(null);
        this.setBounds(0, 0, 896, 640);
        this.victim = victim;
        this.actHm = victim.actHm;
        ;
        this.defeated = false;
        this.totalErrors = 0;

        this.forePanel = new JPanel();
        this.InpPanel = new InpList();
        this.optionPanel = new fightOption();
        this.updateStatLabel();
        this.time = new JLabel();
        detailsPanel = new enemyDetails(victim.abilities, victim.name);
        time.setHorizontalAlignment(SwingConstants.CENTER);
        forePanel.setLayout(null);
        forePanel.setOpaque(false);
        statPanel.setOpaque(false);

        enemy.setIcon(Main.setImageSize(victim.dir, 256, 256));
        enemy.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel player = new JLabel();
        player.setIcon(Main.setImageSize(Main.person.playericon, 256, 256));
        player.setHorizontalAlignment(SwingConstants.CENTER);

        quiztime.setBounds(256, 128, 384, 192);
        forePanel.setBounds(0, 484, 896, 156);
        time.setBounds(388, 0, 120, 105);
        enemy.setBounds(640, 128, 256, 256);
        statPanel.setBounds(0, 0, 895, 105);
        player.setBounds(0, 128, 256, 256);

        JLabel backIMG = new JLabel();
        backIMG.setIcon(new ImageIcon(Main.cl.getResource("Icons/FightIcon/FightBG" + (new Random()).nextInt(2) + ".png").toString().substring(5)));
        backIMG.setBounds(0, 0, 896, 446);
        this.add(backIMG, Integer.valueOf(0));

        foreIMG = new JLabel();
        foreIMG.setIcon(new ImageIcon(Main.cl.getResource("Icons/FightIcon/FightOpt.png").toString().substring(5)));
        foreIMG.setBounds(0, 446, 896, 194);
        this.add(foreIMG, Integer.valueOf(0));

        this.add(this.detailsPanel, Integer.valueOf(2));
        this.add(this.forePanel, Integer.valueOf(1));
        this.add(this.time, Integer.valueOf(1));
        this.add(this.enemy, Integer.valueOf(1));
        this.add(player, Integer.valueOf(1));
        this.add(this.statPanel, Integer.valueOf(1));
    }

    public void updateStatLabel() {
        this.statPanel.removeAll();
        this.statPanel.setLayout(null);
        JLabel victhp = new JLabel(String.valueOf(this.victim.health));
        victhp.setBounds(712, 10, 94, 85);
        victhp.setHorizontalAlignment(SwingConstants.CENTER);
        victhp.setForeground(Color.WHITE);
        JLabel errors = new JLabel(String.valueOf(this.errors));
        errors.setBounds(269, 10, 99, 85);
        errors.setHorizontalAlignment(SwingConstants.CENTER);
        errors.setForeground(Color.WHITE);
        JLabel playhp = new JLabel(String.valueOf(Main.person.health));
        playhp.setBounds(90, 10, 94, 85);
        playhp.setHorizontalAlignment(SwingConstants.CENTER);
        playhp.setForeground(Color.WHITE);

        this.statPanel.add(victhp);
        this.statPanel.add(errors);
        this.statPanel.add(playhp);
        this.statPanel.validate();
        this.statPanel.repaint();
        return;
    }

    public void inpOn(boolean onoroff) {
        for (int i = 0; i < arlofActions.size(); i++) {
            if (onoroff) {
                arlofActions.get(i).turnOn();
            } else {
                arlofActions.get(i).turnOff();
            }
        }
    }

    public void nextTile() {
        this.InpPanel.tileLeft--;
        this.InpPanel.Inputs = Ebility.iterInp(this.victim.name, this.InpPanel.Inputs);
        InpPanel.updateInp();
        // System.out.println("Keys Left:" + InpPanel.Inputs);
        if (InpPanel.Inputs.get(0).equals(" ")) {
            dealDMG(false);
        } else {
            Ebility.mathInp(this.victim.name);
        }
        return;
    }

    public void dealDMG(boolean skip) {
        Main.gui.fightScene.quiztime.remove();
        Main.gui.fightScene.InpPanel.fadingperc = 0;
        Main.gui.fightScene.InpPanel.swapPos = new int[]{0, 0};
        Main.gui.fightScene.inpOn(false);
        Main.gui.fightScene.time.setText("");
        System.out.println(Main.gui.fightScene.InpPanel.timer);

        System.out.println(this.InpPanel.Inputs);

        Main.gui.fightScene.InpPanel.timer.stop();
        System.out.println("TI(MER STOPS ");

        //CALCULATE DEAL DMG
        int damage = (this.optionPanel.dmg * (this.InpPanel.Inputs.size() - this.InpPanel.tileLeft) / this.InpPanel.Inputs.size() - (this.errors));

        //CHECK FOR ABILITY
        if (this.curAbility != null) {
            damage = this.curAbility.useAbil(this.optionPanel.dmg, this.victim, this.errors, this);
            this.curAbility = null;
        }

        //CALCULATE TAKE DMG
        int takeDamage = Ebility.takeDMG(this.victim.name, this.victim.strength, this.errors);

        //CHECK STATUS
        int[] dmgValues = this.checkStatus(damage, takeDamage);
        damage = dmgValues[0];
        takeDamage = dmgValues[1];

        //SUBTRACT ENEMY HEATH UNLESS BOSS PHASE 4
        damage *= Main.boss.phase == 4 && this.InpPanel.tileLeft > 0 ? 0 : 1;//doesnt work with skip

        //SKIP + NEGATIVE DMG CHECK
        damage = skip ? 99999 : (damage < 0 ? 0 : damage);

        victim.health -= damage;
        this.cont.addTxt(damage + " damage dealt");

        //SUBTRACT PLAYER HEALTH + Boss PHASE CHANGE
        Main.boss.checkTwo();
        Main.boss.checkThree();
        Main.boss.checkFour();
        if (!this.checkDie()) {
            takeDamage = Main.boss.phase == 4 && this.InpPanel.tileLeft > 0 ? Main.person.health - 1 : takeDamage;
            Main.person.health -= takeDamage;
            this.cont.addTxt(takeDamage + " dmg taken");
            this.checkDie();
        }

        this.cont.startCont();
        this.errors = 0;
        if (this.victim.name.equals("Dood")) {
            Bossibility.nextATK();
        }
        this.updateStatLabel();
        this.detailsPanel.updateDetails(this.victim.abilities, this.victim.name);
    }

    public boolean checkDie() {
        if (this.victim.health <= 0) {
            this.defeated = true;
            this.cont.addTxt(victim.richness + " coins earned, ");
            Main.person.worth = Main.person.worth.add(new BigDecimal(String.valueOf(victim.richness)));
            if (victim.keyDrop > 0) {
                Main.person.inv.addItem(new Key(victim.keyDrop));
                cont.addTxt("key <" + victim.keyDrop + "> earned");
            }
            return true;
        } else if (Main.person.health <= 0) { //<=0
            System.out.println("ENEMY KILLED U");
            GUI.close();
            Main.gui.deathScene.die(Main.person.checkpoint, "You got killed by a " + victim.name);
            return true;
        }
        return false;
    }

    public int[] checkStatus(int damage, int takeDamage) {
        int[] dmgValues = {damage, takeDamage};
        Iterator hmIterator = this.victim.status.entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hmIterator.next();
            String key = (String) mapElement.getKey();
            int value = ((Integer) mapElement.getValue()).intValue();
            switch (key) {
                case "blck":
                    this.cont.addTxt("BLOCKED " + takeDamage + " Damage");
                    dmgValues[1] = 0;
                    hmIterator.remove();
                    break;

                case "stun":
                    this.cont.addTxt("You crippled " + this.victim.name + "'s feelings'");
                    dmgValues[1] = 0;
                    this.victim.status.put("stun", value - 1);
                    if (value <= 1) {
                        hmIterator.remove();
                    }
                    break;

                case "burn": // Lil'Oddbutok
                    this.cont.addTxt("You cook " + this.victim.name + " and bited " + Main.person.burn.dmgBurn + " times");
                    this.victim.health -= Main.person.burn.dmgBurn;
                    this.victim.status.put("burn", value - 1);
                    if (value <= 1) {
                        hmIterator.remove();
                    }
                    break;

                case "targetted":
                    this.cont.addTxt((value <= 1 ? "chARGE ATtack USED" : "CHARGIN ATTACK"));
                    this.victim.status.put("targetted", value - 1);
                    dmgValues[0] = 0;
                    if (value <= 1) {
                        dmgValues[0] = (this.victim.health / 10) + Main.person.hevy.dmgStore * 3;
                        hmIterator.remove();
                    }
                    break;
            }
        }
        return dmgValues;
    }

    public boolean checkInput(String input) {
        return Main.gui.fightScene.actHm.get(input).equals(Main.gui.fightScene.InpPanel.Inputs.get(0));
    }

    public void startFight(fightPanel fightScene) {
        GUI.toggleInput(false, false);

        Main.gui.mainPanel.removeAll();
        Main.gui.map.toggleMove(false);
        Main.gui.mainPanel.add(fightScene);
        Main.gui.fightScene.validate();
        Main.gui.fightScene.repaint();
        Main.gui.mainPanel.validate();
        Main.gui.mainPanel.repaint();
        Main.gui.frame.validate();
        Main.gui.frame.repaint();
        this.optionPanel.startOption();

        if (this.victim.name.equals("Dood")) {
            Bossibility.nextATK();
        }

        for (int i = 0; i < victim.possibleKeys.length; i++) {
            arlofActions.add(new inpAction(victim.possibleKeys[i]));
        }

    }
}
