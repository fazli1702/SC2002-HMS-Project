package user;

/**
 * Staff class is a sub class of User class and stores an addiional salary attribute
 * Staff refers to Doctor, Pharmacist and Doctor
 */
public class Staff extends User{
    private int salary;

    /**
     * Constructor
     * @param hospitalID hospitalID of staff
     * @param name name of staff
     * @param email email of staff
     * @param userType user type of staff(DOCTOR, PHARMACIST, ADMINISTRATOR)
     * @param phoneNumber phone number of staff
     * @param age age of staff
     * @param gender gender of staff
     * @param salary salary of staff
     */
    public Staff(int hospitalID, String name, String email, UserType userType, int phoneNumber, int age, Gender gender, int salary){
        super(hospitalID, name, email, userType, phoneNumber, age, gender);
        this.salary = salary;

    }

    /**
     * 
     * @return salary of staff
     */
    public int getSalary(){
        return this.salary;
    }

    /**
     * sets salary of staff
     * @param newSalary
     */
    public void setSalary(int newSalary){
        this.salary = newSalary;
    }

    /**
     * prints staff information with formatting
     */
    public void viewUser(){
        System.out.println(String.format("%-5d %-15s %-15s %-15s %-15d", super.getHospitalID(), super.getName(), super.getUserType(), super.getPassword(), this.salary));
    }
}