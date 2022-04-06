package intergalactic_fighters;


import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;


public abstract class Enemy extends Player{
    
    public Enemy(String name, int x, int y, int width, int height,Image img) {
        super(name, x, y, width, height,img);
    }
    
    public abstract void movement();
}
