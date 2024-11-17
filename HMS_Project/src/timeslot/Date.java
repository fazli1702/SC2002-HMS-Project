package timeslot;

/**
 * Date class stores date and contains methods related to it
 * Paramters - day, month, year
 */
public class Date {
   
    private int day;
    private int month;
    private int year;
		

    /**
     * Constructor
     * @param day day of date
     * @param month month of date
     * @param year year of date
     */
    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    /**
     * @return day of date
     */
    public int getDay() {
        return day;
    }

    /**
     * @return month of date
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return year of date
     */
    public int getYear() {
        return year;
    }

    /**
     * set day of date
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * set month of date
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * set year of date
     */
    public void setYear(int year) {
        this.year = year;
    }
}
