/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters.backgrounds;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author marton552
 */
public class BasicBackground {
    private double speed;
    private Image img;
    
    public BasicBackground(){
        this.speed = 1;
        img = new ImageIcon("src/design/images/backs/orange.png").getImage();
        
    }
}
