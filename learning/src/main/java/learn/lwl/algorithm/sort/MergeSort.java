package learn.lwl.algorithm.sort;

import java.util.Arrays;

public class MergeSort implements Sortable {
    @Override
    public void sort(Comparable[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void merge(Comparable[] array, int left, int right, int mid) {
        int i = left;
        int j = mid + 1;
        Comparable[] temp = new Comparable[right - left + 1];
        int k = 0;
        while (i <= mid && j <= right) {
            if (array[i].compareTo(array[j]) < 0) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        k = 0;
        while (left <= right) {
            array[left++] = temp[k++];
        }
    }

    private void mergeSort(Comparable[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, right, mid);
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 3, 5, 7, 9, 2, 4, 6, 11, 13};
        merge(array, 0, 9, 4);
        Arrays.stream(array).forEach(System.out::println);
    }
}
