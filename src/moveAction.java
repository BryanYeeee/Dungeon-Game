import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class moveAction extends AbstractAction {
	int movex = 0;
	int movey = 0;
	String keyStroke;

	public moveAction(int movex, int movey, String keyStroke){
		this.movex = movex;
		this.movey = movey;
		this.keyStroke = keyStroke;

      	this.turnOn();
	}
	public void turnOff(){
      Main.gui.map.getInputMap(Main.gui.map.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(keyStroke));
      Main.gui.map.getActionMap().remove(keyStroke);			
	}
	public void turnOn(){
	  Main.gui.map.getInputMap(Main.gui.map.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyStroke), keyStroke);
	  Main.gui.map.getActionMap().put(keyStroke, this);
	}

	public void actionPerformed(ActionEvent e) {
		  Main.person.move(movex, movey, Main.arlofLevels.get(Main.currentLvl));
		  Main.arlofLevels.get(Main.currentLvl).outputMap();

	}
      
}