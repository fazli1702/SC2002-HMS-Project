package user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Methods related to administrator and viewing users
 */
public class AdministratorViewing {

    /**
     * Views staff by role
     * @param userManager manager of users
     */
    public void viewStaffByRole(UserManager userManager){
        ArrayList<Staff> staff = getStaff(userManager);
        Collections.sort(staff, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2) {
                return s1.getUserType().compareTo(s2.getUserType());
            }
        });
        printStaff(staff);
    }

    /**
     * View staff by gender
     * @param userManager
     */
    public void viewStaffByGender(UserManager userManager){
        ArrayList<Staff> staff = getStaff(userManager);
        Collections.sort(staff, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2) {
                return s1.getGender().compareTo(s2.getGender());
            }
        });
        printStaff(staff);
    }

    /**
     * View staff by age
     * @param userManager
     */
    public void viewStaffByAge(UserManager userManager){
        ArrayList<Staff> staff = getStaff(userManager);
        Collections.sort(staff, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2){
                return Integer.compare(s1.getAge(), s2.getAge());
            }
        });
        printStaff(staff);
    }

    /**
     * View staff by salary
     * @param userManager
     */
    public void viewStaffBySalary(UserManager userManager){
        ArrayList<Staff> staff = getStaff(userManager);
        Collections.sort(staff, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2){
                return Integer.compare(s1.getSalary(), s2.getSalary());
            }
        });
        printStaff(staff);
    }

    /**
     * View staff by ID
     * @param userManager
     */
    public void viewStaffByID(UserManager userManager){
        ArrayList<Staff> staff = getStaff(userManager);
        // quicksort(staff, 0, staff.size(), 5);
        Collections.sort(staff, new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2){
                return Integer.compare(s1.getHospitalID(), s2.getHospitalID());
            }
        });
        printStaff(staff);
    }

    /**
     * Returns all users that are Staff(Doctor, Pharmacist, Administrator)
     * @param userManager
     * @return staffs
     */
    public ArrayList<Staff> getStaff(UserManager userManager){
        ArrayList<Staff> staff = new ArrayList<>();
        ArrayList<User> userDB = userManager.getUserDB();
        for (User user : (userDB)){
            if (user.getUserType() != UserType.PATIENT){
                staff.add((Staff) user);
            }
        }
        return staff;
    }
    
    /**
     * Prints all staff details in format
     * @param staff
     */
    public void printStaff(ArrayList<Staff> staff){//email, phonenumber,password
        System.out.println(String.format("%-10s %-20s %-15s %-10s %-5s %-10s %-20s %-15s %-20s", "StaffID", "Name", "Role", "Salary", "Age", "Gender", "email","contactNum", "Password"));
        for (int i = 0; i < 120; i++){
            System.out.print("-");
        }
        System.out.print("\n");
        for (int i = 0; i < staff.size(); i++){
            Staff s = staff.get(i);
            System.out.println(String.format("%-10d %-20s %-15s $%-10d %-5d %-10s %-20s %-15d %-20s", s.getHospitalID(), s.getName(), s.getUserType(),s.getSalary(), s.getAge(), s.getGender(), s.getEmail(), s.getPhoneNumber(), s.getPassword()));
        }
    }

}
