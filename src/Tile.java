import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import static java.util.Map.entry;
import java.util.HashMap;

public class Tile {
  Object object;
  String symbol;
  String dir;
  String backDir;
	String propName;
  Random rand = new Random();

  public Tile(String propName, String object, String[] params) {
    this.propName = propName;
		if(object.length() > 1){
      System.out.println(object);
			int x = 1/0;
		}
		// this.backDir = ".//Icons//FloorIcon//TileIcon" + (new Random()).nextInt(4) + ".png";
		this.backDir = Main.cl.getResource("Icons/FloorIcon/Tile" + (new Random()).nextInt(5) + ".png").toString().substring(5);
		// this.backDir = ".//Icons//FloorIcon//FLOOR.png";
    this.symbol = object;
    switch (object) {
			case "O":
				this.object = Main.person;
				this.dir ="PlayerIcon.png";
				break;
      case "-":
				this.object = null;
				break;
      case ":":
				this.object = new Decoration(params[0]);
				this.dir = ((Decoration)this.object).dir;
				break;
      case "H":
      // case " ": < WHY DOES THIS EXIST??? no fking clue
			int x = 1/0;
				break;
      case "#":
				this.object = new Ladder();
				this.dir = (new Ladder()).dir;
				break;
      case "$":
				this.object = new Coin();
				this.dir = (new Coin()).dir;
				break;
      case "@":
				this.object = new Health();
				this.dir = (new Health()).dir;
				break;
      case "P":
        this.object = new RockPass();
				this.dir = ((RockPass)this.object).dir;
        break;
      case "J":
				this.object = new Pot(Integer.parseInt(String.join("",params)));//has to be double digits
				this.dir = ((Pot)this.object).dir;
				break;
      case "*":
				this.object = new AbilUnlock(Integer.parseInt(params[0]));
				this.dir = ((AbilUnlock)this.object).dir;
				break;
      case "!":
				this.object = new Steroid();
				this.dir = (new Steroid()).dir;
				break;
      case "/":
				this.object = new Note(Integer.parseInt(params[0]));
				this.dir = ((Note)this.object).dir;
				break;
      case "=":
				this.object = new Stopper();
				this.dir = (new Stopper()).dir;
				break;
      case "+":
				this.object = new OmniSlider();
				this.dir = (new OmniSlider()).dir;
				break;
      case "R": //random shop
				this.object = new Shop();
				this.dir = ((Shop)this.object).dir;
				break;
      case "S": //doesnt use other shops yet [perbuy, amount]
				HashMap<Item, int[]> hm = new HashMap<>();
				hm.put(new MaxHp(), new int[]{5, 20});
				hm.put(new Steroid(), new int[]{1, 10});
				hm.put(new AbilUnlock(1), new int[]{1, 1});
				this.object = new Shop(hm);
				this.dir = ((Shop)this.object).dir;
				break;
      case "G":
        this.object = new Boulder(Integer.parseInt(params[0]), Integer.parseInt(params[1]), params[2]);
				this.dir = ((Boulder)this.object).dir;
        break;
      case "V":
				this.object = new Slider(params[0]);
				this.dir = ((Slider)this.object).dir;
				break;
      case "U":
				this.object = new Teleporter(Integer.parseInt(params[0]),Integer.parseInt(params[1]));
				this.dir = ((Teleporter)this.object).dir;
				break;
      case "|":
				this.object = new Door(Integer.parseInt(params[0]));
				this.dir = ((Door)this.object).dir;
				break;
      case "<":
				this.object = new Key(Integer.parseInt(params[0]));
				this.dir = ((Key)this.object).dir;
				break;
      case "L":
				this.object = new Switch(Integer.parseInt(params[0]));
				this.dir = ((Switch)this.object).dir;
				break;
      case "_":
				this.object = new Plate(Integer.parseInt(params[0]));
				this.dir = ((Plate)this.object).dir;
				break;
      case "&": //NEED TRY CATCH IF WANTING SINGLE DIGIT TIMER
				switch(String.valueOf(rand.nextInt(2))) {
					case "0":
							this.object = new AsciiCipher(Integer.parseInt(String.join("", params)));
							break;
					case "1":
							this.object = new PatternCipher(Integer.parseInt(String.join("", params)));
							break;
				}
				this.dir = ((Cipher)this.object).dir;
				break;
      case "^":
				this.object = new SwitchDoor(Integer.parseInt(params[0]), Integer.parseInt(params[2]), Integer.parseInt(params[3]));
				this.dir = ((SwitchDoor)this.object).dir;
				break;
      case "0":
        this.object = new Portal(Integer.parseInt(params[0]), params[1]);
				this.dir = ((Portal)this.object).dir;
        break;
      case "Y":
        this.object = new Void();
        this.dir = ((Void)this.object).dir;
        break;
      case "7":
				this.object = new CheckPoint();
				this.dir = ((CheckPoint)this.object).dir;
        break;
      case "~":
        this.object = new theSomething(Integer.parseInt(params[0]));
        this.dir = ".png";
        break;

      // START OF ENEMIES	
      case "E":
				this.object = new Enemy("Neelan");
				this.enemyInit(params);
				break;
      case "F":
        this.object = new Enemy("Prisoner");
        this.enemyInit(params);
				break;
      case "D":
        this.object = new Enemy("Dual");
        this.enemyInit(params);
				break;
      case "8": 
				this.object = new Enemy("Panda");
				this.enemyInit(params);
				break;	
      case "K": 
				this.object = new Enemy("Koala");
				this.enemyInit(params);
				break;
      case "M": 
				this.object = new Enemy("Metadog");
				this.enemyInit(params);
				break;
      case "2":
				this.object = new Enemy("Clone");
				this.enemyInit(params);
				break;	
      case "I":
				this.object = new Enemy("Penguin");
				this.enemyInit(params);
				break;	
      case "%":
				this.object = new Enemy("Moe");
				this.enemyInit(params);
				break;	
      case "C":
				this.object = new Enemy("Octopus");
				this.enemyInit(params);
				break;	
      case "?":
				this.object = new Enemy("Jester");
				this.enemyInit(params);
				break;	
			case "N":
				this.object = new Enemy("Dood");
				this.dir = Main.cl.getResource("Icons/EnimeIcon/"+ (params[0].equals("0") ? "Neelan.png" : "Dood" + params[0] + ".png")).toString().substring(5);
        break;
      default:
				this.object = null;
				break;

    }
  }
	public Tile(String[][] level, int x, int y){
		this.propName = "H  ";
    this.symbol = "H";
		this.object = new Walls(level, x ,y);
		this.dir = ((Walls)this.object).dir;
	}   
  public void enemyInit(String[] params){
		this.dir = ((Enemy)this.object).dir;
		if(!params[0].equals(" ")) {
				((Enemy)this.object).addKey(Integer.parseInt(params[0]));
		}
		return;
  }
}
