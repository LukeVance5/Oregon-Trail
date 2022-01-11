package persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import model.Caravan;
import model.Person;

import java.io.IOException;


public class JsonReaderCaravan extends JsonReader {

    public JsonReaderCaravan(String source) {
        super(source);
    }

    //EFFECTS reads caravan from file and returns it;
    // throws IOException if error is thrown
    public Caravan readCaravan() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCaravan(jsonObject);
    }

    private Caravan parseCaravan(JSONObject jsonObject) {
        String caravanName = jsonObject.getString("caravanName");
        Caravan caravan = new Caravan(caravanName);
        caravan.changeFood(jsonObject.getInt("food"));
        caravan.changeCondition(jsonObject.getInt("condition"));
        caravan.changeDistanceFromOregon(jsonObject.getInt("distanceFromOregon"));
        readCaravanPersonList(caravan, jsonObject);
        return caravan;
    }

    private void readCaravanPersonList(Caravan c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("people");
        for (Object json : jsonArray) {
            JSONObject nextPerson = (JSONObject) json;
            addPerson(c, nextPerson);

        }
    }

    private void addPerson(Caravan c, JSONObject jsonObject) {
        String personName = jsonObject.getString("name");
        int personAge = jsonObject.getInt("age");
        int personHealth = jsonObject.getInt("health");
        Person person = new Person(personName, personAge);
        person.changeHeath(personHealth);
        c.addPerson(person);
    }



}
