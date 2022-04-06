/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Prokkály László
 */
public class Wall {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    
    public Wall(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }
    
    public void draw(Graphics g, int zoomLevel, int Xoffset, int Yoffset) {//kiolvassa a valodi koordinatakat es visszaszamolja azoknak jelenelg hol kell elhelyezkedniuk a torzitott kepen.
        g.drawImage(image, x * zoomLevel + Xoffset, y * zoomLevel + Yoffset, width * zoomLevel, height * zoomLevel, null);
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
