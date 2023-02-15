package MyGUI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private JButton callButton;
    private JButton raiseButton;
    private JButton foldButton;
    private JButton checkButton;
    private MyPanel myPanel;
    private CommunitInterface ComPanel;



    public GamePanel() {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        callButton = new JButton("Call");
        raiseButton = new JButton("Raise");
        foldButton = new JButton("Fold");
        checkButton = new JButton("Check");
        myPanel = new MyPanel();
        ComPanel = new CommunitInterface();


        buttonPanel.add(callButton);
        buttonPanel.add(raiseButton);
        buttonPanel.add(foldButton);
        buttonPanel.add(checkButton);
        add(buttonPanel, BorderLayout.SOUTH);
        add(myPanel,BorderLayout.CENTER);



    }
}
