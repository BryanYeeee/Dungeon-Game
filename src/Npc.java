import java.util.ArrayList;
import java.util.Arrays;

public class Npc {
    ArrayList<String[]> npcText = new ArrayList<>(Arrays.asList(
            new String[]{"Hi","2nd message","bye"},
            new String[]{"Hi","2nd message","bye"}
    ));
    int index;
    String[] dialogue;
    String dir;
    public Npc(int index){
        this.index = index;
        this.dialogue = npcText.get(index);
    }

    public void startDiag(){
        Main.gui.diagScene = new dialogueGUI(this);
        switch(this.index){
            case 1:
                break;
        }
        Main.gui.diagScene.startDiag(Main.gui.diagScene);
    }

    public void endDiag(){
        switch(this.index){
            case 1:
                break;
        }
    }
}
