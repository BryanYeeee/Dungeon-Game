import java.util.ArrayList;

  public class Inventory {
    ArrayList<Item> items;
		int invCap;
    Sword sword;
    Spell spell;
    Hammer hammer;

    public Inventory() {
				this.invCap = 20;
        this.items = new ArrayList<Item>();
        this.sword = new Sword();
        this.spell = new Spell();
        this.hammer = new Hammer();
        // this.items.add(this.sword);
        // this.items.add(this.spell);
        // this.items.add(this.hammer);
    }

		public ArrayList<String> outputInv() {
        ArrayList<String> stringItems = new ArrayList<>();
			for(int i = 0; i < this.items.size(); i++){
				stringItems.add(this.items.get(i).name);
			}
			return stringItems;
		}
		public boolean containsKey(int id) {
        for(int i=0;i<this.items.size();i++){
          if(this.items.get(i) instanceof Key && ((Key)this.items.get(i)).id == id ){
            return true;
          }
        }
				return false;
		}

    public void clearKeys() {
        ArrayList<Item> nokeys = new ArrayList<>();
        for(int i=0;i<this.items.size();i++){
          if(!(this.items.get(i) instanceof Key)){
            nokeys.add(this.items.get(i));
          }
        }
        this.items = nokeys;
        return;
    }

		public void removeItem(int index) {
			if(!(this.items.get(index) instanceof Key)) {
			this.items.remove(index);
			for(int i = index; i< this.items.size(); i++){
				this.items.get(i).index = i;
			}
			}
		}

		public void addItem(Item item){
			item.index = this.items.size();
			this.items.add(item);
		}
}
