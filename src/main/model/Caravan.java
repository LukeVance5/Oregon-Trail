package model;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
// The Users Caravan used to travel the trail

public class Caravan implements Writable {
    private static String caravanName;
    private List<Person> group;
    private int food; // food to be consumed
    private int condition; // current condition of the caravan, modifies travel speed and at 0 game end
    private int distanceFromOregon; // Distance from oregon in miles
    public static final int GROUP_CAPACITY = 3;

    // Constructor
    // EFFECTS: Constructs a caravan with caravan name
    public Caravan(String c) {
        group = new ArrayList<>();
        food = 500; // !!! change test in CaravanTest.java if this value is changed
        condition = 3;
        caravanName = c;
        distanceFromOregon = 2200;
    }


    // EFFECTS returns caravanName
    public String getCaravanName() {
        return caravanName;
    }

    // EFFECTS: returns group
    public List<Person> getGroup() {
        return group;
    }

    // EFFECTS: returns condition
    public int getCondition() {
        return condition;
    }

    // EFFECTS: returns food
    public int getFood() {
        return food;
    }

    // EFFECTS: returns distanceFromOregon
    public int getDistanceFromOregon() {
        return distanceFromOregon;
    }


    // REQUIRES: c must be > 0 *** USE ONLY BY JSON
    // MODIFIES: this
    // EFFECTS: changes condition to c
    public void changeCondition(int c) {
        condition = c;
    }

    // REQUIRES: f must be > 0 *** USE ONLY BY JSON
    // MODIFIES: this
    // EFFECTS: changes food to f
    public void changeFood(int f) {
        food = f;
    }

    // REQUIRES: d must be > 0 *** USE ONLY BY JSON
    // MODIFIES: this
    // EFFECTS: changes distanceFromOregon to d
    public void changeDistanceFromOregon(int d) {
        distanceFromOregon = d;
    }


    // MODIFIES: this
    // EFFECTS: adds a person to the caravan
    public void addPerson(Person p) {
        group.add(p);
    }

    public void takeDamage() {
        System.out.println("Damage taken! Condition reduced by one");
        condition = condition - 1;
    }

    // MODIFIES: this
    // EFFECTS: subtracts 10 from food !!! change tests if this value changes
    // !!! change test in CaravanTest.java if subtraction amount changed
    public void takeFood() {
        food = food - 10;
    }

    // MODIFIES: this
    // EFFECTS: travels dx miles on the trail
    public void travel(int dx) {
        distanceFromOregon = distanceFromOregon - dx;
    }

    // REQUIRES: a person's health must be >=0
    // MODIFIES: this
    // EFFECTS: removes a person if their health is = 0
    public void anyDead() {
        for (Person person : group) {
            if (person.getHealth() == 0) {
                System.out.println(person.getName() + " has died!");
                group = removePerson(person);
            }
        }
    }

    // EFFECTS: returns a list of persons with persons removed who health = 0

    private List<Person> removePerson(Person p) {
        List<Person> groupWithoutDeadPerson = new ArrayList<>();
        for (Person person : group) {
            if (p.getHealth() != person.getHealth()) {
                groupWithoutDeadPerson.add(person);
            }

        }
        return groupWithoutDeadPerson;




    }


    // EFFECTS: returns true if group = 0, else false
    public boolean allDead() {
        if (group.size() == 0) {
            System.out.println("All members of the Caravan have died \nGame Over!");
            return true;
        }
        return false;
    }

    // EFFECTS: returns true if condition <= 0 , else false
    public boolean brokenDown() {
        if (condition <= 0) {
            System.out.println("Your Caravan broke beyond repair \nGame Over!");
            return true;
        }
        return false;


    }

    // EFFECTS: returns true if food <= 0, else false
    public boolean noFood() {
        if (food <= 0) {
            System.out.println("Your Caravan Starved to death \nGame Over!");
            return true;
        }
        return false;
    }

    // EFFECTS: returns true if distanceFromOregon <=0
    public boolean atOregon() {
        if (distanceFromOregon <= 0) {
            System.out.println("Your Caravan made it to Oregon \nYou won!");
            return true;
        }
        return false;
    }


    @Override
    // MODIFIES: jsonFile
    // EFFECTS: writes the current caravan to a jsonFile
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("caravanName", getCaravanName());
        json.put("food", getFood());
        json.put("condition", getCondition());
        json.put("distanceFromOregon", getDistanceFromOregon());
        json.put("people", groupToJson());
        return json;
    }

    // EFFECTS: helper method for toJson() that creates jsonArray of the group
    private JSONArray groupToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Person p : group) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;

    }
}
