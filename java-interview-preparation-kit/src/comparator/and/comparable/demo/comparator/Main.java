package comparator.and.comparable.demo.comparator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<Employee> employeeList = Stream.of(
            new Employee(101, "Varun", 1200),
            new Employee(98, "Dhanush", 100),
            new Employee(198, "Dhawan", 1100),
            new Employee(120, "Karan", 1500),
            new Employee(116, "Upadhyay", 1400),
            new Employee(108, "Akhilesh", 1200),
            new Employee(108, "Binod", 1200),
            new Employee(104, "Modi", 1000),
            new Employee(151, "Barun", 900),
            new Employee(143, "Carol", 1500)
        ).collect(Collectors.toList());

        System.out.println("Before sorting: ");
        System.out.println(employeeList);
//        Before sorting:
//[Employee{id=101, name='Varun', salary=1200.0}, Employee{id=98, name='Dhanush', salary=100.0}, Employee{id=198, name='Dhawan', salary=1100.0}, Employee{id=120, name='Karan', salary=1500.0}, Employee{id=116, name='Upadhyay', salary=1400.0}, Employee{id=108, name='Akhilesh', salary=1200.0}, Employee{id=108, name='Binod', salary=1200.0}, Employee{id=104, name='Modi', salary=1000.0}, Employee{id=151, name='Barun', salary=900.0}, Employee{id=143, name='Carol', salary=1500.0}]

        System.out.println("Sorting based on id's");
        Collections.sort(employeeList, new IdComparator());
        System.out.println(employeeList);
//        Sorting based on id's
//        [Employee{id=98, name='Dhanush', salary=100.0}, Employee{id=101, name='Varun', salary=1200.0}, Employee{id=104, name='Modi', salary=1000.0}, Employee{id=108, name='Akhilesh', salary=1200.0}, Employee{id=108, name='Binod', salary=1200.0}, Employee{id=116, name='Upadhyay', salary=1400.0}, Employee{id=120, name='Karan', salary=1500.0}, Employee{id=143, name='Carol', salary=1500.0}, Employee{id=151, name='Barun', salary=900.0}, Employee{id=198, name='Dhawan', salary=1100.0}]

        System.out.println("Sorting based on names");
        Collections.sort(employeeList, new NameComparator());
        System.out.println(employeeList);
//        Sorting based on names
//[Employee{id=108, name='Akhilesh', salary=1200.0}, Employee{id=151, name='Barun', salary=900.0}, Employee{id=108, name='Binod', salary=1200.0}, Employee{id=143, name='Carol', salary=1500.0}, Employee{id=98, name='Dhanush', salary=100.0}, Employee{id=198, name='Dhawan', salary=1100.0}, Employee{id=120, name='Karan', salary=1500.0}, Employee{id=104, name='Modi', salary=1000.0}, Employee{id=116, name='Upadhyay', salary=1400.0}, Employee{id=101, name='Varun', salary=1200.0}]


        System.out.println("Sorting based on salaries");
        Collections.sort(employeeList, new SalaryComparator());
        System.out.println(employeeList);
//        Sorting based on salaries
//[Employee{id=98, name='Dhanush', salary=100.0}, Employee{id=151, name='Barun', salary=900.0}, Employee{id=104, name='Modi', salary=1000.0}, Employee{id=198, name='Dhawan', salary=1100.0}, Employee{id=108, name='Akhilesh', salary=1200.0}, Employee{id=108, name='Binod', salary=1200.0}, Employee{id=101, name='Varun', salary=1200.0}, Employee{id=116, name='Upadhyay', salary=1400.0}, Employee{id=143, name='Carol', salary=1500.0}, Employee{id=120, name='Karan', salary=1500.0}]


        System.out.println("Sorting based on id, name, salary ====> all three");
        Collections.sort(employeeList, new IdNameSalaryComparator());
        System.out.println(employeeList);
//        Sorting based on id, name, salary ====> all three
//[Employee{id=98, name='Dhanush', salary=100.0}, Employee{id=101, name='Varun', salary=1200.0}, Employee{id=104, name='Modi', salary=1000.0}, Employee{id=108, name='Akhilesh', salary=1200.0}, Employee{id=108, name='Binod', salary=1200.0}, Employee{id=116, name='Upadhyay', salary=1400.0}, Employee{id=120, name='Karan', salary=1500.0}, Employee{id=143, name='Carol', salary=1500.0}, Employee{id=151, name='Barun', salary=900.0}, Employee{id=198, name='Dhawan', salary=1100.0}]


//        Notes on Comparator:
//        We can do sorting based on multiple fields like shown above (IdNameSalaryComparator)
//        This won't affect original class like in case on Comparable
//        If you get a requirement of changing sorting based on other field, then you don;t have to change
//            code. instead you can define one more custom comparator and simply use it inside sort function
//        Always choose Comparator
    }
}
