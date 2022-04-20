package com.intergalactic_fighters.sprites;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;


public class Explosion {

    private int x;
    private int y;
    private int picPos = 0;
    private Image image;
    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void nextFrame(){
        picPos++;
        if(picPos <= 12)image = new ImageIcon(this.getClass().getResource("/images/explosion/"+picPos+".png")).getImage();
    }

    public int getPicPos() {
        return picPos;
    }

    public Image getImage() {
        return image;
    }
    
        public void draw(Graphics g, double zoomLevel){
        g.drawImage(image, (int)(x * zoomLevel), (int)(y * zoomLevel), (int)(40 * zoomLevel), (int)(40 * zoomLevel), null);
    }
}
