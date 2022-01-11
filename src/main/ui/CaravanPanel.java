package ui;

import javax.swing.*;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CaravanPanel extends JPanel implements ActionListener {
    private JButton addPersonButton;
    private JButton removePersonButton;
    private DefaultListModel<Person> groupListModel;
    private JList groupDisplayList;
    private JScrollPane groupListDisplay;
    private JToolBar toolBar;
    private CaravanNamePanel namePanel;
    private CaravanBarPanel caravanBarPanel;




    // MODIFIES: this
    // EFFECTS: Constructs a JPanel for creating a Caravan
    public CaravanPanel() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BorderLayout());
        groupListModel = new DefaultListModel<Person>();
        groupDisplayList = new JList(groupListModel);
        groupDisplayList.setSize(new Dimension(100,200));
        groupDisplayList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        groupDisplayList.setLayoutOrientation(JList.VERTICAL);
        groupDisplayList.setVisibleRowCount(3);
        groupDisplayList.setFixedCellHeight(100);
        groupDisplayList.setFixedCellWidth(100);
        groupListDisplay = new JScrollPane(groupDisplayList);
        namePanel = new CaravanNamePanel(this);
        add(namePanel, BorderLayout.NORTH);
        add(groupListDisplay, BorderLayout.CENTER);
        toolBar = new JToolBar();
        add(toolBar, BorderLayout.SOUTH);
        initializeButtons();


    }

    // MODIFIES: this
    // EFFECTS: Constructs a toolbar with buttons to add and remove persons
    private void initializeButtons()  {
        addPersonButton = new JButton();
        addPersonButton.addActionListener(this);
        toolBar.add(addPersonButton);
        addPersonButton.setText("Add Person");
        removePersonButton = new JButton();
        removePersonButton.addActionListener(this);
        removePersonButton.setText("Remove Person");
        toolBar.add(removePersonButton);
    }

    // EFFECTS: returns groupListModel
    public DefaultListModel<Person> getGroupList() {
        return groupListModel;
    }

    // EFFECTS: returns namePanel caravanName
    public String getCaravanName() {
        return namePanel.getCaravanName();
    }


    // MODIFIES: this
    // EFFECTS: Loads a previously saved caravan into groupListModel and namePanel.caravanName
    public void loadCaravan(Caravan caravan) {
        groupListModel.clear();
        for (Person person : caravan.getGroup()) {
            groupListModel.addElement(person);
        }
        namePanel.setCaravanName(caravan.getCaravanName());
        caravanBarPanel.setFactor(groupListModel.size());
        caravanBarPanel.paintRectangle();
    }

    // MODIFIES: this
    // EFFECTS: sets the caravanBar
    public void addCaravanBar(CaravanBarPanel caravanBarPanel) {
        this.caravanBarPanel = caravanBarPanel;
    }



    // MODIFIES: this
    // EFFECTS: preforms the actions when removePersonButton and addPersonButton are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addPersonButton)) {
            new AddPersonPopUp(groupListModel, caravanBarPanel);
        } else if (e.getSource().equals(removePersonButton)) {
            Integer index = groupDisplayList.getSelectedIndex();
            if (index >= 0) {
                groupListModel.remove(index);
            }

        }
        caravanBarPanel.setFactor(groupListModel.size());
        caravanBarPanel.paintRectangle();
    }


}
