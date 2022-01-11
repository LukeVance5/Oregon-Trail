package ui;

import exceptions.NullCaravanOrTimeException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class LoadGameTest {

    @Test
    public void loadCaravanNull() {
        Caravan caravan;
        LoadGame loadGame = new LoadGame("./data/testReaderNullCaravan.json",
                "./data/testReaderNullTime.json");
        try {
            caravan = loadGame.loadCaravan();
            fail("Exception not thrown!");
        } catch (NullCaravanOrTimeException e) {
            // PASS
        }

    }

    @Test
    public void loadTimeNull() {
        Time clock;
        LoadGame loadGame = new LoadGame("./data/testReaderNullCaravan.json",
                "./data/testReaderNullTime.json");
        try {
            clock = loadGame.loadTime();
            fail("Exception not thrown!");
        } catch (NullCaravanOrTimeException e) {
            // PASS
        }

    }

    @Test
    public void loadCaravanPass() {
        Caravan caravan;
        Time clock;
        LoadGame loadGame = new LoadGame("./data/testReaderGeneralCaravan.json",
                "./data/testReaderGeneralTime.json");
        try {
            caravan = loadGame.loadCaravan();
            clock = loadGame.loadTime();
            assertEquals("Donner Party", caravan.getCaravanName());
            assertEquals("April 23 1848", clock.getTime());
        } catch (NullCaravanOrTimeException e) {
            fail("Exception thrown!");
        }

    }



}
