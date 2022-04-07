/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intergalactic_fighters.backgrounds;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class BasicBackground {
    private int speed;
    private int posY = -1400;
    private Image img;
    private boolean live = true;
    
    public BasicBackground(){
        this.speed = 1;
        Random r = new Random();
        int randomNumber=(r.nextInt((5-1)+1)+1);
        img = new ImageIcon(this.getClass().getResource("/images/backs/space"+randomNumber+".png")).getImage();
        
    }
    

    public void step(){
        posY+=speed;
    }
    
    public int getPosY(){
        return posY;
    }

    public Image getImg() {
        return img;
    }
    
    public void setPosY(int x){
        posY = x;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
