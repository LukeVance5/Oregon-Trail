package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorPopUp extends JFrame implements ActionListener {
    private JLabel errorLabel;
    private JButton exitButton;
    private static final int TEXT_BARRIER = 25;

    // EFFECTS: Constructs an error pop up message with error message
    public ErrorPopUp(String errorMessage) {
        errorLabel = new JLabel(errorMessage);
        this.add(errorLabel);
        exitButton = new JButton();
        exitButton.setText("Exit");
        exitButton.addActionListener(this);
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(new Dimension(400,150));
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(errorLabel);
        add(exitButton);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: disposes of jframe when exit button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
