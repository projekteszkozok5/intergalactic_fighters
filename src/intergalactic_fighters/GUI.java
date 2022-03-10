/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Prokkály László
 */
public class GUI {
    public JFrame frame;
    public GameEngine GameArea;
    public Map map;
    
    public GUI(){
        frame = new JFrame("TEST Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameArea = new GameEngine(1);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        frame.addKeyListener(new MoveKeyListener());

        //GameArea.addKeyListener(new MoveKeyListener());
       
        GameArea.setPreferredSize(new Dimension(800, 600));
        
        map = new Map();
        GameArea.addWalls(map.generateMap());
        
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(GameArea);
        
        frame.getContentPane().add(container);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
    }
    
    public class MoveKeyListener extends JFrame implements KeyListener{
      
        public void keyTyped(KeyEvent e) {
            
        }
        
        public void keyPressed(KeyEvent e) {
            //W - 87, A - 65 , S - 83 , D - 68 
            //Move player with WASD
            //W pressed
            if(e.getKeyCode() == 87)
            {   
                GameArea.Players.get(0).moveForward();
                GameArea.Players.get(0).setImage(new ImageIcon(this.getClass().getResource("/design/images/playership.png")).getImage());

            }
            //S pressed
            if(e.getKeyCode() == 83)
            {
                GameArea.Players.get(0).moveBackward();
                GameArea.Players.get(0).setImage(new ImageIcon(this.getClass().getResource("/design/images/playershipdown.png")).getImage());
            }
            //A pressed
            if(e.getKeyCode() == 65)
            {
                GameArea.Players.get(0).moveLeft();
                GameArea.Players.get(0).setImage(new ImageIcon(this.getClass().getResource("/design/images/playershipleft.png")).getImage());
            }
            //D pressed
            if(e.getKeyCode() == 68)
            {
                GameArea.Players.get(0).moveRight();
                GameArea.Players.get(0).setImage(new ImageIcon(this.getClass().getResource("/design/images/playershipright.png")).getImage());
            }
            enemyMovement();
        }

        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == 87 || e.getKeyCode() == 83)
            {   
                GameArea.Players.get(0).setZeroY();
            }
            if(e.getKeyCode() == 65 || e.getKeyCode() == 68)
            {
                GameArea.Players.get(0).setZeroX();
            }
        }

    }
    public void enemyMovement(){
        Random r = new Random();
        int randomNumber=(r.nextInt((4-1)+1)+1);
        switch (randomNumber){
            case 1:
                GameArea.Enemies.get(0).moveForward();
                GameArea.Enemies.get(0).setImage(new ImageIcon(this.getClass().getResource("/design/images/enemyship.png")).getImage());
                break;
            case 2:
                GameArea.Enemies.get(0).moveBackward();
                GameArea.Enemies.get(0).setImage(new ImageIcon(this.getClass().getResource("/design/images/enemyshipdown.png")).getImage());
                break;
            case 3:
                GameArea.Enemies.get(0).moveRight();
                GameArea.Enemies.get(0).setImage(new ImageIcon(this.getClass().getResource("/design/images/enemyshipright.png")).getImage());
                break;
            case 4:
                GameArea.Enemies.get(0).moveLeft();
                GameArea.Enemies.get(0).setImage(new ImageIcon(this.getClass().getResource("/design/images/enemyshipleft.png")).getImage());
                break;
        }

    }
}