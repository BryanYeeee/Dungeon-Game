import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;import java.util.Arrays;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.lang.Math;
import static java.util.Map.entry;


public class Shop {
    String dir = Main.cl.getResource("Icons/TileIcon/ShopIcon.png").toString().substring(5);
    Random rand = new Random();
    Map<Item, int[]> items = new HashMap<>();
    ArrayList<Item> totalItems = new ArrayList<Item>(Arrays.asList(new MaxHp(), new Steroid(), new Pot(25), new Health()));

    public Shop(Map<Item, int[]> items) { //set shop
			this.items = items;
    }
		public Shop() { //random shop
		this.dir = "R" + this.dir;
			this.randomize();
    }

    public void outputShop(){
        System.out.println("[ Wallet: " + Main.person.worth);				
				Iterator it = this.items.entrySet().iterator();
				while (it.hasNext()) {
						Map.Entry<Item, Integer> pair = (Map.Entry<Item, Integer>)it.next();
						System.out.println("[ " + (((Item)(pair.getKey())).name) + " " + pair.getValue());
				}
    }
		public void randomize(){
			for(int i=0;i<Math.min(totalItems.size()+1, 3);i++){
        Item item = totalItems.get(rand.nextInt(totalItems.size()));
        items.put(item,new int[]{3+rand.nextInt(5), 3+rand.nextInt(5)});
        totalItems.remove(item);
        
      }
		}
    public boolean buy(int option){
				Item item = ((Item)this.items.keySet().toArray()[option]);
				int perbuy = (int)((int[])(this.items.values().toArray()[option]))[0];
				int quantity = (int)((int[])(this.items.values().toArray()[option]))[1];
        
        if(Main.person.worth.doubleValue() >= (perbuy*item.price) && (quantity!=0) && Main.person.inv.items.size() < Main.person.inv.invCap){
					item.buy(perbuy);
					quantity--;
					this.items.replace(item, new int[]{perbuy, quantity});
					
					 Main.gui.shopScene.buttons.get(option).quantity--;
				// /curButtonn).setText("<html>" +curButton.perbuy+" "+curButton.name + " for " + Main.gui.shopScene.buttons.get(option).total + "<br>" + curButton.quantity + " left </html>"
          return true;
				}
    return false;

    }

}