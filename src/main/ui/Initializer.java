package ui;

import model.Caravan;
import model.Person;

import java.util.Scanner;

public class Initializer {
    private Caravan caravan;
    private final Scanner input;


    public Initializer() {
        input = new Scanner(System.in);
        caravan = null;

    }

    // EFFECTS: returns a initlized caravan
    public Caravan initializeCaravan() {
        initializeNames();
        return caravan;
    }

    // MODIFIES gameApp
    // EFFECTS: returns a starting month
    public String startingMonth() {
        String month = null;
        boolean illegalValue = true;
        while (illegalValue) {
            month = input.nextLine();
            if (month.matches("[1-3]") && month.length() == 1) {
                if (month.equals("1")) {
                    month = "February";
                } else if (month.equals("2")) {
                    month = "March";
                } else {
                    month = "April";
                }
                illegalValue = false;
            } else {
                System.out.print("invalid input! please input either 1,2, or 3");
            }

        }
        return month;
    }

    // MODIFIES caravan
    // EFFECTS: adds 3 persons to a caravan
    private void initializeNames() {
        System.out.print("Please choose a name a for your Caravan \n");
        String caravanName = handleName();
        String personName;
        String personAge;
        caravan = new Caravan(caravanName);
        while (caravan.getGroup().size() < 3) {
            System.out.print("Please choose a name a person \n");
            personName = handleName();
            personAge = handleAge();
            caravan.addPerson(new Person(personName, Integer.parseInt(personAge)));
        }


    }

    // MODIFIES: caravan
    // EFFECTS: returns an allowed name
    private String handleName() {
        String name = null;
        boolean illegalName = true;
        while (illegalName) {
            name = input.nextLine();
            if (name.matches("^[a-zA-Z_ ]*$") && name.length() != 0 && name.trim().length() != 0) {
                illegalName = false;
            } else {
                System.out.print("Invalid name! Please type in a name with only letters and not empty \n");
            }
        }
        System.out.println(name.trim());
        return name.trim();

    }

    // MODIFIES: caravan
    // EFFECTS: makes sure input can only be of integer value, not allowing spaces
    private String handleAge() {
        String age = null;
        System.out.print("Please choose a age for this person \n");
        boolean illegalAge = true;
        while (illegalAge) {
            age = input.nextLine();
            if (age.matches("[1-9]+[0-9]*")) {
                illegalAge = false;
            } else {
                System.out.print("Invalid age! please type in an age containing only integers and no spaces \n");
            }
        }
        return age;
    }


}
