import java.util.Scanner;
import java.util.Random;
public class AsciiCipher extends Cipher {
    Random rand = new Random();
    Scanner sc = new Scanner(System.in);

    public AsciiCipher(int dif) {
        super(dif);
        this.key = (rand.nextInt(25) + 65);
        this.instruction = ("<html>= Cipher =" + "   "+ "= If A = 1, B = 2 C =3.....<br>= What does "+ (char) this.key + " equal? </html>");
    }

    public boolean hack(int answer) {
            try {
                if(key-64 != answer) {
									Main.person.health = Main.person.health - this.dif*10;
                    return false;
                }
            } catch (Exception e) {
                System.out.println("ERRORRRRR " + e);
                return false;
            }
        return true;

    }
}
