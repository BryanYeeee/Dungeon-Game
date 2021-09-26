import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.awt.event.*;
import javax.swing.Timer;
import java.math.BigDecimal;
import javax.swing.*;

public class Player {
    int checkpoint;
    int strength;
    int health;
    int maxhp;
    BigDecimal worth;
    int x;
    int y;
    public boolean isAlive = true;
    public static Timer timer;//temptem,ptem,p
    public Inventory inv;
    int counter = 0;//temptemptemp
    public boolean boulPush;

    LinkedHashMap<String, Boolean> bestiary = new LinkedHashMap<String, Boolean>();

    public Block blck;
    public Burn burn;
    public Heal heal;
    public Heavy hevy;
    public Strike strk;
    public Stun stun;
    public Wait wait;

    public static String playericon = Main.cl.getResource("Icons/PlayerIcon.png").toString().substring(5);

    public Player(int strength, int health, double worth, int maxhp) {
        this.inv = new Inventory();
        this.blck = new Block();
        this.burn = new Burn();
        this.heal = new Heal();
        this.hevy = new Heavy();
        this.strk = new Strike();
        this.stun = new Stun();
        this.wait = new Wait();

        this.boulPush = false;
        this.strength = strength;
        this.health = health;
        this.worth = new BigDecimal(String.valueOf(worth));
        this.maxhp = maxhp;
        this.initBest();
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        return;
    }

    public void initBest() {
        ArrayList<String> enemyList = new ArrayList<String>(Arrays.asList("Neelan", "Prisoner", "Koala", "Clone", "Metadog", "Panda", "Penguin", "Octopus", "Dual", "Moe", "Jester", "Dood", "Rock"));
        this.bestiary = new LinkedHashMap<>();
        for (int i = 0; i < enemyList.size(); i++) {
            this.bestiary.put(enemyList.get(i), true);
        }
        return;
    }

