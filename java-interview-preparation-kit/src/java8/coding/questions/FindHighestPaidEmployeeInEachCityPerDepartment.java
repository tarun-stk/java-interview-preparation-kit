package java8.coding.questions;

import java8.coding.questions.employee.Employee;
import java8.coding.questions.insertData.InsertData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//highest paid employee in each department per city
public class FindHighestPaidEmployeeInEachCityPerDepartment {
    public static void main(String[] args) {
        findHighestPaidEmployeeInEachCityPerDepartment(InsertData.loadData());
    }

    private static void findHighestPaidEmployeeInEachCityPerDepartment(List<Employee> employees) {
        employees.stream().collect(Collectors.groupingBy(Employee::getCity,
                        Collectors.groupingBy(Employee::getDepartment,
                                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)))))
                .entrySet().stream()
                .map(stringMapEntry -> stringMapEntry.getKey() + " -> " + (stringMapEntry.getValue().entrySet().stream().map(entry -> entry.getKey() + " - " + entry.getValue().map(emp -> emp.getFirstName() + emp.getLastName()).orElseGet(null)).collect(Collectors.toSet())))
                .forEach(System.out::println);
    }
}
