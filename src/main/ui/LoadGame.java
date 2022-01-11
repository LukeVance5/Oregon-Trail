package ui;

import exceptions.NullCaravanOrTimeException;
import model.Caravan;
import model.Time;
import persistence.*;

import java.io.IOException;

public class LoadGame {
    private String caravanSource;
    private String timeSource;


    // MODIFIES: this
    // EFFECTS: creates a LoadGame
    public LoadGame(String caravanSource, String timeSource) {
        this.caravanSource = caravanSource;
        this.timeSource = timeSource;
    }

    // EFFECTS: returns a loaded caravan, throws exception if caravan is inappropriate
    public Caravan loadCaravan() throws NullCaravanOrTimeException {
        JsonReaderCaravan jsonReaderCaravan = new JsonReaderCaravan(caravanSource);
        try {
            Caravan caravan = jsonReaderCaravan.readCaravan();
            if (caravan.allDead()) {
                throw new NullCaravanOrTimeException();

            }
            return caravan;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // EFFECTS: returns a loaded time, throws exception if caravan is time
    public Time loadTime() throws NullCaravanOrTimeException {
        JsonReaderTime jsonReaderTime = new JsonReaderTime(timeSource);
        try {
            Time time = jsonReaderTime.readTime();
            if (time.getMonth().equals("null")) {
                throw new NullCaravanOrTimeException();
            }
            return time;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
