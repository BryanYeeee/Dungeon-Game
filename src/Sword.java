import java.util.Arrays;
import java.math.BigDecimal;
import java.util.ArrayList;
public class Sword extends Weapon {
  ArrayList<String> names = new ArrayList<>(Arrays.asList("Sword", "upgrade"));
	ArrayList<String> damages = new ArrayList<String>(Arrays.asList("1.5", "2"));
	ArrayList<String> prices = new ArrayList<String>(Arrays.asList("1.5", "2"));
  public Sword(){
		this.index = 0;
		this.basename = "sword";
    this.damage = Double.valueOf(damages.get(index));
		this.name = names.get(index);
		this.price = Double.valueOf(prices.get(index));
    this.toolTip = "SWORD that deals " + this.damage + " dmg";
		this.propName = "";
  }
  public void pickUp() {
    Main.person.inv.sword = this;
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