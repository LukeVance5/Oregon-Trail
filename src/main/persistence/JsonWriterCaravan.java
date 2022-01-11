package persistence;

import model.Caravan;
import org.json.JSONObject;



public class JsonWriterCaravan extends JsonWriter {

    // EFFECTS: constructs the class using JsonWriter constructor
    public JsonWriterCaravan(String destination) {
        super(destination);
    }

    // EFFECTS: writes caravan to file
    public void write(Caravan c) {
        JSONObject json = c.toJson();
        saveToFile(json.toString(TAB));
    }

}
