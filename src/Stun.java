import java.util.Random;

public class Stun extends Ability{
  Random rand = new Random();
  public Stun(){

  }
 public int useAbil(int curDamage, Enemy victim, int errors, fightPanel fightScene){
	 if(errors < 3 && fightScene.InpPanel.tileLeft == 0&&!(victim.weak.contains("/hammer"))) {
   	victim.status.put("stun", rand.nextInt(2)+2);
	 }
    return (int)(curDamage*0.5);
  }
}