package com.intergalactic_fighters;


/**
 * <p>
 * Oh, the misery Everybody wants to be my enemy <br>
 * Please see the {@link com.intergalactic_fighters.Player} class for truly understand.<br>
 * This is an abstract class, and ther are different types of enemies. Check: <br>
 * Crazy: {@link com.intergalactic_fighters.sprites.enemies.CrazyEnemy}<br>
 * Easy: {@link com.intergalactic_fighters.sprites.enemies.EasyEnemy}
 * </p>
 *
 * @author Peszleg Márton
 */
public abstract class Enemy extends Player {

    /**
     * <p>
     * The constuctor creates an enemy, and call the Player class.
     * </p>
     *
     * @param name the name of the enemy ship
     * @param x the starting position on x axis
     * @param y the starting position on y axis
     * @param width the width of the ship in pixels
     * @param height the height of the ship in pixels
     */
    protected Enemy(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
    }

    /** <p> This is an abstract function, every enemy moves differently. </p> */
    public abstract void movement();
}
