package pl.wsb.hotel.domain.client;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class Client {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private String street;
    private String city;
    private String zipCode;
    private Number phoneNumber;

    public Client(String id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.street = null;
        this.city = null;
        this.zipCode = null;
        this.phoneNumber = null;
    }

    public Client(String id, String firstName, String lastName, LocalDate birthDate, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.street = null;
        this.city = city;
        this.zipCode = null;
        this.phoneNumber = null;
    }

    public String getId() {
        return id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Number getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Number phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() { return birthDate; }

    public int getAge() throws Exception {
        if(this.birthDate == null) {
            throw new Exception("Birth date is not set for client");
        }

        LocalDate currentDate = LocalDate.now();

        if ((this.birthDate.isBefore(currentDate))) {
            return Period.between(this.birthDate, currentDate).getYears();
        } else {
            throw new Exception("Cannot calculate age for future birth date");
        }
    }

    public String getFullName() {
        return this.firstName + ' ' + this.lastName;
    }
    public String getAddress() {
        return
            Optional.ofNullable(this.street).orElse("---") + ", " +
            Optional.ofNullable(this.city).orElse("---")  + ' ' +
            Optional.ofNullable(this.zipCode).orElse("---");
    }

    public String getAllSummaries() throws Exception {
        return
            "Name & surname: " + this.getFullName() + " \n" +
            "Address: " + this.getAddress() + " \n" +
            "Age: " + this. getAge() + " \n"
        ;
    }
}
