/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intergalactic_fighters.sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class Powerup {
    private Random r = new Random();
    protected Image image;
    protected int x;
    protected int y;
    protected String whatisit = "";
    /*
        HP növelés
        HP gyorsabb növekedés
        Gyorsabb mozgás
        Gyorsabb lövedék
        Több lövedék
    */
    private int[] powerups = {0,0,0,0,0};

    public Powerup(int x, int y) {
        this.x = x;
        this.y = y;
        image = new ImageIcon(this.getClass().getResource("/images/powerup.png")).getImage();
        int randomNumber=(r.nextInt((5-1)+1)+1);
        powerups[randomNumber-1] = 1;
        if(randomNumber == 1) whatisit = "Maximum HP +2";
        else if(randomNumber == 2) whatisit = "HP visszatöltési sebesség +1";
        else if(randomNumber == 3) whatisit = "Sebesség +1";
        else if(randomNumber == 4) whatisit = "Lövedék sebesség +1";
        else if(randomNumber == 5) whatisit = "Maximum lövedék +1";
    }

    public int[] getPowerups() {
        return powerups;
    }
    
    public void step(){
        y+=2;
    }
    public void draw(Graphics g, double zoomLevel){
        g.drawImage(image, (int)(x * zoomLevel), (int)(y * zoomLevel), (int)(15 * zoomLevel), (int)(15 * zoomLevel), null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getWhatisit() {
        return whatisit;
    }
    
}
