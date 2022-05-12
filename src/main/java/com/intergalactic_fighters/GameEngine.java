package com.intergalactic_fighters;

import com.intergalactic_fighters.backgrounds.BasicBackground;
import com.intergalactic_fighters.sprites.Explosion;
import com.intergalactic_fighters.sprites.PowerUpgrade;
import com.intergalactic_fighters.sprites.Powerup;
import com.intergalactic_fighters.sprites.enemies.BossLaser;
import com.intergalactic_fighters.sprites.enemies.BossShield;
import com.intergalactic_fighters.sprites.enemies.CrazyEnemy;
import com.intergalactic_fighters.sprites.enemies.EasyEnemy;
import com.intergalactic_fighters.sprites.enemies.LaserEnemy;
import com.intergalactic_fighters.sprites.enemies.NullEnemy;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * <p>
 * This class is the core of the game. Everything happens here.
 * </p>
 *
 * @author Prokkály László
 */
public class GameEngine extends JPanel {

    private char[][] maps = new char[][]{
        {'c', 'c', 'c', 'c', 'c', 'e', 'e', 'e', 'e', 'e'},
        {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'e', 'e', 'e'},
        {'c', 'c', 'c', 'c', 'c', 'e', 'e', 'e', 'l', 'l'},
        {'l', 'l', 'l', 'l', 'l', 'e', 'e', 'e', 'e', 'e'},
        {'n', 'n', 'n', 'c', 'c', 'e', 'e', 'e', 'e', 'e'},
        {'n', 'n', 'l', 'l', 'l', 'e', 'e', 'e', 'e', 'c'},
        {'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'n', 'n'},
        {'n', 'n', 'n', 'n', 'n', 'l', 'l', 'l', 'l', 'l'},
        {'1', 'c', 'c', 'c', 'c', 'e', 'e', 'e', 'e', 'e'},
        {'2', 'c', 'c', 'c', 'c', 'e', 'e', 'e', 'e', 'e'},};

    /**
     * <p>
     * The public array of the players is static and reachable from
     * everywhere</p>
     */
    public static List<Player> Players = new ArrayList<>();
    /**
     * <p>
     * The public array of the enemies is static and reachable from
     * everywhere</p>
     */
    public static List<Enemy> Enemies = new ArrayList<>();
    private final int FPS = 60;
    public static int gridSize = 40;
    /**
     * <p>
     * This variable shows how much the screen zoomed. Every size multiplied
     * with this.</p>
     */
    public static double zoomLevel = 1.5;
    private int PlayerNumber;
    private int score = 0;
    /**
     * <p>
     * The public array of the backgrounds is static and reachable from
     * everywhere</p>
     */
    public static List<BasicBackground> backs = new ArrayList<>();
    /**
     * <p>
     * The public array of the powerups is static and reachable from
     * everywhere</p>
     */
    public static List<Powerup> powerups = new ArrayList<>();
    /**
     * <p>
     * The public array of the explosions is static and reachable from
     * everywhere</p>
     */
    public static List<Explosion> explosions = new ArrayList<>();
    private int zoomTimer = -1;
    private boolean travel = false;
    private boolean gameover = false;
    private Random r = new Random();
    private int level = 0;
    private Timer newFrameTimer;
    private static String fontStyle = "TimesRoman";
    private int EnemySizeNow = 0;
    public static boolean isBossAlive = false;
    private boolean boss1killed = false;
    private boolean boss12killed = false;
    private PowerUpgrade left;
    private PowerUpgrade right;
    private List<Integer> powers;

    private static final Font timesNewRomanFont = new Font(fontStyle, Font.PLAIN, 20);

    /**
     * <p>
     * Constructor setup everything. Creates the player(s) and a basic
     * background. Call set() method.</p>
     *
     * @param number the number of the players
     */
    public GameEngine(int number) {
        super();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
        this.PlayerNumber = number;
        for (int i = 0; i < PlayerNumber; i++) {
            Players.add(new Player("Player" + PlayerNumber + "", (int) (800 / zoomLevel / 2 - gridSize / 2.0), (int) (500 / zoomLevel - gridSize / 2.0), gridSize, gridSize));//player
        }
        set();
        BasicBackground back1 = new BasicBackground();
        back1.setPosY(-800);
        backs.add(back1);
        powers = new ArrayList<Integer>();
        for (int i = 1; i < 6; i++) {
            powers.add(i);
        }
    }

    /**
     * <p>
     * This method creates random number of enemies </p>
     */
    private void set() {
        Enemies.clear();
        int rand = r.nextInt(8);
        if (level % 2 == 0 && level > 0) {
            rand = r.nextInt(10 - 8) + 8;
        }
        if (rand == 8 && boss1killed) {
            rand = 3;
        }
        if (rand == 9 && boss12killed) {
            rand = 4;
        }
        int crazynum = 0;
        int easynum = 0;
        int lasernum = 0;
        int nullnum = 0;
        if (rand < 8) {
            for (int i = 0; i < 10; i++) {
                if (maps[rand][i] == 'c') {
                    crazynum++;
                } else if (maps[rand][i] == 'e') {
                    easynum++;
                } else if (maps[rand][i] == 'l') {
                    lasernum++;
                } else if (maps[rand][i] == 'n') {
                    nullnum++;
                }
                crazynum += (level * 2);
            }
            for (int i = 0; i < crazynum; i++) {
                Enemies.add(new CrazyEnemy("CrazyEnemy" + i, (int) (800 / zoomLevel / 2 + gridSize * i), gridSize + 10, gridSize, gridSize));//"enemy"
            }
            for (int i = 0; i < easynum; i++) {
                Enemies.add(new EasyEnemy("EasyEnemy" + i, (int) (800 / zoomLevel / 2 + gridSize * i - 150), gridSize + 10, gridSize, gridSize));//"enemy"
            }
            for (int i = 0; i < lasernum; i++) {
                Enemies.add(new LaserEnemy("LaserEnemy" + i, (int) (800 / zoomLevel / 2 + gridSize * i - 200), gridSize * 3, gridSize, gridSize));//"enemy"
            }
            for (int i = 0; i < nullnum; i++) {
                Enemies.add(new NullEnemy("NullEnemy" + i, (int) (800 / zoomLevel / 2 + gridSize * 2 * i - 150), gridSize + 10, gridSize, gridSize));//"enemy"
            }
        } else if (rand == 8) {
            Enemies.add(new BossShield("BossShield", (int) (800 / zoomLevel / 2 + gridSize * 2 - 150), gridSize + 10, gridSize * 4, gridSize * 4));
            isBossAlive = true;
            boss1killed = true;
        } else if (rand == 9) {
            Enemies.add(new BossLaser("BossLaser", (int) (800 / zoomLevel / 2 + gridSize * 2 - 150), gridSize - 10, gridSize * 4, gridSize * 3));
            isBossAlive = true;
            boss12killed = true;
        }
    }

    /**
     * <p>
     * This method draws into the screen. Also move the backgrounds. </p>
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        setBackground(Color.BLACK);//hatter
        for (int i = 0; i < backs.size(); i++) {
            if (Enemies.size() == 0 && score > 0) {
                travel = true;
                score = 0;
                level++;
            }
            backs.get(i).step();
            grphcs.drawImage(backs.get(i).getImg(), 0, backs.get(i).getPosY(), 800, 1400, null);
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

        if (left != null) {
            left.draw(grphcs, zoomLevel);
            if (left.isChoosed()) {
                zoomTimer--;
                powers.remove(powers.indexOf(left.getArgs()));
                collectPower(left.getArgs());
            }
        }
        if (right != null) {
            right.draw(grphcs, zoomLevel);
            if (right.isChoosed()) {
                zoomTimer--;
                powers.remove(powers.indexOf(right.getArgs()));
                collectPower(right.getArgs());
            }
        }

        grphcs.setFont(timesNewRomanFont);
        grphcs.setColor(Color.white);
        grphcs.fillRect(600, 550, 200, 50);
        grphcs.fillRect(0, 550, 200, 50);
        grphcs.setColor(Color.red);
        grphcs.drawString("HP:", 560, 580);
        grphcs.fillRect(605, 555, (int) (190 * (Players.get(0).getHp() / Players.get(0).getMaxHP())), 40);
        grphcs.setColor(Color.blue);
        grphcs.fillRect(5, 555, (int) (190 * (Players.get(0).getBullets().size() / (double) Players.get(0).getMaxBullets())), 40);
        grphcs.setFont(new Font(fontStyle, Font.PLAIN, 30));
        grphcs.setColor(Color.red);
        if (isBossAlive) {
            grphcs.drawString("HP: " + Integer.toString(Enemies.get(0).getHp()) + "/" + Integer.toString(10), 320, 40);
        } else {
            grphcs.drawString("Score: " + Integer.toString((10 + (level * 2)) - Enemies.size()) + "/" + Integer.toString(10 + (level * 2)), 320, 40);
        }
        grphcs.setFont(new Font(fontStyle, Font.PLAIN, 20));
        grphcs.setColor(Color.green);
        grphcs.drawString(Players.get(0).getWhatCollected(), 320, 580);
        if (gameover) {
            grphcs.setFont(new Font(fontStyle, Font.PLAIN, 125));
            grphcs.setColor(Color.red);
            grphcs.drawString("GAME OVER", 25, 330);
            grphcs.setFont(new Font(fontStyle, Font.PLAIN, 30));
            grphcs.drawString("Cleared levels: " + Integer.toString(level), 300, 370);
        }
    }

    private void collectPower(int i) {
        left = null;
        right = null;
        if (i == 1) {
            Players.get(0).setPower1(true);
            Players.get(0).setMaxBullets();
        }
        if (i == 2) {
            Players.get(0).setPower2(true);
        }
        if (i == 3) {
            Players.get(0).setPower3(true);
        }
        if (i == 4) {
            Players.get(0).setPower4(true);
        }
        if (i == 5) {
            Players.get(0).setPower5(true);
        }
    }

    private class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                for (int i = 0; i < Players.size(); i++) {
                    if (Players.get(i).isDead && !gameover) {
                        gameover = true;
                        isBossAlive = false;
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
                if (level < 5) {
                    if ((zoomTimer > 20 || zoomTimer < 19) && zoomTimer > 0) {
                        zoomTimer--;
                        for (int i = 0; i < backs.size(); i++) {
                            if (zoomTimer > 100) {
                                backs.get(i).setSpeed((200 - zoomTimer) / 2);
                            } else {
                                backs.get(i).setSpeed(zoomTimer / 2);
                            }
                        }
                        if (zoomTimer < 19) {
                            Players.get(0).moveBackward();
                        }
                    } else if (zoomTimer == 20) {
                        int rand1 = (r.nextInt(powers.size()));
                        int rand2;
                        do {
                            rand2 = (r.nextInt(powers.size()));
                        } while (rand1 == rand2);
                        left = new PowerUpgrade((int) (800 / zoomLevel) / 4, (int) (100 / zoomLevel), powers.get(rand1));
                        right = new PowerUpgrade((int) (((800 / zoomLevel) / 4) * 3) - 100, (int) (100 / zoomLevel), powers.get(rand2));
                        zoomTimer--;
                    } else if (zoomTimer == 0) {
                        set();
                        for (int i = 0; i < backs.size(); i++) {
                            backs.get(i).setSpeed(1);
                        }
                        zoomTimer--;
                    }
                } else {
                    if (zoomTimer > 0) {
                        zoomTimer--;
                        for (int i = 0; i < backs.size(); i++) {
                            if (zoomTimer > 100) {
                                backs.get(i).setSpeed((200 - zoomTimer) / 2);
                            } else {
                                backs.get(i).setSpeed(zoomTimer / 2);
                            }
                        }
                    }
                    else if (zoomTimer == 0) {
                        set();
                        for (int i = 0; i < backs.size(); i++) {
                            backs.get(i).setSpeed(1);
                        }
                        zoomTimer--;
                    }

                }
                repaint();
            } catch (NullPointerException e)//ezt meg meg kellene oldani.
            {

            }

        }

    }

    /**
     * <p>
     * If an enemy die, there will be an explosion and drops powerup. Also
     * remove the enemy of the arraylist.</p>
     *
     * @param i which enemy died. It's an index for the Enemies arraylsit
     */
    public void enemyDie(int i) {
        explosions.add(new Explosion(Enemies.get(i).getX(), Enemies.get(i).getY()));
        powerups.add(new Powerup(Enemies.get(i).getX(), Enemies.get(i).getY()));
        Enemies.remove(i);
        score++;
    }

    /**
     * <p>
     * Getter of the backgrounds </p>
     *
     * @return all backgrounds
     */
    public List<BasicBackground> getBacks() {
        return backs;
    }

    /**
     * <p>
     * Getter of the explosions </p>
     *
     * @return all explosions
     */
    public static List<Explosion> getExplosions() {
        return explosions;
    }

    /**
     * <p>
     * Check if the ship is finished a level and traveling now </p>
     *
     * @return if traveling
     */
    public boolean isTravel() {
        return travel;
    }

    /**
     * <p>
     * Check if the player died </p>
     *
     * @return if the game is over
     */
    public boolean isGameover() {
        return gameover;
    }

    /**
     * <p>
     * Getter of the powerups </p>
     *
     * @return all powerups
     */
    public static List<Powerup> getPowerups() {
        return powerups;
    }

    /**
     * <p>
     * Setter of the score. </p>
     *
     * @param score the ammount of the added score
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * <p>
     * Getter of the score </p>
     *
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }
}
