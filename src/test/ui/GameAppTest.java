package ui;

import model.Caravan;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameAppTest {

    Person personOne;
    Person personTwo;
    Person personThree;
    Caravan caravan;

    @BeforeEach
    private void initializeTests() {
        personOne = new Person("Jonathan", 16);
        personTwo = new Person("Susanna", 14);
        personThree = new Person("Theodore", 42);
        caravan = new Caravan("Donner Party");
        caravan.addPerson(personOne);
        caravan.addPerson(personTwo);
        caravan.addPerson(personThree);
    }

    @Test
    private void commandHandleTestBaseTravel1() {
        caravan.travel(100);
        caravan.takeFood();

    }
}
