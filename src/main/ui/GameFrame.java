package ui;

import model.Caravan;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private CaravanPanel caravanPanel;
    private CaravanSavePanel caravanSavePanel;
    private CaravanBarPanel caravanBarPanel;
    private Caravan caravan;
    private JPanel buttonPanel;
    private static final Integer WIDTH = 800;
    private static final Integer HEIGHT = 600;
    private GameApp gameApp;
    JButton startButton;
    JButton loadButton;
    JButton saveButton;
    JButton quitButton;
    JButton travelButton;
    JButton playAgainButton;


    // MODIFIES: this
    // EFFECTS: creates gameFrame
    public GameFrame(GameApp gameApp) {
        this.caravanPanel = new CaravanPanel();
        this.caravanSavePanel = new CaravanSavePanel(caravanPanel);
        this.caravanBarPanel = new CaravanBarPanel(caravanPanel, this);
        caravanPanel.addCaravanBar(caravanBarPanel);
        this.gameApp = gameApp;
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setTitle("The Oregon Trail");
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setJMenuBar(caravanSavePanel.getMenu());
        caravanBarPanel.setPreferredSize(new Dimension(this.getWidth() / 4, this.getHeight()));
        caravanPanel.setPreferredSize((new Dimension(this.getWidth() / 2
                + this.getWidth() / 8, this.getHeight())));
        this.add(caravanBarPanel);
        this.add(caravanPanel);
        createButtonPanel();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // EFFECTS: returns the caravanPanel
    public CaravanPanel getCaravanPanel() {
        return caravanPanel;
    }

    // EFFECTS: returns the buttons
    public JButton getStartButton() {
        return startButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public JButton getTravelButton() {
        return travelButton;
    }

    public JButton getPlayAgainButton() {
        return playAgainButton;
    }


    // MODIFIES: this.frame
    // EFFECTS: creates and adds buttonPanel to jframe
    private void createButtonPanel() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(1);
        gridLayout.setRows(12);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(gridLayout);
        addButtons();
        buttonPanel.setPreferredSize(new Dimension(this.getWidth() / 8, this.getHeight()));
        this.add(buttonPanel);

    }

    // MODIFIES: this.buttonPanel
    // EFFECTS: helper method for createButtonPanel(), adds buttons to buttonPanel
    private void addButtons() {
        startButton = new JButton("Start Game");
        loadButton = new JButton("Load Game");
        saveButton = new JButton("Save Game");
        quitButton = new JButton(" Quit Game");
        travelButton = new JButton("Travel");
        playAgainButton = new JButton("Play Again");
        startButton.addActionListener(gameApp);
        loadButton.addActionListener(gameApp);
        saveButton.addActionListener(gameApp);
        quitButton.addActionListener(gameApp);
        travelButton.addActionListener(gameApp);
        playAgainButton.addActionListener(gameApp);
        buttonPanel.add(startButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(playAgainButton);
        buttonPanel.add(travelButton);
        buttonPanel.add(quitButton);
    }

}
