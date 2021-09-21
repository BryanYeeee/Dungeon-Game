import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.util.Random;
import java.awt.*;

public class Dood {
    public int phase;
    public int enemiesKilled;
    public int fadeperc = 100;
    public Timer timer;
    Random rand = new Random();

    public Dood() {
        this.phase = 0;//CHANG THIS
        this.enemiesKilled = 0;
    }

    public void phaseOne(String dif, int amount) {
        System.out.println("STARTIN PHASE ONE");

        ArrayList<String> possibleEnemy = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int x = rand.nextInt(7);
            int y = rand.nextInt(10);
            while (!Main.arlofLevels.get(Main.currentLvl).map[x][y].symbol.equals("-") || (x == Main.person.x && y == Main.person.y)) {
                System.out.println("STUCK IN WHIL LOP" + x + " " + y + " " + Main.arlofLevels.get(Main.currentLvl).map[x][y].symbol);
                x = rand.nextInt(7);
                y = rand.nextInt(10);
            }
            switch (dif) {
                case "Weak":
                    possibleEnemy = new ArrayList<String>(Arrays.asList("2", "K", "M"));
                    break;
                case "Medium":
                    possibleEnemy = new ArrayList<String>(Arrays.asList("8", "I", "C"));
                    break;
                case "Strong":
                    possibleEnemy = new ArrayList<String>(Arrays.asList("D", "%", "?"));
                    break;
            }
            String chosenEnemy = possibleEnemy.get(rand.nextInt(3));
            Main.arlofLevels.get(Main.currentLvl).map[x][y] = new Tile(chosenEnemy + "  ", chosenEnemy, new String[]{" "});
        }
        Main.gui.map.updateMap();
    }

    public boolean checkOne() {
        if (enemiesKilled == 10) {
            this.phaseOne("Medium", 5);
        } else if (enemiesKilled == 15) {
            this.phaseOne("Strong", 3);
        } else if (enemiesKilled == 18) {
            Main.person.inv.addItem(new Key(1));
            Main.arlofLevels.get(Main.currentLvl).map[3][8] = new Tile("/1 ", "/", new String[]{"1"});//later make a note saying im evil or smth
            return true;
        }
        return false;
    }

    public void checkTwo() {
        if (Main.gui.fightScene.victim.health <= 0 && Main.boss.phase == 2 && Main.gui.fightScene.victim.name.equals("Dood")) {//GET RID OF AFTER\
            while (true) {
                System.out.println("NOT ALLOWED");
            }
        }
        if (Main.gui.fightScene.victim.health <= 75 && Main.boss.phase == 2 && Main.gui.fightScene.victim.name.equals("Dood")) {//INCREASE LATR
            Main.boss.phase = 3;
        }
    }

    public void checkThree() {
        if (Main.gui.fightScene.victim.health <= 0 && Main.boss.phase == 3) {
            Main.boss.phase = 4;
            Main.gui.fightScene.victim.health = 1;
            Main.gui.fightScene.optionPanel.removeAll();
            Main.gui.fightScene.optionPanel.setLayout(new GridLayout());
            Main.gui.fightScene.optionPanel.init();
            Main.gui.fightScene.optionPanel.validate();
            Main.gui.fightScene.optionPanel.repaint();
            Main.gui.fightScene.victim.status.clear();
        }
    }

    public void checkFour() {
        if (Main.gui.fightScene.victim.health <= 0 && Main.boss.phase == 4) {
            Main.boss.death();
        }
    }

    public void death() {
        for (int i = 2; i < 5; i++) {
            Main.arlofLevels.get(Main.currentLvl).map[i][12] = new Tile("-  ", "-", new String[]{""});
            Main.arlofLevels.get(Main.currentLvl).map[i][13] = new Tile("-  ", "-", new String[]{""});
            Main.arlofLevels.get(Main.currentLvl).map[i][14] = new Tile("-  ", "-", new String[]{""});
        }
        Main.arlofLevels.get(Main.currentLvl).map[3][13] = new Tile("-  ", "/", new String[]{"3"});
    }

    public void fadeOut(int x, int y) {
        Main.gui.map.toggleMove(false);//THIS NO WORK
        Main.boss.fadeperc = 100;
        this.timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.boss.fadeperc > 0) {
                    Main.boss.fadeperc--;
                    Main.gui.map.Panels[x][y].repaint();
                } else {
                    Main.arlofLevels.get(Main.currentLvl).map[x][y] = new Tile("-  ", "-", new String[1]);
                    Main.gui.map.toggleMove(true);
                    Main.boss.timer.stop();
                }

            }
        });
        this.timer.setCoalesce(true);
        this.timer.start();
    }
}
