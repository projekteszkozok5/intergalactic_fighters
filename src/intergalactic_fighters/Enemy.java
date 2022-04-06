package intergalactic_fighters;


import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemy extends Player{
    /**
     * @param name   Name of player
     * @param x      Where the player spawn on x-axis
     * @param y      Where the player spawn on y-axis
     * @param width  width of player
     * @param height height of player
     * @param image  start image of player (not-animated)
     */
    public Enemy(String name, int x, int y, int width, int height,Image img) {
        super(name, x, y, width, height,img);
    }
    
    
    public void movement(){
        Random r = new Random();
        int randomNumber=(r.nextInt((4-1)+1)+1);
        switch (randomNumber){
            case 1:
                moveForward();
                setImage(new ImageIcon(this.getClass().getResource("/design/images/enemyship.png")).getImage());
                break;
            case 2:
                moveBackward();
                setImage(new ImageIcon(this.getClass().getResource("/design/images/enemyshipdown.png")).getImage());
                break;
            case 3:
                moveRight();
                setImage(new ImageIcon(this.getClass().getResource("/design/images/enemyshipright.png")).getImage());
                break;
            case 4:
                moveLeft();
                setImage(new ImageIcon(this.getClass().getResource("/design/images/enemyshipleft.png")).getImage());
                break;
        }

    }
}
