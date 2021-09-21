import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class tileGUI extends JPanel {
    Tile currentTile;
    int x;
    int y;

    tileGUI(Tile tile, int x, int y) {
        super(new GridBagLayout());
        this.currentTile = tile;
        this.setBackground(new Color(168, 189, 171));
        // if(tile.dir == null) {
        // this.add(new JLabel(new ImageIcon("TileIcon" + (new Random()).nextInt(4) + ".png").toString().substring(5))));
        // } else {
        // this.add(new JLabel(new ImageIcon(tile.dir)));
        // }

        this.x = x;
        this.y = y;
        this.setPreferredSize(new Dimension(128, 128));
        this.repaint();
        this.validate();
    }

    public void paint(Graphics g) {
        super.paint(g); // paint background

        Graphics2D g2D = (Graphics2D) g;
        Image tileImage = (Main.AutoSize(this.currentTile.backDir)).getImage();
        g2D.drawImage(tileImage, 0, 0, null);

        if (this.currentTile.object != null) {
            // System.out.println(this.currentTile.dir);
            tileImage = (Main.AutoSize(this.currentTile.dir)).getImage();
            switch (this.currentTile.symbol) {
                case "N":
                    g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Main.boss.fadeperc*(0.01f)));
                    g2D.drawImage(tileImage,0,0,null);
                default:
                    g2D.drawImage(tileImage, 0, 0, null);
                    break;
            }
        }

        Object thing;
        String dirPath = "Icons/NumIcon/number";
        switch (this.currentTile.symbol) {
            case "|":
                thing = ((Door) this.currentTile.object);
                g2D.drawImage((Main.setImageSize(Main.cl.getResource(dirPath + ((Door) thing).id + ".png").toString().substring(5), 64, 64)).getImage(), Door.paintx, Door.painty, null);
                break;
            case "^":
                thing = ((SwitchDoor) this.currentTile.object);
                g2D.drawImage((Main.setImageSize(Main.cl.getResource(dirPath + ((SwitchDoor) thing).id + ".png").toString().substring(5), 64, 64)).getImage(), SwitchDoor.paintx, SwitchDoor.painty, null);
                break;
            case "<":
                thing = ((Key) this.currentTile.object);
                g2D.drawImage((Main.setImageSize(Main.cl.getResource(dirPath + ((Key) thing).id + ".png").toString().substring(5), 64, 64)).getImage(), Key.paintx, Key.painty, null);
                break;
            case "L":
                thing = ((Switch) this.currentTile.object);
                g2D.drawImage((Main.setImageSize(Main.cl.getResource(dirPath + ((Switch) thing).id + ".png").toString().substring(5), 64, 64)).getImage(), Switch.paintx, Switch.painty, null);
                break;
            case "G":
                thing = ((Boulder) this.currentTile.object);
                if (((Boulder) thing).done) {
                    g2D.drawImage((Main.AutoSize(Main.cl.getResource("Icons/TileIcon/DPlateIcon.png").toString().substring(5))).getImage(), 0, 0, null)
                    ;
                    g2D.drawImage((Main.AutoSize(((Boulder) thing).dir)).getImage(), 0, 0, null);
                }
                break;
        }

        try {
            if (Main.arlofLevels.get(Main.currentLvl).map[x+1][y].object instanceof Decoration && ((Decoration)Main.arlofLevels.get(Main.currentLvl).map[x+1][y].object).tall) {
                System.out.println(((Decoration)Main.arlofLevels.get(Main.currentLvl).map[x+1][y].object).dir.replace('1','2').toString());
                System.out.println(((Decoration)Main.arlofLevels.get(Main.currentLvl).map[x+1][y].object).dir);
                System.out.println(Main.arlofLevels.get(Main.currentLvl).map[x+1][y].object);
                g2D.drawImage((Main.AutoSize(((Decoration)Main.arlofLevels.get(Main.currentLvl).map[x+1][y].object).dir.replace('1','2'))).getImage(), 0, 0, null);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}