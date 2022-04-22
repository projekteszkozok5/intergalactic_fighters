package com.intergalactic_fighters;

import com.intergalactic_fighters.sprites.Powerup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

public class PowerUpTests {

    GameEngine g;
    Player p;

    @BeforeEach
    void init(){
        g = new GameEngine(1);
        p = new Player(("Player1"), 100, 100, 40, 40);
    }

    //powerup tests

    @Test //pick up maxHP boost
    @DisplayName("Test: Powerup max hp increase")
    public void maxHpPowerUp()
    {
        Powerup powerup = new Powerup(100,100,1);
        g.powerups.add(powerup);
        p.collect();
        assertEquals(p.getMaxHP(), 105);
    }

    @Test //pick up speed boost
    @DisplayName("Test: Powerup speed increase")
    public void speedPowerUp()
    {
        Powerup powerup = new Powerup(100,100,3);
        g.powerups.add(powerup);
        p.collect();
        assertTrue(p.getSpeed() > 3);
    }

    @Test //pick up max bullet boost
    @DisplayName("Test: Powerup max bullet number increase")
    public void maxBulletPowerUp()
    {
        Powerup powerup = new Powerup(100,100,5);
        g.powerups.add(powerup);
        p.collect();
        assertEquals(p.getMaxBullets(),3);
    }
}
