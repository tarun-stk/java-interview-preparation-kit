package collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HiddenFeaturesOfCollections {
    public static void main(String[] args) {
        nCopiesDemo(10, "Value");
        findFrequencyDemo(1, Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 54, 2));
        singletonDemo();
        rotateDemo();
    }

    private static void rotateDemo() {
        /*This rotate can be useful when you want to rotate the list, either from left/right
        * give positive value, it rotates from right, give negative it rotates from left
        * give out of bounds value it find index by cyclically*/
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Collections.rotate(list, 4);
//        [7,8,9,10,1,2,3,4,5,6]
        System.out.println("List rotated from right: " + list);
        Collections.rotate(list1, -3);
//        [4, 5, 6, 7, 8, 9, 10, 1, 2, 3]
        System.out.println("List rotated from left: " + list1);

        Collections.rotate(list, list.size());
//        [7, 8, 9, 10, 1, 2, 3, 4, 5, 6]  -> no change
        System.out.println(list);
//      [2, 3, 4, 5, 6, 7, 8, 9, 10, 1]  -> cyclically find suitable index
        Collections.rotate(list, -15);
        System.out.println("Collections.rotate(list, -15): " + list);
        Collections.rotate(list, 0);
//         [2, 3, 4, 5, 6, 7, 8, 9, 10, 1]  -> no change
        System.out.println("Collections.rotate(list, 0): " + list);
    }

    private static void singletonDemo() {
        /*When you need only one object to be stored in collection
         * then use below method, it return immutable object meaning that you cannot modify */
        List<String> singletonList = Collections.singletonList("Tarun Stk");
        try {
            singletonList.set(0, "Tarun Kumar Stk");
        } catch (UnsupportedOperationException operationException) {
            System.out.println("Operation not allowed, cannot modify immutable object");
        }
    }

    private static void findFrequencyDemo(int element, List<Integer> list) {
        /*Instead of using traditional for loop to count freq, use below method*/
        int frequency = Collections.frequency(list, element);
        System.out.println("Frequency: " + frequency);
    }

    private static String nCopiesDemo(int frequency, Object value) {
        /*Used to create dummy data n number of times, instead of using traditional for loop
         * to add into a list, use below method*/
        List<Object> list = Collections.nCopies(frequency, value);
        System.out.println(list);
        return list.toString();
    }
}
