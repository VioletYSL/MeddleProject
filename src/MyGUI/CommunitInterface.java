package MyGUI;

import javax.swing.*;
import java.awt.*;

public class CommunitInterface extends JPanel{
    private ImageIcon image;

    public CommunitInterface(){

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel CardPanel1 = new JPanel();
        CardPanel1.setPreferredSize(new Dimension(150, 105));
        JPanel CardPanel2 = new JPanel();
        CardPanel2.setPreferredSize(new Dimension(150,105));
        JPanel CardPanel3 = new JPanel();
        CardPanel3.setPreferredSize(new Dimension(150,105));
        JPanel CardPanel4 = new JPanel();
        CardPanel4.setPreferredSize(new Dimension(150,105));
        JPanel CardPanel5 = new JPanel();
        CardPanel5.setPreferredSize(new Dimension(150,105));


        mainPanel.add(CardPanel1);

    }

    public void setComCardImage(){



    }
    public void cleanComCardImage(){



    }
}
