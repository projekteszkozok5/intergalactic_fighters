package com.intergalactic_fighters.backgrounds;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * <p>
 * This is a background. Backgrounds scrolling and scrolling.
 * </p>
 *
 * @author Peszleg Márton
 */
public class BasicBackground {
    private int speed;
    private int posY = -1400;
    private Image img;
    private boolean live = true;
    
        /**
     * <p>
     * The constuctor creates a background. There are 5 type of backs and random pick one.
     * </p>
     */
    public BasicBackground(){
        this.speed = 1;
        Random r = new Random();
        int randomNumber=(r.nextInt((5-1)+1)+1);
        img = new ImageIcon(this.getClass().getResource("/images/backs/space"+randomNumber+".png")).getImage();
    }
    
    /** <p> Scroll the background one step down. </p> */
    public void step(){
        posY+=speed;
    }
    
     /** <p> Getter of the Y position </p> 
     * @return the position on y axis*/
    public int getPosY(){
        return posY;
    }

     /** <p> Getter of the image </p> 
     * @return the background image*/
    public Image getImg() {
        return img;
    }
    
        /** <p> Setter of the position </p> 
     * @param y where the background located*/
    public void setPosY(int y){
        posY = y;
    }

        /** <p> Check if the background is alive </p> 
     * @return if the background still visible on the screen or not*/
    public boolean isLive() {
        return live;
    }

       /** <p> Set background alive </p> 
     * @param live is alive or is dead*/
    public void setLive(boolean live) {
        this.live = live;
    }

     /** <p> Setter of the speed </p> 
     * @param speed how fast the back scroll*/
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
