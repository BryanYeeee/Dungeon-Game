import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.Timer;
import java.math.BigDecimal;


public class GUI { 

    public mapGUI map;
//    public Dimension mapSize = new Dimension(500, 500);
    public JScrollPane scrollBar;
    public JFrame frame;
    public fightPanel fightScene;
    public shopGUI shopScene;
    public cipherGUI ciphScene;
    public deathGUI deathScene;
    public invGUI invScene;
    public JTextArea box;
    public JPanel mainPanel;
    public JLayeredPane gamePanel;
    public statGUI stats;
    public startingGUI start;
    public loadGUI loadScene;
    public saveGUI saveScene;
    public dialogueGUI diagScene;
    public static Timer timer;
    public static boolean fastmode = true;//TOGGLE
    public static boolean godmode = false;//TOGGLE

    public static boolean toggleInv = false;

    public GUI() { //LAYERED PANE NO WORK WHEN BORDERLAYOUT WEST
        this.frame = new JFrame("TRAPINELANBASMET");
        System.out.println("CRETaD ED A NEW FRAME");
        this.frame.setSize(909, 670);//896, 640
        System.out.println(this.frame.getSize());
    }

    public void startMenu() {
        Main.gui.start = new startingGUI();
        Main.gui.loadScene = new loadGUI();
        Main.gui.saveScene = new saveGUI();
        Main.gui.mainPanel = new JPanel();
        Main.gui.mainPanel.add(start, BorderLayout.CENTER);

        Main.gui.frame.add(Main.gui.mainPanel);
        Main.gui.frame.setVisible(true);

        toggleInput(false, false);
    }

    public void initGUI() {
        Main.gui.frame.setLayout(new BorderLayout());

        Main.gui.scrollBar = new JScrollPane();
        Main.gui.mainPanel = new JPanel();
        Main.gui.stats = new statGUI();
        Main.gui.gamePanel = new JLayeredPane();
        Main.gui.invScene = new invGUI();
        map = new mapGUI();
        Main.gui.map.plup = new moveAction(-1, 0, "W");
        Main.gui.map.plleft = new moveAction(0, -1, "A");
        Main.gui.map.pldown = new moveAction(1, 0, "S");
        Main.gui.map.plright = new moveAction(0, 1, "D");
        Main.gui.map.toggleMove(true);

        // showDialogue("NEELAN.TXT");
        Main.gui.frame.setBackground(Color.GREEN);
        Main.gui.mainPanel.setLayout(new BorderLayout());
        Main.gui.gamePanel.setLayout(null);
        Main.gui.gamePanel.setVisible(true);
        // Main.gui.gamePanel.setPreferredSize(new Dimension(700, 500));
        Main.gui.mainPanel.setBackground(Color.CYAN);

        Main.gui.frame.add(Main.gui.mainPanel, BorderLayout.CENTER);
        Main.gui.gamePanel.setBackground(Color.YELLOW);
        Main.gui.mainPanel.add(Main.gui.gamePanel, BorderLayout.CENTER);

        updateBar();

        Main.gui.scrollToPlayer();
        toggleInput(true, false);


        Main.gui.map.toggleMove(true);
        // Main.gui.mainPanel.add(stats, BorderLayout.SOUTH);
        Main.gui.mainPanel.validate();
        Main.gui.mainPanel.repaint();
        Main.gui.frame.validate();
        Main.gui.frame.repaint();

    }

    public void changeScreen(String screen) {
        Main.gui.mainPanel.removeAll();
        switch (screen) {
            case "start":
                Main.gui.mainPanel.add(Main.gui.start);
                break;
            case "load":
                Main.gui.mainPanel.add(Main.gui.loadScene);
                break;
        }
        Main.gui.mainPanel.validate();
        Main.gui.mainPanel.repaint();
    }

