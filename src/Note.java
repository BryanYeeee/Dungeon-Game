import java.math.BigDecimal;
public class Note extends Item{
  String[] notes = new String[]{"HIS WEAKNESS ROTATES FROM HAMMER > SWORD > SPELL", "I'm actually evil", "you beat game yay", "I AM A GOD ICON"};

  public Note(int id) {
    this.dir = dir;
    this.name = "Note";
    this.toolTip = this.notes[id];
    this.price = 500;//placeholder
  	this.dir = Main.cl.getResource("Icons/ItemIcon/NoteIcon.png").toString().substring(5);
		this.propName = "Note<"+id;
  }
  
  public void pickUp() {
      System.out.println("Paper is cool");
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
