package persistence;


import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {



    @Test
    void testCaravanWriterInvalidFile() {
        try {
            Caravan caravan = new Caravan("Donner Party");
            JsonWriterCaravan writer = new JsonWriterCaravan("./data/\0invalidfile.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // PASS
        }
    }
    @Test
    void testTimeWriterInvalidFile() {
        try {
            Time clock = new Time("March");
            JsonWriterTime writer = new JsonWriterTime("./data/\0invalidfile.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // PASS
        }
    }

    @Test
    void testCaravanNullCaravan() {
        try {
            Caravan caravan = new Caravan("Null");
            JsonWriterCaravan writer = new JsonWriterCaravan("./data/testReaderNullCaravan");
            writer.open();
            writer.write(caravan);
            writer.close();

            JsonReaderCaravan reader = new JsonReaderCaravan("./data/testReaderNullCaravan");
            caravan = reader.readCaravan();
            assertEquals("Null", caravan.getCaravanName());
            assertEquals(0, caravan.getGroup().size());
        }   catch (IOException e) {
            fail("Exception thrown!");
        }
    }

    @Test
    void testTimeNullTime() {
        try {
            Time clock = new Time("Null");
            JsonWriterTime writer = new JsonWriterTime("./data/testReaderNullTime");
            writer.open();
            writer.write(clock);
            writer.close();

            JsonReaderTime reader = new JsonReaderTime("./data/testReaderNullTime");
            clock = reader.readTime();
            assertEquals("Null 1 1848", clock.getTime());

        }   catch (IOException e) {
            fail("Exception thrown!");
        }
    }

    @Test
    void testCaravanGeneralCaravan() {
        try {
            Caravan caravan = new Caravan("O'Donnell's");
            caravan.addPerson(new Person("Jack", 12));
            caravan.addPerson(new Person("Mike", 14));
            caravan.takeDamage();
            caravan.takeFood();
            JsonWriterCaravan writer = new JsonWriterCaravan("./data/testReaderGeneralCaravan");
            writer.open();
            writer.write(caravan);
            writer.close();

            JsonReaderCaravan reader = new JsonReaderCaravan("./data/testReaderGeneralCaravan");
            caravan = reader.readCaravan();
            assertEquals(2,caravan.getGroup().size());
            assertEquals(490,caravan.getFood());

        }   catch (IOException e) {
            fail("Exception thrown!");
        }
    }

    @Test
    void testTimeGeneralTime() {
        try {
            Time clock = new Time("February");
            clock.timeStep(29);
            JsonWriterTime writer = new JsonWriterTime("./data/testReaderGeneralTime");
            writer.open();
            writer.write(clock);
            writer.close();
            JsonReaderTime reader = new JsonReaderTime("./data/testReaderGeneralTime");
            clock = reader.readTime();
            assertEquals("March 1 1848", clock.getTime());

        } catch (IOException e) {
            fail("Exception thrown!");
        }

    }
}
