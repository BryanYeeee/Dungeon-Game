import java.math.BigDecimal;
public class AbilUnlock extends Item {
    String dir = Main.cl.getResource("Icons/AbilIcon/AbilityIcon.png").toString().substring(5);
		public int abil;
    public AbilUnlock(int abil) {
			this.name = "Ability";
			this.toolTip = "UNLOCK NEW ABILITY";
      this.price = 100;
			this.abil = abil;
    }
    
    public void pickUp() {
				this.unlockAbil();
        return;
    }
    public void buy(int perbuy){
        Main.person.worth = Main.person.worth.subtract((new BigDecimal(String.valueOf(price))).multiply(new BigDecimal(String.valueOf(perbuy))));
				this.unlockAbil();
    }
    public void use(){
			int x = 1/0;     
    }

		public void unlockAbil() {
			switch(this.abil){
				case 1:
					Main.person.strk.unlocked = true;
					break;
				case 2:
					Main.person.heal.unlocked = true;
					break;
				case 3:
					Main.person.stun.unlocked = true;
					break;
				case 4:
					Main.person.blck.unlocked = true;
					break;
				case 5:
					Main.person.burn.unlocked = true;
					break;
				case 6:
					Main.person.hevy.unlocked = true;
					break;
			}
			return;
		}

}