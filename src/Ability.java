abstract public class Ability{
	// public int manaDrain;
	public boolean unlocked;
  public Ability(){
		this.unlocked = false;
		// this.used = false;
  }
  public abstract int useAbil(int curDamage, Enemy victim, int errors, fightPanel fightScene);


}