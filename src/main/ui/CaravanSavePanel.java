package ui;

import model.Caravan;
import model.Person;
import persistence.JsonReaderCaravan;
import persistence.JsonWriterCaravan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CaravanSavePanel extends JPanel implements ActionListener {
    JMenuBar menu;
    JMenu file;
    JMenuItem save;
    JMenuItem load;
    CaravanPanel caravanPanel;



    // MODIFIES: this
    // EFFECTS: creates a menu where caravan can be loaded and saved
    public CaravanSavePanel(CaravanPanel caravanPanel) {
        menu = new JMenuBar();
        file = new JMenu("File");
        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        file.add(save);
        file.add(load);
        menu.add(file);
        save.addActionListener(this);
        load.addActionListener(this);
        this.caravanPanel = caravanPanel;
    }

    public JMenuBar getMenu() {
        return menu;
    }

    // MODIFIES: caravanPanel, GuiCaravan.json
    // EFFECTS: preforms the actions of the save and load buttons in Jmenu. Loads a caravan in caravanPanel if
    // MenuItem pressed is load, or save to GuiCaravan.json if save MenuItem is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            Caravan caravan = new Caravan(caravanPanel.getCaravanName());
            DefaultListModel<Person> groupList = caravanPanel.getGroupList();
            for (int i = 0; i < groupList.size(); i++) {
                caravan.addPerson(groupList.get(i));
            }
            JsonWriterCaravan writer = new JsonWriterCaravan("./data/GuiCaravan.json");
            try {
                writer.open();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            writer.write(caravan);
            writer.close();

        } else if (e.getSource() == load) {
            JsonReaderCaravan jsonReader = new JsonReaderCaravan("./data/GuiCaravan.json");
            try {
                Caravan caravan = jsonReader.readCaravan();
                caravanPanel.loadCaravan(caravan);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }
}
