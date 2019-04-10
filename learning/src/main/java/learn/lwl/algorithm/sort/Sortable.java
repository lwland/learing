package learn.lwl.algorithm.sort;

public interface Sortable<T extends Comparable> {
    void sort(T[] array);

    default void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}