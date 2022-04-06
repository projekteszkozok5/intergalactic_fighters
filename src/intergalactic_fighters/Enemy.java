package intergalactic_fighters;


import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;


public class Enemy extends Player{
    
    public Enemy(String name, int x, int y, int width, int height,Image img) {
        super(name, x, y, width, height,img);
    }
    
    
    public void movement(){
        Random r = new Random();
        int randomNumber=(r.nextInt((5-1)+1)+1);
        switch (randomNumber){
            case 1:
                moveForward();
                setImage(new ImageIcon(this.getClass().getResource("/design/images/enemies/enemyship.png")).getImage());
                break;
            case 2:
                moveBackward();
                setImage(new ImageIcon(this.getClass().getResource("/design/images/enemies/enemyshipdown.png")).getImage());
                break;
            case 3:
                moveRight();
                setImage(new ImageIcon(this.getClass().getResource("/design/images/enemies/enemyshipright.png")).getImage());
                break;
            case 4:
                moveLeft();
                setImage(new ImageIcon(this.getClass().getResource("/design/images/enemies/enemyshipleft.png")).getImage());
                break;
            case 5:
                Bullet b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), direction, bulletSpeed, "enemy");
                bullets.add(b);
                break;
        }

    }
}
