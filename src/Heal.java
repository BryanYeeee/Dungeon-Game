import java.util.Random;
public class Heal extends Ability{
  public Heal(){

  }
 public int useAbil(int curDamage, Enemy victim, int errors, fightPanel fightScene){
    int healed = (int)(curDamage * (0.1*((new Random()).nextInt(2) + 5)+1));
		Main.person.health += healed;
    if(Main.person.health > Main.person.maxhp){
					Main.person.health = Main.person.maxhp;
		}
    int damage = ((fightScene.InpPanel.Inputs.size()-fightScene.InpPanel.tileLeft)/fightScene.InpPanel.Inputs.size() - (errors))*(int)(0.7*curDamage);
    return damage;
  }


}