package ragul.srushty.com.neuroeye;

/**
 * Created by karthi2 on 9/15/2016.
 */


import ragul.srushty.com.neuroeye.data.Car;

import java.util.Comparator;

/**
 * A collection of {@link Comparator}s for {@link Car} objects.
 *
 * @author ISchwarz
 */

public final class CarComparators {

    private CarComparators() {
        //no instance
    }


    public static Comparator<Car> getNameComparator() {
        return new NameComparator();
    }


    public static Comparator<Car> getPatientIdComparator() {
        return new PatientIdComparator();
    }



    public static Comparator<Car> getPatientGenderComparator() {
        return new PatientGenderComparator();
    }


    public static Comparator<Car> getPatientMobileComparator() {
        return new PatientMobileComparator();
    }

    public static Comparator<Car> getPatientEmailComparator() {
        return new PatientEmailComparator();
    }



    public static Comparator<Car> getPatientAgeComparator() {
        return new PatientAgeComparator();
    }


    public static Comparator<Car> getTestDateComparator() {
        return new TestDateComparator();
    }


    public static Comparator<Car> getCarNameComparator() {
        return new NameComparator();
    }





    private static class NameComparator implements Comparator<Car> {

        @Override
        public int compare(final Car car1, final Car car2) {
            return car1.getPatientName().compareTo(car2.getPatientName());
        }
    }




    private static class PatientIdComparator implements Comparator<Car> {

        @Override
        public int compare(final Car car1, final Car car2) {
            return car1.getPatientId().compareTo(car2.getPatientId());
        }
    }


    private static class PatientGenderComparator implements Comparator<Car> {

        @Override
        public int compare(final Car car1, final Car car2) {
            return car1.getPatientGender().compareTo(car2.getPatientGender());
        }
    }





    private static class PatientMobileComparator implements Comparator<Car> {

        @Override
        public int compare(final Car car1, final Car car2) {
            return car1.getPatientMobile().compareTo(car2.getPatientMobile());
        }
    }


    private static class PatientEmailComparator implements Comparator<Car> {

        @Override
        public int compare(final Car car1, final Car car2) {
            return car1.getPatientEmail().compareTo(car2.getPatientEmail());
        }
    }



    private static class PatientAgeComparator implements Comparator<Car> {

        @Override
        public int compare(final Car car1, final Car car2) {
            return car1.getPatientAge().compareTo(car2.getPatientAge());
        }
    }


    private static class TestDateComparator implements Comparator<Car> {

        @Override
        public int compare(final Car car1, final Car car2) {
            return car1.getTestDate().compareTo(car2.getTestDate());
        }
    }
}