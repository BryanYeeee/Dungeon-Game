public abstract class Item {
  String name;
  double price;
	String toolTip;
	int index;
	String dir;
	String propName;
    public Item() {
    }
    public abstract void pickUp();
    public abstract void buy(int perbuy);
    public abstract void use();

}