package com.intergalactic_fighters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;

public class PlayerTests {
    GameEngine g;

    @BeforeEach
    void init(){
        g = new GameEngine(1);
    }
    //basic visibility tests

    @Test //player created
    @DisplayName("Test: Player created")
    public void playerCreated()
    {
        assertTrue(g.players.size() > 0);
    }

    @Test //player die
    @DisplayName("Test: Player can die")
    public void playerDie()
    {
        Player p = new Player(("Player1"), 100, 100, 40, 40);
        p.loseHp(101);
        assertTrue(p.isIsDead());
    }
}
