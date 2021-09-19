import java.util.Arrays;
public class Walls {
  String dir = "Wall.png";

    public Walls(String[][] level, int x, int y) {
			String modifier = "";
      try {
				if(!level[x-1][y].equals("H  ")){
				// System.out.println(!level[x-1][y]+"]");
					modifier += "T";
				}
			} catch (Exception e){
			}
      try {
				if(!level[x+1][y].equals("H  ")){
					modifier += "B";
				}
			} catch (Exception e){
			}
      try {
				if(!level[x][y+1].equals("H  ")){
					modifier += "R";
				}
			} catch (Exception e){
			}
      try {
				if(!level[x][y-1].equals("H  ")){
					modifier += "L";
				}
			} catch (Exception e){
			}
			if(modifier.equals("")){
				try {
					if(!level[x+1][y+1].equals("H  ")){
						modifier += "CBR";
					}
				} catch (Exception e){
				}
				try {
					if(!level[x+1][y-1].equals("H  ")){
						modifier += "CBL";
					}
				} catch (Exception e){
				}
				try {
					if(!level[x-1][y+1].equals("H  ")){
						modifier += "CTR";
					}
				} catch (Exception e){
				}
				try {
					if(!level[x-1][y-1].equals("H  ")){
						modifier += "CTL";
					}
				} catch (Exception e){
				}
			}
//		System.out.println(modifier + " "+ x + " " + y);
//		 for(int i = 0; i < level.length; i++) { //rows
//		     for(int j = 0; j < level[0].length; j++) { //collumns
//		         System.out.print("|"+level[i][j] + "|");
//		     }
//		     System.out.println();
//		 }
			this.dir = Main.cl.getResource("Icons/WallIcon/"+modifier+this.dir).toString().substring(5);
    }
}
