package java8.coding.questions.exercises;

import java8.coding.questions.employee.Employee;
import java8.coding.questions.insertData.InsertData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*Employees salary less than 70000 -> low
 * salary between 70000 and 90000 -> medium
 * salary greater than 90000 -> high*/
public class GroupEmployeeBasedOnSalariesLowMediumHigh {
    public static void main(String[] args) {
        groupEmployeeBasedOnSalariesLowMediumHigh(InsertData.loadData());
    }

    private static void groupEmployeeBasedOnSalariesLowMediumHigh(List<Employee> employees) {
        Map<String, List<String>> employeesGroupedBySalaryRanges = employees.stream().collect(Collectors.groupingBy(emp -> {
            if (emp.getSalary() < 70000)
                return "LOW";
            else if (emp.getSalary() < 90000)
                return "Medium";
            else
                return "High";
        }, Collectors.mapping(emp -> emp.getFirstName() + " " + emp.getLastName(), Collectors.toList())));
        System.out.println(employeesGroupedBySalaryRanges);
    }
}
