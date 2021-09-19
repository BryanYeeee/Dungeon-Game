import java.math.BigDecimal;
public class Key extends Item{
    int id;
    public static int paintx = 0;
    public static int painty = 0;
    public Key(int id) {
      this.id = id;
			this.name = "Key";
			this.toolTip = "Opens doors labeled " + this.id;
      this.price = 500;//placeholder
			this.dir = Main.cl.getResource("Icons/ItemIcon/key.png").toString().substring(5);
			this.propName = "Key<"+this.id;
    }
    
    public void pickUp() {
        System.out.println("KEY " + id + " WAS PICKED UP");
        Main.person.inv.addItem(this);
        return;
    }
    public void buy(int perbuy){
        Main.person.worth = Main.person.worth.subtract((new BigDecimal(String.valueOf(price))).multiply(new BigDecimal(String.valueOf(perbuy))));
        //Main.person.health += perbuy;
        Main.person.inv.addItem(this);
    }
    public void use(){
			int x = 1/0;     
    }

}
