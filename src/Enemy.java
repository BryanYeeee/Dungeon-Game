import java.util.Random;
import java.util.HashMap;

public class Enemy {

    public String name;
    public int health;
    public int strength;
    String weak;
    String resist;
    int x;
    int y;
    double richness;
    int keyDrop = 0;
    String dir;

    public String[] possibleKeys;
    public int timeLeft;
    public String abilities;
    public int inpSize;
    public HashMap<String, String> actHm;
    public HashMap<String, Integer> status;

    public Enemy(String name) {
			this.status = new HashMap<>();
			this.name = name;
			this.dir = Main.cl.getResource("Icons/EnimeIcon/"+name+".png").toString().substring(5);
      switch(name){//str,hp,$,res,weak,size,time,abil,keys,act
        case "Neelan":
				this.makeStats(10,10,(new Random()).nextInt(2)+3,"none","none",20,20,"NONe10", new String[]{"W","A","S","D",}, new String[][]{{"W","A","S","D"}, {"W", "A","S","D"}});
        break;
        case "Prisoner":
        this.makeStats(15,15,(new Random()).nextInt(2)+4,"none","none",20,20,"Orange Clothes", new String[]{"W","A","S","D"}, new String[][]{{"W","A","S","D"}, {"W","A","S","D"}});
        break;

        case "Metadog":
        this.makeStats(15,30,(new Random()).nextInt(5)+15,"sword","hammer",10,10,"???", new String[]{"W","A","S","D"},new String[][]{{"W","A","S","D"}, {"W","A","S","D"}});
        break;
        case "Clone":
        this.makeStats(20,25,(new Random()).nextInt(5)+15,"spell","sword",20,20,"Uses IJKL", new String[]{"I","J","K","L"}, new String[][]{{"I","J","K","L"}, {"I","J","K","L"}});
        break;
        case "Koala":
        this.makeStats(10,25,(new Random()).nextInt(5)+15,"hammer","spell",20,20,"NMOp<br>ƎpISԀ∩", new String[]{"W","A","S","D"},new String[][]{{"W","A","S","D"}, {"W", "A","S","D"}});
        break;

        case "Penguin":
        this.makeStats(15,40,(new Random()).nextInt(5)+15,"sword","spell",20,20,"swap i&k <br>j&l", new String[]{"I","J","K","L"}, new String[][]{{"I","J","K","L"}, {"K","L","I","J"}});
        break;
        case "Octopus":
        this.makeStats(15,45,(new Random()).nextInt(5)+15,"spell","hammer",20,20,"tiles change", new String[]{"W","A","S","D"},new String[][]{{"W","A","S","D"}, {"W", "A","S","D"}});
        break;
        case "Panda":
				this.makeStats(15,50,(new Random()).nextInt(5)+15,"hammer","sword",10,10,"swap w&s <br>a&d", new String[]{"W","A","S","D"},new String[][]{{"W","A","S","D"}, {"S","D","W","A"}});
        break;

        case "Moe":
        this.makeStats(30,100,(new Random()).nextInt(5)+15,"/spell","none",20,20,"Blinds you ;)", new String[]{"W","A","S","D"},new String[][]{{"W","A","S","D"}, {"W","A","S","D"}});
        break;
        case "Jester": 
        this.makeStats(30,100,(new Random()).nextInt(5)+15,"/hammer","none",5,50,"QUIZ TIME", new String[]{"W","A","S","D"},new String[][]{{"W","A","S","D"}, {"W","A","S","D"}});
        break;
        case "Dual":
        this.makeStats(30,100,0,"/sword","none",20,20,"ALLL SWAP", new String[]{"W","A","S","D","I","J","K","L"}, new String[][]{{"W","A","S","D","I","J","K","L"}, {"S","D","W","A", "K","L","I","J"}});
        break;

        case "Dood":
				this.makeStats(60,150,9999,"none","none",20,20,"DOOOOD", new String[]{"W","A","S","D"}, new String[][]{{"W","A","S","D"}, {"W", "A","S","D"}});
        break;
        case "Rock":
				this.dir = "kBoulderIcon.png";
        this.makeStats(1,100,0,"sword","none",20,20,"None", new String[]{"W","A","S","D"}, new String[][]{{"W","A","S","D"}, {"W","A","S","D"}});
        break;
      }

    }
		public void makeStats(int strength, int health, double richness, String resist, String weak, int inpSize, int timeLeft, String abilities, String[] possibleKeys, String[][] actarr){
			this.strength = strength;
			this.health = health;
			this.richness = richness;
			this.resist = resist;
			this.weak = weak;
			this.inpSize = inpSize;
			this.timeLeft = timeLeft;
			this.abilities = abilities;
			this.possibleKeys = possibleKeys;
			this.arrToHm(actarr);
			return;
		}
    public void arrToHm(String[][] array){
      this.actHm = new HashMap<>();
      for(int i=0;i<array[0].length;i++){
        this.actHm.put(array[0][i], array[1][i]);
      }
      return;
    }

    public void addKey(int id) {
        this.keyDrop = id;
        return;
    }

}