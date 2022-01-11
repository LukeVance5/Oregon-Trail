package persistence;


import model.Time;
import org.json.JSONObject;

public class JsonWriterTime extends JsonWriter {

    // EFFECTS: constructs the class using JsonWriter constructor
    public JsonWriterTime(String destination) {
        super(destination);
    }


    // EFFECTS: writes Time to file
    public void write(Time t) {
        JSONObject json = t.toJson();
        saveToFile(json.toString(TAB));
    }
}
