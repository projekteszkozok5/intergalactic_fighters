/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

/**
 *
 * @author Prokkály László
 */
public class intergalactic_fighters {

    /**
     * @param args the command line arguments
     */
    public static GUI GeneralUserInterface;
    
    public static void main(String[] args) {
        GeneralUserInterface = new GUI();
        GeneralUserInterface.frame.addKeyListener(new MoveKeyListener());
        //test
    }
    
    
    public static class MoveKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) { //alternetiv iranyitas
            if(e.getKeyCode() == 38)
            {
                //GeneralUserInterface.GameArea.Player.moveForward();
            }
            if(e.getKeyCode() == 40)
            {
                //GeneralUserInterface.GameArea.Player.moveBackward();
            }
            if(e.getKeyCode() == 39)
            {
                //GeneralUserInterface.GameArea.Player.moveRight();
            }
            if(e.getKeyCode() == 37)
            {
                //GeneralUserInterface.GameArea.Player.moveLeft();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
    
    }
      
}