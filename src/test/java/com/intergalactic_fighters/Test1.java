package com.intergalactic_fighters;

import com.intergalactic_fighters.sprites.Powerup;
import com.intergalactic_fighters.sprites.enemies.CrazyEnemy;
import java.awt.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test1
{
    static GameEngine g = new GameEngine(1);

    //basic visibility tests
    
    @Test
    public void shouldAnswerWithTrue()
    {
        g = new GameEngine(1);
        System.out.println("Test file loaded");
        assertTrue(g.test == 6);
    }
    
    @Test //player created
    public void playerCreated()
    {
        g = new GameEngine(1);
        System.out.println("Player loaded");
        assertTrue(g.Players.size() > 0);
    }
    
    @Test //enemies created
    public void enemiesCreated()
    {
        g = new GameEngine(1);
        System.out.println("Enemies loaded");
        assertTrue(g.Enemies.size() > 0);
    }
    
    @Test //enemies number is right
    public void backsCreated()
    {
        g = new GameEngine(1);
        System.out.println("Backgrounds loaded");
        assertTrue(g.getBacks().size() > 0);
    }
    
    @Test //enemies number is right
    public void enemiesNumber()
    {
        g = new GameEngine(1);
        System.out.println("Testing: enough enemy loaded");
        assertTrue(g.Enemies.size() == 10);
    }
    
    @Test //enemies can die
    public void enemyCanDie()
    {
        Enemy e = new CrazyEnemy(("enemy"), 100, 100, 40, 40, null);
        System.out.println("Testing: enemy can die");
        e.loseHp(100);
        assertTrue(e.isIsDead());
    }
    
    @Test //enemies drop powerup
    public void enemyDropPowerup()
    {
        g = new GameEngine(1);
        System.out.println("Testing: enemy drop powerup");
        g.Enemies.get(0).loseHp(100);
        g.enemyDie(0);
        assertTrue(g.getPowerups().size()>0);
    }
    
    @Test //enemies exploding
    public void enemyExplode()
    {
        g = new GameEngine(1);
        System.out.println("Testing: enemy explode");
        g.Enemies.get(0).loseHp(100);
        g.enemyDie(0);
        assertTrue(g.getExplosions().size()>0);
    }
    
    //collision tests
    
    @Test //a fast bullet got the enemy
    public void enemyGotShot()
    {
        System.out.println("Testing: enemy and bullet collision");
        Enemy e = new CrazyEnemy(("enemy"), 100, 100, 40, 40, null);
        Bullet b = new Bullet(new Point(95,120), new Point(1,0), 10, "player");
        b.move();
        boolean th = e.inBox(b.getStartPos().x, b.getStartPos().y);
        assertTrue(th);
    }
    
    @Test //a slower bullet
    public void enemyGotShot2()
    {
        System.out.println("Testing: enemy and bullet collision");
        Enemy e = new CrazyEnemy(("enemy"), 100, 100, 40, 40, null);
        Bullet b = new Bullet(new Point(90,120), new Point(1,0), 5, "player");
        b.move();
        boolean th = e.inBox(b.getStartPos().x, b.getStartPos().y);
        assertTrue(!th);
    }
    
    //powerup tests
    
    @Test //pick up maxHP boost
    public void powerup1()
    {
        g = new GameEngine(1);
        System.out.println("Testing: powerup1");
        Player p = new Player(("Player1"), 100, 100, 40, 40, null);
        Powerup powerup = new Powerup(100,100,1);
        g.powerups.add(powerup);
        p.collect();
        assertTrue(p.getMaxHP() == 105);
    }
    
    @Test //pick up maxHP boost
    public void powerup3()
    {
        g = new GameEngine(1);
        System.out.println("Testing: powerup3");
        Player p = new Player(("Player1"), 100, 100, 40, 40, null);
        Powerup powerup = new Powerup(100,100,3);
        g.powerups.add(powerup);
        p.collect();
        assertTrue(p.getSpeed() > 3);
    }
    
    @Test //pick up max bullet boost
    public void powerup5()
    {
        g = new GameEngine(1);
        System.out.println("Testing: powerup5");
        Player p = new Player(("Player1"), 100, 100, 40, 40, null);
        Powerup powerup = new Powerup(100,100,5);
        g.powerups.add(powerup);
        p.collect();
        assertTrue(p.getMaxBullets() == 3);
    }
    
    //player get shoot
    
    @Test //player die
    public void playerDie()
    {
        System.out.println("Testing: player can die");
        Player p = new Player(("Player1"), 100, 100, 40, 40, null);
        p.loseHp(101);
        assertTrue(p.isIsDead());
    }
}