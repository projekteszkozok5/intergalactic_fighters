/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author Prokkály László
 */
public class GameEngine extends JPanel {
    
    public static ArrayList<Player> Players;
    public static ArrayList<Player> Enemies;
    public ArrayList<Wall> Walls;
    private Image OrangeImage;
    private Image GreenImage;
    private Image BlueImage;
    private Image PurpleImage;
    private Image BlackCircle;
    private Image PlayerShip;
    private Image EnemyShip;
    private Timer newFrameTimer;
    private final int FPS = 60;
    private int gridSize = 40;//segedhalo racsmerete
    public int zoomLevel = 2;//zoomlevel szamítson az eltolasnal? nem.
    private int cameraMoveSpeed = 3;//kamera mozgasi sebessege
    private int Xoffset = 0;//kamera X iranyu kimozdulasa
    private int Yoffset = 0;//kamera Y iranyu kimozdulasa
    private int motionSpeed = 3; //pixel jump/tick
    private int PlayerNumber;
    
    public GameEngine(int PlayerNumber) {
        super();
        this.PlayerNumber = PlayerNumber;
        
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
        Setup();
    }
    
    public void Setup (){
        OrangeImage = new ImageIcon("src/design/images/orange.png").getImage();
        GreenImage = new ImageIcon("design/images/green.png").getImage();
        BlueImage = new ImageIcon("design/images/blue.png").getImage();
        PurpleImage = new ImageIcon("design/images/purple.png").getImage();
        BlackCircle = new ImageIcon(this.getClass().getResource("/design/images/circle.png")).getImage();
        PlayerShip = new ImageIcon(this.getClass().getResource("/design/images/playership.png")).getImage();
        EnemyShip = new ImageIcon(this.getClass().getResource("/design/images/enemyship.png")).getImage();
        Walls = new ArrayList<>();
        Players = new ArrayList<>();
        Enemies = new ArrayList<>();
        Players.add(new Player("Player1", gridSize, gridSize, gridSize, gridSize, PlayerShip));//player
        Enemies.add(new Player("Enemy1", gridSize+10, gridSize+10, gridSize, gridSize, EnemyShip));//"enemy"
    }

    public void addWall( int x, int y, int width, int height, Image image){
        Walls.add(new Wall(x,y,width,height,image));
    }
    
    public void addWalls(ArrayList<Wall> walls){
        Walls = walls;
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        setBackground(Color.LIGHT_GRAY);//hatter
        for (int i = 0; i < (600 / gridSize) + 1; i++) {//vizszintes seged vonalak
            grphcs.drawLine(0 + Xoffset, i * gridSize * zoomLevel + Yoffset, 800 * zoomLevel + Xoffset, i * gridSize * zoomLevel  + Yoffset);
        }
        for (int i = 0; i < (800 / gridSize) + 1; i++) {//fuggoleges seged vonalak
            grphcs.drawLine(i * gridSize * zoomLevel + Xoffset, 0  + Yoffset, i * gridSize * zoomLevel + Xoffset, 600 * zoomLevel  + Yoffset);
        }
        for (int i = Walls.size() - 1; i >= 0 ; i--) {
            Walls.get(i).draw(grphcs, zoomLevel, Xoffset, Yoffset);
        }
        for (int i = Players.size() - 1; i >= 0 ; i--) {
            Players.get(i).draw(grphcs, zoomLevel, Xoffset, Yoffset);
            Enemies.get(i).draw(grphcs, zoomLevel, Xoffset, Yoffset);
        }
        
        grphcs.drawString("Health: " + Integer.toString(Players.get(PlayerNumber-1).getHp()), 10, 20);
        grphcs.drawString("Capacity: " + Integer.toString(0) + "/" + Integer.toString(20), 100, 20);
    }
    
    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try
            {
                //collisiondetection NYI
                /*for (int i = 0; i < Walls.size(); i++) {
                    if (List.get(0).collide_left(Walls.get(i))){
                        if(List.get(0).getVelx() > 0){
                            List.get(0).setVelx(0);
                        }
                    }
                    if (List.get(0).collide_right(Walls.get(i))){
                        if(List.get(0).getVelx() < 0){
                            List.get(0).setVelx(0);
                        }
                    }
                    if (List.get(0).collide_down(Walls.get(i))){
                        if(List.get(0).getVely() > 0){
                            List.get(0).setVely(0);
                        }
                    }
                    if (List.get(0).collide_up(Walls.get(i))){
                        if(List.get(0).getVely() < 0){
                            List.get(0).setVely(0);
                        }
                    }
                }*/
                //mozgas
                for (int i = 0; i < Players.size(); i++) {
                    Players.get(i).move(motionSpeed);
                    Enemies.get(i).move(motionSpeed);
                }
                
                //cameracorrection                                                        |      zoomlál elcsuszik.    |    paros szamu racs van ezzel kozepre helyzem
                Xoffset = 400 / zoomLevel - Players.get(PlayerNumber-1).getX() * zoomLevel + gridSize * (zoomLevel + 1) + gridSize / 2;
                Yoffset = 300 / zoomLevel - Players.get(PlayerNumber-1).getY() * zoomLevel + gridSize * (zoomLevel + 1) - gridSize / 2;
                repaint();
                }
            catch(NullPointerException e)//ezt meg meg kellene oldani.
            {
                System.out.println();
            }
        }
    }
}
