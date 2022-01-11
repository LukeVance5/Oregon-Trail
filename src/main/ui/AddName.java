package ui;

import javax.swing.*;
import java.awt.*;

public class AddName extends JPanel {
    private JTextField name;
    private static final JLabel NAME_LABEL = new JLabel("Name");


    // EFFECTS: Constructs the name textfield for AddPersonPopUp
    public AddName() {
        name = new JTextField("Type Name", 20);
        NAME_LABEL.setLabelFor(name);
        setLayout(new FlowLayout());
        add(NAME_LABEL);
        add(name);
    }

    // EFFECTS: returns inputted name
    public String getInput() {
        return name.getText();
    }
}
