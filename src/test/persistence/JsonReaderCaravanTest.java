package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderCaravanTest {
    @Test
    void TestNoRealFileException() {
        JsonReaderCaravan JsonReader = new JsonReaderCaravan("./data/noRealFile.json");
        try {
            Caravan caravan = JsonReader.readCaravan();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReadNullCaravan() {
        JsonReaderCaravan JsonReader = new JsonReaderCaravan("./data/testReaderNullCaravan.json");
        try {
            Caravan caravan = JsonReader.readCaravan();
            assertTrue(caravan.allDead());
            assertEquals(caravan.getFood(), 0);
            assertEquals(caravan.getDistanceFromOregon(),0);
            assertEquals(caravan.getCaravanName(), "null");
            assertEquals(caravan.getCondition(), 0);
        } catch (IOException e) {
            fail("Exception thrown!");
        }
    }

    @Test
    void testReadGeneralCaravan() {
        JsonReaderCaravan JsonReader = new JsonReaderCaravan("./data/testReaderGeneralCaravan.json");
        try {
            Caravan caravan = JsonReader.readCaravan();
            assertEquals(3,caravan.getGroup().size());
            assertEquals(caravan.getFood(), 412);
            assertEquals(caravan.getDistanceFromOregon(),1000);
            assertEquals(caravan.getCaravanName(), "Donner Party");
            assertEquals(caravan.getCondition(), 2);
            assertEquals(1,caravan.getGroup().get(2).getHealth());
        } catch (IOException e) {
            fail("Exception thrown!");
        }
    }
}
