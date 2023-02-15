package MyGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private static final int NUM_OF_BLOCKS = 5;
    private static final int BLOCK_SPACING = 2;
    private static final int PREFERRED_WIDTH = 500;
    private static final int PREFERRED_HEIGHT = 100;
    private JLabel[] imageLabels;

    public ImagePanel() {
        setLayout(new GridLayout(1, NUM_OF_BLOCKS, BLOCK_SPACING, 0));
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        imageLabels = new JLabel[NUM_OF_BLOCKS];
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {
            imageLabels[i] = new JLabel();
            add(imageLabels[i]);
        }
    }

    public void loadImage(int index, String imagePath) {
        try {
            Image image = ImageIO.read(new File(imagePath));
            Image scaledImage = image.getScaledInstance(imageLabels[index].getWidth(), imageLabels[index].getHeight(), Image.SCALE_SMOOTH);
            imageLabels[index].setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearImages() {
        for (JLabel label : imageLabels) {
            label.setIcon(null);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Panel");
        ImagePanel panel = new ImagePanel();
        frame.add(panel, BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Image");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your image file path
                panel.loadImage(0, "/file01/pokertable.jpg");
            }
        });

        JButton clearButton = new JButton("Clear Images");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.clearImages();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(clearButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
