package comparator.and.comparable.demo.comparator;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee> {

//    Sorting based on salary (ascending)

    @Override
    public int compare(Employee e1, Employee e2) {
        if(e1.getSalary() > e2.getSalary())
            return 1; // If you want to sort in descending then return -1;
        else if(e1.getSalary() < e2.getSalary())
            return -1; // If you want to sort in descending then return 1;
        return 0;
    }
}