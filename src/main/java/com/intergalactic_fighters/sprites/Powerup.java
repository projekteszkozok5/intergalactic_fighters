package com.intergalactic_fighters.sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * <p>
 * Powerups can increase the player speed or the maximum hp... see bellow
 * </p>
 *
 * @author Peszleg Márton
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

     /**
     * <p>
     * The constuctor get where the powerup will be appear.<br>
     * immediately pick a random ability of:<br>
     * HP növelés<br>
     * HP gyorsabb növekedés<br>
     * Gyorsabb mozgás<br>
     * Gyorsabb lövedék<br>
     * Több lövedék<br>
     * </p>
     * @param x the position in x axis.
     * @param y the position in y axis.
     * 
     */
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
    
     /**
     * <p>
     * The same constructor but the ability can be set.
     * </p>
     * @param x the position in x axis.
     * @param y the position in y axis.
     * @param index which abilty 
     */
    public Powerup(int x, int y,int index) {
        this.x = x;
        this.y = y;
        image = new ImageIcon(this.getClass().getResource("/images/powerup.png")).getImage();
        powerups[index-1] = 1;
        if(index == 1) whatisit = "Maximum HP +2";
        else if(index == 2) whatisit = "HP visszatöltési sebesség +1";
        else if(index == 3) whatisit = "Sebesség +1";
        else if(index == 4) whatisit = "Lövedék sebesség +1";
        else if(index == 5) whatisit = "Maximum lövedék +1";
    }

    /** <p> Getter of the powerup ability array </p> 
     * @return the randomized ability array*/
    public int[] getPowerups() {
        return powerups;
    }
    
    /** <p> Fall down the powerup. </p> */
    public void step(){
        y+=2;
    }
    
   /** <p> This method draws the powerup. </p> 
     * @param g is a Graphics see {@link java.awt.Graphics}
     * @param zoomLevel how much the screen zoomed
     */
    public void draw(Graphics g, double zoomLevel){
        g.drawImage(image, (int)(x * zoomLevel), (int)(y * zoomLevel), (int)(15 * zoomLevel), (int)(15 * zoomLevel), null);
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

    /** <p> Getter of the powerup ability label</p> 
     * @return the randomized ability in text*/
    public String getWhatisit() {
        return whatisit;
    }
    
}
