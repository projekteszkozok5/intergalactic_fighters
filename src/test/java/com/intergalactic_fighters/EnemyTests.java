package com.intergalactic_fighters;

import com.intergalactic_fighters.sprites.enemies.CrazyEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyTests {
    GameEngine g;
    Player p;

    @BeforeEach
    void init(){
        g = new GameEngine(1);
    }

    @Test //enemies created
    public void enemiesCreated()
    {
        assertTrue(g.Enemies.size() > 0);
    }

    @Test //enemies number is right
    public void backgroundCreated()
    {
        assertTrue(g.getBacks().size() > 0);
    }

    @Test //enemies number is right
    public void enemiesNumber()
    {
        assertEquals(g.Enemies.size(),10);
    }

    @Test //enemies can die
    public void enemyCanDie()
    {
        Enemy e = new CrazyEnemy(("enemy"), 100, 100, 40, 40, null);
        e.loseHp(100);
        assertTrue(e.isIsDead());
    }

    @Test //enemies drop powerup
    public void enemyDropPowerup()
    {
        g.Enemies.get(0).loseHp(100);
        g.enemyDie(0);
        assertTrue(g.getPowerups().size()>0);
    }

    @Test //enemies exploding
    public void enemyExplode()
    {
        g.Enemies.get(0).loseHp(100);
        g.enemyDie(0);
        assertTrue(g.getExplosions().size()>0);
    }

    //collision tests

    @Test //a fast bullet got the enemy
    public void enemyGotShotByFasterBullet()
    {
        Enemy e = new CrazyEnemy(("enemy"), 100, 100, 40, 40, null);
        Bullet b = new Bullet(new Point(95,120), new Point(1,0), 10, "player");
        b.move();
        boolean th = e.inBox(b.getStartPos().x, b.getStartPos().y);
        assertTrue(th);
    }

    @Test //a slower bullet
    public void enemyGotShotBySlowerBullet()
    {
        Enemy e = new CrazyEnemy(("enemy"), 100, 100, 40, 40, null);
        Bullet b = new Bullet(new Point(90,120), new Point(1,0), 5, "player");
        b.move();
        boolean th = e.inBox(b.getStartPos().x, b.getStartPos().y);
        assertTrue(!th);
    }
}
