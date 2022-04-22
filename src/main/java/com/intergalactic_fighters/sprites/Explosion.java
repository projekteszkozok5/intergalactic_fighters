package com.intergalactic_fighters.sprites;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * <p>
 * Explosion class is basicly just an animation.
 * </p>
 *
 * @author Peszleg Márton
 */
public class Explosion {

    private int x;
    private int y;
    private int picPos = 0;
    private Image image;
     /**
     * <p>
     * The constuctor get where the explosion will be appear.</p>
     * @param x the position in x axis.
     * @param y the position in y axis.
     */
    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /** <p> Jump to the next frame. There are 12 frames, so if it's over the explosion dies. </p> */
    public void nextFrame(){
        picPos++;
        if(picPos <= 12)image = new ImageIcon(this.getClass().getResource("/images/explosion/"+picPos+".png")).getImage();
    }

    /** <p> Getter of the picture pos </p> 
     * @return which is the current frame*/
    public int getPicPos() {
        return picPos;
    }

    /** <p> Getter of the image </p> 
     * @return the actual image of the animation*/
    public Image getImage() {
        return image;
    }
    
    /** <p> This method draws the actual frame of the explosion. </p> 
     * @param g is a Graphics see {@link java.awt.Graphics}
     * @param zoomLevel how much the screen zoomed
     */
        public void draw(Graphics g, double zoomLevel){
        g.drawImage(image, (int)(x * zoomLevel), (int)(y * zoomLevel), (int)(40 * zoomLevel), (int)(40 * zoomLevel), null);
    }
}
