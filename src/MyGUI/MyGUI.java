package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyGUI implements ActionListener {
    private JButton start;
    private JButton setting;
    private JButton exit;

    private JFrame gameframe;
    private GamePanel gamePanel;

    public static void main(String[] args) {
        MyGUI gui = new MyGUI();
        gui.createGUI();
    }

    public void createGUI() {
        gameframe = new JFrame("My Game");
        gameframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameframe.setSize(1280, 800);

        // Create the start button
        start = new JButton("START GAME");
        start.addActionListener(this);
        start.setPreferredSize(new Dimension(100,60));

        setting = new JButton("SETTING");
//        setting.addActionListener();
        exit = new JButton("EXIT");
//        exit.addActionListener();

        // Add the button to the frame
        gameframe.getContentPane().add(start, BorderLayout.SOUTH);
        gameframe.getContentPane().add(setting,BorderLayout.SOUTH);
        gameframe.getContentPane().add(exit,BorderLayout.SOUTH);

        gameframe.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            // Remove the start button from the frame
            gameframe.getContentPane().remove(start);

            // Create the game panel
            gamePanel = new GamePanel();


            // Add the game panel to the frame
            gameframe.getContentPane().add(gamePanel, BorderLayout.CENTER);

            // Redraw the frame
            gameframe.revalidate();
            gameframe.repaint();
        }
    }
}
