package ragul.srushty.com.neuroeye.data;

/**
 * Created by karthi2 on 9/15/2016.
 */

import java.util.Date;

/**
 * Data object representing a car.
 *
 * @author ISchwarz
 */
public class Car {

    private final String patient_name;
    private final String patient_id;
    private final String patient_gender;
    private final String patient_age;
    private final Date test_date;
    private final String patient_mobile;
    private final String patient_email;

    public Car(final String patient_name, final String patient_id, final String patient_gender, final String patient_age, final Date test_date, final String mobile, final String email) {

        this.patient_name = patient_name;
        this.patient_id = patient_id ;
        this.patient_gender = patient_gender;
        this.patient_age = patient_age;
        this.test_date = test_date;
        this.patient_mobile = mobile;
        this.patient_email = email;
    }

    public String getPatientName() {
        return  this.patient_name;
    }

    public String getPatientId() {
        return  this.patient_id;
    }

    public String getPatientAge() {
        return  this.patient_age;
    }

    public String getPatientMobile() {
        return  this.patient_mobile;
    }

    public String getPatientEmail() {
        return  this.patient_email;
    }

    public Date getTestDate() {
        return  this.test_date;
    }

    public String getPatientGender() {
        return  this.patient_gender;
    }

}
