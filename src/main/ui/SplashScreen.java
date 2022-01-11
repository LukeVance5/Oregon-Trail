package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class SplashScreen extends JFrame implements ActionListener {
    private JPanel splashPanel;
    private JLabel pictureLabel;
    private Timer timer;

    // EFFECTS: creates a splashPanel
    public SplashScreen() {
        BufferedImage splashPicture = null;
        try {
            splashPicture = ImageIO.read(new File("./data/SplashImage.jpg"));
            pictureLabel = new JLabel(new ImageIcon(splashPicture));
            createPicturePanel();
            this.setSize(new Dimension(splashPicture.getWidth(), splashPicture.getHeight()));
            this.add(splashPanel);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // EFFECTS: adds pictureLabel to splashPanel
    private void createPicturePanel() {
        splashPanel = new JPanel();
        splashPanel.setLayout(new BorderLayout());
        splashPanel.add(pictureLabel, BorderLayout.CENTER);
    }

    // EFFECTS: disposes SplashScreen after timer has finished in GameApp
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
