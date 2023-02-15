package MyGUI;

import javax.swing.*;
import java.awt.*;


import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MytestGUI implements ActionListener {
    private JFrame frame;
    private GamePanel gamePanel;
    private JButton startButton, settingButton, exitButton;
    private JLabel backgroundLabel;

    public static void main(String[] args) {
        MytestGUI gui = new MytestGUI();
        gui.createGUI();
    }

    public void createGUI() {
        frame = new JFrame("My Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);


        // 設置背景圖片
        ImageIcon backgroundIcon = new ImageIcon("");
        backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundLabel);

        // 設置按鈕
        startButton = new JButton("STARTGAME");
        settingButton = new JButton(" SETTING ");
        exitButton = new JButton("   EXIT  ");

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(50, 0, 0, 0);
        backgroundLabel.add(startButton, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 0, 0, 0);
        backgroundLabel.add(settingButton, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 0, 0, 0);
        backgroundLabel.add(exitButton, c);

        // 監聽按鈕事件
        startButton.addActionListener(this);
        settingButton.addActionListener(this);
        exitButton.addActionListener(this);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // 將介面換成遊戲介面
            gamePanel = new GamePanel();
            gamePanel.setBackground(Color.BLACK);
            frame.setContentPane(gamePanel);

            frame.revalidate();
            frame.repaint();
        } else if (e.getSource() == exitButton) {
            // 結束程式並關閉視窗
            frame.dispose();
            System.exit(0);
        }
    }
}

