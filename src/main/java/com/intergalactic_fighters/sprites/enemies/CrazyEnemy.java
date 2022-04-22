package com.intergalactic_fighters.sprites.enemies;

import com.intergalactic_fighters.Bullet;
import com.intergalactic_fighters.Enemy;
import java.awt.Point;
import java.security.SecureRandom;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * <p>
 * This is a specified type of enemy. Crazy enemies randomly move and shoot everywhere. The parent class:<br>
 * Enemy: {@link com.intergalactic_fighters.Enemy}
 * </p>
 *
 * @author Peszleg Márton
 */
public class CrazyEnemy extends Enemy {
  private Random r = new SecureRandom();
        /**
     * <p>
     * The constuctor creates a crazy enemy, and call the Enemy class.
     * </p>
     *
     * @param name the name of the enemy ship
     * @param x the starting position on x axis
     * @param y the starting position on y axis
     * @param width the width of the ship in pixels
     * @param height the height of the ship in pixels
     */
    public CrazyEnemy(String name, int x, int y, int width, int height) {
        super("crazy",x, y, width,height);
    }
    
    /** <p> This method randomly pick a number between 1 and 5. <br>
     * Number 1-4 is a random direction of movement. Number 5 = shoot.</p> */
     @Override
    public void movement(){
        int randomNumber=(r.nextInt((5-1)+1)+1);
        switch (randomNumber){
            case 1:
                moveForward();
                setImage(new ImageIcon(this.getClass().getResource("/images/enemies/enemyship.png")).getImage());
                break;
            case 2:
                moveBackward();
                setImage(new ImageIcon(this.getClass().getResource("/images/enemies/enemyshipdown.png")).getImage());
                break;
            case 3:
                moveRight();
                setImage(new ImageIcon(this.getClass().getResource("/images/enemies/enemyshipright.png")).getImage());
                break;
            case 4:
                moveLeft();
                setImage(new ImageIcon(this.getClass().getResource("/images/enemies/enemyshipleft.png")).getImage());
                break;
            case 5:
                Bullet b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), direction, getBulletSpeed(), "enemy");
                bullets.add(b);
                break;
            default:
                break;
        }
    }
}
