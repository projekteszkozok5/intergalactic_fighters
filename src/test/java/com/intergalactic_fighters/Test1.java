package com.intergalactic_fighters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test1
{
    static GameEngine g = new GameEngine(0);

    @Test
    public void shouldAnswerWithTrue()
    {
        System.out.println("Test file loaded");
        assertTrue(g.test == 6);
    }
    
    @Test //player created
    public void playerCreated()
    {
        System.out.println("Player loaded");
        assertTrue(g.Players.size() > 0);
    }
    
    @Test //enemies created
    public void enemiesCreated()
    {
        System.out.println("Enemies loaded");
        assertTrue(g.Enemies.size() > 0);
    }
    
    @Test //enemies number is right
    public void backsCreated()
    {
        System.out.println("Backgrounds loaded");
        assertTrue(g.getBacks().size() > 0);
    }
    
    @Test //enemies number is right
    public void enemiesNumber()
    {
        System.out.println("Testing: enough enemy loaded");
        assertTrue(g.Enemies.size() == 10);
    }
    
    @Test //enemies number is right
    public void enemyCanDie()
    {
        System.out.println("Testing: enemy can die");
        g.Enemies.get(0).loseHp(100);
        assertTrue(g.Enemies.get(0).isIsDead());
    }
    
    @Test //enemies number is right
    public void enemyDropPowerup()
    {
        System.out.println("Testing: enemy drop powerup");
        g.Enemies.get(0).loseHp(100);
        g.enemyDie(0);
        assertTrue(g.getPowerups().size()>0);
    }
}