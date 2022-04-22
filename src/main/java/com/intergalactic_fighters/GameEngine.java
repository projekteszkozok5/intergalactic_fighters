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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Prokkály Lászlóka
 */
public class GameEngine extends JPanel {

    public static List<Player> players;
    public static List<Enemy> enemies;
    private Image playerShip;
    private Image enemyShip;
    private Timer newFrameTimer;
    private final int fps = 60;
    private int gridSize = 40;//segedhalo racsmerete
    public static double zoomLevel = 1.5;//zoomlevel szamítson az eltolasnal? nem.
    private int playerNumber;
    private int score = 0;
    public static List<BasicBackground> backs;
    public static List<Powerup> powerups;
    public static List<Explosion> explosions;
    private int zoomTimer = -1;
    private boolean travel = false;
    private boolean finished = false;
    private boolean gameover = false;
    private Random r = new Random();
    private int level = 0;
    public void addScore(int score) {
        this.score += score;
    }
    private static final Font timesNewRomanFont =new Font("TimesRoman", Font.PLAIN, 20);
    
    /**
 * <p>This is a simple description of the method. . .
 * <a href="http://www.supermanisthegreatest.com">Superman!</a>
 * </p>
 * @param PlayerNumber is the number of the players
 * @see <a href="http://www.link_to_jira/HERO-402">HERO-402</a>
 * @since 1.0
 */

    public GameEngine(int PlayerNumber) {
        super();
        this.playerNumber = PlayerNumber;

        newFrameTimer = new Timer(1000 / fps, new NewFrameListener());
        newFrameTimer.start();
        setup();
    }

    public void setup() {
        players = new ArrayList<>();
        enemies = new ArrayList<>();
        playerShip = new ImageIcon(this.getClass().getResource("/images/playership.png")).getImage();
        enemyShip = new ImageIcon(this.getClass().getResource("/images/enemies/enemyship.png")).getImage();
        players.add(new Player("Player1", (int) (800 / zoomLevel / 2 - gridSize / 2), (int) (500 / zoomLevel - gridSize / 2), gridSize, gridSize, playerShip));//player
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
        for (int i = 0; i < rand; i++) {
            enemies.add(new CrazyEnemy("CrazyEnemy" + i, (int) (800 / zoomLevel / 2 + gridSize * i), gridSize + 10, gridSize, gridSize, enemyShip));//"enemy"
        }
        for (int i = 0; i < ((10 + (level * 2)) - rand); i++) {
            enemies.add(new EasyEnemy("EasyEnemy" + i, (int) (800 / zoomLevel / 2 + gridSize * i - 150), gridSize + 10, gridSize, gridSize, enemyShip));//"enemy"
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
            grphcs.drawImage(backs.get(i).getImg(), 0, backs.get(i).getPosY(), 800,1400, null);
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
        for (int i = players.size() - 1; i >= 0; i--) {
            if (!players.get(i).isDead) {
                players.get(i).draw(grphcs, zoomLevel, 0, 0);
            }
        }
        for (int i = enemies.size() - 1; i >= 0; i--) {
            enemies.get(i).draw(grphcs, zoomLevel, 0, 0);
        }
        for (int i = explosions.size() - 1; i >= 0; i--) {
            explosions.get(i).draw(grphcs, zoomLevel);
        }

        grphcs.setFont(timesNewRomanFont);
        grphcs.setColor(Color.white);
        grphcs.fillRect(600, 550, 200, 50);
        grphcs.fillRect(0, 550, 200, 50);
        grphcs.setColor(Color.red);
        grphcs.drawString("HP:", 560, 580);
        grphcs.fillRect(605, 555, (int) (190 * (players.get(0).getHp() / players.get(0).getMaxHP())), 40);
        grphcs.setColor(Color.blue);
        grphcs.fillRect(5, 555, (int) (190 * (players.get(0).getBullets().size() / (double) players.get(0).getMaxBullets())), 40);
        grphcs.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        grphcs.setColor(Color.red);
        grphcs.drawString("Score: " + Integer.toString(score) + "/" + Integer.toString(10 + (level * 2)), 320, 40);
        grphcs.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        grphcs.setColor(Color.green);
        grphcs.drawString(players.get(0).getWhatCollected(), 320, 580);
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
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).isDead && !gameover) {
                        gameover = true;
                        enemies.clear();
                        powerups.clear();
                        explosions.add(new Explosion(players.get(i).getX(), players.get(i).getY()));
                    } else {
                        players.get(i).move();
                    }
                }
                for (int i = 0; i < enemies.size(); i++) {
                    if (enemies.get(i).isIsDead()) {
                        enemyDie(i);
                    } else {
                        enemies.get(i).move();
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
                    enemies.clear();
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

            }

        }

    }

    public void enemyDie(int i) {
        explosions.add(new Explosion(enemies.get(i).getX(), enemies.get(i).getY()));
        powerups.add(new Powerup(enemies.get(i).getX(), enemies.get(i).getY()));
        enemies.remove(i);
        score++;
    }

    public List<BasicBackground> getBacks() {
        return backs;
    }

    public static List<Explosion> getExplosions() {
        return explosions;
    }

    public boolean isTravel() {
        return travel;
    }

    public boolean isGameover() {
        return gameover;
    }

    public static List<Powerup> getPowerups() {
        return powerups;
    }
}
