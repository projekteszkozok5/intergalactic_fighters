/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intergalactic_fighters.sprites.enemies;

import com.intergalactic_fighters.Bullet;
import com.intergalactic_fighters.Enemy;
import com.intergalactic_fighters.Laser;
import java.awt.Point;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class LaserEnemy extends Enemy {
        private int dir = -1;
    private int whenshoot = 10;
    private int level = 2;
    private Random r = new Random();
    
     /**
     * <p>
     * The constuctor creates an easy enemy, and call the Enemy class.
     * </p>
     *
     * @param name the name of the enemy ship
     * @param x the starting position on x axis
     * @param y the starting position on y axis
     * @param width the width of the ship in pixels
     * @param height the height of the ship in pixels
     */
    public LaserEnemy(String name, int x, int y, int width, int height) {
        super("laser",x, y, width,height);
        whenshoot=(r.nextInt(100-20) + 20);
    }
    
     /** <p> This method move the enemy left or right. <br>
     * It has a counter when the enemy will shoot.</p> */
    @Override
    public void movement(){
        if(whenshoot == 0) {
            l = new Laser(new Point(x+width/2-15/2,y+height/2-15/2), "enemy",10);
            hasLaser = true;
            whenshoot=(r.nextInt(100-20) + 20);
        }
        whenshoot--;
        if(collided){
            dir*=-1;
            collided = false;
        }
        if(dir == -1){
            moveLeft();
            setImage(new ImageIcon(this.getClass().getResource("/images/enemies/enemyship_laser.png")).getImage());
        }
        else{
            moveRight();
            setImage(new ImageIcon(this.getClass().getResource("/images/enemies/enemyship_laser.png")).getImage());
        }
    }
}
