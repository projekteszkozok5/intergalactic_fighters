/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intergalactic_fighters;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * a
 * @author marton552
 */
public class Bullet {
    private Point startPos;
    private Point direction;
    private double speed;
    private String owner;
    private Image img;
    private int size=15;
    private Timer timer;
    private boolean isDead = false;

    public Bullet(Point startPos, Point direction, double speed, String owner) {
        this.startPos = startPos;
        this.direction = direction;
        this.speed = speed;
        this.owner = owner;
        if(owner == "player"){
            if(direction.x == 0) img = new ImageIcon(this.getClass().getResource("/images/bullet.png")).getImage();
            else img = new ImageIcon(this.getClass().getResource("/images/bullet_side.png")).getImage();
        }
        else{
            if(direction.x == 0) img = new ImageIcon(this.getClass().getResource("/images/enemies/enemy_bullet.png")).getImage();
            else img = new ImageIcon(this.getClass().getResource("/images/enemies/enemy_bullet_side.png")).getImage();
        }
        timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!isDead){
                    move();
                    detectCollision();
                }
            }});
        timer.start();
    }

    public Point getStartPos() {
        return startPos;
    }

    public Image getImg() {
        return img;
    }
    
    public void move(){
        if(direction.x == 0) startPos.y+=direction.y*speed;
        else startPos.x+=direction.x*speed;
        if(!inBox(0, 0, (int)(800/GameEngine.zoomLevel))) isDead=true;
    }
    
    private void detectCollision(){
        if(owner == "player"){
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
    
    private boolean inBox(int x, int y, int boxSize){
        return (startPos.x >= x && startPos.x <= x+boxSize && startPos.y >= y && startPos.y <= y+boxSize);
    }

    public boolean isIsDead() {
        return isDead;
    }
    
    
}
