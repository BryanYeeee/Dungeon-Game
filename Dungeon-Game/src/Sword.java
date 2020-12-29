public class Sword extends Item {
    public Sword() {
    }
    public boolean pickUp(Player person) {
        System.out.println("SWORD WAS PICKED UP");
        person.strength = (int) (person.strength*1.5);
        return true;
    }
}
