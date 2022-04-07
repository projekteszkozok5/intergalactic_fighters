/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intergalactic_fighters;

import com.intergalactic_fighters.backgrounds.BasicBackground;
import com.intergalactic_fighters.sprites.Explosion;
import com.intergalactic_fighters.sprites.Powerup;
import com.intergalactic_fighters.sprites.enemies.CrazyEnemy;
import com.intergalactic_fighters.sprites.enemies.EasyEnemy;
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
    public static ArrayList<Explosion> explosions;
    private int zoomTimer = -1;
    private boolean travel = false;
    private boolean finished = false;
    private boolean gameover = false;
    private Random r = new Random();
    private int level = 0;
    public static int test = 6;

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
        PlayerShip = new ImageIcon(this.getClass().getResource("/images/playership.png")).getImage();
        EnemyShip = new ImageIcon(this.getClass().getResource("/images/enemies/enemyship.png")).getImage();
        Players.add(new Player("Player1", (int) (800 / zoomLevel / 2 - gridSize / 2), (int) (500 / zoomLevel - gridSize / 2), gridSize, gridSize, PlayerShip));//player
        set();
        BasicBackground back1 = new BasicBackground();
        back1.setPosY(-800);
        backs = new ArrayList<>();
        backs.add(back1);
        powerups = new ArrayList<>();
        explosions = new ArrayList<>();
    }

    private void set() {
        int rand = r.nextInt(11 + (level * 2));
        int c = 0;
        for (int i = 0; i < rand; i++) {
            Enemies.add(new CrazyEnemy("CrazyEnemy" + i, (int) (800 / zoomLevel / 2 + gridSize * i), gridSize + 10, gridSize, gridSize, EnemyShip));//"enemy"
            c++;
        }
        for (int i = 0; i < ((10 + (level * 2)) - rand); i++) {
            Enemies.add(new EasyEnemy("EasyEnemy" + i, (int) (800 / zoomLevel / 2 + gridSize * i - 150), gridSize + 10, gridSize, gridSize, EnemyShip));//"enemy"
            c++;
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        setBackground(Color.BLACK);//hatter
        for (int i = 0; i < backs.size(); i++) {
            if (score == 10 + (level * 2)) {
                travel = true;
                score = 0;
                level++;
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
            if (!Players.get(i).isDead) {
                Players.get(i).draw(grphcs, zoomLevel, 0, 0);
            }
        }
        for (int i = Enemies.size() - 1; i >= 0; i--) {
            Enemies.get(i).draw(grphcs, zoomLevel, 0, 0);
        }
        for (int i = explosions.size() - 1; i >= 0; i--) {
            explosions.get(i).draw(grphcs, zoomLevel);
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
        grphcs.drawString("Score: " + Integer.toString(score) + "/" + Integer.toString(10 + (level * 2)), 320, 40);
        grphcs.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        grphcs.setColor(Color.green);
        grphcs.drawString(Players.get(0).getWhatCollected(), 320, 580);
        if (gameover) {
            grphcs.setFont(new Font("TimesRoman", Font.PLAIN, 125));
            grphcs.setColor(Color.red);
            grphcs.drawString("GAME OVER", 25, 330);
            grphcs.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            grphcs.drawString("Cleared levels: " + Integer.toString(level), 300, 370);
        }
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                for (int i = 0; i < Players.size(); i++) {
                    if (Players.get(i).isDead && gameover == false) {
                        gameover = true;
                        Enemies.clear();
                        powerups.clear();
                        explosions.add(new Explosion(Players.get(i).getX(), Players.get(i).getY()));
                    } else {
                        Players.get(i).move();
                    }
                }
                for (int i = 0; i < Enemies.size(); i++) {
                    if (Enemies.get(i).isIsDead()) {
                        enemyDie(i);
                    } else {
                        Enemies.get(i).move();
                    }
                }
                for (int i = 0; i < powerups.size(); i++) {
                    powerups.get(i).step();
                }
                for (int i = 0; i < explosions.size(); i++) {
                    explosions.get(i).nextFrame();
                    if (explosions.get(i).getPicPos() > 12) {
                        explosions.remove(i);
                    }
                }
                if (travel) {
                    zoomTimer = 200;
                    travel = false;
                    int random = r.nextInt(3) + 3;
                    Enemies.clear();
                    score = 0;
                    for (int i = 0; i < random; i++) {
                        Powerup p = new Powerup(r.nextInt(500) + 50, 0);
                        powerups.add(p);
                    }
                }
                if (zoomTimer > 0) {
                    zoomTimer--;
                    for (int i = 0; i < backs.size(); i++) {
                        if (zoomTimer > 100) {
                            backs.get(i).setSpeed((200 - zoomTimer) / 2);
                        } else {
                            backs.get(i).setSpeed(zoomTimer / 2);
                        }
                    }
                } else if (zoomTimer == 0) {
                    set();
                    for (int i = 0; i < backs.size(); i++) {
                        backs.get(i).setSpeed(1);
                    }
                    zoomTimer--;
                }
                repaint();
            } catch (NullPointerException e)//ezt meg meg kellene oldani.
            {
                test = 6;
            }

        }

    }

    public void enemyDie(int i) {
        explosions.add(new Explosion(Enemies.get(i).getX(), Enemies.get(i).getY()));
        powerups.add(new Powerup(Enemies.get(i).getX(), Enemies.get(i).getY()));
        Enemies.remove(i);
        score++;
    }

    public ArrayList<BasicBackground> getBacks() {
        return backs;
    }

    public static ArrayList<Explosion> getExplosions() {
        return explosions;
    }

    public boolean isTravel() {
        return travel;
    }

    public boolean isGameover() {
        return gameover;
    }

    public static ArrayList<Powerup> getPowerups() {
        return powerups;
    }
}
