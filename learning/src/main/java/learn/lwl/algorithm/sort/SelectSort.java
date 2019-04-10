package learn.lwl.algorithm.sort;

public class SelectSort implements Sortable {
    @Override
    public void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            Comparable key = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[min].compareTo(array[j]) > 0) {
                    min = j;
                }
            }
            if (i != min) {
                array[i] = array[min];
                array[min] = key;
            }
        }
    }
}
