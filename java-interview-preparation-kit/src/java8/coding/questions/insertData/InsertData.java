package java8.coding.questions.insertData;

import java8.coding.questions.employee.Employee;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class InsertData {
	
	public static List<Employee> loadData() {
		List<Employee> list = new LinkedList<>();
		list.add(new Employee(1, 70000, 'M', 26, "Michael", "scott", "IT", Arrays.asList("9989998999", "8899889988"), "Hyderabad"));
		list.add(new Employee(2, 100000, 'F', 32, "Mona", "Lisa", "HR", Arrays.asList("9989998999", "8899889988"), "Pune"));
		list.add(new Employee(3, 68000, 'M', 56, "Jimping", "Xi", "Support", Arrays.asList("9989998999", "8899889988"), "Hyderabad"));
		list.add(new Employee(4, 65000, 'F', 21, "Kate", "Perry", "IT", Arrays.asList("9989998999", "8899889988"), "Chennai"));
		list.add(new Employee(5, 85000, 'M', 28, "David", "Josh", "Sales", Arrays.asList("9989998999", "8899889988"), "Hyderabad"));
		list.add(new Employee(6, 1000, 'F', 60, "Alina", "Crus", "HR", Arrays.asList("9989998999", "8899889988"), "Hyderabad"));
		list.add(new Employee(7, 78000, 'F', 19, "Thomas", "Cook", "IT", Arrays.asList("9989998999", "8899889988"), "Pune"));
		list.add(new Employee(8, 99000, 'F', 20, "Debalina", "Apple", "Support", Arrays.asList("9989998999", "8899889988"), "Hyderabad"));
		return list;
	}

}
