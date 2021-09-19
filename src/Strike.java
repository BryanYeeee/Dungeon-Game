public class Strike extends Ability{
  public Strike(){

  }
 public int useAbil(int curDamage, Enemy victim, int errors, fightPanel fightScene){
   int damage = (curDamage * ((fightScene.InpPanel.Inputs.size()-(fightScene.InpPanel.tileLeft + (errors*2)))/10));
   System.out.println(curDamage+"*("+fightScene.InpPanel.Inputs.size()+"-("+fightScene.InpPanel.tileLeft+"+"+errors+")/10"+")");
	 fightScene.cont.addTxt("Strike used");
	 return damage;
  }


}