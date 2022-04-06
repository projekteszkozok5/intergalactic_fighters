/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public class Player{
    private int HP; //HealthPoint
    protected String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    private int moveX = 0;
    private int moveY = 0;
    private int bulletSpeed = 100;
    private ArrayList<Bullet> bullets;
    private Point direction;

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Player(String name, int x, int y, int width, int height, Image image)
    {
        this.HP = 100;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        bullets = new ArrayList<>();
        direction = new Point(0,-1);
    }
    public void loseHp(int points)
    {
        HP = HP - points;
    }
    
    public int getHp()
    {
        return HP;
    }
    
    public void draw(Graphics g, int zoomLevel, int Xoffset, int Yoffset) {
        g.drawImage(image, x * zoomLevel + Xoffset, y * zoomLevel + Yoffset, width * zoomLevel, height * zoomLevel, null);
        for (int i = 0; i < bullets.size(); i++) {
            g.drawImage(bullets.get(i).getImg(), bullets.get(i).getStartPos().x* zoomLevel + Xoffset, bullets.get(i).getStartPos().y * zoomLevel + Yoffset, 15 * zoomLevel, 15 * zoomLevel, null);
        }
    }

    public void moveForward()
    {
        moveY = -1;
        direction = new Point(0,-1);
    }
    public void moveBackward()
    {
        moveY = 1;
        direction = new Point(0,1);
    }

    public void moveRight()
    {
        moveX = 1;
        direction = new Point(1,0);
    }

    public void moveLeft()
    {
        moveX = -1;
        direction = new Point(-1,0);
    }

    public void setZeroY()
    {
        moveY = 0;
    }

    public void setZeroX()
    {
        moveX = 0;
    }

    public void move(int speed)
    {
        x += moveX * speed;
        y += moveY * speed;
    }

    public void setImage(Image image){
        this.image=image;
    }
    public Image getImage(){
        return this.image;
    }
    
    public void shoot(){
        Bullet b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), direction, bulletSpeed, "player");
        bullets.add(b);
    }
}
