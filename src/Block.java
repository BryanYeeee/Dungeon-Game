public class Block extends Ability{
  public Block(){

  }
 public int useAbil(int curDamage, Enemy victim, int errors, fightPanel fightScene){
	 if(errors < 3 && fightScene.InpPanel.tileLeft == 0&&!(victim.weak.contains("/sword"))) {
    victim.status.put("blck", 1);
	 }
    return 0;
  }
}