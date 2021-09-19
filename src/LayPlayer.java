import java.util.ArrayList;
public class LayPlayer {
ArrayList<Tile[][]> arlofPlayerLayer;

    public LayPlayer() {
     arlofPlayerLayer = new ArrayList<>();
        for(int i=0;i<Main.arlofLevels.size();i++){
          Tile[][] PlayerLayers = new Tile[Main.arlofLevels.get(i).map.length][Main.arlofLevels.get(i).map[0].length];
          for(int j=0;j<Main.arlofLevels.get(i).map.length;j++){
            for(int k=0;k<Main.arlofLevels.get(i).map[0].length;k++){
              PlayerLayers[j][k] = new Tile("-  ","-", new String[1]);

            }
          }
          // PlayerLayers[(Main.arlofLevels.get(i)).startx][(Main.arlofLevels.get(i)).starty] = new Tile(" O "); <??????
          PlayerLayers[(Main.arlofLevels.get(i)).startx][(Main.arlofLevels.get(i)).starty] = new Tile("O  ","O", new String[1]);
          arlofPlayerLayer.add(PlayerLayers);

        }

    }

}