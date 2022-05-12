/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intergalactic_fighters.sprites.enemies;

import com.intergalactic_fighters.Bullet;
import com.intergalactic_fighters.Enemy;
import com.intergalactic_fighters.GameEngine;
import static com.intergalactic_fighters.GameEngine.zoomLevel;
import com.intergalactic_fighters.Laser;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class BossLaser extends Enemy{
        private int dir = -1;
    private int whenshoot = 10;
    private int shooting = -1;
    private int level = 2;
    private Random r = new Random();
    private int shipHP = 10;
    private int levl = 0;
    private Laser left;
    private Laser right;
    private Laser center;
    private int shooter = 0;
    
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
    public BossLaser(String name, int x, int y, int width, int height) {
        super("boos_laser",x, y, width,height);
        shield = new ImageIcon(this.getClass().getResource("/images/enemies/boss/boss_laser_shield.png")).getImage();
        canLoseHp = false;
    }
    
     /** <p> This method move the enemy left or right..</p> */
    @Override
    public void movement(){
        if(whenshoot == 0) {
            shooting = 75;
            whenshoot = -1;
            shooter = 1;
            canLoseHp = true;
        }
        if(shooting == 50){
            shooter = 0;
            canLoseHp = false;
        }
        if(shooting == 25){
            canLoseHp = true;
            shooter = 2;
        }
        if(shooting == 3){
            if(levl < 2){
                moveBackward();
            }
            else if(levl < 0){
                moveForward();
            }
            if(levl == 2) levl*=-1;
            levl++;
        }
        whenshoot--;
        shooting--;
        int freq = shipHP-4;
        if(freq < 2) freq = 2;
        if(shooting%shipHP==5 && shooting > 50){
            canLoseHp = true;
            Bullet b = new Bullet(new Point(x+width-5,y+height/2+20), new Point(0,1), getBulletSpeed(), "enemy");
            bullets.add(b);
            b = new Bullet(new Point(x+5,y+height/2+20), new Point(0,1), getBulletSpeed(), "enemy");
            bullets.add(b);
        }
        else if(shooting%shipHP==0 && shooting > 0 && shooting < 25){
            canLoseHp = true;
            Bullet b = new Bullet(new Point(x+width/2-15/2,y+height/2+10), new Point(0,1), getBulletSpeed(), "enemy");
            bullets.add(b);
        }
        else if( shooting <= 0 && whenshoot < 0){
            setZeroY();
            shooter = 0;
            whenshoot=(r.nextInt(70-10) + 10);
            canLoseHp = true;
            if(r.nextInt(10-0) + 0 > 4){
            for (int i = 0; i < 5-freq; i++) {
                GameEngine.Enemies.add((new CrazyEnemy("CrazyEnemy" + i, (int) (800 / zoomLevel / 2 + GameEngine.gridSize * i - 150), GameEngine.gridSize*3, GameEngine.gridSize, GameEngine.gridSize)));//"enemy")
            }}
            else{
            for (int i = 0; i < 5-freq; i++) {
                GameEngine.Enemies.add((new NullEnemy("NullEnemy" + i, (int) (800 / zoomLevel / 2 + GameEngine.gridSize * i - 150), GameEngine.gridSize*3, GameEngine.gridSize, GameEngine.gridSize)));//"enemy")
            }
            }
        }
        if(collided){
            dir*=-1;
            collided = false;
        }
        if(dir == -1){
            moveLeft();
        }
        else{
            moveRight();
        }
        setImage(new ImageIcon(this.getClass().getResource("/images/enemies/boss/boss_laser"+shipHP+".png")).getImage());
        
        if(shooter == 0){
            left = null;
            right = null;
            center = null;
        }
        else if(shooter == 1){
            center = new Laser(new Point(x,y), name, 30);
            shooter = 99;
        }
        else if(shooter == 2){
            center = null;
            left = new Laser(new Point(x-50,y), name, 30);
            right = new Laser(new Point(x+50,y), name, 30);
            shooter = 99;
        }
    }
    
    @Override
    public void draw(Graphics g, double zoomLevel, int xOffset, int yOffset) {
        for (int i = 0; i < bullets.size(); i++) {
            if(bullets.get(i).isIsDead()) bullets.remove(i);
            else g.drawImage(bullets.get(i).getImg(),
                    (int)(bullets.get(i).getStartPos().x* zoomLevel + xOffset),
                    (int)(bullets.get(i).getStartPos().y * zoomLevel + yOffset),
                    (int)(15 * zoomLevel),
                    (int)(15 * zoomLevel), null);
        }
        if(left != null) left.move(x+96, y+60);
        if(right != null) right.move(x+0, y+60);
        if(center != null) center.move(x+48, y+70);
         if(left != null) g.drawImage(left.getImg(), (int)(left.getStartPos().x * zoomLevel + xOffset), (int)(left.getStartPos().y * zoomLevel + yOffset), (int)(40 * zoomLevel), (int)(500 * zoomLevel), null);
        if(right != null) g.drawImage(right.getImg(), (int)(right.getStartPos().x * zoomLevel + xOffset), (int)(right.getStartPos().y * zoomLevel + yOffset), (int)(40 * zoomLevel), (int)(500 * zoomLevel), null);
        if(center != null) g.drawImage(center.getImg(), (int)(center.getStartPos().x * zoomLevel + xOffset), (int)(center.getStartPos().y * zoomLevel + yOffset), (int)(40 * zoomLevel), (int)(500 * zoomLevel), null);
        g.drawImage(image, (int)(x * zoomLevel + xOffset), (int)(y * zoomLevel + yOffset), (int)(width * zoomLevel), (int)(height * zoomLevel), null);
        if(!canLoseHp) g.drawImage(shield, (int)(x * zoomLevel + xOffset), (int)(y * zoomLevel + yOffset), (int)(width * zoomLevel), (int)(height * zoomLevel), null);
      }
    
    
    @Override
    public void loseHp(int points)
    {
        if(canLoseHp){shipHP--; canLoseHp = false;}
        if(shipHP <= 0) {isDead = true; GameEngine.isBossAlive = false;}
        canLoseHp = false;
    }
    
    @Override
    public int getHp(){
        return shipHP;
    }
}
