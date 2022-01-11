package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import exceptions.NullCaravanOrTimeException;
import model.Person;
import model.Caravan;
import model.Time;
import persistence.JsonWriterCaravan;
import persistence.JsonWriterTime;

import javax.swing.*;

public class GameApp implements ActionListener {
    private Caravan caravan;
    private Time clock;
    private boolean gameState;
    private final Random random;
    private int randomInt;
    private Initializer initializer;
    private GameFrame gameFrame;
    private static Integer WIDTH = 800;
    private static Integer HEIGHT = 600;

    // MODIFIES: this
    // EFFECTS: initializes the fields of the game
    public GameApp() {
        setUpGui();
        initializer = new Initializer();
        gameState = false;
        random = new Random();
        randomInt = 0;

    }


    // MODIFIES: this
    // EFFECTS: sets up the display
    private void setUpGui() {
        doSplash();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameFrame = new GameFrame(this);
    }

    // EFFECTS: initiates a splashScreen Frame, and disposes after a delay
    private void doSplash() {
        SplashScreen splashScreen = new SplashScreen();
        Timer timer = new Timer(3000, splashScreen);
        timer.start();
        splashScreen.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: sets the caravan, clock, and gameState to begin game
    private void startGame() {
        DefaultListModel<Person> groupList = gameFrame.getCaravanPanel().getGroupList();
        caravan = new Caravan(gameFrame.getCaravanPanel().getCaravanName());
        for (int i = 0; i < groupList.size(); i++) {
            caravan.addPerson(groupList.get(i));
        }
        System.out.print("Choose a starting month \n 1. February \n 2. March \n 3. April \n");
        clock = new Time(initializer.startingMonth());
        System.out.println("We begin our journey in Independence, Missouri");
        gameState = true;
        playGame();
    }



    // EFFECTS: prints hud if gameState is true
    private void playGame() {
        if (gameState) {
            hud();
        }

    }

    // MODIFIES: Caravan.json and Time.json
    // EFFECTS: Saves current game to json
    private void saveCaravan() {
        try {
            JsonWriterCaravan writerCaravan = new JsonWriterCaravan("./data/Caravan.json");
            writerCaravan.open();
            writerCaravan.write(caravan);
            writerCaravan.close();
            JsonWriterTime writerTime = new JsonWriterTime("./data/Time.json");
            writerTime.open();
            writerTime.write(clock);
            writerTime.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Cannot Save Caravan!");
        }
    }

    // EFFECTS: prints out fields from caravan and time class with descriptions to help user
    private void hud() {

        System.out.println("\n \n" + clock.getTime());
        System.out.println("Distance From Oregon " + caravan.getDistanceFromOregon());
        System.out.println("Condition = " + (caravan.getCondition()));
        System.out.println("Food = " + caravan.getFood());
        System.out.println("The " + caravan.getCaravanName() + " caravan");
        for (Person p : caravan.getGroup()) {
            printPerson(p);
        }
    }


    // MODIFIES: this
    // EFFECTS: decides what to do with commands inputted
    private void handleEndState() {
        if (caravan.allDead() || caravan.noFood() || caravan.brokenDown()) {
            SplashScreen splashScreen = new SplashScreen();
            splashScreen.setVisible(true);
            gameState = false;
        } else if (caravan.atOregon()) {
            gameState = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: events that happen on the trail
    private void events() {
        clock.timeStep(7);
        caravan.travel((random.nextInt(51) + 50));
        caravan.takeFood();
        randomInt = random.nextInt(20) + 1;
        if (randomInt == 12 || randomInt == 13) {
            caravan.takeDamage();
        } else if (randomInt >= 1 && randomInt <= 5) {
            caravan.getGroup().get(random.nextInt(caravan.getGroup().size())).takeDamage();
            caravan.anyDead();
        } else if (randomInt >= 6 && randomInt <= 11) {
            caravan.takeFood();
        }
        handleEndState();

    }


    // EFFECTS: prints out the fields of persons still alive in caravan
    private void printPerson(Person p) {
        System.out.println(p.getName() + ", Age " + p.getAge()
                + ", Health = " + p.getHealth());
    }

    // MODIFIES: this
    // EFFECTS: preforms in game actions when buttons on gameFrame.buttonPanel are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        int groupSize = gameFrame.getCaravanPanel().getGroupList().size();
        if (e.getSource() == gameFrame.getStartButton()) {
            if (caravan == null && groupSize == 3) {
                startGame();
            } else if (caravan != null) {
                new ErrorPopUp("Game Already Started!");
            } else if (groupSize < 3) {
                new ErrorPopUp("Caravan not full!");
            }
        }   else if (e.getSource() == gameFrame.getLoadButton()) {
            LoadGame loadGame = new LoadGame("./data/Caravan.json","./data/Time.json");
            try {
                caravan = loadGame.loadCaravan();
                clock = loadGame.loadTime();
                gameState = true;
                playGame();
            } catch (NullCaravanOrTimeException ex) {
                new ErrorPopUp("No game to load!");
            }
        }
        preformButtons(e);
    }

    // EFFECTS: a helper method for actionPreformed()
    private void preformButtons(ActionEvent e) {
        int groupSize = gameFrame.getCaravanPanel().getGroupList().size();
        if (e.getSource() == gameFrame.getSaveButton()) {
            if (gameState = true && caravan != null) {
                saveCaravan();
            }

        }   else if (e.getSource() == gameFrame.getTravelButton()) {
            if ((gameState = true) && (caravan != null)) {
                events();
                playGame();
            }
        }   else if (e.getSource() == gameFrame.getQuitButton()) {
            doSplash();
            gameFrame.dispose();
        }   else if (e.getSource() == gameFrame.getPlayAgainButton()) {
            if ((groupSize == 3) && (!gameState) && (caravan != null))  {
                startGame();
            }   else if (gameState)  {
                new ErrorPopUp("Game already running");
            }   else if (groupSize != 3) {
                new ErrorPopUp("Caravan not full!");
            }
        }
    }
}
