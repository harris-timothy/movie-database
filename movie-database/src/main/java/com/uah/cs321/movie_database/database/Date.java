package main.java.com.uah.cs321.movie_database.database;

/**
 * Small class just used to store date information.
 * @author Quen Parson
 * @version 1.0
 * @since 1.8
 */
public class Date {
    /**
     * The month in range [1,12].
     * -1 = N/A
     */
    private int month;
    /**
     * The day of the month, starting from 1.
     * -1 = N/A
     */
    private int day;
    /**
     * The year.
     * -1 = N/A
     */
    private int year;

    /**
     * Default constructor, sets all fields to N/A (-1)
     */
    public Date() {
        month = -1;
        day = -1;
        year = -1;
    }

    /**
     * Constructor for specifying date fields.
     * Specify -1 for any field that is N/A.
     * @param mo Month
     * @param dy Day
     * @param yr Year
     */
    public Date(int mo, int dy, int yr) {
        month = mo;
        day = dy;
        year = yr;
    }

    /**
     * Gets the day of this date object.
     * @return The integer representing the day. -1 if N/A.
     */
    public int getDay() {
        return day;
    }

    /**
     * Gets the month of this date object.
     * @return The integer representing the month. -1 if N/A.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the year of this date object.
     * @return The integer representing the year. -1 if N/A.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the day of this date object.
     * @param newDay The new day. -1 if N/A.
     */
    public void setDay(int newDay) {
        day = newDay;
    }

    /**
     * Sets the month of this date object.
     * @param newMonth The new month. -1 if N/A.
     */
    public void setMonth(int newMonth) {
        month = newMonth;
    }

    /**
     * Sets the year of this date object.
     * @param newYear The new year. -1 if N/A.
     */
    public void setYear(int newYear) {
        year = newYear;
    }

    /**
     * Returns a readable string of this date object.
     * Format: M/D/Y, N/A fields are labelled as -.
     * Ex: -/21/2020 has an N/A entry for the month.
     * @return The formatted string of this date object.
     */
    @Override
    public String toString() {
        String out = "";
        if (month == -1) {
            out += "-/";
        } else {
            out += month + "/";
        }
        if (day == -1) {
            out += "-/";
        } else {
            out += day + "/";
        }
        if (year == -1) {
            out += "-";
        } else {
            out += year;
        }
        return out;
    }

    /**
     * Compares a date object to another.
     * N/A entries for time are considered less than (earlier in time) defined times.
     * Ex: 4/21/2020 is considered later in time than 4/-/2020. If both entries have an N/A in the same field, they will be considered equal.
     * @param other The other date object to compare this one to.
     * @return Returns 0 if the two dates are equal, -1 if this date is less than (earlier in time) than the other date, and 1 if this date is later in time than the other date.
     */
    public int compareTo(Date other) {
        // First compare the years
        if (year != other.getYear()) {
            if (year > other.getYear()) {
                return 1;
            }
            return -1;
        }
        // If years are equal, compare the months
        if (month != other.getMonth()) {
            if (month > other.getMonth()) {
                return 1;
            }
            return -1;
        }
        // Finally, compare the days
        if (day != other.getDay()) {
            if (day > other.getDay()) {
                return 1;
            }
            return -1;
        }
        // If all fields are equal, return 0
        return 0;
    }
}
