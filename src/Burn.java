import java.util.Random;

public class Burn extends Ability{
  public int dmgBurn;
  Random rand = new Random();
  public Burn(){	
		int dmgBurn = 0;
  }
 public int useAbil(int curDamage, Enemy victim, int errors, fightPanel fightScene){
	 if(errors < 3 && fightScene.InpPanel.tileLeft == 0&&!(victim.weak.contains("/spell"))) {
		int x = rand.nextInt(2);
		System.out.println(x + " BURN AMOUNT");
    victim.status.put("burn", x+2); 
    this.dmgBurn = curDamage;
	 }
    // victim.status.put("burn", 3);   
    return 0;
  }
}