import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;

public class Bossibility{//WANT TO GO THROUGH ALL ATTACKS ATLEAST ONCE WITH MAX STR
  public static boolean DoodOcto;
  public static boolean DoodMoe;
  public static boolean DoodKoala;
  static Random rand = new Random();

  public static ArrayList<String> allAbil = new ArrayList<>(Arrays.asList(
		"WASD", 
		"SPAM W", 
		"SPAM A",
		"SPAM S", 
		"SPAM D",
		"I&K J&L",
		"W&S A&D<br>I&K J&L",
		"W&A S&D<br>I&J K&L",
		"WASD<br>IJKL<br>TFGH",
		"W&S A&D I&K<br>J&L T&G F&H"
		));
  public static ArrayList<String[]> allInputs = new ArrayList<>(Arrays.asList(
		new String[]{"W","A","S","D"},
		new String[]{"W"},
		new String[]{"A"},
		new String[]{"S"},
		new String[]{"D"},
		new String[]{"I","J","K","L"},
		new String[]{"W","A","S","D","I","J","K","L"},
		new String[]{"W","A","S","D","I","J","K","L"},
		new String[]{"W","A","S","D","I","J","K","L","T","F","G","H"},
		new String[]{"W","A","S","D","I","J","K","L","T","F","G","H"}
		));
  public static ArrayList<String[][]> allMaps = new ArrayList<>(Arrays.asList(
		new String[][]{{"W","A","S","D"}, {"W", "A","S","D"}},
		new String[][]{{"W"}, {"W"}},
		new String[][]{{"A"}, {"A"}},
		new String[][]{{"S"}, {"S"}},
		new String[][]{{"D"}, {"D"}},	
		new String[][]{{"I","J","K","L"}, {"K","L","I","J"}},
		new String[][]{{"W","A","S","D","I","J","K","L"}, {"S","D","W","A", "K","L","I","J"}},
		new String[][]{{"W","A","S","D","I","J","K","L"}, {"A","W","D","S", "J","I","L","K"}},
		new String[][]{{"W","A","S","D","I","J","K","L","T","F","G","H"},{"W","A","S","D","I","J","K","L","T","F","G","H"}},
		new String[][]{{"W","A","S","D","I","J","K","L","T","F","G","H"},{"S","D","W","A","K","L","I","J","G","H","T","F"}}
	));
	public static ArrayList<Integer[]> sizeTime = new ArrayList<>(Arrays.asList(//SIZE TIME
		new Integer[]{20,20},
		new Integer[]{25,10},
		new Integer[]{25,10},
		new Integer[]{25,10},
		new Integer[]{25,10},
		new Integer[]{20,13},
		new Integer[]{25,15},
		new Integer[]{25,15},
		new Integer[]{25,15},
		new Integer[]{25,17}
		));
	public static ArrayList<String> weaknesses = new ArrayList<>(Arrays.asList("hammer", "sword", "spell"));

	public static String abilIII = "FULL POWER";
	public static String[] inputsIII = {
		"A","B","C","D","E","F","G","H","I","J",
		"K","L","M","N","O","P","Q","R","S","T",
		"U","V","W","X","Y","Z"};
	public static String[][] mapIII = {{
		"A","B","C","D","E","F","G","H","I","J",
		"K","L","M","N","O","P","Q","R","S","T",
		"U","V","W","X","Y","Z"}, {
		"A","B","C","D","E","F","G","H","I","J",
		"K","L","M","N","O","P","Q","R","S","T",
		"U","V","W","X","Y","Z"}};
		public static ArrayList<Integer[]> sizeTimeIII = new ArrayList<Integer[]>(Arrays.asList(//SIZE TIME
			new Integer[]{50,50},
			new Integer[]{100,100}
		));

