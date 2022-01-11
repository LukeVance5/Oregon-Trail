package model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class PersonTest {
    Person person;
    @BeforeEach
    public void createPerson() {
        person = new Person("eliza", 20);
    }

    @Test
    public void takeDamageTest() {
        person.takeDamage();
        assertEquals(2,person.getHealth());
    }

    @Test
    public void setHealthTest() {
        person.changeHeath(1);
        assertEquals(1, person.getHealth());
    }

    @Test
    public void returnNameTest() {
        assertEquals("eliza", person.getName());
    }

    @Test
    public void returnHealthBase() {
        assertEquals(3, person.getHealth());
    }

    @Test
    public void returnHealthDamaged() {
        person.takeDamage();
        assertEquals(2, person.getHealth());
    }

    @Test
    public void returnAge() {
        assertEquals(20, person.getAge());
    }


    @Test
    public void testToString() {
        String personString = person.toString();
        assertEquals("eliza : Age 20 Current Health 3",personString );
    }
}
