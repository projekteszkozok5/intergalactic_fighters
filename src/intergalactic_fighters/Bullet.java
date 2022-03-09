/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import java.awt.Point;

/**
 *
 * @author marton552
 */
public class Bullet {
    private Point startPos;
    private Point direction;
    private double speed;
    private Entity owner;

    public Bullet(Point startPos, Point direction, double speed, Entity owner) {
        this.startPos = startPos;
        this.direction = direction;
        this.speed = speed;
        this.owner = owner;
    }
    
}
