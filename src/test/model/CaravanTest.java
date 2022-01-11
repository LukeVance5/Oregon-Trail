package model;


import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class CaravanTest {
    Caravan caravan;
    Person personOne;
    Person personTwo;
    Person personThree;

    @BeforeEach
    public void createCaravan() {
        personOne = new Person("Jonathan", 16);
        personTwo = new Person("Susanna", 14);
        personThree = new Person("Theodore", 42);
        caravan = new Caravan("Donner Party");
        caravan.addPerson(personOne);
        caravan.addPerson(personTwo);
        caravan.addPerson(personThree);
    }

    @Test
    public void getNameTest() {
        assertEquals("Donner Party", caravan.getCaravanName());
    }

    @Test
    public void getGroupTest() {
        ArrayList<Person> listOfPersons = new ArrayList<>();
        listOfPersons.add(personOne);
        listOfPersons.add(personTwo);
        listOfPersons.add(personThree);
        assertEquals(listOfPersons , caravan.getGroup() );
    }

    @Test

    public void getConditionTest() {
        assertEquals(3, caravan.getCondition());
    }

    @Test

    public void getFoodTest() {
        assertEquals(500, caravan.getFood());
    }

    @Test

    public void getDistanceFromOregonTest() {
        assertEquals(2200, caravan.getDistanceFromOregon());
    }


    @Test
    public void changeConditionTest() {
        caravan.changeCondition(2);
        assertEquals(2, caravan.getCondition());
    }

    @Test
    public void changeFoodTest() {
        caravan.changeFood(410);
        assertEquals(410, caravan.getFood());
    }

    @Test
    public void changeDistanceFromOregon() {
        caravan.changeDistanceFromOregon(312);
        assertEquals(312, caravan.getDistanceFromOregon());
    }

    @Test
    public void takeDamageTest() {
        caravan.takeDamage();
        assertEquals(2, caravan.getCondition());
    }

    @Test
    public void addPersonTest() {

    }

    @Test
    public void takeFoodTest() {
        caravan.takeFood();
        assertEquals(490, caravan.getFood());
    }


    @Test
    public void travelTestNoDistance() {
        caravan.travel(0);
        assertEquals(2200,caravan.getDistanceFromOregon());
    }

    @Test
    public void travelTestDistance() {
        caravan.travel(100);
        assertEquals(2100,caravan.getDistanceFromOregon());
    }


    @Test
    public void anyDeadNone() {
        caravan.anyDead();
        assertEquals(3, caravan.getGroup().size());
    }

    @Test
    public void anyDeadOne() {
        personOne.takeDamage();
        personOne.takeDamage();
        personOne.takeDamage();
        caravan.anyDead();
        assertEquals(2, caravan.getGroup().size());
    }
    @Test
    public void anyDeadAll() {
        for (Person p : caravan.getGroup()) {
            for (int i = 0; i < 3; i++) {
                p.takeDamage();
            }
        }
        caravan.anyDead();
        assertEquals(0, caravan.getGroup().size());
    }


    @Test
    public void allDeadNo() {
        assertFalse(caravan.allDead());
    }
    @Test
    public void allDeadYes() {
        for (Person p : caravan.getGroup()) {
            for (int i = 0; i < 3; i++) {
                p.takeDamage();
            }
        }
        caravan.anyDead();
       assertTrue(caravan.allDead());
    }

    @Test
    public void brokenDownTestNo() {
        caravan.takeDamage();
        assertFalse(caravan.brokenDown());
    }
    @Test
    public void brokenDownTestYes() {
        caravan.takeDamage();
        caravan.takeDamage();
        caravan.takeDamage();
        assertTrue(caravan.brokenDown());
    }

    @Test
    public void atOregonTestFalse() {
        assertFalse(caravan.atOregon());
    }

    @Test
    public void atOregonTestTrue() {
        caravan.travel(2200);
        assertTrue(caravan.atOregon());
    }

    @Test
    public void noFoodNo() {
        assertFalse(caravan.noFood());
    }

    @Test
    public void noFoodYesEdge() {
        for (int i = 0; i < 50 ; i++ ) {
            caravan.takeFood();
        }
        assertEquals(0,caravan.getFood());
        assertTrue(caravan.noFood());
    }

    @Test
    public void noFoodYes() {
        for (int i = 0; i < 52 ; i++ ) {
            caravan.takeFood();
        }
        assertEquals(-20, caravan.getFood());
        assertTrue(caravan.noFood());
    }



}