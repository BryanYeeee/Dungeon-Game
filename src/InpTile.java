import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.math.BigDecimal;
import java.awt.image.RescaleOp;
import java.io.*;
import javax.imageio.ImageIO;
import static javax.swing.BorderFactory.createEmptyBorder;
public class InpTile extends JPanel {
			String symb;
			public InpTile(String symb) {
					// this.setBackground(Color.green);
					this.setOpaque(false);
					this.setBorder(createEmptyBorder());
					this.symb = symb;
					// if(!symb.equals(" ")) {
					// 		this.setBorder(BorderFactory.createEtchedBorder());
					// }
					this.setPreferredSize(new Dimension(100, 100));
					if(!symb.equals(" ")) {
						this.add(Ebility.makeTile(Main.gui.fightScene.victim.name, symb));
					}
			}
	}