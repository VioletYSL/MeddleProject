package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUI implements ActionListener {
    private JButton button;
    private JLabel label;
    private Timer timer;

    public static void main(String[] args) {
        MyGUI gui = new MyGUI();
        gui.createGUI();
    }

    public void createGUI() {
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 800);
        frame.setLayout(new GridBagLayout());

        label = new JLabel("Welcome to my game!");
        label.setPreferredSize(new Dimension(200, 200));
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(0, 0, 10, 0);
        labelConstraints.anchor = GridBagConstraints.CENTER;
        frame.getContentPane().add(label, labelConstraints);

        button = new JButton("Start Game");
        button.setPreferredSize(new Dimension(100, 50));
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(0, 0, -250, 0);
        buttonConstraints.anchor = GridBagConstraints.PAGE_END;
        frame.getContentPane().add(button, buttonConstraints);
        button.addActionListener(this);

        timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Welcome to my game!");
            }
        });



        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            label.setText("Game Started!");
            timer.start();
        }
    }
}
