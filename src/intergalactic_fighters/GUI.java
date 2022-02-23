/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intergalactic_fighters;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
            }
            //S pressed
            if(e.getKeyCode() == 83)
            {
                GameArea.Players.get(0).moveBackward();
            }
            //A pressed
            if(e.getKeyCode() == 65)
            {
                GameArea.Players.get(0).moveLeft();
            }
            //D pressed
            if(e.getKeyCode() == 68)
            {
                GameArea.Players.get(0).moveRight();
            }
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
}