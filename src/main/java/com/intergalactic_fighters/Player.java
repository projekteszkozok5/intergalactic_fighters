package com.intergalactic_fighters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.List;

/**
 * <p>
 * This is the class of the player and also a parent class of the enemies.
 * </p>
 *
 * @author Peszleg Márton
 */
public class Player{
    protected double maxHP = 100;
    protected double hp;
    protected double healPerSecond = 1;
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
    protected Laser l;
    protected boolean hasLaser = false;
    protected Image shield;
    protected boolean canLoseHp = false;

    /**
     * <p>
     * The constuctor creates a Player and set the image. Default HP is 100, and default bulletspeed is 7.
     * </p>
     *
     * @param name the name of the player ship (for multiplayer)
     * @param x the starting position on x axis
     * @param y the starting position on y axis
     * @param width the width of the ship in pixels
     * @param height the height of the ship in pixels
     */
    public Player(String name, int x, int y, int width, int height)
    {
        this.hp = 100;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bullets = new ArrayList<>();
        direction = new Point(0,-1);
        bulletSpeed=7.0;
        setImage(new ImageIcon(this.getClass().getResource("/images/playership.png")).getImage());
    }
    
    public Image getShield(){
        return shield;
    }
    
    /** <p> Getter of the bullet speed </p> 
     * @return bullet speed = how much pixels move per frame*/
    public double getBulletSpeed() {
        return bulletSpeed;
    }

    /** <p> Getter of the position on x axis </p>
     * @return x posion */
    public int getX()
    {
        return x;
    }

    /** <p> Getter of the position on y axis </p> 
     * @return y posion*/
    public int getY()
    {
        return y;
    }

    /** <p> Getter of the ship width </p> 
     * @return sprite width*/
    public int getWidth() {
        return width;
    }

     /** <p> Lose X number of health point </p> 
     * @param points how much hp lose*/
    public void loseHp(int points)
    {
        hp = hp - points;
        if(hp <= 0) isDead = true;
    }
    
     /** <p> Getter of the helthpoints </p> 
     * @return the HP. If HP bellow 0, it return with 0*/
    public int getHp()
    {
        if(hp <= 0) return 0;
        return (int) hp;
    }

    /** <p> Check if the player is dead </p> 
     * @return if dead*/
    public boolean isIsDead() {
        return isDead;
    }
    
    /** <p> This method draws the player and it bullets </p> 
     * @param g is a Graphics see {@link java.awt.Graphics}
     * @param zoomLevel how much the screen zoomed
     * @param xOffset offset in x axis
     * @param yOffset offset in y axis
     */
    public void draw(Graphics g, double zoomLevel, int xOffset, int yOffset) {
        g.drawImage(image, (int)(x * zoomLevel + xOffset), (int)(y * zoomLevel + yOffset), (int)(width * zoomLevel), (int)(height * zoomLevel), null);
        if(!canLoseHp) g.drawImage(shield, (int)(x * zoomLevel + xOffset), (int)(y * zoomLevel + yOffset), (int)(width * zoomLevel), (int)(height * zoomLevel), null);
        for (int i = 0; i < bullets.size(); i++) {
            if(bullets.get(i).isIsDead()) bullets.remove(i);
            else g.drawImage(bullets.get(i).getImg(),
                    (int)(bullets.get(i).getStartPos().x* zoomLevel + xOffset),
                    (int)(bullets.get(i).getStartPos().y * zoomLevel + yOffset),
                    (int)(15 * zoomLevel),
                    (int)(15 * zoomLevel), null);
        }
        if(hasLaser) g.drawImage(l.getImg(), (int)(l.getStartPos().x * zoomLevel + xOffset), (int)(l.getStartPos().y * zoomLevel + yOffset), (int)(20 * zoomLevel), (int)(500 * zoomLevel), null);
    }

    /** <p> Set the direction of movement and also the image </p> */
    public void moveForward()
    {
        moveY = -1;
        direction = new Point(0,-1);
        setImage(new ImageIcon(this.getClass().getResource("/images/playership.png")).getImage());
    }
    
    /** <p> Set the direction of movement and also the image </p> */
    public void moveBackward()
    {
        moveY = 1;
        direction = new Point(0,1);
        setImage(new ImageIcon(this.getClass().getResource("/images/playershipdown.png")).getImage());
    }

    /** <p> Set the direction of movement and also the image </p> */
    public void moveRight()
    {
        moveX = 1;
        direction = new Point(1,0);
        setImage(new ImageIcon(this.getClass().getResource("/images/playershipright.png")).getImage());
    }

