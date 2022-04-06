/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters.sprites.enemies;

import intergalactic_fighters.Bullet;
import intergalactic_fighters.Enemy;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class EasyEnemy extends Enemy{
    
    int dir = -1;
    int whenshoot = 10;
    int level = 7;
    private Random r = new Random();
    
    public EasyEnemy(String name, int x, int y, int width, int height,Image img) {
        super("easy",x, y, width,height,null);
    }
    
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
            setImage(new ImageIcon(this.getClass().getResource("/design/images/enemies/enemyshipdown1.png")).getImage());
        }
        else{
            moveRight();
        }
    }
}
