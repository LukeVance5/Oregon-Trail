package persistence;

import model.Time;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTimeTest {
    @Test
    void TestNoRealFileException() {
        JsonReaderTime JsonReader = new JsonReaderTime("./data/noRealFile.json");
        try {
            Time clock = JsonReader.readTime();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void TestReadNullTime() {
        JsonReaderTime JsonReader = new JsonReaderTime("./data/testReaderNullTime.json");
        try {
            Time clock = JsonReader.readTime();
            assertEquals("null",clock.getMonth());
        }   catch (IOException e) {
            fail("Exception thrown!");
        }
    }

    @Test
    void TestReadGeneralTime() {
        JsonReaderTime JsonReader = new JsonReaderTime("./data/testReaderGeneralTime.json");
        try {
            Time clock = JsonReader.readTime();
            assertEquals("April 23 1848", clock.getTime());
        } catch (IOException e) {
            fail("Exception thrown!");
        }
    }
}
