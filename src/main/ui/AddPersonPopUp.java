package ui;

import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPersonPopUp extends JFrame implements ActionListener {
    private DefaultListModel<Person> groupList;
    private AddName name;
    private AddAge age;
    private JButton addPersonButton;
    private Label topLabel;
    private CaravanBarPanel caravanBarPanel;



    // MODIFIES: this
    // EFFECTS: constructs the addPersonPopUp
    public AddPersonPopUp(DefaultListModel<Person> groupList, CaravanBarPanel caravanBarPanel) {
        this.groupList = groupList;
        this.caravanBarPanel = caravanBarPanel;
        topLabel = new Label("Person Adder");
        addPersonButton = new JButton();
        addPersonButton.addActionListener(this);
        addPersonButton.setText("Add Person");
        this.setLayout(new FlowLayout());
        topLabel.setAlignment((int) CENTER_ALIGNMENT);
        this.setSize(new Dimension(300,300));
        this.setResizable(false);
        name = new AddName();
        age = new AddAge();
        add(topLabel);
        add(name);
        add(age);
        add(addPersonButton);
        this.setVisible(true);

    }


    // MODIFIES: caravanPanel.groupListModel
    // EFFECTS: adds a person to caravanPanel.groupListModel when name.getInput() is an appropiate name and
    // age.getInput() is an appropiate age
    @Override
    public void actionPerformed(ActionEvent e) {
        Boolean properName = name.getInput().trim().matches(
                "((([a-zA-Z]')?)[a-zA-Z]+('[a-z])?)+( ((([a-zA-Z]')?)[a-zA-Z]+('[a-z])?))*");
        Boolean properAge = age.getInput().trim().matches("[1-9]+[0-9]*");
        if (properName && properAge && groupList.size() < 3) {
            groupList.addElement(new Person(name.getInput(),Integer.parseInt(age.getInput())));
            topLabel.setText("Person Adder");
        } else if (groupList.size() >= 3) {
            new ErrorPopUp("ERROR! Caravan already at max capacity");
        } else if (!properName) {
            new ErrorPopUp("ERROR! Name must be a proper name, only one space between");
        } else if (!properAge) {
            new ErrorPopUp("ERROR! Age must be an integer");
        }
        caravanBarPanel.setFactor(groupList.size());
        caravanBarPanel.paintRectangle();


    }
}


