import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.AffineTransform;


public class sliderBest extends BasicSliderUI
{
  public sliderBest(JSlider b) {
    super(b);
  }
  public ImageIcon thumb = Main.Auto4Size(Main.cl.getResource("Icons/InventoryIcon/Settings/SliderThumb.png").toString().substring(5));
  public ImageIcon track = Main.Auto4Size(Main.cl.getResource("Icons/InventoryIcon/Settings/SliderTrack.png").toString().substring(5));

  @Override
  public void paintTrack(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    g2D.drawImage(track.getImage(),trackRect.x,trackRect.y,null);

    g2D.dispose();
  }

}