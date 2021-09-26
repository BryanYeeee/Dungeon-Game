
import java.io.File;
import java.math.BigDecimal;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.geom.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class Main { // A
// b
    public static Player person;
    public static Dood boss;
    public static int currentLvl;
    public static ArrayList<Level> arlofLevels;
    public static ArrayList<Tile[][]> arlofPlayerLayer;
    public static GUI gui;
    public static Font endlessbattle;
    public static ImageIcon slidethumb;
    public static ClassLoader cl = Main.class.getClassLoader();
// a
    public static void main(String args[]) {
        cl = Main.class.getClassLoader();
        slidethumb = Auto4Size(cl.getResource("Icons/InventoryIcon/Settings/SliderThumb.png").toString().substring(5));
        try {
            endlessbattle = Font.createFont(Font.TRUETYPE_FONT, new File("EndlessBossBattleRegular-v7Ey.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(endlessbattle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UIManager.put("Button.font", endlessbattle);
        UIManager.put("Label.font", endlessbattle);
        UIManager.put("Panel.font", endlessbattle);
        UIManager.put("ScrollPane.font", endlessbattle);
        UIManager.put("Viewport.font", endlessbattle);
        UIManager.put("TextField.font", endlessbattle);
        UIManager.put("TextArea.font", endlessbattle);
        UIManager.put("Slider.horizontalThumbIcon", slidethumb);

        playSound(".//SOUNDS//clapp.wav");


        gui = new GUI();
        gui.startMenu();
        // gui.initGUI();
        // startGame();

    }

    public static void playSound(String dir) {
        File file = new File(dir);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.setMicrosecondPosition(0);
            clip.start();
        } catch (Exception e) {
            System.out.println("ASDASDASD");
        }
    }

    public static BufferedImage rotate(String dir, int degree) {
        BufferedImage buffImg = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        ;
        try {
            buffImg = ImageIO.read(new File(dir));
        } catch (IOException e) {
        }
        double rotationRequired = Math.toRadians(degree);
        double locationX = buffImg.getWidth() / 2;
        double locationY = buffImg.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(buffImg, null);
    }

    public static BufferedImage imgtoBuff(Image img) {
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(img, 0, 0, null);
        bg.dispose();
        return bi;
    }

    public static BufferedImage crop(ImageIcon icon, int x, int y, int width, int height) {
        width = width < 0 ? 0 : width;
        height = height < 0 ? 0 : height;
        BufferedImage original = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = original.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        BufferedImage image = original.getSubimage(x, y, height, width);
        return image;
    }

    public static ImageIcon setImageSize(String dir, int width, int height) {
        return new ImageIcon(new ImageIcon(dir).getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    public static ImageIcon AutoSize(String dir) {
        return new ImageIcon(new ImageIcon(dir).getImage().getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH));
    }

    public static ImageIcon Auto4Size(String dir) {
        ImageIcon icon = new ImageIcon(dir);
        return new ImageIcon(new ImageIcon(dir).getImage().getScaledInstance(icon.getIconWidth() * 4, icon.getIconHeight() * 4, java.awt.Image.SCALE_SMOOTH));
    }

    // 	return new ImageIcon(new ImageIcon(dir).getImage().getScaledInstance(height, width, java.awt.Image.SCALE_SMOOTH));
    // }

    public static void startGame() {
        person = new Player(4/* default 1 */, 15000/* default 150 */, 0, 100);
        boss = new Dood();
        currentLvl = 0;// +1
        arlofLevels = (new LevelList()).arlofLevels;
        arlofPlayerLayer = (new LayPlayer()).arlofPlayerLayer;

        Scanner sc = new Scanner(System.in);
        System.out.println("+------ WELCOME TO YOUR DOOD ------+");
        System.out.println("|        -- Pick a Stick --        |");
        System.out.println("| -+ (1)Sword (2)Wand (3)Hammer +- |");
        System.out.println("|        -- Pick a Stick --        |");
        System.out.println("+---- -+--------------------+- ----+");

        person.setLocation(arlofLevels.get(currentLvl).startx, arlofLevels.get(currentLvl).starty);

        person.toggleAbils();// debug only Jdashdasdhaoiusdhu9adhsa

    }

    public static void saveGame(int index) {
        try {
            File File = new File("C:\\Users\\halod\\eclipse-workspace\\KNEELAND\\src\\.saves\\save" + index);
            FileWriter fw = new FileWriter(File);
            BufferedWriter fileWriter = new BufferedWriter(fw);

            fileWriter.write("XY: " + Main.person.x + " " + Main.person.y);
            fileWriter.newLine();
            fileWriter.write("Player: " +
                    Main.person.strength + " " +
                    Main.person.health + " " +
                    (Main.person.worth).toString() + " " +
                    Main.person.maxhp + " " +
                    Main.person.inv.invCap);
            //NEED TO SAVE MANA TOOO
            fileWriter.newLine();
            fileWriter.write("Abil: " +
                    Main.person.strk.unlocked + " " +
                    Main.person.heal.unlocked + " " +
                    Main.person.stun.unlocked + " " +
                    Main.person.blck.unlocked + " " +
                    Main.person.burn.unlocked + " " +
                    Main.person.hevy.unlocked);
            fileWriter.newLine();
            fileWriter.write("Weapon: " +
                    Main.person.inv.sword.index + " " +
                    Main.person.inv.spell.index + " " +
                    Main.person.inv.hammer.index);
            fileWriter.newLine();
            fileWriter.write("Inv:");
            for (int i = 0; i < Main.person.inv.items.size(); i++) {
                fileWriter.write(" " + (Main.person.inv.items.get(i).propName.equals("") ? Main.person.inv.items.get(i).name : Main.person.inv.items.get(i).propName));
            }
            fileWriter.newLine();
            fileWriter.write("Bestiary:");
            Iterator it = Main.person.bestiary.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                fileWriter.write(" " + (Boolean) pair.getValue());
            }
            fileWriter.newLine();
            fileWriter.write("Levels:");
            fileWriter.newLine();
            for (int k = 0; k < Main.arlofLevels.size(); k++) {
                fileWriter.write("LevelNum: " + k);
                fileWriter.newLine();
                for (int i = 0; i < Main.arlofLevels.get(k).map.length; i++) {
                    for (int j = 0; j < Main.arlofLevels.get(k).map[0].length; j++) {
                        fileWriter.write(Main.arlofLevels.get(k).map[i][j].propName + ",");
                    }
                    fileWriter.newLine();
                }
            }

            fileWriter.close();
            System.out.println("Successfully Saved the Game.");
            return;
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return;
        }

    }

    public static void loadGame(int save) {
        File File = new File("C:\\Users\\halod\\eclipse-workspace\\KNEELAND\\src\\.saves\\save" + save);
        Scanner reader;
        try {
            reader = new Scanner(File);
            String[] playerPos = reader.nextLine().split(" ");
            Main.person.setLocation(Integer.valueOf(playerPos[1]), Integer.valueOf(playerPos[2]));

            String[] playerStats = reader.nextLine().split(" ");
            Main.person.strength = Integer.valueOf(playerStats[1]);
            Main.person.health = Integer.valueOf(playerStats[2]);
            Main.person.worth = new BigDecimal(playerStats[3]);
            Main.person.maxhp = Integer.valueOf(playerStats[4]);
            Main.person.inv.invCap = Integer.valueOf(playerStats[5]);

            String[] abilUnlock = reader.nextLine().split(" ");
            Main.person.strk.unlocked = Boolean.valueOf(abilUnlock[1]);
            Main.person.heal.unlocked = Boolean.valueOf(abilUnlock[2]);
            Main.person.stun.unlocked = Boolean.valueOf(abilUnlock[3]);
            Main.person.blck.unlocked = Boolean.valueOf(abilUnlock[4]);
            Main.person.burn.unlocked = Boolean.valueOf(abilUnlock[5]);
            Main.person.hevy.unlocked = Boolean.valueOf(abilUnlock[6]);

            String[] weapLvl = reader.nextLine().split(" ");
            Main.person.inv.sword.upgrade(Integer.valueOf(weapLvl[1]));
            Main.person.inv.spell.upgrade(Integer.valueOf(weapLvl[2]));
            Main.person.inv.hammer.upgrade(Integer.valueOf(weapLvl[3]));

            String[] inventory = reader.nextLine().split(" ");
            Main.person.inv.items = new ArrayList<Item>();
            for (int i = 1; i < inventory.length; i++) {
                String[] item = inventory[i].split("<");
                switch (item[0]) {
                    case "Key":
                        Main.person.inv.items.add(new Key(Integer.valueOf(item[1])));
                        break;
                    case "Note":
                        Main.person.inv.items.add(new Note(Integer.valueOf(item[1])));
                        break;
                    case "Pot":
                        Main.person.inv.items.add(new Pot(Integer.valueOf(item[1])));
                        break;
                    case "RockPass":
                        Main.person.inv.items.add(new RockPass());
                        break;
                }
            }

            String[] beast = reader.nextLine().split(" ");
            Iterator it = Main.person.bestiary.entrySet().iterator();
            int index = 1;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Main.person.bestiary.put((String) pair.getKey(), Boolean.valueOf(beast[index]));
                index++;
                System.out.println(Main.person.bestiary);
            }

            reader.nextLine();
            for (int i = 0; i < Main.arlofLevels.size(); i++) {
                reader.nextLine();
                String[][] newMap = new String[Main.arlofLevels.get(i).map.length][Main.arlofLevels.get(i).map[0].length];
                for (int j = 0; j < Main.arlofLevels.get(i).map.length; j++) {//height of level
                    newMap[j] = reader.nextLine().split(",");
                }
                Main.arlofLevels.get(i).initMap(newMap);
            }
            Main.gui.map.updateMap();
            Main.gui.scrollToPlayer();

        } catch (Exception e) {
            System.out.println("ERROR READING FILE");
            e.printStackTrace();
            return;
        }

    }

}