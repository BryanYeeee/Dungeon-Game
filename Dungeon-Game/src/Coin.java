public class Coin extends Item {
    public Coin() {
    }
    public boolean pickUp(Player person) {
        System.out.println("COIN WAS PICKED UP");
        person.worth++;
        return true;
    }
}
