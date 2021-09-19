import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;

public class Ebility{
	public static JLabel makeTile(String enemyName, String symb) {
		switch(enemyName){
			case "Koala":
			case "Dood":
				int degrees =
				Bossibility.DoodKoala && Main.boss.phase >= 3 ? (new Random()).nextInt(5)*90:
				Bossibility.DoodKoala || enemyName.equals("Koala") ? 180 : 0;
				return new JLabel(new ImageIcon(Main.rotate(Main.cl.getResource("Icons/InpTilesIcon/"+symb+"Inp.png").toString().substring(5), degrees)));
			case "Jester":
				return new JLabel(new ImageIcon(symb.equals("?") ? Main.cl.getResource("Icons/InpTilesIcon/jesterInp.png").toString().substring(5) : (Main.cl.getResource("Icons/InpTilesIcon"+symb + "Inp.png").toString().substring(5))));
			default:
				return new JLabel(new ImageIcon(Main.cl.getResource("Icons/InpTilesIcon/"+symb + "Inp.png").toString().substring(5)));
		}
	}
	public static void updateInputList(String enemyName, ArrayList<String> Inputs, ArrayList<String> jestDis) {
	
			if(enemyName.equals("Jester")) {
				for (int i = 0; i < 5; i++) {
					System.out.println(Arrays.asList(jestDis));
					if(Main.gui.fightScene.curAbility instanceof Strike && (Inputs.size()-Main.gui.fightScene.InpPanel.tileLeft) > 3){
						Main.gui.fightScene.InpPanel.add(new InpTile(jestDis.get(i+Inputs.size()-Main.gui.fightScene.InpPanel.tileLeft-3)));
					} else {
						Main.gui.fightScene.InpPanel.add(new InpTile(jestDis.get(i)));
					}
				}
			} else {
				for (int i = 0; i < Inputs.size(); i++) {
						Main.gui.fightScene.InpPanel.add(new InpTile(Inputs.get(i)));
				}
			}
	}
	public static int takeDMG(String enemyName, int strength, int errors) {
		int damage = (new Random()).nextInt(10) + strength;
		// System.out.println("THE ENEMIES NAME IS " + enemyName + " " + strength + " " + errors);
		switch(enemyName){
			case "Metadog":
				Main.gui.fightScene.victim.name = "Sedicat";
				Main.gui.fightScene.detailsPanel.updateDetails("errors+ = <br>dmg taken+",Main.gui.fightScene.victim.name);
        Main.gui.fightScene.enemy.setIcon(new ImageIcon(Main.cl.getResource("Icons/EnimeIcon/Sedicat.png").toString().substring(5)));
				break;
			case "Sedicat": //MIGHT NEED TO NERF BC MATTHEW OP
				Main.gui.fightScene.victim.name = "Metadog";
				Main.gui.fightScene.detailsPanel.updateDetails("changes <br>form",Main.gui.fightScene.victim.name);
        Main.gui.fightScene.enemy.setIcon(new ImageIcon(Main.cl.getResource("Icons/EnimeIcon/Metadog.png").toString().substring(5)));
				errors++;
				damage = (new Random()).nextInt(errors*10) + strength*errors;
				break;
			default:
				break;
		}
		return damage;
	}

	public static int incrTime(String enemyName, int timePast){
		if(enemyName.equals("Moe")||Bossibility.DoodMoe){	
			Main.gui.fightScene.InpPanel.repaint();
			int newPerc = Main.gui.fightScene.InpPanel.fadingperc;
			Main.gui.fightScene.InpPanel.fadingperc = 
			timePast <= 1000 ? newPerc+1 : 
			(timePast > 4000 ? newPerc-1 : 
			(timePast == 5000 ? 0 : newPerc));

			return timePast == 5000 ? 0 : timePast+10;
		}
		return timePast+10;
	}

	public static ArrayList<String> iterInp(String enemyName, ArrayList<String> Inputs){
		ArrayList<String> newInputs = Inputs;
		if(enemyName.equals("Jester")){
			if(!Main.gui.fightScene.InpPanel.jestDis.get(4).equals("?")) {
			}
			Main.gui.fightScene.InpPanel.jestDis.set((Main.gui.fightScene.InpPanel.jestDis.size()-(Main.gui.fightScene.InpPanel.tileLeft+1)),newInputs.get(0));
		}
    newInputs.remove(0);
    newInputs.add(" ");
		if(enemyName.equals("Octopus")||Bossibility.DoodOcto){	
			try {
				int posA = (new Random()).nextInt(Math.min(7,Main.gui.fightScene.InpPanel.tileLeft-1))+1;
				int posB = (new Random()).nextInt(Math.min(7,Main.gui.fightScene.InpPanel.tileLeft-1))+1;
				posA = Main.gui.fightScene.InpPanel.tileLeft == 2 ? 0 : posA;
        while (posB==posA){
          posB = (new Random()).nextInt(Math.min(7,Main.gui.fightScene.InpPanel.tileLeft-1))+1;
        }

				System.out.println(posA + " " + posB+ " " + Main.gui.fightScene.InpPanel.tileLeft);
				if(!newInputs.get(posA).equals(" ") && !newInputs.get(posB).equals(" ")) {
					Main.gui.fightScene.InpPanel.swapPos = new int[]{posA, posB};
					Collections.swap(newInputs, posA, posB);
        	System.out.println("Swapped List " + newInputs);
				}
			} catch (Exception e) {
					Main.gui.fightScene.InpPanel.swapPos = new int[2];
				System.out.println(e);
			}
		}
		Main.gui.fightScene.InpPanel.repaint();
		return newInputs;
	}
  
   public static void mathInp(String enemyName){
    if(enemyName.equals("Jester")){
      Main.gui.fightScene.add(Main.gui.fightScene.quiztime, Integer.valueOf(1));
      Random rand = new Random();
      ArrayList<String> WASD = new ArrayList<String>(Arrays.asList("W","A","S","D"));
      ArrayList<Integer> ans = new ArrayList<Integer>();

			int firstnumber = rand.nextInt(10);
      int secondnumber = rand.nextInt(11);
      int answer = firstnumber + secondnumber;
      System.out.println(firstnumber +"+"+ secondnumber);

      for(int i=0;i<4;i++){//make sure its not printing out answer
				int number = rand.nextInt(20);
				while(ans.contains(number) || number == answer) {
					number = rand.nextInt(20);
				}
				if(WASD.get(0).equals(Main.gui.fightScene.InpPanel.Inputs.get(0))){
					System.out.println(Main.gui.fightScene.InpPanel.Inputs.get(0)+":"+answer);
					ans.add(answer);
				} else {
					System.out.println(WASD.get(0)+":"+number);
					ans.add(number);
				}
				WASD.remove(0);
				
      }
      Main.gui.fightScene.quiztime.updateJesterGUI(firstnumber, secondnumber, ans);
      System.out.println("//////////////");
   }
  } 

}