package com.intergalactic_fighters;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * <p>
 * GUI creates a frame. Methods move enemies and the player.
 * </p>
 *
 * @author Peszleg Márton
 */
public class GUI {

    private JFrame frame;
    private GameEngine GameArea;
    Timer timer;

    /**
     * <p>
     * The constuctor creates a Game frame and put menuitems on it. It put a
     * panel on the frame, and starts the game.
     * </p>
     */
    public GUI() {
        frame = new JFrame("GAME Frame");
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

        JMenuItem newMenuItem = new JMenuItem("Új játék");
        gameMenu.add(newMenuItem);
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GameArea.Enemies.clear();
                GameArea.Players.clear();
                GameArea.powerups.clear();
                GameArea.backs.clear();
                GameArea = new GameEngine(1);
                timer.restart();
            }
        });

        frame.addKeyListener(new MoveKeyListener());

        GameArea.setPreferredSize(new Dimension(800, 600));

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(GameArea);

        frame.getContentPane().add(container);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);

        timer = new Timer(100, (ActionEvent ae) -> {
            for (int i = 0; i < GameArea.Enemies.size(); i++) {
                GameArea.Enemies.get(i).movement();
            }
        });
        timer.start();
    }

    /**
     * <p>
     * The control is WASD and SPACE and if the player hit that button, the player direction is changed.
     * </p>
     */
    private class MoveKeyListener extends JFrame implements KeyListener {

        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            //W - 87, A - 65 , S - 83 , D - 68 
            //Move player with WASD
            //W pressed
            if (e.getKeyCode() == 87) {
                GameArea.Players.get(0).moveForward();

            }
            //S pressed
            if (e.getKeyCode() == 83) {
                GameArea.Players.get(0).moveBackward();
            }
            //A pressed
            if (e.getKeyCode() == 65) {
                GameArea.Players.get(0).moveLeft();
            }
            //D pressed
            if (e.getKeyCode() == 68) {
                GameArea.Players.get(0).moveRight();
            }
            if (e.getKeyCode() == 32) {
                GameArea.Players.get(0).shoot();
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
