public class Enemy {
    public int health;
    public int strength;
    int x;
    int y;
    int richness;
    int keyDrop = 0;

    public Enemy(int strength, int health, int x, int y, int richness) {
        this.health = health;
        this.strength = strength;
        this.x = x;
        this.y = y;
        this.richness = richness;
    }

    public void addKey(int id) {
        this.keyDrop = id;
        return;
    }



}