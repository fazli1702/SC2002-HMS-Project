package user;


/**
 * Patient class that is subclass to User class
 */
public class Patient extends User{

    private MedicalRecord medicalRecord;

    /**
     * Constuctor
     * @param name name of patient
     * @param hospitalID hospitalID of patient
     * @param email email of patient
     * @param phoneNumber phone number of patient
     * @param age age of patient
     * @param gender gender of patient
     * @param DOB date of birth of patient
     * @param bloodType blood type of patient
     */
    public Patient(String name, int hospitalID, String email, int phoneNumber, int age, Gender gender, String DOB, String bloodType){
        super(hospitalID, name, email, UserType.PATIENT, phoneNumber, age, gender);
        medicalRecord = new MedicalRecord(hospitalID, name, DOB, gender, phoneNumber, email, bloodType);
    }

    /**
     * 
     * @return MedialRecord instance of patient
     */
    public MedicalRecord getMedicalRecord(){
        return this.medicalRecord;
    }

    /**
     * Prints the medical record of patient with formatting
     */
    public void viewMedicalRecord(){
        this.medicalRecord.viewMedicalRecord();
    }   
    
    /**
     * Sets name of patient
     */
    public void setName(String name){
        super.setName(name);
        this.medicalRecord.setName(name);
    }

    /**
     * Sets phone number of patient
     */
    public void setPhoneNumber(int newPhoneNumber){
        super.setPhoneNumber(newPhoneNumber);
        this.medicalRecord.setPhoneNumber(newPhoneNumber);
    }

    /**
     * Sets email of patient
     */
    public void setEmail(String email){
        super.setEmail(email);
        this.medicalRecord.setEmail(email);
    }

    /**
     * Updates the medical record of patient
     * @param treatment treatment will be added 
     * @param diagnosis diagnosis that will be added 
     * @param presciption prescription that will be added
     */
    public void updateMedicalRecord(String treatment, String diagnosis, String presciption){
        medicalRecord.addDiagnosis(diagnosis);
        medicalRecord.addPrescribedMedication(presciption);
        medicalRecord.addTreatment(treatment);
    }

    
}