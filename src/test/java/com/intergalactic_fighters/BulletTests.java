package com.intergalactic_fighters;

import com.intergalactic_fighters.sprites.enemies.CrazyEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.Point;
import javax.swing.ImageIcon;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author marton552
 */
public class BulletTests {
    Bullet b;

    @BeforeEach
    void init(){
        b = new Bullet(new Point(100,100),new Point(0,1),10,"player");
    }
    
    @Test //bullet created
    @DisplayName("Test: player bullet created")
    public void bulletCreated()
    {
        assertEquals(b.getImg(),(new ImageIcon(this.getClass().getResource("/images/bullet.png")).getImage()));
    }
    
    @Test //bullet in a box
    @DisplayName("Test: bullet in a box")
    public void bulletInBox()
    {
        assertTrue(b.inBox(0, 0, 200));
    }
    
    @Test //bullet is alive
    @DisplayName("Test: bullet in alive")
    public void bulletIsAlive()
    {
        assertTrue(!b.isIsDead());
    }
}
