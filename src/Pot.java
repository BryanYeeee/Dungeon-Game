import java.math.BigDecimal;
public class Pot extends Item {
		int value;
    public Pot(int value) {
			this.value=value;
      this.price=value*0.1;
      this.name = "Pot";
      this.toolTip = "Heals " + value + " hp when DRANKEN";
    	this.dir = Main.cl.getResource("Icons/ItemIcon/PotIcon.png").toString().substring(5);
			this.propName = "Pot<"+value;
    }
    public void pickUp() {
        System.out.println("HEALTH POT WAS PICKED UP");
				Main.person.inv.addItem(this);
        return;
    
		}
		public void buy(int perbuy){
        Main.person.worth = Main.person.worth.subtract((new BigDecimal(String.valueOf(price))).multiply(new BigDecimal(String.valueOf(perbuy))));
				Main.person.inv.addItem(this);
		}
		public void use(){
      System.out.println("USED POT FROM INV " + this.index + " TO HEAL " + this.value + " HEALTH");
			Main.person.health += this.value;
			if(Main.person.health > Main.person.maxhp){
				Main.person.health = Main.person.maxhp;
			}
      Main.person.inv.removeItem(this.index);
			
    }
}
