import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.math.BigDecimal;

public class fightOption extends JPanel {

    public int dmg;
    public double weaponBonus;

    public fightOption() {
        super(new GridLayout());
        this.init();
    }

    public void init() {
        System.out.println("fightop iotn init");
        // this.setBackground(Color.blue);
        this.setOpaque(false);
        this.setLayout(new GridLayout());
        this.setBounds(50, 0, 796, 156);
        ((GridLayout) this.getLayout()).setHgap(20);
        this.atkBut();
        if (Main.boss.phase != 4) {
            this.abilBut();
            //this.itemBut (not put in yet)
            this.add(new JButton("ITEM"));
        }
        this.skipBut();
    }

    public void skipBut() {
        JButton skip = new JButton("skip");
        skip = makeButton(skip);
        skip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.gui.fightScene.InpPanel.startInp();
                Main.gui.fightScene.dealDMG(true);
            }
        });
        this.add(skip);
    }

    public void atkBut() {
        JButton attack = new JButton("");
        attack = makeButton(attack);
        attack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.gui.fightScene.foreIMG.setIcon(new ImageIcon(Main.cl.getResource("Icons/FightIcon/FightAtk.png").toString().substring(5)));
                Main.gui.fightScene.foreOpt = (ImageIcon)Main.gui.fightScene.foreIMG.getIcon();
                Main.gui.fightScene.optionPanel.removeAll();
                Main.gui.fightScene.optionPanel.setLayout(new GridLayout());
                ((GridLayout) Main.gui.fightScene.optionPanel.getLayout()).setHgap(20);
                JButton sword = new JButton("sword");
                sword = makeButton(sword);
                JButton spell = new JButton("magic");
                spell = makeButton(spell);
                JButton hammer = new JButton("hammer");
                hammer = makeButton(hammer);
                sword.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("ATTACKING WITH SWORD");
                        attack(Main.person.inv.sword);
                    }
                });
                spell.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        attack(Main.person.inv.spell);
                    }
                });
                hammer.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        attack(Main.person.inv.hammer);
                    }
                });
                Main.gui.fightScene.optionPanel.add(sword);
                Main.gui.fightScene.optionPanel.add(spell);
                Main.gui.fightScene.optionPanel.add(hammer);
                Main.gui.fightScene.optionPanel.backBut();
                Main.gui.fightScene.optionPanel.validate();
                Main.gui.fightScene.optionPanel.repaint();
            }
        });
        this.add(attack);
    }

    JButton Strike = new JButton("Strike");
    JButton Heal = new JButton("Heal");
    JButton Stun = new JButton("Stun");
    JButton Block = new JButton("Block");
    JButton Burn = new JButton("Burn");
    JButton Heavy = new JButton("Heavy");

    public void abilBut() {
        Strike = new JButton("");
        Strike = makeButton(Strike);
        Strike.setIcon(Main.setImageSize(Main.cl.getResource("Icons/AbilIcon/StrikeIcon.png").toString().substring(5), 100, 100));
        Heal = new JButton("");
        Heal = makeButton(Heal);
        Heal.setIcon(Main.setImageSize(Main.cl.getResource("Icons/AbilIcon/HealIcon.png").toString().substring(5), 100, 100));
        Stun = new JButton("");
        Stun = makeButton(Stun);
        Stun.setIcon(Main.setImageSize(Main.cl.getResource("Icons/AbilIcon/StunIcon.png").toString().substring(5), 100, 100));
        Block = new JButton("");
        Block = makeButton(Block);
        Block.setIcon(Main.setImageSize(Main.cl.getResource("Icons/AbilIcon/BlockIcon.png").toString().substring(5), 100, 100));
        Burn = new JButton("");
        Burn = makeButton(Burn);
        Burn.setIcon(Main.setImageSize(Main.cl.getResource("Icons/AbilIcon/BurnIcon.png").toString().substring(5), 100, 100));
        Heavy = new JButton("Heavy");
        Heavy = makeButton(Heavy);

        JButton abil = new JButton("");
        abil = makeButton(abil);
        abil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.gui.fightScene.foreIMG.setIcon(new ImageIcon(Main.cl.getResource("Icons/FightIcon/FightAbil.png").toString().substring(5)));
                Main.gui.fightScene.foreOpt = (ImageIcon)Main.gui.fightScene.foreIMG.getIcon();
                Main.gui.fightScene.optionPanel.removeAll();
                Main.gui.fightScene.optionPanel.setLayout(new GridLayout());
                ((GridLayout) Main.gui.fightScene.optionPanel.getLayout()).setHgap(4);
                if (Main.person.strk.unlocked) {
                    Strike.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("it is time to strike");
                            Main.gui.fightScene.curAbility = Main.person.strk;
                            // Main.gui.fightScene.optionPanel.remove(Strike);
                            attack(Main.person.inv.sword);
                        }
                    });
                    Main.gui.fightScene.optionPanel.add(Strike);
                }

                if (Main.person.heal.unlocked) {
                    Heal.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("healing myself");
                            Main.gui.fightScene.curAbility = Main.person.heal;
                            // Main.gui.fightScene.optionPanel.remove(Heal);
                            attack(Main.person.inv.spell);
                        }
                    });
                    Main.gui.fightScene.optionPanel.add(Heal);
                }

                if (Main.person.stun.unlocked) {
                    Stun.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("stunNED");
                            Main.gui.fightScene.curAbility = Main.person.stun;
                            // Main.gui.fightScene.optionPanel.remove(Stun);
                            attack(Main.person.inv.hammer);
                        }
                    });
                    Main.gui.fightScene.optionPanel.add(Stun);
                }

                if (Main.person.blck.unlocked) {
                    Block.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("YOUVE BEEN BLOCKED");
                            Main.gui.fightScene.curAbility = Main.person.blck;
                            // Main.gui.fightScene.optionPanel.remove(Block);
                            attack(Main.person.inv.sword);
                        }
                    });
                    Main.gui.fightScene.optionPanel.add(Block);
                }

                if (Main.person.burn.unlocked) {
                    Burn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("actualy roasted");
                            Main.gui.fightScene.curAbility = Main.person.burn;
                            // Main.gui.fightScene.optionPanel.remove(Burn);
                            attack(Main.person.inv.spell);
                        }
                    });
                    Main.gui.fightScene.optionPanel.add(Burn);
                }

                if (Main.person.hevy.unlocked) {
                    Heavy.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("charginnnnnnnnng");
                            Main.gui.fightScene.curAbility = Main.person.hevy;
                            Main.gui.fightScene.optionPanel.waitBut();
                            attack(Main.person.inv.hammer);
                        }
                    });
                    Main.gui.fightScene.optionPanel.add(Heavy);
                }

                Main.gui.fightScene.optionPanel.backBut();
                Main.gui.fightScene.optionPanel.validate();
                Main.gui.fightScene.optionPanel.repaint();
            }
        });
        this.add(abil);
    }

    public void backBut() {
        JButton back = new JButton("Back");
        back = makeButton(back);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.gui.fightScene.foreIMG.setIcon(new ImageIcon(Main.cl.getResource("Icons/FightIcon/FightOpt.png").toString().substring(5)));
                Main.gui.fightScene.foreOpt = (ImageIcon)Main.gui.fightScene.foreIMG.getIcon();
                Main.gui.fightScene.optionPanel.removeAll();
                Main.gui.fightScene.optionPanel.setLayout(new GridLayout());
                Main.gui.fightScene.optionPanel.init();
                Main.gui.fightScene.optionPanel.validate();
                Main.gui.fightScene.optionPanel.repaint();
            }
        });
        this.add(back);
    }

    JButton wait = new JButton("Wait");

    public void waitBut() {
        Main.gui.fightScene.foreIMG.setIcon(new ImageIcon(Main.cl.getResource("Icons/FightIcon/FightOne.png").toString().substring(5)));
        Main.gui.fightScene.optionPanel.removeAll();
        Main.gui.fightScene.optionPanel.setLayout(new GridLayout());
        Main.gui.fightScene.optionPanel.validate();
        Main.gui.fightScene.optionPanel.repaint();
        wait = makeButton(wait);
        wait.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.gui.fightScene.foreIMG.setIcon(new ImageIcon(Main.cl.getResource("Icons/FightIcon/FightOpt.png").toString().substring(5)));
                Main.gui.fightScene.foreOpt = (ImageIcon)Main.gui.fightScene.foreIMG.getIcon();
                System.out.println("WAITING");
                Main.gui.fightScene.curAbility = Main.person.wait;
                attack(Main.person.inv.hammer);
                Main.gui.fightScene.optionPanel.removeAll();
                Main.gui.fightScene.optionPanel.setLayout(new GridLayout());
                Main.gui.fightScene.optionPanel.init();
                Main.gui.fightScene.optionPanel.validate();
                Main.gui.fightScene.optionPanel.repaint();
            }
        });
        this.add(wait);
    }

    public void attack(Weapon weapon) { //should be weapon class
        weaponBonus = 1;
        String weak = Main.gui.fightScene.victim.weak;
        String resist = Main.gui.fightScene.victim.resist;
        weaponBonus += (new Random()).nextInt(4) > 3 ? 0.5 : 0;
        weaponBonus += weak.equals(weapon.basename) ? 0.5 : 0;
        weaponBonus -= resist.equals(weapon.basename) ? 0.25 : 0;
        weaponBonus = (resist.contains(weapon.basename) && resist.contains("/")) ? 0 : weaponBonus;

        Main.gui.fightScene.optionPanel.dmg = (int) ((weapon.damage * Main.person.strength) * weaponBonus); // CHANGE 5 & 1 with weappon dmg and player strength
        Main.gui.fightScene.forePanel.remove(Main.gui.fightScene.optionPanel);
        Main.gui.fightScene.InpPanel.startInp();

    }

    public JButton makeButton(JButton button) {
        JButton newbutton = button;
        newbutton.setOpaque(false);
        newbutton.setContentAreaFilled(false);
        newbutton.setBorderPainted(false);
        return newbutton;
    }

    public void startOption() {
        Main.gui.fightScene.forePanel.removeAll();
        Main.gui.fightScene.forePanel.setLayout(new BorderLayout());
        Main.gui.fightScene.forePanel.validate();
        Main.gui.fightScene.forePanel.repaint();
        //Main.gui.fightScene.inpOn(true);
        // Main.gui.fightScene.inpOn(false);
        Main.gui.fightScene.forePanel.add(Main.gui.fightScene.optionPanel);
        this.validate();
    }
}