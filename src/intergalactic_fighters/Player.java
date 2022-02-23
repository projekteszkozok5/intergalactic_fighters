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
/**
 * @author Németh Csaba
 * @version 1.0.2
 * @since 2021. 02. 26.
 */
public class Player{
    //Játékos tulajdonságok
    /**
     * Health Point of player
     */
    private int HP; //HealthPoint
    /**
     * Shield Point of player
     */
    private int SP; //ShieldPoint
    /**
     * Movement Speed of player
     */
    private int Speed;
    
    private int[][] direction = new int[3][3]; 
    /*
    A játékos pillanatnyi irányának megjegyzésére egy mátrixot használok.
    Ha a játékos felfelé néz akkor a mátrix így néz ki:
    0 1 0
    0 0 0
    0 0 0
    
    Jobb:
    0 0 0
    0 0 1
    0 0 0
    */
    //private ArrayList<Utility> Hand; Készülőben, a kézben lévő tárgyakat gyűjtjük ide fegyverek csapdák kulcs stb.. 
    
    
    /**
     * permission for shooting
     */
    private boolean CanShoot; //Ha a játékos lőni próbál ez a változó alapján dől el, hogy tüzelő képes-e vagy sem.
    // Amint fegyver kerül a kézbe és megvan töltve a változót True-ra kell állítani.
    // A lövések közötti időt a fegyver osztályban kell szabályozni
    
    //Egy player lerakásához szükséges változók

    /**
     * name of player
     */
    protected String name;

    /**
     * x-axis of player on map
     */
    protected int x;

    /**
     * y-axis of player on map
     */
    protected int y;

    /**
     * Width of player
     */
    protected int width;

    /**
     * Height of player
     */
    protected int height;

    /**
     *Image of Player
     */
    protected Image image;

    //public ArrayList<Point> Waypoints;

    /**
     * Represents coordinates ot player
     */
    public Point Destination;
    
    
    private int moveX = 0;
    private int moveY = 0;
    
    /**
     * x-axis coordinate
     * @return x variable
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * return y-axis coordinate
     * @return y variable
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * 
     * @param name Name of player
     * @param x Where the player spawn on x-axis
     * @param y Where the player spawn on y-axis
     * @param width width of player
     * @param height height of player
     * @param image start image of player (not-animated)
     */
    public Player(String name, int x, int y, int width, int height, Image image)
    {
        this.HP = 100;
        this.SP = 50;
        this.Speed = 5;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        Destination = new Point(x,y);
    }
    
    /**
     * How many health point lose the player
     * @param points
     */
    public void loseHp(int points)
    {
        HP = HP - points;
    }
    
    
    /**
     * Return the health points of player
     * @return healht point
     */
    public int getHp()
    {
        return HP;
    }
    
    /**
     * Drawing player image to map
     * @param g Grapics board
     * @param zoomLevel Height of the view
     * @param Xoffset x-axis coordinate
     * @param Yoffset y-axis corrdinate
     */
    public void draw(Graphics g, int zoomLevel, int Xoffset, int Yoffset) {
        g.drawImage(image, x * zoomLevel + Xoffset, y * zoomLevel + Yoffset, width * zoomLevel, height * zoomLevel, null);
    }
    
    /**
     * Changing the direction of player movement to up
     */
    public void moveForward()
    {
        moveY = -1;
    }
    
    /**
     * Changing the direction of player movement to down
     */
    public void moveBackward()
    {
        moveY = 1;
    }
    
    /**
     * Changing the direction of player movement to right
     */
    public void moveRight()
    {
        moveX = 1;
    }
    
    /**
     * Changing the direction of player movement to left
     */
    public void moveLeft()
    {
        moveX = -1;
    }
  
    /**
     * Player stop moving on Y-axis
     */
    public void setZeroY()
    {
        moveY = 0;
    }
    
    /**
     * Player stop moving on X-axis
     */
    public void setZeroX()
    {
        moveX = 0;
    }
    
    /**
     * Moving the player with speed
     * @param speed movement speed of player
     */
    public void move(int speed)
    {
        x += moveX * speed;
        y += moveY * speed;
    }
}
