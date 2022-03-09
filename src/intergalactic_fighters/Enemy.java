package intergalactic_fighters;


import java.awt.Image;

public class Enemy extends Player{
    /**
     * @param name   Name of player
     * @param x      Where the player spawn on x-axis
     * @param y      Where the player spawn on y-axis
     * @param width  width of player
     * @param height height of player
     * @param image  start image of player (not-animated)
     */
    public Enemy(String name, int x, int y, int width, int height, Image image) {
        super(name, x, y, width, height, image);
    }
}
