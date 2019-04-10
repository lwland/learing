package learn.lwl.algorithm.sort;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2018/11/12
 * Time:14:29
 **/
public class BubbleSort implements Sortable {

    @Override
    public void sort(Comparable[] array) {
        bubbleSort(array);

    }

    /**
     * 冒泡排序：每趟把最大的值排到最后 i控制需要多少趟和比较次数，j控制交换。
     * 时间复杂度： (n-1)+(n-2)+…1 平均O(n^2) 最好O(n^2) 最坏O(n^2)
     * 空间复杂度： O(1)
     * 稳定性：稳定
     * 排序类型：交换排序
     *
     * @param array
     */
    public void bubbleSort(Comparable[] array) {
        int len = array.length;
        if (len == 1) {
            return;
        }
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
    }
}
