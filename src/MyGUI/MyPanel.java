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

    public MyPanel() {
        // 建立圖片
        image = new ImageIcon("src/image/NEWTable.png");
        label = new JLabel(image);
        label.setHorizontalAlignment(JLabel.CENTER);



        // 將圖片加入JPanel
        add(label);
    }

}


