import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;
import java.awt.image.BufferedImage;
import static javax.swing.BorderFactory.createEmptyBorder;

public class invSettings extends Screen{

  JPanel settingsBG;
  JPanel BGPanel;
  JLabel backIMG = new JLabel();
  JScrollPane scroll;
  int settings = 1;

  public invSettings(){
    super();

    this.settingsBG = new JPanel();
		this.settingsBG.setBounds(0,0,600,344);
		this.settingsBG.setOpaque(false);

    this.scroll = new JScrollPane(this.settingsBG);
    this.scroll.setOpaque(false);
    this.scroll.getViewport().setOpaque(false);
		this.scroll.getVerticalScrollBar().setUI(new scrollBest());
    this.scroll.getVerticalScrollBar().setPreferredSize(new Dimension(600,344));
    this.scroll.setBorder(createEmptyBorder());
		this.scroll.setBounds(20,20,600,344);

    this.BGPanel = new JPanel();
		this.BGPanel.setOpaque(false);
		this.BGPanel.setLayout(null);
		this.BGPanel.setBounds(0,0,640,384);
		this.add(this.BGPanel, Integer.valueOf(1));

    this.backIMG.setIcon(Main.setImageSize(Main.cl.getResource("Icons/InventoryIcon/Settings/SettBG.png").toString().substring(5),384,640));
		this.backIMG.setBounds(0,0,640,384);
		this.add(backIMG, Integer.valueOf(0));

  }
  public void updateScreen(){
		this.BGPanel.removeAll();
		this.BGPanel.setLayout(null);

    this.BGPanel.add(this.scroll);
		this.makeSettings();

		this.BGPanel.validate();
		this.BGPanel.repaint();
	}

	public void closeScreen(){
	}

  public void makeSettings(){
		this.settingsBG.removeAll();
    this.settingsBG.setLayout(new GridLayout(settings, 2));
    JSlider mastervolume =  makeMasterVolume("SOUDNS WAVEs");
    settingsBG.add(mastervolume);

  }
  public JSlider makeMasterVolume(String name){
    this.settingsBG.add(new JLabel(name));
    JSlider slider = new JSlider();
		slider.setOpaque(false);
    //slider.setUI(new sliderBest(slider));
    slider.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent ce) {
            System.out.println(slider.getValue());
            repaint();
         }
      });
    return slider;
  }
}