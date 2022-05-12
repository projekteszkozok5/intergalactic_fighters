/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intergalactic_fighters.sprites.enemies;

import com.intergalactic_fighters.Bullet;
import com.intergalactic_fighters.Enemy;
import com.intergalactic_fighters.GameEngine;
import static com.intergalactic_fighters.GameEngine.Enemies;
import static com.intergalactic_fighters.GameEngine.zoomLevel;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class BossShield extends Enemy {
    private int dir = -1;
    private int whenshoot = 30;
    private int shooting = -1;
    private int level = 2;
    private Random r = new Random();
    private int shipHP = 10;
    private boolean hor=true;
    
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
    public BossShield(String name, int x, int y, int width, int height) {
        super("boos_shield",x, y, width,height);
        shield = new ImageIcon(this.getClass().getResource("/images/enemies/boss/boss_shield.png")).getImage();
        canLoseHp = false;
    }
    
     /** <p> This method move the enemy left or right..</p> */
    @Override
    public void movement(){
        if(shipHP > 0){
        if(whenshoot == 0) {
            shooting = 50;
            whenshoot = -1;
            //whenshoot=(r.nextInt(300-100) + 100);
        }
        whenshoot--;
        shooting--;
        int freq = shipHP-4;
        if(freq < 2) freq = 2;
        if(shooting%shipHP==0 && shooting > 0){
            canLoseHp = true;
            Bullet b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), new Point(0,1), getBulletSpeed(), "enemy");
            bullets.add(b);
            b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), new Point(0,-1), getBulletSpeed(), "enemy");
            bullets.add(b);
            b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), new Point(1,0), getBulletSpeed(), "enemy");
            bullets.add(b);
            b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), new Point(-1,0), getBulletSpeed(), "enemy");
            bullets.add(b);
        }
        else if( shooting <= 0 && whenshoot < 0){
            whenshoot=(r.nextInt(70-10) + 10);
            canLoseHp = false;
            if(r.nextInt(10-0) + 0 > 4){
            for (int i = 0; i < 5-freq; i++) {
                GameEngine.Enemies.add((new EasyEnemy("EasyEnemy" + i, (int) (800 / zoomLevel / 2 + GameEngine.gridSize * i - 150), GameEngine.gridSize + 10, GameEngine.gridSize, GameEngine.gridSize)));//"enemy")
            }}
            else{
            for (int i = 0; i < 5-freq; i++) {
                GameEngine.Enemies.add((new LaserEnemy("LaserEnemy" + i, (int) (800 / zoomLevel / 2 + GameEngine.gridSize * i - 150), GameEngine.gridSize + 10, GameEngine.gridSize, GameEngine.gridSize)));//"enemy")
            }
            }
        }
        if(collided){
            dir*=-1;
            collided = false;
        }
        if(dir == -1 && !canLoseHp){
            moveForward();
            setImage(new ImageIcon(this.getClass().getResource("/images/enemies/boss/boss"+shipHP+".png")).getImage());
        }
        else if(dir == 1 && !canLoseHp){
            moveBackward();
            setImage(new ImageIcon(this.getClass().getResource("/images/enemies/boss/boss"+shipHP+".png")).getImage());
        }
        else{
            setZeroY();
        }
        }
    }
   
    
    @Override
    public void loseHp(int points)
    {
        if(canLoseHp){shipHP--; shooting = -1;}
        if(shipHP <= 0) {isDead = true; GameEngine.isBossAlive = false;}
        canLoseHp = false;
    }
    
    @Override
    public int getHp(){
        return shipHP;
    }
}
