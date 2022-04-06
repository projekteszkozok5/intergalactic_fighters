/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/** This class is used to generate maps.
 *
 * @author Kuti Viktória
 * @version 1.0
 * @since 1.0
 */
public class Map {
    private ArrayList<Wall> walls;
    
    
    public Map() {
        walls = new ArrayList<>();
    }
    
    
    /** String representing a fixed map,
     * that only contains walls.
     * 
     */
    private String map = 
            "11111111111111111111x"
          + "10001000000000000001x"
          + "10001000000000000001x"
          + "10001000001000000001x"
          + "10011100001000111111x"
          + "10000000001111100001x"
          + "10000000000000000001x"
          + "11100111000000000001x"
          + "10000100000000000001x"
          + "10000100001111110001x"
          + "10000100000000011111x"
          + "10000101111000010001x"
          + "10000100001000010001x"
          + "10000100001000010001x"
          + "11111111111111111111x";
    
    
    /** Generates a map from a String.
     * 
     * @return An ArrayList containing the walls.
     */
    public ArrayList<Wall> generateMap() {
	int i = 0;
	int x = 0;
	int y = 0;

	while(i < map.length()) {
            if(map.charAt(i) == '1') { //x tengely iranyaba allo fal: -
                walls.add(new Wall(x, y, 40, 40, new ImageIcon(this.getClass().getResource("/design/images/black.png")).getImage()));
                x += 40;
            }
            if(map.charAt(i) == '0') { //sorvege karakter: x
                x += 40;
            }
            if(map.charAt(i) == 'x') { //sorvege karakter: x
                x = 0;
                y += 40;
            }
            i++;
	}
        
        return walls;
    }
}
