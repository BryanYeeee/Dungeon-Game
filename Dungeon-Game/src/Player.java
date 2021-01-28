import java.util.Random;

public class Player {
    int strength;
    int health;
    int worth;
    int x;
    int y;
    public boolean isAlive = true;
    public boolean atLadder = true;
    public static Inventory inv = new Inventory();

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
                Object thing = level.map[this.x + movex][this.y + movey].object;
                if(thing instanceof Item) {

                    ((Item)thing).pickUp(this);
                    level.map[this.x + movex][this.y + movey] = new Tile(" - ");
                } else if(thing instanceof Ladder) {

                    this.atLadder = true;
                    return true;
                } else if (thing instanceof Enemy) {

                    Enemy victim = ((Enemy)thing);
                    this.fight(victim.health, victim.strength, victim.richness, victim.keyDrop);
                    if(this.isAlive) {
                        level.map[this.x + movex][this.y + movey] = new Tile(" - ");
                    } else {
                        return false;
                    }
                } else if (thing instanceof Shop) {

                    ((Shop)thing).outputShop(this);
                    return true;
                } else if (thing instanceof Door) {

                    if(this.inv.items.contains("<"+(((Door)level.map[this.x + movex][this.y + movey].object).id)+">")) {
                        level.map[this.x + movex][this.y + movey] = new Tile(" - ");
                    } else {
                        System.out.println("You need a key");
                        return false;
                    }
                }
                Tile tempTile = level.map[this.x + movex][this.y + movey]; //swapping tiles to move
                level.map[this.x + movex][this.y + movey] = level.map[this.x][this.y];
                level.map[this.x][this.y] = tempTile;
                this.setLocation(this.x + movex, this.y + movey);
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | StringIndexOutOfBoundsException e) {
            System.out.println("Error in Move: " + e);
            return false;
        }
    }

    public void fight(int eHealth, int eStrength, int eWorth, int eKey) {// e=enemy
        Random rand = new Random();
        while(true) {
            eHealth = eHealth - (rand.nextInt(this.strength) + this.strength);
            if(eHealth <= 0) {
                this.worth = this.worth + eWorth;
                if(eKey > 0) {
                    this.inv.add("<"+eKey+">");
                }
                return;
            }
            this.health = this.health - (rand.nextInt(5) + eStrength);
            if(this.health <= 0) {
                this.health = 0;
                this.isAlive = false;
                return;
            }
            System.out.println("== phealth:" +  this.health + " pStrength:" + this.strength + " eHealth:" + eHealth + " eStrength:" + eStrength + " ==");
        }
    }
}
