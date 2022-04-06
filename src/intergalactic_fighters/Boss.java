/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import java.awt.Point;

/**
 *
 * @author Majercsik Szabolcs
 */
public class Boss {
    private Point position;
    private Point direction;
    private int lives;

    public Boss(Point position, Point direction, int lives) {
        this.position = position;
        this.direction = direction;
        this.lives = lives;
    }
}
