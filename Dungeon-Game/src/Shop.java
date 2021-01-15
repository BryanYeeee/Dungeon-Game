import java.util.List;
import java.util.ArrayList;

public class Shop {
     List items = new ArrayList<>();

    public Shop(List<String> items) {
        for(int i = 0; i < items.size(); i++) {
            String[] itemDetails = items.get(i).split(",");
            this.items.add(itemDetails);
        }
    }

    public void outputShop(){
        for(int i = 0; i < items.size(); i++) {
            System.out.println(i + " " + items);
//            System.out.println((i+1) + " to buy +" + (this.items.get(i))[1] + " " + (this.items.get(i))[0] + " for " + (this.items.get(i))[2]);
        }
    }
}