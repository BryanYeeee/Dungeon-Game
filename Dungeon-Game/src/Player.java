public class Player {
    int strength;
    int health;
    int x;
    int y;
    // mana, energy, armour, money?

    public Player(int strength, int health) {
        this.strength = strength;
        this.health = health;
    }
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        return;
    }

    public boolean move(String move, Level level,boolean doMove) {
        try {
            switch (move) {
                case "w":
                    Tile tempTile = level.map[this.x - 1][this.y]; //swapping tiles to move
                    level.map[this.x - 1][this.y] = level.map[this.x][this.y];
                    level.map[this.x][this.y] = tempTile;
                    this.setLocation(this.x - 1, this.y);
                    break;

            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;

        }
    }
}
