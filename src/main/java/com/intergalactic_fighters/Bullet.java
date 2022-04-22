package com.intergalactic_fighters;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * <p>
 *  Bullet is an object that players and enemies can shoot.<br>
 *  Bullets can go horisontal and vertical, and it has a timer for the movement and the collision detection.
 * </p>
 * 
 * @author Peszleg Márton
 */

public class Bullet {
    private Point startPos;
    private Point direction;
    private double speed;
    private String owner;
    private Image img;
    private Timer timer;
    private boolean isDead = false;

    /**
 * <p>The constuctor creates a bullet, and its timer.
 * </p>
 * @param startPos the starting position (x,y) for the bullet
 * @param direction the direction (x,y) e.g.:(1,0) = going positive on X axis.
 * @param speed the ammount of moved pixels in 25ms.
 * @param owner the owner identifier. Its a string, it can be "player" or anything else.
 */
    
    public Bullet(Point startPos, Point direction, double speed, String owner) {
        this.startPos = startPos;
        this.direction = direction;
        this.speed = speed;
        this.owner = owner;
        if("player".equals(owner)){
            if(direction.x == 0) img = new ImageIcon(this.getClass().getResource("/images/bullet.png")).getImage();
            else img = new ImageIcon(this.getClass().getResource("/images/bullet_side.png")).getImage();
        }
        else{
            if(direction.x == 0) img = new ImageIcon(this.getClass().getResource("/images/enemies/enemy_bullet.png")).getImage();
            else img = new ImageIcon(this.getClass().getResource("/images/enemies/enemy_bullet_side.png")).getImage();
        }
        timer = new Timer(25, (ActionEvent ae) -> {
            if(!isDead){
                move();
            }
        });
        timer.start();
    }

    /** <p> Getter of the position. </p> 
     * @return the actual position*/
    public Point getStartPos() {
        return startPos;
    }

    /** <p> Getter of the bullet image. </p> 
     * @return the image of the bullet*/
    public Image getImg() {
        return img;
    }
    
    /** <p> It moves the bullet. dirextion*speed. <br> If the bullet goes out of the screen, kills it. </p> */
    public void move(){
        if(direction.x == 0) startPos.y+=direction.y*speed;
        else startPos.x+=direction.x*speed;
        if(!inBox(0, 0, (int)(800/GameEngine.zoomLevel))) isDead=true;
        detectCollision();
    }
    
    /** <p> It detect the collision. If the bulle owner is player, it goes througth the enemy array <br>
     * Check {@link com.intergalactic_fighters.GameEngine}</p> */
    private void detectCollision(){
        if("player".equals(owner)){
            for (int i = 0; i < GameEngine.Enemies.size(); i++) {
                if(inBox(GameEngine.Enemies.get(i).getX(),GameEngine.Enemies.get(i).getY(),GameEngine.Enemies.get(i).getWidth())){
                    GameEngine.Enemies.get(i).loseHp(100);
                    isDead = true;
                }
            }
        }
        else{
            for (int i = 0; i < GameEngine.Players.size(); i++) {
                if(inBox(GameEngine.Players.get(i).getX(),GameEngine.Players.get(i).getY(),GameEngine.Players.get(i).getWidth())){
                    GameEngine.Players.get(i).loseHp(20);
                    isDead = true;
                }
            }
        }
    }
    /** 
     * @param x the box x position (lfet top)
     * @param y the box y position (lfet top)
     * @param boxSize the size of a box.
     * @return if the bullet is in a box/area.
     */
    public boolean inBox(int x, int y, int boxSize){
        return (startPos.x >= x && startPos.x <= x+boxSize && startPos.y >= y && startPos.y <= y+boxSize);
    }

    /** <p> Check if the bullet is dead </p> 
     * @return if the bullet is dead. */
    public boolean isIsDead() {
        return isDead;
    }
    
    
}
