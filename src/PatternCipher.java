import java.util.Scanner;
import java.util.Random;
public class PatternCipher extends Cipher {
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);
		int nextVar = 0;

    public PatternCipher(int dif) {
        super(dif);
        this.key = (rand.nextInt(59));
        this.nextVar = (rand.nextInt(dif*10) + this.key);
        this.instruction = ("<html>= What's the Next Variable?<br>= "+ this.key + " , " + nextVar + " , ___</html>");
    }

    public boolean hack(int answer) {
        try {
            if(answer != this.nextVar+(this.nextVar-this.key)) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("ERRORRRRR 3" + e);
            return false;
        }
        return true;

    }
}
