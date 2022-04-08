package com.intergalactic_fighters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTests {
    GameEngine g;

    @BeforeEach
    void init(){
        g = new GameEngine(1);
    }
    //basic visibility tests

    @Test //player created
    public void playerCreated()
    {
        assertTrue(g.Players.size() > 0);
    }

    @Test //player die
    public void playerDie()
    {
        Player p = new Player(("Player1"), 100, 100, 40, 40, null);
        p.loseHp(101);
        assertTrue(p.isIsDead());
    }
}
