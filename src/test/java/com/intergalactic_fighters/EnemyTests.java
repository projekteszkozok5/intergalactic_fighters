package com.intergalactic_fighters;

import com.intergalactic_fighters.sprites.enemies.CrazyEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

public class EnemyTests {
    GameEngine g;
    Player p;

    @BeforeEach
    void init(){
        g = new GameEngine(1);
    }

    @Test //enemies created
    @DisplayName("Test: Enemy created")
    public void enemiesCreated()
    {
        assertTrue(g.enemies.size() > 0);
    }

    @Test //enemies number is right
    @DisplayName("Test: Background created")
    public void backgroundCreated()
    {
        assertTrue(g.getBacks().size() > 0);
    }

    @Test //enemies number is right
    @DisplayName("Test: Enemy number is okay")
    public void enemiesNumber()
    {
        assertEquals(g.enemies.size(),10);
    }

    @Test //enemies can die
    @DisplayName("Test: Enemy can die")
    public void enemyCanDie()
    {
        Enemy e = new CrazyEnemy(("enemy"), 100, 100, 40, 40, null);
        e.loseHp(100);
        assertTrue(e.isIsDead());
    }

    @Test //enemies drop powerup
    @DisplayName("Test: Enemy drop powerup")
    public void enemyDropPowerup()
    {
        g.enemies.get(0).loseHp(100);
        g.enemyDie(0);
        assertTrue(g.getPowerups().size()>0);
    }

    @Test //enemies exploding
    @DisplayName("Test: Enemy exploding")
    public void enemyExplode()
    {
        g.enemies.get(0).loseHp(100);
        g.enemyDie(0);
        assertTrue(g.getExplosions().size()>0);
    }

    //collision tests

    @Test //a fast bullet got the enemy
    @DisplayName("Test: Enemy got shoot fast bullet")
    public void enemyGotShotByFasterBullet()
    {
        Enemy e = new CrazyEnemy(("enemy"), 100, 100, 40, 40, null);
        Bullet b = new Bullet(new Point(95,120), new Point(1,0), 10, "player");
        b.move();
        boolean th = e.inBox(b.getStartPos().x, b.getStartPos().y);
        assertTrue(th);
    }

    @Test //a slower bullet
    @DisplayName("Test: Enemy got shoot slow bullet")
    public void enemyGotShotBySlowerBullet()
    {
        Enemy e = new CrazyEnemy(("enemy"), 100, 100, 40, 40, null);
        Bullet b = new Bullet(new Point(90,120), new Point(1,0), 5, "player");
        b.move();
        boolean th = e.inBox(b.getStartPos().x, b.getStartPos().y);
        assertTrue(!th);
    }
}