    public static void toggleInput(boolean yes, boolean esc) {
        if (yes) {
            Main.gui.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "reset");
            Main.gui.frame.getRootPane().getActionMap().put("reset", reset);
            Main.gui.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("T"), "hack");
            Main.gui.frame.getRootPane().getActionMap().put("hack", hack);
            Main.gui.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"), "openinv");
            Main.gui.frame.getRootPane().getActionMap().put("openinv", openinv);
            //TEMP
            Main.gui.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("M"), "savegame");
            Main.gui.frame.getRootPane().getActionMap().put("savegame", save);
            Main.gui.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("N"), "loadgame");
            Main.gui.frame.getRootPane().getActionMap().put("loadgame", load);

        } else {
            Main.gui.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke("T"));
            Main.gui.frame.getRootPane().getActionMap().remove("hack");
            Main.gui.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke("E"));
            Main.gui.frame.getRootPane().getActionMap().remove("openinv");
            Main.gui.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke("R"));
            Main.gui.frame.getRootPane().getActionMap().remove("reset");
        }
        if (esc) {
            Main.gui.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("G"), "escape");
            Main.gui.frame.getRootPane().getActionMap().put("escape", escapeMAP);

        } else {
            Main.gui.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke("G"));
            Main.gui.frame.getRootPane().getActionMap().remove("escape");
        }
    }

    public static void updateBar() {
        Main.gui.scrollBar = new JScrollPane(Main.gui.map);//PUT THIS BACK ONE DAY
        Main.gui.scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        Main.gui.scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Main.gui.scrollBar.setBorder(null);
        Main.gui.gamePanel.removeAll();
        Main.gui.gamePanel.setLayout(null);

        // Main.gui.scrollBar.setBounds(0,0,Math.min(Main.arlofLevels.get(Main.currentLvl).map[0].length*100,700),Math.min(Main.arlofLevels.get(Main.currentLvl).Main.gui.map.length*100,500));
        // System.out.println("WIDTH:" + Math.min(Main.arlofLevels.get(Main.currentLvl).Main.gui.map.length*100,700));
        // System.out.println("HEIGHT:" + Math.min(Main.arlofLevels.get(Main.currentLvl).map[0].length*100,500));
        // Main.gui.scrollBar.setPreferredSize(new Dimension(Math.min(Main.arlofLevels.get(Main.currentLvl).map[0].length*100,700),Math.min(Main.arlofLevels.get(Main.currentLvl).Main.gui.map.length*100,500)));
        // Main.gui.mainPanel.setSize(new Dimension(Math.min(Main.arlofLevels.get(Main.currentLvl).map[0].length*100,700),Math.min(Main.arlofLevels.get(Main.currentLvl).Main.gui.map.length*100,500)));

        Main.gui.scrollBar.setBounds(0, 0, 896, 640);
        Main.gui.scrollBar.setPreferredSize(new Dimension(896, 640));
        Main.gui.gamePanel.add(Main.gui.scrollBar, Integer.valueOf(0));
        Main.gui.mainPanel.validate();
        Main.gui.mainPanel.repaint();
    }

    int counter;

    public void ActuallyGUI(int movex, int movey) {
        System.out.println(this.frame.getSize());

        Main.gui.map.toggleMove(false);

        if (fastmode) {
            counter = 128;
        } else {
            counter = 0;
        }
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                if (counter < 128) {
                    if (Main.gui.map.curBould != null) {
                        System.out.println("CUR BOUOLD:  " + Main.gui.map.curBould.mx + Main.gui.map.curBould.my);
                        Main.gui.map.curBould.mx += movex;
                        Main.gui.map.curBould.my += movey;
                        //Main.gui.map.repaint();
                    } else {
                        Main.gui.map.x += Main.gui.map.xVelocity * movex;
                        Main.gui.map.y += Main.gui.map.yVelocity * movey;
                        // Main.gui.map.repaint();
                    }
                    Main.gui.scrollToPlayer();
                    Main.gui.map.repaint();

                    counter++;
                } else {
                    timer.stop();
                    counter = 0;
                    Main.gui.map.toggleMove(true);
                    Main.person.move(movex, movey, Main.arlofLevels.get(Main.currentLvl), true);
                    if (Main.gui.map.curBould != null) {
                        // Main.arlofLevels.get(Main.currentLvl).map[Main.person.x+movex*2][Main.person.y+movey*2].dir.replaceAll(".png", "");
                        // Main.arlofLevels.get(Main.currentLvl).map[Main.person.x+movex*2][Main.person.y+movey*2].dir = Main.gui.map.curBould.done ?
                        // Main.gui.map.curBould.dir+"Done.png" : Main.gui.map.curBould.dir+".png";

                        Main.gui.map.curBould = null;
                        Main.gui.map.repaint();
                    } else {
                        Main.gui.map.x = Main.person.x * 128;
                        Main.gui.map.y = Main.person.y * 128;
                    }
                    System.out.println("move back on");
                    Main.gui.map.repaint();
                    Main.gui.map.updateMap();
                    Main.gui.scrollToPlayer();

                }

            }
        });
        timer.setCoalesce(true);
        timer.start();
        Main.gui.afterMove();

    }

    public static void scrollToPlayer() {

        if (Main.gui.map.y - 384 <= 0) {
            Main.gui.scrollBar.getHorizontalScrollBar().setValue(1);
            //System.out.println("FAR LEFT");
        } else {
            Main.gui.scrollBar.getHorizontalScrollBar().setValue(Main.gui.map.y - 384);
            //System.out.println("MIDDLE x"+ (Main.gui.map.y));
        }
        if (Main.gui.map.x - 256 <= 0) {
            Main.gui.scrollBar.getVerticalScrollBar().setValue(1);
            //System.out.println("VERY TOP");
        } else {
            Main.gui.scrollBar.getVerticalScrollBar().setValue(Main.gui.map.x - 256);
            //System.out.println("Middle y: " + (Main.gui.map.x));
        }

    }

    public static void showDialogue(String dialogue) {
        System.out.println(dialogue);
        if (Main.gui.box != null) {
            Main.gui.frame.remove(Main.gui.box);
        }

        Main.gui.box = new JTextArea(dialogue);
        Main.gui.box.setEditable(false);
        Main.gui.box.setBounds(500, 0, 800, 100);
        Main.gui.box.setFont(new Font("Comic Sans", 50, 50));
        Main.gui.frame.add(Main.gui.box, BorderLayout.SOUTH);
        Main.gui.frame.validate();
        Main.gui.frame.repaint();
    }

    public static void afterMove() {
        Main.gui.map.updateMap();
        //Main.gui.scrollToPlayer();

        if (!Main.person.isAlive) { //dead
            System.out.println("YOU DIED");
            Main.person.worth = new BigDecimal("0");
            Main.currentLvl = 0;
            Main.person.setLocation(Main.arlofLevels.get(Main.currentLvl).startx, Main.arlofLevels.get(Main.currentLvl).starty);
            Main.gui.scrollToPlayer();
            Main.person.inv.clearKeys();
        }
        // System.out.println("{" + Main.arlofLevels.get(Main.currentLvl).text + "}");
        //System.out.println("Level: " + Main.arlofLevels.get(Main.currentLvl).levelnum);
        //System.out.println("[Health: " + Main.person.health + "|Strength: " + Main.person.strength + "|Money: " + Main.person.worth + "]");
        //System.out.println("--"+Main.person.inv.items+"--");
        //Main.arlofLevels.get(Main.currentLvl).outputMap();
        Main.gui.frame.validate();
        Main.gui.frame.repaint();

    }

    public static void resetLevel() {
        Main.startGame();
    }

    public static void close() {
        updateBar();
        toggleInput(true, false);
        Main.gui.map.toggleMove(true);
        Main.gui.mainPanel.removeAll();
        Main.gui.mainPanel.setLayout(new BorderLayout());
        Main.gui.mainPanel.add(Main.gui.gamePanel, BorderLayout.CENTER);
        Main.gui.map.updateMap();
        Main.gui.mainPanel.validate();
        Main.gui.mainPanel.repaint();
        Main.gui.frame.validate();
        Main.gui.frame.repaint();
    }

    public static void hacking() {
        JTextField health = new JTextField(7);
        JTextField strength = new JTextField(7);
        JTextField worth = new JTextField(7);
        JTextField curlvl = new JTextField(2);
        JTextField fast = new JTextField(1);
        JTextField god = new JTextField(0);

        JButton done = new JButton("done");
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Main.person.health = Integer.parseInt(health.getText());
                } catch (NumberFormatException e) {
                    System.out.println("WRONG INPUIT");
                }
                try {
                    Main.person.strength = Integer.parseInt(strength.getText());
                } catch (NumberFormatException e) {
                    System.out.println("WRONG INPUIT");
                }
                try {
                    Main.person.worth = new BigDecimal(worth.getText());
                } catch (NumberFormatException e) {
                    System.out.println("WRONG INPUIT");
                }
                try {
                    Main.currentLvl = Integer.parseInt(curlvl.getText());
                    Main.person.setLocation(Main.arlofLevels.get(Main.currentLvl).startx, Main.arlofLevels.get(Main.currentLvl).starty);

                } catch (NumberFormatException e) {
                    System.out.println("WRONG INPUIT");
                }
                try {
                    if (Integer.parseInt(fast.getText()) == 0) {
                        fastmode = false;
                    } else {
                        fastmode = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("WRONG INPUIT");
                }
                try {
                    if (Integer.parseInt(god.getText()) == 0) {
                        godmode = false;
                    } else {
                        godmode = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("WRONG INPUIT");
                }
                Main.gui.map.updateMap();
                close();

            }
        });

        Main.gui.mainPanel.removeAll();
        Main.gui.mainPanel.setLayout(new GridLayout(2, 6));


        Main.gui.mainPanel.add(new JLabel("health"));
        Main.gui.mainPanel.add(new JLabel("stre"));
        Main.gui.mainPanel.add(new JLabel("worth"));
        Main.gui.mainPanel.add(new JLabel("curlvl"));
        Main.gui.mainPanel.add(new JLabel("fast?"));
        Main.gui.mainPanel.add(new JLabel("god?"));
        Main.gui.mainPanel.add(done);
        Main.gui.mainPanel.add(health);
        Main.gui.mainPanel.add(strength);
        Main.gui.mainPanel.add(worth);
        Main.gui.mainPanel.add(curlvl);
        Main.gui.mainPanel.add(fast);
        Main.gui.mainPanel.add(god);


        Main.gui.mainPanel.validate();
        Main.gui.mainPanel.repaint();
    }

    static Action reset = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            resetLevel();
        }
    };
    static Action hack = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            hacking();
        }
    };
    static Action escapeMAP = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            System.out.println("ESCAPE");
            close();

        }
    };
    static Action escapeSTART = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            System.out.println("ESCAPE");
            Main.gui.start = new startingGUI();
            Main.gui.mainPanel = new JPanel();
            Main.gui.mainPanel.add(Main.gui.start, BorderLayout.CENTER);
            toggleInput(false, false);

        }
    };
    static Action save = new AbstractAction() { //TEMP
        public void actionPerformed(ActionEvent e) {
            System.out.println("save");
            Main.saveGame(1);
        }
    };
    static Action load = new AbstractAction() { //TEMP
        public void actionPerformed(ActionEvent e) {
            System.out.println("load");
            Main.loadGame(1);
        }
    };
    static Action openstats = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            if (Main.gui.mainPanel.getComponent(0) == Main.gui.scrollBar) {


                try {
                    System.out.println(Main.gui.mainPanel.getComponent(1));
                    Main.gui.mainPanel.remove(Main.gui.stats);

                } catch (Exception f) {
                    Main.gui.mainPanel.add(Main.gui.stats, BorderLayout.SOUTH);

                }
            }
            Main.gui.mainPanel.repaint();
            Main.gui.mainPanel.validate();
            Main.gui.frame.validate();
            Main.gui.frame.repaint();
        }
    };

    static Action openinv = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            if (!toggleInv) {
                Main.gui.gamePanel.add(Main.gui.invScene, Integer.valueOf(1));
                Main.gui.map.toggleMove(false);
                Main.gui.invScene.menuDir = Main.cl.getResource("Icons/InventoryIcon/Inv/ItemButton.png").toString().substring(5);
                Main.gui.invScene.updateInv();
                Main.gui.invScene.item.addScreen();
                toggleInv = true;
            } else {
                Main.gui.gamePanel.remove(Main.gui.invScene);
                Main.gui.invScene.closeInv();
                Main.gui.map.toggleMove(true);
                toggleInv = false;

            }
            Main.gui.gamePanel.repaint();
            Main.gui.gamePanel.validate();
            Main.gui.frame.validate();
            Main.gui.frame.repaint();
        }
    };

}