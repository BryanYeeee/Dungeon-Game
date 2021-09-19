import java.util.ArrayList;
import java.util.Arrays;

public class Guide {
  String dir = "GuideIcon.png";
  public static ArrayList<String> Dialogue = new ArrayList<String>(Arrays.asList(new String[]{"Smell my leg hairs","Press A to move to the left ;)"}));
  static int line;

    public Guide(int line) {
      this.line = line;
    }
    public static void speak(){
      GUI.showDialogue(Dialogue.get(line));
    }
}
