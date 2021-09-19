import java.math.BigDecimal;
public class Steroid extends Item {
    public Steroid() {
      this.name ="Steroid";//steroid
      this.price = 10;
  		this.dir = Main.cl.getResource("Icons/ItemIcon/SteroidIcon.png").toString().substring(5);
			this.propName = "";
    }
    public void pickUp() {
        System.out.println("STEROID WAS PICKED UP");
        Main.person.strength = (int) (Main.person.strength*1.25);
        System.out.println(Main.person.strength);
        return;
    }
    public void buy(int perbuy) {
        Main.person.worth = Main.person.worth.subtract((new BigDecimal(String.valueOf(price))).multiply(new BigDecimal(String.valueOf(perbuy))));
        Main.person.strength += perbuy;
    }
    public void use(){
			int x = 1/0;     
    }
}
