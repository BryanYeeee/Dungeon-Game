import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class inpAction extends AbstractAction {
    String keyStroke;

    public inpAction(String keyStroke) {
        this.keyStroke = keyStroke;

        this.turnOff();
    }

    public void turnOff() {
        Main.gui.mainPanel.getInputMap(Main.gui.mainPanel.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(keyStroke));
        Main.gui.mainPanel.getActionMap().remove(keyStroke);
    }

    public void turnOn() {
        Main.gui.mainPanel.getInputMap(Main.gui.mainPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyStroke), keyStroke);
        Main.gui.mainPanel.getActionMap().put(keyStroke, this);
    }

    public void actionPerformed(ActionEvent e) {
        if (Main.gui.fightScene.checkInput(this.keyStroke)) {
            Main.gui.fightScene.nextTile();
        } else {
            // if(Main.gui.fightScene.stunActive[0] > 0) {
            // 	Main.gui.fightScene.stunActive[2]++;
            // }
            Main.gui.fightScene.totalErrors++;
            Main.gui.fightScene.errors++;
//					System.out.println("STATT " + Main.gui.fightScene.errors);
            Main.gui.fightScene.updateStatLabel();
        }


    }

}