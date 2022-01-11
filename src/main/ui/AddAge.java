package ui;

import javax.swing.*;
import java.awt.*;

public class AddAge extends JPanel  {
    private JTextField age;
    private static final JLabel AGE_LABEL = new JLabel("  Age ");


    // EFFECTS: Constructs the age textfield for AddPersonPopUp
    public AddAge() {
        age = new JTextField("Type Age", 20);
        AGE_LABEL.setLabelFor(age);
        setLayout(new FlowLayout());
        add(AGE_LABEL);
        add(age);
    }

    // effects: returns inputted age
    public String getInput() {
        return age.getText();
    }

}
