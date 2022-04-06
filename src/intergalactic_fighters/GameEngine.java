/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import intergalactic_fighters.backgrounds.BasicBackground;
import intergalactic_fighters.sprites.Powerup;
import intergalactic_fighters.sprites.enemies.CrazyEnemy;
import intergalactic_fighters.sprites.enemies.EasyEnemy;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
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
    public static ArrayList<Enemy> Enemies;
    private Image PlayerShip;
    private Image EnemyShip;
    private Timer newFrameTimer;
    private final int FPS = 60;
    private int gridSize = 40;//segedhalo racsmerete
    public static double zoomLevel = 1.5;//zoomlevel szamítson az eltolasnal? nem.
    private int PlayerNumber;
    private int score = 0;
    private ArrayList<BasicBackground> backs;
    public static ArrayList<Powerup> powerups;
    private int zoomTimer = -1;
    private boolean travel = false;
    private boolean finished = false;
    private Random r = new Random();

    public void addScore(int score) {
        this.score += score;
    }

    public GameEngine(int PlayerNumber) {
        super();
        this.PlayerNumber = PlayerNumber;

        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
        Setup();
    }

    public void Setup() {
        Players = new ArrayList<>();
        Enemies = new ArrayList<>();
        PlayerShip = new ImageIcon(this.getClass().getResource("/design/images/playership.png")).getImage();
        EnemyShip = new ImageIcon(this.getClass().getResource("/design/images/enemies/enemyship.png")).getImage();
        Players.add(new Player("Player1", (int) (800 / zoomLevel / 2 - gridSize / 2), (int) (500 / zoomLevel - gridSize / 2), gridSize, gridSize, PlayerShip));//player
        set();
        BasicBackground back1 = new BasicBackground();
        back1.setPosY(-800);
        backs = new ArrayList<>();
        backs.add(back1);
        powerups = new ArrayList<>();
    }
    
    private void set(){
        int rand = r.nextInt(10);
        for (int i = 0; i <= rand; i++) {
            Enemies.add(new CrazyEnemy("CrazyEnemy" + i, (int) (800 / zoomLevel / 2 + gridSize * i), gridSize + 10, gridSize, gridSize, EnemyShip));//"enemy"
        }
        for (int i = 0; i <= (10-rand); i++) {
            Enemies.add(new EasyEnemy("EasyEnemy" + i, (int) (800 / zoomLevel / 2 + gridSize * i), gridSize + 10, gridSize, gridSize, EnemyShip));//"enemy"
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        setBackground(Color.BLACK);//hatter
        for (int i = 0; i < backs.size(); i++) {
            if (score == 10) {
                travel = true;
                score = 0;
            }
            backs.get(i).step();
            grphcs.drawImage(backs.get(i).getImg(), 0, (int) (backs.get(i).getPosY()), (int) (800), (int) (1400), null);
        }
        if (backs.get(0).getPosY() > 0 && backs.get(0).isLive()) {
            backs.get(0).setLive(false);
            backs.add(new BasicBackground());
        }
        if (backs.get(0).getPosY() > 600) {
            backs.remove(0);
        }

        for (int i = powerups.size() - 1; i >= 0; i--) {
            powerups.get(i).draw(grphcs, zoomLevel);
        }
        for (int i = Players.size() - 1; i >= 0; i--) {
            Players.get(i).draw(grphcs, zoomLevel, 0, 0);
        }
        for (int i = Enemies.size() - 1; i >= 0; i--) {
            if (Enemies.get(i).isIsDead()) {
                powerups.add(new Powerup(Enemies.get(i).getX(), Enemies.get(i).getY()));
                Enemies.remove(i);
                score++;
            } else {
                Enemies.get(i).draw(grphcs, zoomLevel, 0, 0);
            }
        }

        grphcs.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        grphcs.setColor(Color.white);
        grphcs.fillRect(600, 550, 200, 50);
        grphcs.fillRect(0, 550, 200, 50);
        grphcs.setColor(Color.red);
        grphcs.drawString("HP:", 560, 580);
        grphcs.fillRect(605, 555, (int) (190 * (Players.get(0).getHp() / Players.get(0).getMaxHP())), 40);
        grphcs.setColor(Color.blue);
        grphcs.fillRect(5, 555, (int) (190 * (Players.get(0).getBullets().size() / (double) Players.get(0).getMaxBullets())), 40);
        grphcs.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        grphcs.setColor(Color.red);
        grphcs.drawString("Score: " + Integer.toString(score) + "/" + Integer.toString(10), 320, 40);
        grphcs.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        grphcs.setColor(Color.green);
        grphcs.drawString(Players.get(0).getWhatCollected(), 320, 580);
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                for (int i = 0; i < Players.size(); i++) {
                    Players.get(i).move();
                }
                for (int i = 0; i < Enemies.size(); i++) {
                    Enemies.get(i).move();
                }
                for (int i = 0; i < powerups.size(); i++) {
                    powerups.get(i).step();
                }
                if (travel) {
                    zoomTimer = 200;
                    travel = false;
                    int random = r.nextInt(3)+3;
                    for (int i = 0; i < random; i++) {
                        Powerup p = new Powerup(r.nextInt(500)+50, 0);
                        powerups.add(p);
                    }
                }
                if (zoomTimer > 0) {
                    zoomTimer--;
                    for (int i = 0; i < backs.size(); i++) {
                        if(zoomTimer > 100) backs.get(i).setSpeed((200-zoomTimer)/2);
                        else backs.get(i).setSpeed(zoomTimer/2);
                    }
                } else if(zoomTimer == 0) {
                    set();
                    for (int i = 0; i < backs.size(); i++) {
                        backs.get(i).setSpeed(1);
                    }
                    zoomTimer--;
                }
                repaint();
            } catch (NullPointerException e)//ezt meg meg kellene oldani.
            {
                System.out.println();
            }

        }

    }
}
