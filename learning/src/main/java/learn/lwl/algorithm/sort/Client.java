package learn.lwl.algorithm.sort;

import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        Integer[] array = {7, 4, 18, 3, 9,3,3,3, 8, 17,2,22,19,5};
//        Sortable bubbleSort=new BubbleSort();
//        bubbleSort.sort(array);
//        Sortable sortable = new MergeSort();
//        sortable.sort(array);
        Sortable quickSort = new QuickSort();
        quickSort.sort(array);
        Arrays.stream(array).forEach(x -> System.out.print(x + ","));
    }
}
