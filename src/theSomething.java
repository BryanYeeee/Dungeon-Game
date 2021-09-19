public class theSomething {

  String dir = ".png";
  int something;
  
    public theSomething(int something) {
      this.something = something;
    }
   
    public void doSomething(){
      switch(this.something){
        case 1:
        Main.arlofLevels.get(Main.currentLvl).map[4][10] = new Tile("|1 ","|", new String[]{"1"});
        Main.boss.phase = 1;
		Main.boss.phaseOne("Weak", 10);
        break;
      }
      
    }

    
}