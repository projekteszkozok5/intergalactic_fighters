/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters.sprites.enemies;

import intergalactic_fighters.Enemy;
import java.awt.Point;

/**
 *
 * @author marton552
 */
public class RedEnemy extends Enemy {
    
    public RedEnemy(Point position, Point direction, int lives) {
        super("red",position.x, position.y, 100,100,null);
    }
    
}
