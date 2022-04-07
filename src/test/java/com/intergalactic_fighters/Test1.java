package com.intergalactic_fighters;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Test1 
{
    static GameEngine g = new GameEngine(1);
    
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue(g.test == 6);
    }
}
