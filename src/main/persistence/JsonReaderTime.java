package persistence;

import model.Time;
import org.json.JSONObject;

import java.io.IOException;

public class JsonReaderTime extends JsonReader {


    public JsonReaderTime(String source) {
        super(source);
    }

    public Time readTime() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTime(jsonObject);
    }

    private Time parseTime(JSONObject jsonObject) {
        String month = jsonObject.getString("month");
        int year = jsonObject.getInt("year");
        int day = jsonObject.getInt("day");
        Time clock = new Time(month);
        clock.changeDay(day);
        clock.changeYear(year);
        return clock;
    }
}
