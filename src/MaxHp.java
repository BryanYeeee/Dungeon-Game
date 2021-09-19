import java.math.BigDecimal;
public class MaxHp extends Item {//NO DIR
    public MaxHp(){
			this.name = "MaxHp";
  		this.price = 1;
			this.propName = "";
			// this.dir = Main.cl.getResource("Icons/ItemIcon/MaxHp.png").toString().substring(5);
    }
    public void pickUp() {
        System.out.println("LIFE CRYSTAL WAS PICKED UP");
				Main.person.maxhp += 5 ;//can chagnge later
        return;
    }
    public void buy(int perbuy){
        Main.person.worth = Main.person.worth.subtract((new BigDecimal(String.valueOf(price))).multiply(new BigDecimal(String.valueOf(perbuy))));
        Main.person.maxhp += perbuy;
    }
    public void use(){
			int x = 1/0;     
    }
}
