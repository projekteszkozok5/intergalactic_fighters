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
    protected int bulletSpeed = 7;
    protected ArrayList<Bullet> bullets;
    protected int maxBullets = 2;
    protected Point direction;
    protected boolean isDead = false;
    protected boolean collided = false;

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth() {
        return width;
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
        if(HP <= 0) isDead = true;
    }
    
    public int getHp()
    {
        if(HP <= 0) return 0;
        return HP;
    }

    public boolean isIsDead() {
        return isDead;
    }
    
    
    public void draw(Graphics g, double zoomLevel, int Xoffset, int Yoffset) {
        g.drawImage(image, (int)(x * zoomLevel + Xoffset), (int)(y * zoomLevel + Yoffset), (int)(width * zoomLevel), (int)(height * zoomLevel), null);
        for (int i = 0; i < bullets.size(); i++) {
            if(bullets.get(i).isIsDead()) bullets.remove(i);
            else g.drawImage(bullets.get(i).getImg(),
                    (int)(bullets.get(i).getStartPos().x* zoomLevel + Xoffset),
                    (int)(bullets.get(i).getStartPos().y * zoomLevel + Yoffset),
                    (int)(15 * zoomLevel),
                    (int)(15 * zoomLevel), null);
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
        if(x + moveX * speed > 0 && x + moveX * speed < 800/GameEngine.zoomLevel-width) x += moveX * speed;
        else collided = true;
        if(y + moveY * speed > 0 && y + moveY * speed < 600/GameEngine.zoomLevel-height) y += moveY * speed;
        else collided = true;
    }

    public void setImage(Image image){
        this.image=image;
    }
    public Image getImage(){
        return this.image;
    }
    
    public void shoot(){
        if(bullets.size()+1<=maxBullets){
        Bullet b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), direction, bulletSpeed, "player");
        bullets.add(b);
        System.out.println(getX()+","+getY());
        }
    }

    public int getHP() {
        return HP;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public int getMaxBullets() {
        return maxBullets;
    }
    
}