    public boolean move(int movex, int movey, Level level) {
        try {
            System.out.println("DEWTED" + level.map[this.x + movex][this.y + movey].symbol);
            if (!Main.gui.godmode) {//CHANGE AFTER
                Object thing = level.map[this.x + movex][this.y + movey].object;
                switch (level.map[this.x + movex][this.y + movey].symbol) {
                    case "H":
                    case "^":
                    case ";":
                        return false;///////////////////////
                    case "$":
                    case "@":
                    case "!":
                    case "J":
                    case "<":
                    case "P":
                    case "*":
                    case "/":
                        System.out.println(Main.person.inv.items.size() + " " + Main.person.inv.items);
                        if (Main.person.inv.items.size() < Main.person.inv.invCap) {
                            ((Item) thing).pickUp();
                            level.map[this.x + movex][this.y + movey] = new Tile("-  ", "-", new String[1]);
                            break;//////////////////////////////
                        } else {
                            return false;
                        }
                    case "#":

                        ((Ladder) thing).nextLvl();

                        return true;////////////////////////

                    case "N":
                        if (Main.boss.phase == 0) {
                            break;
                        }
                    case "G":
                        System.out.println("I MPOVEDS A BOULDEr");
                        if (!((Boulder) thing).killable || Main.person.boulPush) {
                            if (((Boulder) thing).create(level, movex, movey)) {
                                return ((Boulder) thing).push(level, movex, movey);
                            }
                            return false;
                        }
//                    case "B":
                    case "F":
                    case "E":
                    case "8":
                    case "K":
                    case "2":
                    case "M":
                    case "D":
                    case "I":
                    case "%":
                    case "C":
                    case "?":
                        Enemy victim;
                        if (level.map[this.x + movex][this.y + movey].symbol.equals("G")) {
                            victim = new Enemy("Rock");
                            System.out.println("BOULDER BBBBBBBBBBBBBBBBBBB");
                            ((Boulder) thing).fight(level, movex, movey);
                            Main.gui.fightScene = new fightPanel(victim);
                            (Main.gui.fightScene).startFight((Main.gui).fightScene);
                            this.bestiary.put(victim.name, true);
                        } else {
                            if (Main.person.inv.items.size() < Main.person.inv.invCap || level.map[this.x + movex][this.y + movey].symbol.equals("N")) {
                                victim = ((Enemy) thing);
                                level.map[this.x + movex][this.y + movey] = new Tile("-  ", "-", new String[1]);
                                Main.gui.fightScene = new fightPanel(victim);
                                (Main.gui.fightScene).startFight((Main.gui).fightScene);
                                this.bestiary.put(victim.name, true);
                            }
                        }
                        if (Main.boss.phase == 1) {
                            Main.boss.enemiesKilled++;
                            System.out.println(Main.boss.enemiesKilled + " KILELD");
                            if (Main.boss.checkOne()) {
                                Main.arlofLevels.get(Main.currentLvl).map[this.x + movex][this.y + movey] = new Tile("/0 ", "/", new String[]{"0"});
                                Main.boss.phase = 2;
                            }
                        }
                        return false;///////////////////////////

                    case "R":
                    case "S":
                        Shop curShop = ((Shop) thing);
                        Main.gui.shopScene = new shopGUI(curShop);
                        (Main.gui.shopScene).startShop((Main.gui).shopScene);

                        ((Shop) thing).outputShop();
                        return false;/////////////////////////////

                    case "|":
                        if (this.inv.containsKey(((Door) thing).id)) {
                            level.map[this.x + movex][this.y + movey] = new Tile("-  ", "-", new String[1]);
                        } else {
                            System.out.println("You need a key");
                            return false;
                        }
                        break;
                    case "L":
                        level.outputMap();
                        ((Switch) thing).use();
                        System.out.println("USED SWITCH");
                        return false;//////////////////////////

                    case "U":
                        if (!(((Teleporter) thing).x == this.x && ((Teleporter) thing).y == this.y)) {

                            // level.map[((Teleporter)thing).x][((Teleporter)thing).y] = level.map[this.x][this.y];
                            level.map[this.x][this.y] = new Tile("-  ", "-", new String[1]);
                            this.setLocation(((Teleporter) thing).x, ((Teleporter) thing).y);
                            Main.gui.map.x = Main.person.x * 100;
                            Main.gui.map.y = Main.person.y * 100;
                            Main.gui.scrollToPlayer();
                            Main.gui.map.repaint();

                            return true;
                        }
                        return false;////////////////////////////////////

                    case "V":

                        ActuallyMove(movex, movey);
                        ((Slider) thing).slide();
                        return false;/////////////////////

                    case "+":

                        Main.gui.map.toggleMove(false);
                        ((OmniSlider) thing).slide(movex, movey, movex, movey);


                        return true;//////////////////////////////////////////

                    case "=":
                        ((Stopper) thing).tostopper(movex, movey);
                        return false;///////////////////////////////

                    case "X":
                        Guide.speak();
                        return false;///////////////////////////////////

                    case "&":
                        Cipher curCipher = ((Cipher) thing);
                        Main.gui.ciphScene = new cipherGUI(curCipher);
                        (Main.gui.ciphScene).startCipher((Main.gui).ciphScene);
                        level.map[this.x + movex][this.y + movey] = new Tile("-  ", "-", new String[1]);
                        return false;

                    case "0":

                        ((Portal) thing).reset();

                        return true;
                    case "Y":

                        this.setLocation(Main.arlofLevels.get(Main.currentLvl).startx, Main.arlofLevels.get(Main.currentLvl).starty);
                        Main.gui.map.x = Main.person.x * 100;
                        Main.gui.map.y = Main.person.y * 100;
                        Main.gui.scrollToPlayer();
                        Main.gui.map.repaint();

                        return true;
                    case "7":
                        Main.gui.saveScene = new saveGUI();
                        (Main.gui.saveScene).openSave();

                        return false;
                    case "~":
                        ((theSomething) thing).doSomething();
                        level.map[this.x + movex][this.y + movey] = new Tile("-  ", "-", new String[1]);
                        return true;
                    case "T":
                        ((Npc) thing).startDiag();
                        return false;

                }
            }


            //swapTiles(movex,movey,level);
            Main.gui.ActuallyGUI(movex, movey, false);
            ActuallyMove(movex, movey);

            return true;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("Error in Move: " + e);
            return false;
        }
    }

    public void swapTiles(int movex, int movey, Level level) {
        Tile tempTile = level.map[this.x + movex][this.y + movey]; //swapping tiles to move
        level.map[this.x + movex][this.y + movey] = level.map[this.x][this.y];
        level.map[this.x][this.y] = tempTile;

        ActuallyMove(movex, movey);


    }

    public void ActuallyMove(int movex, int movey) {
        Tile tempTile = (Main.arlofPlayerLayer.get(Main.currentLvl))[this.x + movex][this.y + movey]; //PLAYER IS MOVEING
        (Main.arlofPlayerLayer.get(Main.currentLvl))[this.x + movex][this.y + movey] = (Main.arlofPlayerLayer.get(Main.currentLvl))[this.x][this.y];
        (Main.arlofPlayerLayer.get(Main.currentLvl))[this.x][this.y] = tempTile;
        this.setLocation(this.x + movex, this.y + movey);
        System.out.println("plaerylocat" + this.x + "" + this.y);
    }

    public void toggleAbils() {
        this.strk.unlocked = !this.strk.unlocked;
        this.heal.unlocked = !this.heal.unlocked;
        this.hevy.unlocked = !this.hevy.unlocked;
        this.stun.unlocked = !this.stun.unlocked;
        this.burn.unlocked = !this.burn.unlocked;
        this.blck.unlocked = !this.blck.unlocked;
    }


}
