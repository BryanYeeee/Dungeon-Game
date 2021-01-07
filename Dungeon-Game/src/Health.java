public class Health extends Item {
    public Health() {
    }
    public boolean pickUp(Player person) {
        System.out.println("HEALTH WAS PICKED UP");
        person.health = person.health + 10;
        return true;
    }
}
