package comparator.and.comparable.demo.comparator;

import java.util.Comparator;

public class IdNameSalaryComparator implements Comparator<Employee> {

//Example of sorting on multiple fields
//    if id's are equal then sort based on names
//    if names also same then sort based on salaries.

    @Override
    public int compare(Employee e1, Employee e2) {
        if (e1.getId() > e2.getId())
            return 1; // If you want to sort in descending then return -1;
        else if (e1.getId() < e2.getId())
            return -1; // If you want to sort in descending then return 1;
        else {
//            if id's are same then we enter this else condition
//            calculating which is greater for names
//            if diff is not 0 then names are different we can directly return diff
//            if diff is 0, then we should sort based on salaries.
            int diff = e1.getName().compareTo(e2.getName());
            if (diff == 0) {
//                sorting based on salaries.
                if (e1.getSalary() > e2.getSalary())
                    return 1;
                else if (e1.getSalary() < e2.getSalary())
                    return -1;
                return 0;
            } else
                return diff;
        }
    }
}