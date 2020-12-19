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

    public boolean move(String move, Level level, boolean doMove) {
        try {
            String Direction = move.substring(0, 1);
            int Count = 1;
            if (move.length() > 1) {
                Count = Integer.parseInt(move.substring(1, 2));
            }
            int movex = 0;
            int movey = 0;
            switch (Direction) {
                case "w" -> movex = -1;
                case "a" -> movey = -1;
                case "s" -> movex = 1;
                case "d" -> movey = 1;
            }
            for (int i = 1; i < Count; i++) {
                if (!level.map[this.x + movex * i][this.y + movey * i].symbol.equals("-")) {
                    System.out.println(level.map[this.x + movex * i][this.y + movey * i].symbol);
                    return false;
                }
            }
            if(doMove) {
                Tile tempTile = level.map[this.x + movex * Count][this.y + movey * Count]; //swapping tiles to move
                level.map[this.x + movex * Count][this.y + movey * Count] = level.map[this.x][this.y];
                level.map[this.x][this.y] = tempTile;
                this.setLocation(this.x + movex * Count, this.y + movey * Count);
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error in Move");
            return false;

        }
    }
}
