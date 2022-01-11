package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TimeTest {

    Time clock;
    @BeforeEach
    public void createTime() {
        clock = new Time("February");
    }

    @Test
    public void getMonthTest() {
        assertEquals("February", clock.getMonth());
    }

    @Test
    public void getTimeTestNoStep() {
        assertEquals("February 1 1848",  clock.getTime());
    }

    @Test
    public void timeStepTestZeroDays() {
        clock.timeStep(0);
        assertEquals("February 1 1848",  clock.getTime());
    }

    @Test
    public void changeDay() {
        clock.changeDay(2);
        assertEquals("February 2 1848",  clock.getTime());

    }

    @Test
    public void changeMonth() {
        clock.changeMonth("March");
        assertEquals("March 1 1848",  clock.getTime());

    }

    @Test
    public void changeYear() {
        clock.changeYear(1849);
        assertEquals("February 1 1849",  clock.getTime());

    }

    @Test
    public void timeStepTestJan() {
        clock = new Time("January");
        clock.timeStep(31);
        assertEquals("February 1 1848", clock.getTime());
    }

    @Test
    public void timeStepTestOneDay() {
        clock.timeStep(1);
        assertEquals("February 2 1848",  clock.getTime());
    }

    @Test
    public void timeStepTestEdgeLastDay() {
        clock.timeStep(28);
        assertEquals("February 29 1848",  clock.getTime());
    }

    @Test
    public void timeStepTestEdgeFirstDay() {
        clock.timeStep(29);
        assertEquals("March 1 1848", clock.getTime());
    }

    @Test
    public void timeStepTestEdgeYearLastDay() {
        clock.timeStep(334);
        assertEquals("December 31 1848", clock.getTime());

    }
    @Test
    public void timeStepTestEdgeYearFirstDay() {
        clock.timeStep(335);
        assertEquals("January 1 1849", clock.getTime());

    }

    @Test
    public void timeStepTestOverAYear() {
        clock.timeStep(731);
        assertEquals("February 1 1850",clock.getTime());
    }

    @Test
    public void timeStepYearJanuary() {
        clock = new Time("January");
        clock.timeStep(366);
        assertEquals("January 1 1849", clock.getTime());

    }

    @Test
    public void timeStepManySmall() {
        clock = new Time("January");
        for (int i = 0; i < 52; i++) {
            clock.timeStep(7);
        }
        assertEquals("December 30 1848",clock.getTime());
    }







}
