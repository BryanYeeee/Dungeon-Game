import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.AffineTransform;

public class scrollBest extends BasicScrollBarUI  {
    public ImageIcon thumb = new ImageIcon(Main.cl.getResource("Icons/InventoryIcon/Bestiary/BestThumb.png").toString().substring(5));
    public ImageIcon track = new ImageIcon(Main.cl.getResource("Icons/InventoryIcon/Bestiary/BestTrack.png").toString().substring(5));
    private final Dimension d = new Dimension();

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return d;
            }
        };
    }
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
      Graphics2D g2 = (Graphics2D) g;
      g2.drawImage(thumb.getImage(),r.x,r.y,r.width,48,null);
    }
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
      Graphics2D g2 = (Graphics2D) g;
      g2.drawImage(track.getImage(),r.x,r.y,r.width,r.height,null);
    }

  }