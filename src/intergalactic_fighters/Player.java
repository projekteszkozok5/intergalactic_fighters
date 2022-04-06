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
    protected double maxHP = 100;
    protected double HP;
    protected double HealPerSecond = 1;
    protected double speed = 3;
    protected String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    protected int moveX = 0;
    protected int moveY = 0;
    protected double bulletSpeed;
    protected ArrayList<Bullet> bullets;
    protected int maxBullets = 2;
    protected Point direction;
    protected boolean isDead = false;
    protected boolean collided = false;
    protected String whatCollected = "";
    private int cooldown = 15;

    public double getBulletSpeed() {
        return bulletSpeed;
    }

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
        bulletSpeed=7.0;
    }
    public void loseHp(int points)
    {
        HP = HP - points;
        if(HP <= 0) isDead = true;
    }
    
    public int getHp()
    {
        if(HP <= 0) return 0;
        return (int)HP;
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

    public void move()
    {
        if(x + moveX * speed > 0 && x + moveX * speed < 800/GameEngine.zoomLevel-width) x += moveX * speed;
        else collided = true;
        if(y + moveY * speed > 0 && y + moveY * speed < 600/GameEngine.zoomLevel-height) y += moveY * speed;
        else collided = true;
        cooldown--;
        if(cooldown<0){
            whatCollected="";
            if(HP<maxHP)HP+=HealPerSecond/50;
        }
        if(name=="Player1")collect();
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

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public int getMaxBullets() {
        return maxBullets;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public String getWhatCollected() {
        return whatCollected;
    }
    
    private void collect(){
        for (int i = 0; i < GameEngine.powerups.size(); i++) {
            if(inBox(GameEngine.powerups.get(i).getX(),GameEngine.powerups.get(i).getY())){
                maxHP+=GameEngine.powerups.get(i).getPowerups()[0]*5;
                HealPerSecond+=GameEngine.powerups.get(i).getPowerups()[1];
                speed+=GameEngine.powerups.get(i).getPowerups()[2]/2;
                bulletSpeed+=GameEngine.powerups.get(i).getPowerups()[3]/2;
                maxBullets+=GameEngine.powerups.get(i).getPowerups()[4];
                whatCollected = GameEngine.powerups.get(i).getWhatisit();
                cooldown = 15;
                GameEngine.powerups.remove(i);
            }
        }
    }
    
    private boolean inBox(int x0, int y0){
        return (x0 >= x && x0 <= x+width && y0 >= y && y0 <= y+width);
    }
}
