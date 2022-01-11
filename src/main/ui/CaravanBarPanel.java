package ui;

import javax.swing.*;
import java.awt.*;

public class CaravanBarPanel extends JPanel {
    private CaravanPanel caravanPanel;
    private int factor;
    private GameFrame gameframe;
    private JLabel label;
    private CaravanBar caravanBar;

    // EFFECTS: creates the caravanBarPanel
    public CaravanBarPanel(CaravanPanel caravanPanel, GameFrame gameframe) {
        factor = 0;
        this.caravanPanel = caravanPanel;
        this.gameframe = gameframe;
        label = new JLabel("Amount of people in caravan");
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.SOUTH);
        this.setVisible(true);
        caravanBar = new CaravanBar(this);
        caravanPanel.addCaravanBar(this);
        this.add(caravanBar, BorderLayout.CENTER);

    }

    // EFFECTS: sets factor to 4 - groupSize (size of caravanPanel.groupListModel)
    public void setFactor(int groupSize) {
        factor = 4 - groupSize;

    }


    // EFFECTS: paints a rectangle
    public void paintRectangle() {
        caravanBar.paintRectangle(factor);
    }







}