    /** <p> Set the direction of movement and also the image </p> */
    public void moveLeft()
    {
        moveX = -1;
        direction = new Point(-1,0);
        setImage(new ImageIcon(this.getClass().getResource("/images/playershipleft.png")).getImage());
    }

    /** <p> Null the direction in Y axis </p> */
    public void setZeroY()
    {
        moveY = 0;
    }

    /** <p> Null the direction in X axis </p> */
    public void setZeroX()
    {
        moveX = 0;
    }

    /** <p> Move speed ammount of pixels in direction
     * Also call collect() method</p> */
    public void move()
    {
        if(x + moveX * speed > 0 && x + moveX * speed < 800/GameEngine.zoomLevel-width) x += moveX * speed;
        else collided = true;
        if(y + moveY * speed > 0 && y + moveY * speed < 600/GameEngine.zoomLevel-height) y += moveY * speed;
        else collided = true;
        cooldown--;
        if(cooldown<0){
            whatCollected="";
            if(hp <maxHP) hp += healPerSecond /50;
        }
        if(name.contains("Player"))collect();
        if(hasLaser){
            l.move(x, y);
            if(l.isIsDead()) hasLaser = false;
        }
        if(!inBox(0, 0, (int)(800/GameEngine.zoomLevel))) isDead=true;
    }

    /** <p> Setter of the collected powerup label cooldown. Literaly how many frames it will be visible. </p> 
     * @param cooldown collected powerup cooldown*/
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

     /** <p> Setter of the image </p> 
     * @param image the new image of the ship*/
    public void setImage(Image image){
        this.image=image;
    }
    
    /** <p> Getter of the image </p> 
     * @return ship current image*/
    public Image getImage(){
        return this.image;
    }
    
    /** <p> This method creates a bullet and add to the bullets array </p> */
    public void shoot(){
        if(bullets.size()+1<=maxBullets){
        Bullet b = new Bullet(new Point(x+width/2-15/2,y+height/2-15/2), direction, bulletSpeed, "player");
        bullets.add(b);
        l = new Laser(new Point(x+width/2-15/2,y+height/2-15/2), "player",20);
        hasLaser = true;
        }
    }

    /** <p> Getter of the bullets array </p> 
     * @return bullets array*/
    public List<Bullet> getBullets() {
        return bullets;
    }

     /** <p> Getter of the maximum number of bullets </p> 
     * @return how many bullets can a player shoot*/
    public int getMaxBullets() {
        return maxBullets;
    }

     /** <p> Getter of the maximum helth </p> 
     * @return how many hp can a player have*/
    public double getMaxHP() {
        return maxHP;
    }

     /** <p> Getter of the actually collected powerup </p> 
     * @return the collected poweup name*/
    public String getWhatCollected() {
        return whatCollected;
    }

    /** <p> Getter of the speed </p> 
     * @return speed = how much pixels move in a frame*/
    public double getSpeed() {
        return speed;
    }
    
    /** <p> Go through the powerups array and check if the player touch one <br>
     * Set that powerup and the label of it.</p> */
    public void collect(){
        for (int i = 0; i < GameEngine.powerups.size(); i++) {
            if(inBox(GameEngine.powerups.get(i).getX(),GameEngine.powerups.get(i).getY())){
                maxHP+=GameEngine.powerups.get(i).getPowerups()[0]*5;
                healPerSecond +=GameEngine.powerups.get(i).getPowerups()[1];
                speed+=GameEngine.powerups.get(i).getPowerups()[2]/2.0;
                bulletSpeed+=GameEngine.powerups.get(i).getPowerups()[3]/2.0;
                maxBullets+=GameEngine.powerups.get(i).getPowerups()[4];
                whatCollected = GameEngine.powerups.get(i).getWhatisit();
                cooldown = 15;
                GameEngine.powerups.remove(i);
            }
        }
    }
    
    /** <p> Check if the player is touching a point </p> 
     * @param x0 the x coordinate of the point
     * @param y0 the y coordinate of the point
     * @return if the player coordinates in a box*/
    public boolean inBox(int x0, int y0){
        return (x0 >= x && x0 <= x+width && y0 >= y && y0 <= y+width);
    }
    
        public boolean inBox(int x0, int y0, int boxSize){
        return (x >= x0 && x <= x0+boxSize && y >= y0 && y <= y0+boxSize);
    }
}
