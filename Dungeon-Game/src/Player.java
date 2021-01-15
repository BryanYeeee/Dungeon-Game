import java.util.Random;

public class Player {
    int strength;
    int health;
    int worth;
    int x;
    int y;
    public boolean isAlive = true;
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
            int movex = 0, movey = 0;
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
            if (level.map[this.x + movex][this.y + movey].object instanceof Wall) {
                return false;
            }

            if(doMove) {
                if(level.map[this.x + movex][this.y + movey].object instanceof Item) {
                    ((Item)level.map[this.x + movex][this.y + movey].object).pickUp(this);
                    level.map[this.x + movex][this.y + movey] = new Tile("-");
                } else if(level.map[this.x + movex][this.y + movey].object instanceof Ladder) {
                    this.atLadder = true;
                    return true;
                } else if (level.map[this.x + movex][this.y + movey].object instanceof Enemy) {
                    Enemy victim = ((Enemy)level.map[this.x + movex][this.y + movey].object);
                    this.fight(victim.health, victim.strength, victim.richness);

                    if(this.isAlive) {
                        level.map[this.x + movex][this.y + movey] = new Tile("-");
                    } else {
                        return false;
                    }

                }else if (level.map[this.x + movex][this.y + movey].object instanceof Shop) {
                    System.out.println("HERERERERERRERERERE");
                    //
                    //
                    //
                    //
                    ((Shop)(level.map[this.x + movex][this.y + movey].object)).outputShop();
                    return true;
                }
                Tile tempTile = level.map[this.x + movex][this.y + movey]; //swapping tiles to move
                level.map[this.x + movex][this.y + movey] = level.map[this.x][this.y];
                level.map[this.x][this.y] = tempTile;
                this.setLocation(this.x + movex, this.y + movey);
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("Error in Move");
            return false;
        }
    }

    public void fight(int eHealth, int eStrength, int eWorth) {// e=enemy
        Random rand = new Random();
        for(int i = this.health; i >= 0; i=i-(rand.nextInt(5) + eStrength)) {
            System.out.println("== phealth:" + i + " pStrength:" + this.strength + " eHealth:" + eHealth + " eStrength:" + eStrength + " ==");
            if(this.health <= 0) {
                this.health = 0;
                this.isAlive = false;
                return;
            }
            eHealth = eHealth - (rand.nextInt(this.strength) + this.strength);
            if(eHealth <= 0) {
                this.health = i;
                this.worth = this.worth + eWorth;
                return;
            }
        }
    }
}
