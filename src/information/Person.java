package information;

import annotations.*;
import core.Validator;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    private Timestamp timestamp;

    @NotNull
    private String Name;

    @Email
    private String Email;

    private int Age;

    @Future
    private String Birthday;

    // Contructor -----------------------------------------------
    public Person(String name, String email, String birthday) {
        Name = name;
        Email = email;
        Age = calculateAge(birthday);
        Birthday = birthday;
    }

    public Person(String birthday) {
    }
    // -----------------------------------------------------------

    // Getter, Setter --------------------------------------------
    public String getName() {                       // getName
        return Name;
    }

    public void setName(String name) {              // setName
        Name = name;
    }

    public String getEmail() {                      // getEmail
        return Email;
    }

    public void setEmail(String email) {            // setEmail
        Email = email;
    }

    public int getAge() {                           // getAge
        return Age;
    }

    public String getBirthday() {                   // getBirthday
        return Birthday;
    }

    public void setBirthday(String birthday) {      // setBirthday
        Birthday = birthday;
    }
    // -----------------------------------------------------------

    // toString --------------------------------------------------
    @Override
    public String toString() {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", Email=" + Email +
                ", Age=" + Age +
                ", Birthday='" + Birthday + '\'' +
                '}';
    }

    // -----------------------------------------------------------
    // Age Calculation -------------------------------------------
    private int calculateAge(String birthdayStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthdayStr, formatter);
            return Period.between(birthDate, LocalDate.now()).getYears();
        } catch (Exception e) {
            System.err.println("Error calculating age: " + e.getMessage());
            return -1;
        }
    }
}
