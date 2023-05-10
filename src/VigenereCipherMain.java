import add.Initializer;

import javax.swing.*;

import java.awt.*;

public class VigenereCipherMain {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame();
                //set icon img
                ImageIcon img = new ImageIcon(getClass().getResource("/resources/Thinking.jpg"));
                f.setIconImage(img.getImage());
                //set icon img
                f.setTitle("VigenereCipher");
                f.setLocation(600, 150);
                f.setSize(new Dimension(850, 850));
                f.setLayout(new GridBagLayout());
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                f.setVisible(true);
                //create bg
                JLabel bgLabel = Initializer.createBG(f, img);
                f.setContentPane(bgLabel);
                //create bg
                VigenereScreens.mainScreen(f);
            }
        });
    }
}
