public class Player {
    int strength;
    int health;
    int worth;
    int x;
    int y;
    public boolean atLadder = true;

    public Player(int strength, int health, int worth){
        this.strength = strength;
        this.health = health;
        this.worth = worth;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        return;
    }

    public boolean move(String move, Level level, boolean doMove) {
        try {
            String Direction = move.substring(0, 1);
            int movex = 0, movey = 0, Count = 1;
            if (move.length() > 1) {
                Count = Integer.parseInt(move.substring(1, 2));
            }
            switch (Direction) {
                case "w":
                    movex = -1;
                    break;
                case "a":
                    movey = -1;
                    break;
                case "s":
                    movex = 1;
                    break;
                case "d":
                    movey = 1;
                    break;
            }
            for (int i = 0; i <= Count; i++) {
                if (level.map[this.x + movex * i][this.y + movey * i].object instanceof Wall) {
                    return false;
                } else if(level.map[this.x + movex * i][this.y + movey * i].object instanceof Item && doMove) {
                    ((Item)level.map[this.x + movex * i][this.y + movey * i].object).pickUp(this);
                    level.map[this.x + movex * i][this.y + movey * i] = new Tile("-");
                } else if(level.map[this.x + movex * i][this.y + movey * i].object instanceof Ladder && doMove) {
                    this.atLadder = true;
                    return true;
                }
            }
            if(doMove) {
                Tile tempTile = level.map[this.x + movex * Count][this.y + movey * Count];
                //swapping tiles to move
                level.map[this.x + movex * Count][this.y + movey * Count] = level.map[this.x][this.y];
                level.map[this.x][this.y] = tempTile;
                this.setLocation(this.x + movex * Count, this.y + movey * Count);
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException |       StringIndexOutOfBoundsException e) {
            System.out.println("Error in Move");
            return false;
        }
    }
}
