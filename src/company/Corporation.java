package company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Corporation {
    private List<Employee> employees = new ArrayList<>();

    public void loadFromFile(Path filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filePath)))
        {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Employee list uploaded");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void saveToFile(Path filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(filePath)))
        {
            oos.writeObject(employees);
            System.out.println("The list of employees has been saved");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added");
    }

    public void editEmployee(String lastName, Scanner scanner) {
        for (Employee employee : employees)
        {
            if (employee.getLastName().equalsIgnoreCase(lastName))
            {
                System.out.println("Name:");
                employee.setFirstName(scanner.nextLine());

                System.out.println("Last name:");
                employee.setLastName(scanner.nextLine());

                System.out.println("Age:");
                employee.setAge(scanner.nextInt());

                scanner.nextLine();

                System.out.println("Employee details updated");
                return;
            }
        }

        System.out.println("No employee with this last name was found");
    }

    public void removeEmployee(String lastName) {
        employees.removeIf(employee -> employee.getLastName().equalsIgnoreCase(lastName));
        System.out.println("Employee deleted");
    }

    public void findEmployeeByLastName(String lastName) {
        for (Employee employee : employees)
        {
            if (employee.getLastName().equalsIgnoreCase(lastName))
            {
                System.out.println(employee);
                return;
            }
        }

        System.out.println("No employee with this last name was found");
    }

    public void findEmployeesByAgeOrLastName(char initialLetter, int age) {
        List<Employee> result = employees.stream()
                .filter(employee -> employee.getAge() == age || employee.getLastName().startsWith(String.valueOf(initialLetter)))
                .toList();

        if (result.isEmpty())
        {
            System.out.println("No employees found");
        }
        else
        {
            result.forEach(System.out::println);
        }
    }

    public void saveFoundEmployeesToFile(List<Employee> foundEmployees, Path filePath) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath))
        {
            for (Employee employee : foundEmployees)
            {
                writer.write(employee.toString());
                writer.newLine();
            }

            System.out.println("Found employees are saved to a file");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void showAllEmployees() {
        if (employees.isEmpty())
        {
            System.out.println("Employee list is empty");
        }
        else
        {
            employees.forEach(System.out::println);
        }
    }
}
