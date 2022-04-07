package com.intergalactic_fighters;


import java.awt.Image;


public abstract class Enemy extends Player{
    
    public Enemy(String name, int x, int y, int width, int height,Image img) {
        super(name, x, y, width, height,img);
    }
    
    public abstract void movement();
}
