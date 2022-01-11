package model;

import org.json.JSONObject;

// The people that comprise the caravan
// !! each person has to have a unique name
public class Person {
    private final String name;
    private final int age;
    private int health;

    // Constructs the person

    public Person(String n, int a) {
        name = n;
        age = a;
        health = 3;
    }
    // EFFECTS: returns person name

    public String getName() {

        return name;
    }


    // EFFECTS: returns person age
    public int getAge() {
        return age;
    }

    // EFFECTS: returns person health
    public int getHealth() {

        return health;
    }


    // REQUIRES: h cannot be less than 0 *** USE ONLY BY JSON
    // MODIFIES: this
    // EFFECTS: changes health to h
    public void changeHeath(int h) {
        health = h;
    }


    // REQUIRES: health cannot already be 0
    // MODIFIES: this
    // EFFECTS: lowers health by one
    public void takeDamage() {
        System.out.println(name + " got ill! Health reduced by one");
        health = health - 1;
    }

    // MODIFIES: jsonFile
    // EFFECTS: writes person to jsonFile
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("health", health);
        return json;
    }

    // EFFECTS: returns the string value of person overridden
    @Override
    public String toString() {
        return (name + " : Age " + age + " Current Health " + health);
    }
}
