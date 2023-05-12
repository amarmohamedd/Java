import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {6, 5, 4, 3, 2, 1};
        
        System.out.println("Are arrays equal? " + arraysEqual(arr1, arr2));
    }

    public static boolean arraysEqual(int[] arr1, int[] arr2) {
        Set<Integer> set = new HashSet<>();

        for (int num : arr1) {
            set.add(num);
        }

        for (int num : arr2) {
            if (!set.contains(num)) {
                return false;
            }
        }

        return set.size() == arr2.length;
    }
}