	public static void nextATK(){// all false = 0.7109375, least1 = 0.2890625, all true = 0.007
    if(Main.boss.phase == 2){
			DoodMoe = false;
			DoodOcto = false;
			DoodKoala = false;
			if(rand.nextInt(2)==1){
					DoodMoe = rand.nextInt(4) == 1 ? true : false;
					DoodOcto = rand.nextInt(4) == 1 ? true : false;
					DoodKoala = rand.nextInt(4) == 1 ? true : false;
			}
			Main.gui.fightScene.victim.abilities = allAbil.get(0);
			allAbil.add(allAbil.get(0));
			allAbil.remove(0);

			Main.gui.fightScene.victim.possibleKeys = allInputs.get(0);
			allInputs.add(allInputs.get(0));
			allInputs.remove(0);
			Main.gui.fightScene.arlofActions.clear();
			for(int i=0;i<Main.gui.fightScene.victim.possibleKeys.length;i++){
				Main.gui.fightScene.arlofActions.add(new inpAction(Main.gui.fightScene.victim.possibleKeys[i]));
			}

			System.out.println(Main.gui.fightScene.actHm);
			Main.gui.fightScene.actHm = arrToHm(allMaps.get(0));
			allMaps.add(allMaps.get(0));
			allMaps.remove(0);

			Main.gui.fightScene.victim.timeLeft = sizeTime.get(0)[1];
			Main.gui.fightScene.victim.inpSize = sizeTime.get(0)[0];
			sizeTime.add(sizeTime.get(0));
			sizeTime.remove(0);
    } else if (Main.boss.phase >= 3){
			if(Main.boss.phase == 4){
				DoodMoe = true;
				DoodOcto = true;
				DoodKoala = true;
				Main.gui.fightScene.victim.timeLeft = 100;//size and time of last attack
				Main.gui.fightScene.victim.inpSize = 100;
				
			} else {
				DoodMoe = false;
				DoodOcto = false;
				DoodKoala = false;
				switch(rand.nextInt(3)){
					case 0:
						DoodMoe = true;
						break;
					case 1:
						DoodOcto = true;
						break;
					case 2:
						DoodKoala = true;
						break;
				}
				
				if(rand.nextInt(2)==1){
					boolean chosenII = false;
					while(!chosenII){
						switch(rand.nextInt(3)){
							case 0:
									chosenII = !DoodMoe ? true : false;
									DoodMoe = !DoodMoe ? true : false;
								break;
							case 1:
									chosenII = !DoodOcto ? true : false;
									DoodMoe = !DoodOcto ? true : false;
								break;
							case 2:
									chosenII = !DoodKoala ? true : false;
									DoodMoe = !DoodKoala ? true : false;
								break;
						}
					}
				}
				Main.gui.fightScene.victim.timeLeft = sizeTimeIII.get(0)[1];
				Main.gui.fightScene.victim.inpSize = sizeTimeIII.get(0)[0];
				sizeTimeIII.add(sizeTime.get(0));
				sizeTimeIII.remove(0);
			}
			Main.gui.fightScene.victim.abilities = abilIII;

			Main.gui.fightScene.victim.possibleKeys = inputsIII;
			Main.gui.fightScene.arlofActions.clear();
			for(int i=0;i<Main.gui.fightScene.victim.possibleKeys.length;i++){
				Main.gui.fightScene.arlofActions.add(new inpAction(Main.gui.fightScene.victim.possibleKeys[i]));
			}

			Main.gui.fightScene.actHm = arrToHm(mapIII);
		}
    Main.gui.fightScene.victim.resist = "/"+weaknesses.get(1) + "/" + weaknesses.get(2); 
		Main.gui.fightScene.victim.weak = weaknesses.get(0);
		weaknesses.add(weaknesses.get(0));
		weaknesses.remove(0);
		System.out.println("THE BOSS'S WEAKNESS IS NOW " + Main.gui.fightScene.victim.weak);
	}

	 public static HashMap<String, String> arrToHm(String[][] array){
      HashMap<String, String> actHm = new HashMap<>();
      for(int i=0;i<array[0].length;i++){
        actHm.put(array[0][i], array[1][i]);
      }
      return actHm;
    }
}