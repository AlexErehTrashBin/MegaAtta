package sorts;

import java.util.Arrays;
import java.util.Comparator;

public class BubbleSinkSort {
    public static <T> void sort(T[] data, Comparator<T> c) {
        int n = data.length;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                if (c.compare(data[j], data[j - 1]) < 0) {
                    T tmp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] sampleArray1 = new Integer[]{
                3, 34, 45, 0, 22, 6, 11, 88
        };
        sort(sampleArray1, Comparator.naturalOrder());
        System.out.println(Arrays.toString(sampleArray1));
    }
}
