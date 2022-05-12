/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intergalactic_fighters.sprites.enemies;

import com.intergalactic_fighters.Bullet;
import com.intergalactic_fighters.Enemy;
import java.awt.Point;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class NullEnemy extends Enemy {

private int dir = -1;
    private int whenshoot = 10;
    private int level = 2;
    private Random r = new Random();
    private int shipHP = 3;
    
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
    public NullEnemy(String name, int x, int y, int width, int height) {
        super("null",x, y, width,height);
    }
    
     /** <p> This method move the enemy left or right..</p> */
    @Override
    public void movement(){
        if(whenshoot == 0) {
            Bullet b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), new Point(0,1), getBulletSpeed(), "enemy");
            bullets.add(b);
            whenshoot=(r.nextInt(300-100) + 100);
        }
        whenshoot--;
        if(collided){
            dir*=-1;
            collided = false;
        }
        if(dir == -1){
            moveForward();
            setImage(new ImageIcon(this.getClass().getResource("/images/enemies/ship_hp"+shipHP+".png")).getImage());
        }
        else{
            moveBackward();
            setImage(new ImageIcon(this.getClass().getResource("/images/enemies/ship_hp"+shipHP+".png")).getImage());
        }
    }
    
    @Override
    public void loseHp(int points)
    {
        shipHP--;
        if(shipHP <= 0) isDead = true;
    }
    
}
