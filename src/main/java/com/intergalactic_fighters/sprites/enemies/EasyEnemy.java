package com.intergalactic_fighters.sprites.enemies;

import com.intergalactic_fighters.Bullet;
import com.intergalactic_fighters.Enemy;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * <p>
 * This is a specified type of enemy. Easy enemies slide left and right in the screen The parent class:<br>
 * Enemy: {@link com.intergalactic_fighters.Enemy}
 * </p>
 *
 * @author Peszleg Márton
 */
public class EasyEnemy extends Enemy{
    
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
    public EasyEnemy(String name, int x, int y, int width, int height) {
        super("easy",x, y, width,height);
    }
    
     /** <p> This method move the enemy left or right. <br>
     * It has a counter when the enemy will shoot.</p> */
    @Override
    public void movement(){
        if(whenshoot == 0) {
            Bullet b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), new Point(0,1), getBulletSpeed(), "enemy");
            bullets.add(b);
            whenshoot=(r.nextInt(((100-(10*level))-1)+1)+1);
        }
        whenshoot--;
        if(collided){
            dir*=-1;
            collided = false;
        }
        if(dir == -1){
            moveLeft();
            setImage(new ImageIcon(this.getClass().getResource("/images/enemies/enemyshipdown1.png")).getImage());
        }
        else{
            moveRight();
            setImage(new ImageIcon(this.getClass().getResource("/images/enemies/enemyshipdown1.png")).getImage());
        }
    }
}
