/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intergalactic_fighters;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author marton552
 */
public class Laser {

    private Point startPos;
    private String owner;
    private Image img;
    private boolean isDead = false;
    private int life;
    private int startLife;

    public Laser(Point startPos, String owner,int life) {
        this.startPos = startPos;
        this.owner = owner;
        this.life = life;
        this.startLife = life;
        if ("player".equals(owner)) {
            img = new ImageIcon(this.getClass().getResource("/images/laser_start.png")).getImage();
            startPos.y -= 500;
            startPos.x -= 3;
        } else {
            img = new ImageIcon(this.getClass().getResource("/images/enemy_laser_start.png")).getImage();
            this.life+=20;
            startPos.y += 100;
            startPos.x += 3;
        }
    }

    /**
     * <
     * p>
     * Getter of the position. </p>
     *
     * @return the actual position
     */
    public Point getStartPos() {
        return startPos;
    }

    /**
     * <
     * p>
     * Getter of the bullet image. </p>
     *
     * @return the image of the bullet
     */
    public Image getImg() {
        return img;
    }

    public void move(int x, int y) {
        startPos.x = x;
        startPos.y = y;
        if ("player".equals(owner)) {
            if(life < startLife) img = new ImageIcon(this.getClass().getResource("/images/laser.png")).getImage();
            else img = new ImageIcon(this.getClass().getResource("/images/laser_start.png")).getImage();
            startPos.y -= 490;
            startPos.x += 10;
        } else {
            if(life < startLife) img = new ImageIcon(this.getClass().getResource("/images/enemy_laser.png")).getImage();
            else img = new ImageIcon(this.getClass().getResource("/images/enemy_laser_start.png")).getImage();
            startPos.y += 25;
            startPos.x += 10;
        }
        System.out.println(life);
        if(life < startLife) detectCollision();
        life--;
        if(life <= 0) isDead = true;
    }
    
    public void detectCollision(){
        if("player".equals(owner)){
            for (int i = 0; i < GameEngine.Enemies.size(); i++) {
                if(inBox(GameEngine.Enemies.get(i).getX(),GameEngine.Enemies.get(i).getWidth())){
                    GameEngine.Enemies.get(i).loseHp(100);
                }
            }
        }
        else{
            for (int i = 0; i < GameEngine.Players.size(); i++) {
                if(inBox(GameEngine.Players.get(i).getX(),GameEngine.Players.get(i).getWidth())){
                    GameEngine.Players.get(i).loseHp(20);
                }
            }
        }
    }
    
    public boolean inBox(int x, int width){
        return (startPos.x >= x-5 && startPos.x <= x+width+5);
    }

    /**
     * <
     * p>
     * Check if the bullet is dead </p>
     *
     * @return if the bullet is dead.
     */
    public boolean isIsDead() {
        return isDead;
    }

}
