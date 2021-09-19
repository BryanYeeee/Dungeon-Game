public abstract class Weapon extends Item {
  double damage;
	String basename;
	int level;

    public Weapon() {
    }
    public abstract void pickUp();
    public abstract void buy(int perbuy);
    public abstract void use();
}
