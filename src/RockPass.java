import java.math.BigDecimal;
public class RockPass extends Item{
    public RockPass() {
			this.name = "RockPass";
			this.toolTip = "Push rock : TURNED OFF";
      this.price = 500;//placeholder
		  this.dir = Main.cl.getResource("Icons/ItemIcon/RockPassIcon.png").toString().substring(5);
			this.propName = "";
    }
    
    public void pickUp() {
        System.out.println("You are now a stoner");
        Main.person.inv.addItem(this);
        return;
    }
    public void buy(int perbuy){
        // Main.person.worth = Main.person.worth.subtract((new BigDecimal(String.valueOf(price))).multiply(new BigDecimal(String.valueOf(perbuy))));
        // //Main.person.health += perbuy;
        // Main.person.inv.addItem(this);
				int x = 1/0;
    }
    public void use(){
			Main.person.boulPush = !Main.person.boulPush;
			this.toolTip = Main.person.boulPush ? "Push rock: TURNED ON" : "Push rock: TURNED OFF";
    }

}
