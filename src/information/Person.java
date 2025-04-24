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
    @Length(minLength = 10, maxLength = 25)
    private String Name;

    @Email
    @NotNull
    private String Email;

    @Range(minAge = 0, maxAge = 100)
    private int Age;

    @Future
    @Past
    @NotNull
    private String Birthday;

    // Contructor -----------------------------------------------
    public Person(String name, String email, String birthday) {
        Name = name;
        Email = email;
        Age = calculateAge(birthday);
        Birthday = birthday;
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
    @ValidateMethod
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
