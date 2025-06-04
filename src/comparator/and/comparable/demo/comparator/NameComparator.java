package comparator.and.comparable.demo.comparator;

import java.util.Comparator;

public class NameComparator implements Comparator<Employee> {

//    Sorting based on names (ascending)

    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getName().compareTo(e2.getName());
    }
}