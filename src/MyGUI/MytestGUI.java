package MyGUI;

import javax.swing.*;
import java.awt.*;


import javax.swing.*;
import java.awt.*;

public class MytestGUI {
    private JFrame frame;
    private JLabel background;

    public static void main(String[] args) {
        MyGUI gui = new MyGUI();
        gui.createGUI();
    }

    public void createGUI() {
        frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // 創建JLabel並加入背景圖片
        ImageIcon imageIcon = new ImageIcon("C:\\codepractive\\MeddleProject\\src\\image.jpg");
        Image image = imageIcon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        background = new JLabel(new ImageIcon(image));
        background.setLayout(new GridBagLayout());

        // 創建JLabel放置在background上，顯示遊戲文字
        JLabel label = new JLabel("Welcome to my game!");
        label.setPreferredSize(new Dimension(50, 50));
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(0, 0, 10, 0);
        labelConstraints.anchor = GridBagConstraints.CENTER;
        background.add(label, labelConstraints);

        // 創建按鈕並放置在background上
        JButton button = new JButton("Start Game");
        button.setPreferredSize(new Dimension(10, 10));
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(10, 0, 0, 0);
        buttonConstraints.anchor = GridBagConstraints.PAGE_END;
        background.add(button, buttonConstraints);

        // 將background設定為透明，並放置在JFrame上
        background.setOpaque(false);
        frame.setContentPane(background);

        frame.setVisible(true);
    }
}
