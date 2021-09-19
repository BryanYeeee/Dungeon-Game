import java.math.BigDecimal;
public class Health extends Item {
    public Health() {
      this.name = "Health";
      this.price = 1;
			this.dir = Main.cl.getResource("Icons/ItemIcon/HealthIcon.png").toString().substring(5);
			this.propName = "";
    }
    public void pickUp() {
        System.out.println("HEALTH WAS PICKED UP");
				if(Main.person.health + 10 <= Main.person.maxhp) {
        Main.person.health += 10;
				}
        return;
    }
    public void buy(int perbuy){
        Main.person.worth = Main.person.worth.subtract((new BigDecimal(String.valueOf(price))).multiply(new BigDecimal(String.valueOf(perbuy))));
        Main.person.health += perbuy;
    }
    public void use(){
			int x = 1/0;     
    }

}
