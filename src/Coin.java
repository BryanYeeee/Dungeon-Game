import java.math.BigDecimal;
public class Coin extends Item {
    public Coin() {
      this.name = "Coin";
      this.price = 2;
			this.dir = Main.cl.getResource("Icons/ItemIcon/CoinIcon.png").toString().substring(5);
			this.propName = "";
    }
    
    public void pickUp() {
        System.out.println("COIN WAS PICKED UP");
        Main.person.worth = Main.person.worth.add(new BigDecimal("1"));
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