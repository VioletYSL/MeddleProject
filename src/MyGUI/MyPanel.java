package MyGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel {

    private ImageIcon image;
    private JLabel label;
    private CommunitInterface ComPanel;


    public MyPanel() {
        setLayout(new BorderLayout());
        // 建立圖片
        image = new ImageIcon("src/image/NEWTable.png");
        label = new JLabel(image);
        label.setHorizontalAlignment(JLabel.CENTER);
        ComPanel = new CommunitInterface();

//        add(ComPanel,BorderLayout.CENTER);


    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // 繪製圖像時自動調整圖像的大小以適應容器的大小
            g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    }

}


