import core.Validator;
import information.Person;

import java.util.Scanner;

//import static information.View.people;

public class Main {
    public static void addPerson() throws IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("----- Add Person -----");
        System.out.print("Name: ");
        String name;
        name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email;
        email = scanner.nextLine().replaceAll(" ", "");
        String birthday;
        System.out.print("Birthday (yyyy-mm-dd): ");
        birthday = scanner.nextLine().trim();
        Person person = new Person(name, email, birthday);
        Validator.validator(person);
        System.out.println(person);
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("Hello World");
        addPerson();
    }
}