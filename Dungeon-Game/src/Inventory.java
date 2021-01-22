import java.util.ArrayList;

public class Inventory {
    ArrayList<String> items;

    public Inventory() {

        this.items = new ArrayList<String>();
    }

    public void use(int index) {

    }
    public void add(String item){
        this.items.add(item);
    }
    public void clearAll(){
        this.items.clear();
    }
}
