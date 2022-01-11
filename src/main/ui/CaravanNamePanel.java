package ui;

import model.Caravan;

import javax.swing.*;
import java.awt.*;

public class CaravanNamePanel extends JPanel {
    private JTextField caravanName;
    private static final JLabel LABEL = new JLabel("Caravan Name");
    private CaravanPanel caravanPanel;

    // EFFECTS: Constructs the caravanNamePanel
    public CaravanNamePanel(CaravanPanel caravanPanel) {
        this.caravanPanel = caravanPanel;
        this.setLayout(new FlowLayout());
        caravanName = new JTextField("Type in Name",20);
        LABEL.setLabelFor(caravanName);
        this.add(LABEL);
        this.add(caravanName);
        this.setVisible(true);
    }

    // EFFECTS: returns text in caravanName
    public String getCaravanName() {
        return caravanName.getText();
    }


    // MODIFIES: this
    // EFFECTS: sets the JtextField to a given caravanName when loading in a previous caravan
    public void setCaravanName(String caravanName) {
        this.caravanName.setText(caravanName);
    }

}
