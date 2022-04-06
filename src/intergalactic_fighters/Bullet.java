/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * a
 * @author marton552
 */
public class Bullet {
    private Point startPos;
    private Point direction;
    private double speed;
    private String owner;
    private Image img;
    private int size=15;
    private Timer timer;

    public Bullet(Point startPos, Point direction, double speed, String owner) {
        this.startPos = startPos;
        this.direction = direction;
        this.speed = speed;
        this.owner = owner;
        if(owner == "player"){
            if(direction.x == 0) img = new ImageIcon(this.getClass().getResource("/design/images/bullet.png")).getImage();
            else img = new ImageIcon(this.getClass().getResource("/design/images/bullet_side.png")).getImage();
        }
        timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                move();
            }});
        timer.start();
    }

    public Point getStartPos() {
        return startPos;
    }

    public Image getImg() {
        return img;
    }
    
    private void move(){
        if(direction.x == 0) startPos.y+=direction.y*7;
        else startPos.x+=direction.x*7;
    }
    
}
