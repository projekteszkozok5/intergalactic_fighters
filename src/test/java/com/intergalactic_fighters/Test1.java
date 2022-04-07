package com.intergalactic_fighters;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test1
{
    static GameEngine g = new GameEngine(1);

    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue(g.test == 6);
    }
}