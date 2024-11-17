package user;

import java.util.*;
import timeslot.*;
import timeslot.Date;

/**
 * Class that contains method relating to doctor and availability
 * Contains a date and timeSlots which is an array that stores 1 if available or 0 if unavailable
 * timeSlots has a size of 7 which is the number of slots per day
 */
public class DoctorAvailabilityDate{

    private ArrayList<Integer> timeSlots;
    private Date date;

    /**
     * Constructor
     * @param date instance of date - creating a particular date
     */
    public DoctorAvailabilityDate(Date date){
        this.date = date;
        timeSlots = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            timeSlots.add(1);
        }
    }

    /**
     * Check if available for the particular slot
     * @param slotNum slot number to be checked
     * @return true if available else false
     */
    public int getAvailability(int slotNum){
        return timeSlots.get(slotNum-1);
    }

    /**
     * Sets the particular time slot to available
     * @param slotNum slot number to be set available
     */
    public void setAvailable(int slotNum){
        this.timeSlots.set(slotNum-1, 1);
    }

    /**
     * Sets the particular time slot to unavailable
     * @param slotNum slot number to be set unavailable
     */
    public void setUnavailable(int slotNum){
        this.timeSlots.set(slotNum-1,0);
    }

    /**
     * Prints the availabilty of doctor for the particular date
     */
    public void viewAvailability(){
        System.out.println(String.format("%02d-%-2d-%-9d %-10d %-10d %-10d %-10d %-10d %-10d", 
        date.getDay(), date.getMonth(),date.getYear(), timeSlots.get(0), timeSlots.get(1), timeSlots.get(2),
        timeSlots.get(3), timeSlots.get(4), timeSlots.get(5), timeSlots.get(6)));
    }

    /**
     * Checks if particular date exist inside personal calender
     * @param day
     * @param month
     * @param year
     * @return true if date exist
     */
    public Boolean isDate(int day, int month, int year){
        if (date.getDay() == day){
            if (date.getMonth() == month){
                if (date.getYear() == year){
                    return true;
                }
            }
        }
        return false;
    }
}