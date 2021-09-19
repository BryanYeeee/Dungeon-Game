public class Heavy extends Ability{
	public int dmgStore;
  public Heavy(){
this.dmgStore = 0;
  }
 public int useAbil(int curDamage, Enemy victim, int errors, fightPanel fightScene){
	 System.out.println("hEAVYYYYY");
	 if(errors < 3 && fightScene.InpPanel.tileLeft == 0&&!(victim.weak.contains("/hammer"))) {
    victim.status.put("targetted", 2); 
		this.dmgStore = curDamage;
	 }
    return 0;
  }

}