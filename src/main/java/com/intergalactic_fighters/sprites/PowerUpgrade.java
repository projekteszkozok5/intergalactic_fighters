/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intergalactic_fighters.sprites;

import com.intergalactic_fighters.GameEngine;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class PowerUpgrade {
        private Random r = new Random();
    protected Image image;
    protected int x;
    protected int y;
    private boolean choosed = false;
    private int arg;

    public PowerUpgrade(int x, int y, int ability) {
        this.x = x;
        this.y = y;
        image = new ImageIcon(this.getClass().getResource("/images/powers/"+ability+".png")).getImage();
        arg = ability;
    }
    
    
   /** <p> This method draws the powerup. </p> 
     * @param g is a Graphics see {@link java.awt.Graphics}
     * @param zoomLevel how much the screen zoomed
     */
    public void draw(Graphics g, double zoomLevel){
        g.drawImage(image, (int)(x * zoomLevel), (int)(y * zoomLevel), (int)(100 * zoomLevel), (int)(100 * zoomLevel), null);
        detectCollision();
    }
    
    
    public void detectCollision(){
            for (int i = 0; i < GameEngine.Players.size(); i++) {
                if(inBox(GameEngine.Players.get(i).getX(),GameEngine.Players.get(i).getY(),100)){
                    choosed = true;
                }
            }
    }
    /** 
     * @param x the box x position (lfet top)
     * @param y the box y position (lfet top)
     * @param boxSize the size of a box.
     * @return if the bullet is in a box/area.
     */
    public boolean inBox(int x0, int y0, int boxSize){
        return (x+10 < x0 && x0 <= x+boxSize-10 && y+10 < y0 && y0 <= y+boxSize/2);
    }

    /** <p> Getter of the X position </p> 
     * @return the position on X axis*/
    public int getX() {
        return x;
    }

    /** <p> Getter of the Y position </p> 
     * @return the position on y axis*/
    public int getY() {
        return y;
    }

    public boolean isChoosed() {
        return choosed;
    }
    
    public int getArgs(){
        return arg;
    }
}
