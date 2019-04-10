package learn.lwl.algorithm.sort;

public class InsertSort implements Sortable {
    @Override
    public void sort(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            Comparable key = array[i];
            int j = i - 1;
            while (j > 0 && key.compareTo(array[j]) < 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
