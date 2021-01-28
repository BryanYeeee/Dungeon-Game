public class Key extends Item{
    int id;
    public Key(int id) {
        this.id = id;
    }
    public boolean pickUp(Player person) {
        System.out.println("KEY " + id + " WAS PICKED UP");
        person.inv.add("<"+id+">");
        return true;
    }

}
