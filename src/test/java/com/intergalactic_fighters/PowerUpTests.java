package com.intergalactic_fighters;

import com.intergalactic_fighters.sprites.Powerup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PowerUpTests {

    GameEngine g;
    Player p;

    @BeforeEach
    void init(){
        g = new GameEngine(1);
        p = new Player(("Player1"), 100, 100, 40, 40, null);
    }

    //powerup tests

    @Test //pick up maxHP boost
    public void maxHpPowerUp()
    {
        Powerup powerup = new Powerup(100,100,1);
        g.powerups.add(powerup);
        p.collect();
        assertEquals(p.getMaxHP(), 105);
    }

    @Test //pick up speed boost
    public void speedPowerUp()
    {
        Powerup powerup = new Powerup(100,100,3);
        g.powerups.add(powerup);
        p.collect();
        assertTrue(p.getSpeed() > 3);
    }

    @Test //pick up max bullet boost
    public void maxBulletPowerUp()
    {
        Powerup powerup = new Powerup(100,100,5);
        g.powerups.add(powerup);
        p.collect();
        assertEquals(p.getMaxBullets(),3);
    }
}
