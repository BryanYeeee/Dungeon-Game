import java.util.Arrays;
import java.math.BigDecimal;
import java.util.ArrayList;
public class Hammer extends Weapon {
	//SAVE A LEVEL INT FOR SAVE GAMe
	ArrayList<String> names = new ArrayList<>(Arrays.asList("Hammer", "ironhamer"));
	ArrayList<String> damages = new ArrayList<String>(Arrays.asList("1.5", "2"));
	ArrayList<String> prices = new ArrayList<String>(Arrays.asList("1.5", "2"));
  public Hammer(){
		this.index = 0;
		this.basename = "hammer";
    this.damage = Double.valueOf(damages.get(index));
		this.name = names.get(index);
		this.price = Double.valueOf(prices.get(index));
		this.toolTip = "HAMMER that deals " + this.damage + " dmg";
		this.propName = "";
  }
  public void pickUp() {
    Main.person.inv.hammer = this;
    return;
  }
	public void buy(int perbuy) {
		Main.person.worth = Main.person.worth.subtract((new BigDecimal(String.valueOf(price))).multiply(new BigDecimal(String.valueOf(perbuy))));
		this.upgrade(this.index+1);
	}
	public void use() {
		int x = 1/0;
	}
	public void upgrade(int newlvl) {
		this.index = newlvl;
    this.damage = Double.valueOf(damages.get(index));
		this.name = names.get(index);
		this.price = Double.valueOf(prices.get(index));
	}
}