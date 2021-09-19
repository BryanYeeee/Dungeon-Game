import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.io.File;

public abstract class Screen extends JLayeredPane{

	public Screen(){
		this.setLayout(null);
		this.setBackground(Color.PINK);
	}

	public void addScreen(){
		System.out.println("added screen");
		Main.gui.invScene.mainPanel.removeAll();
		Main.gui.invScene.mainPanel.setLayout(new BorderLayout());
		Main.gui.invScene.mainPanel.add(this, BorderLayout.CENTER);
		this.updateScreen();
		Main.gui.invScene.updateInv();
	}
	public abstract void updateScreen();
	public abstract void closeScreen();
}