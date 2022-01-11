package model;


import org.json.JSONObject;
import persistence.Writable;

// This class that deals with the displayed date
public class Time implements Writable {
    private static final String JAN = "January"; // 31
    private static final String FEB = "February"; // 28 (no need for leap years as 1841-1843 no leap years)
    private static final String MAR = "March"; // 31
    private static final String APR = "April"; // 30
    private static final String MAY = "May";  // 31
    private static final String JUN = "June"; // 30
    private static final String JUL = "July"; // 31
    private static final String AUG = "August"; // 31
    private static final String SEP = "September"; // 30
    private static final String OCT = "October"; // 31
    private static final String NOV = "November"; // 30
    private static final String DEC = "December"; // 31
    private int year;
    private int day;
    private String month; // Months have either 28,30, or 31 days

    // constructs the time
    public Time(String m) {
        year = 1848;
        day = 1;
        month = m;
    }

    //EFFECTS  Converts year and day to string and creates a string from year, day and month and returns
    public String getTime() {
        String strYear = String.valueOf(year);
        String strDay = String.valueOf(day);
        return month + " " + strDay + " " + strYear; // !!! change TimeTest.java if format changes
    }

    // EFFECTS returns month

    public String getMonth() {
        return month;
    }

    // REQUIRES: d must be positive
    // MODIFIES: this
    // EFFECTS: Increases the clock by d days, appropriately changing months / years
    public void timeStep(int d) {
        day += d;

        // The purpose of this while loop is if a very large amount of days is skipped > 28, as then code will have to
        // repeat to move 2 months forward etc.
        while (pastEndOfMonthThirtyOne() || pastEndOfMonthThirty()
                || pastEndOfYear() || pastFebNoLeap() || pastFebLeap()) {
            if (pastEndOfMonthThirtyOne()) {
                thirtyOneDayMonth();
            } else if (pastEndOfMonthThirty()) {
                thirtyDayMonth();
            } else {
                otherMonths();
            }
        }
    }


    // REQUIRES string be in the format "January", "February", etc *** USE ONLY BY JSON
    // MODIFIES: this
    // EFFECTS: changes this.month to m
    public void changeMonth(String m) {
        this.month = m;
    }

    // REQUIRES d be possible for month, ie < 32 for December *** USE ONLY BY JSON
    // MODIFIES: this
    // EFFECTS: changes this.day to d
    public void changeDay(int d) {
        this.day = d;
    }

    // REQUIRES y >= 1848 *** USE ONLY BY JSON
    // MODIFIES: this
    // EFFECTS: changes this.year to y
    public void changeYear(int y) {
        this.year = y;
    }

    // EFFECTS: produces true if in a 31-day month and having more than 31 days
    private boolean pastEndOfMonthThirtyOne() {
        return ((month.equals(JAN) || month.equals(MAR) || month.equals(MAY) || month.equals(JUL) || month.equals(AUG)
                || month.equals(OCT)) && day > 31);
    }

    // EFFECTS: produces true if in a 30-day month and having more than 30 days
    private boolean pastEndOfMonthThirty() {
        return ((month.equals(APR) || month.equals(JUN) || month.equals(SEP) || month.equals(NOV)) && day > 30);
    }

    // EFFECTS: handles being in december month and having more than 31 days
    private boolean pastEndOfYear() {
        return (month.equals(DEC) && day > 31);
    }

    // EFFECTS: produces true if in February month and having more than 28 days while not being in 1848 (leap year)
    private boolean pastFebNoLeap() {
        return (month.equals(FEB) && day > 28 && year != 1848);
    }

    // EFFECTS: produces true if in February month and having more than 29 days while being in 1848 (leap year)
    private boolean pastFebLeap() {
        return (month.equals(FEB) && day > 29 && year == 1848);
    }


    // EFFECTS: switches into the next month, removing the 31 days
    private void thirtyOneDayMonth() {
        if (month.equals(JAN)) {
            month = FEB;
        } else if (month.equals(MAR)) {
            month = APR;
        } else if (month.equals(MAY)) {
            month = JUN;
        } else if (month.equals(JUL)) {
            month = AUG;
        } else if (month.equals(AUG)) {
            month = SEP;
        } else {
            month = NOV;
        }
        day -= 31;

    }

    // EFFECTS: switches into the next month, removing the 30 days
    private void thirtyDayMonth() {
        if (month.equals(APR)) {
            month = MAY;
        } else if (month.equals(JUN)) {
            month = JUL;
        } else if (month.equals(SEP)) {
            month = OCT;
        } else {
            month = DEC;
        }
        day -= 30;


    }

    // EFFECTS: switches into the next month for the exceptional months
    private void otherMonths() {
        if (pastEndOfYear()) {
            month = JAN;
            year += 1;
            day -= 31;
        } else if (pastFebLeap()) {
            month = MAR;
            day -= 29;
        } else {
            month = MAR;
            day -= 28;
        }
    }

    @Override
    // MODIFIES: jsonFile
    // EFFECTS: writes day to jsonFile
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("year",year);
        json.put("day",day);
        json.put("month",month);
        return json;
    }
}









