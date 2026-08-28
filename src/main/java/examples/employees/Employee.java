package examples.employees;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Employee {

    private String name;
    private LocalDate birthData;

    public Employee(String name, LocalDate birthData) {
        this.name = name;
        this.birthData = birthData;
    }

    public static List<Employee> bornAfter(List<Employee> list, LocalDate bornAfter) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("cannot be null");
        }

        return list.stream().filter(employee -> employee.getBirthData().isAfter(bornAfter)).toList();
    }

    public static List<Employee> sortList(List<Employee> list) {
        return list.stream().sorted(Comparator.comparing(employee -> employee.birthData)).toList();
    }

    public static Employee findTheYoungestEmployee(List<Employee> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("cannot be null");
        }


        return list.stream()
                .max(Comparator.comparing(Employee::getBirthData))
                .orElseThrow();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthData() {
        return birthData;
    }

    public void setBirthData(LocalDate birthData) {
        this.birthData = birthData;
    }
}
