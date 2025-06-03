package comparator.and.comparable.demo.comparable;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Using Comparable affects original class, as you see the class itself
//must implement Comparable interface which actually effecting class signature
//And in case if you need to sort data by using names, then you've to come back to this class
//and change code in compareTo method (hardcoding)
public class Student implements Comparable<Student> {


    //    Sorting based on id's (ascending)
    @Override
    public int compareTo(Student s) {
        if (this.id > s.getId())
            return 1;
        else if (this.id < s.getId())
            return -1;
        return 0;
    }

    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {

        List<Student> students = Stream.of(
                        new Student(101, "Arun"),
                        new Student(110, "Tejas"),
                        new Student(104, "Mishra"),
                        new Student(109, "Walker"),
                        new Student(108, "Alena"))
                .collect(Collectors.toList());

        System.out.println("Before sorting by id's: ");
        System.out.println(students);
        Collections.sort(students);
//        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 23423, 32,423));
//        Collections.sort(list, Collections.reverseOrder());
//        System.out.println(list);
        System.out.println("After sorting by id's: ");
        System.out.println(students);

//        Notes on comparable:
//        This always provides single sorting sequence.
//        If in case you want sort based on other field, then you have to manually change code
//            inside compareTo method with that particular field.
//        It also affects original class, as you can see class signature must implement Comparable.
//        Always prefer Comparator over Comparable if you want to sort based on multiple fields.

//        Output:
//        Before sorting by id's:
//                [Student{id=101, name='Arun'}, Student{id=110, name='Tejas'}, Student{id=104, name='Mishra'}, Student{id=109, name='Walker'}, Student{id=108, name='Alena'}]
//        After sorting by id's:
//                [Student{id=101, name='Arun'}, Student{id=104, name='Mishra'}, Student{id=108, name='Alena'}, Student{id=109, name='Walker'}, Student{id=110, name='Tejas'}]

    }

}
