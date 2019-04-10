package learn.lwl.algorithm.sort;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2018/11/12
 * Time:10:05
 **/
public class QuickSort implements Sortable {
    @Override
    public void sort(Comparable[] array) {
        dualPivotQuickSort(array, 0, array.length - 1);
    }

    /**
     * 经典快速排序：通过一趟排序将数组分为2组，然后对2组分别进行递归快速排序
     * 时间复杂度：
     * 1、递归算法主定律 T [n] = aT[n/b] + f (n)
     * 其中 a >= 1 and b > 1 是常量 并且 f (n) 是一个渐近正函数
     * 参考https://www.cnblogs.com/pugang/archive/2012/07/02/2573075.html
     * 2、最好 每次都分成2组则有
     * T(n)=2T(n/2)+f(n)其中T(n)表示n的数排序所需时间 f(n)表示将n的数分成2组所需时间 partialSort方法为n
     * 则有T(n)=2^mT(n/2^m)+mn 当n/2^m=1时 T(1)是一个常量 m=logn
     * T(n)=na+logn*n=O(logn*n)
     * 3、平均O(logn*n) 最好O(logn*n) 最坏 和分组情况有关 如果每次都是分成1 n-1 T[n]=T(1)+T[n-1]+n=O(n)
     * 空间复杂度：辅助空间O(1) +递归空间（和递归次数有关 最多 n次 最优logn次）
     * 稳定性：不稳定 顺序和上次provit位置有关
     * 排序类型：交换
     *
     * @param array
     */
    public void quickSort(Comparable[] array, int left, int rigth) {
        //一趟排序
        int index = partialSort2(array, left, rigth);
        //分组sort
        if (index > left) {
            quickSort(array, left, index - 1);
        }
        if (rigth > index) {
            quickSort(array, index + 1, rigth);
        }

    }

    /**
     * 双分双向  挖坑赋值方式
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    public int partialSort(Comparable[] array, int left, int right) {
        int index = left;
        Comparable key = array[index];
        boolean flag = true;
        while (left <= right) {
            if (flag) {
                if (key.compareTo(array[right]) > 0) {
                    swap(array, index, right);
                    index = right;
                    flag = false;
                }
                right--;
            } else {
                if (key.compareTo(array[left]) <= 0) {
                    swap(array, index, left);
                    index = left;
                    flag = true;
                }
                left++;
            }
        }
        return index;
    }

    /**
     * 两端检测
     *
     * @param array
     * @param left
     * @param right
     */
    public int partialSort1(Comparable[] array, int left, int right) {
        Comparable key = array[left];
        int i = left + 1, j = right;
        while (i <= j) {
            while (i <= j && array[i].compareTo(key) < 0) {
                i++;
            }
            while (i <= j && array[j].compareTo(key) >= 0) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
            }
        }
        swap(array, left, j);
        return j;
    }

    /**
     * 双分单端
     */
    public int partialSort2(Comparable[] array, int left, int right) {
        Comparable key = array[left];
        int i = left, j = i + 1;
        for (; j <= right; j++) {
            if (array[j].compareTo(key) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, left, i);
        return i;
    }

    /**
     * 三分单端
     */
    public void div3ScanSort(Comparable[] array, int left, int right) {
        if (left < right) {
            Comparable key = array[left];
            int i = left, j = right, k = left + 1;
            while (k <= j) {
                if (array[k].compareTo(key) < 0) {
                    swap(array, i, k);
                    i++;
                    k++;
                } else if (array[k].compareTo(key) > 0) {
                    swap(array, j, k);
                    j--;
//                    k++;//array[k]需要在判断一次是否比key小
                } else {
                    k++;
                }
            }
            div3ScanSort(array, left, i - 1);
            div3ScanSort(array, j + 1, right);
        }


    }

    /**
     * 三分双端
     */
    public void div3DeScanSort(Comparable[] array, int left, int right) {
        if (left < right) {
            Comparable key = array[left];
            int i = left, j = right, k = left + 1;
            out:
            while (k <= j) {
                if (array[k].compareTo(key) < 0) {
                    swap(array, i, k);
                    i++;
                    k++;
                } else if (array[k].compareTo(key) == 0) {
                    k++;
                } else {
                    while (array[j].compareTo(key) > 0) {
                        j--;
                        if (k > j) {
                            break out;
                        }
                    }
                    if (array[j].compareTo(key) < 0) {
                        swap(array, j, k);
                        swap(array, i, k);
                        i++;
                    } else {
                        swap(array, j, k);
                    }
                    k++;
                    j--;


                }
            }
            div3DeScanSort(array, left, i - 1);
            div3DeScanSort(array, j + 1, right);
        }
    }

    /**
     * 双轴
     */
    private void dualPivotQuickSort(Comparable[] array, int left, int right) {
        if (left < right) {
            if (array[left].compareTo(array[right]) > 0) {
                swap(array, left, right);
            }
            Comparable low = array[left];
            Comparable hight = array[right];
            int i = left, j = right, k = left + 1;
            out:
            while (k < j) {
                if (array[k].compareTo(low) < 0) {
                    swap(array, ++i, k++);
                } else if (array[k].compareTo(hight) <= 0) {
                    k++;
                } else {
                    while (array[--j].compareTo(hight) > 0) {
                        if (k >= j) {
                            break out;
                        }
                    }
                    if (array[j].compareTo(low) < 0) {
                        swap(array, j, k);
                        swap(array, ++i, k);
                    } else {
                        swap(array, j, k);
                    }
                    k++;
                }
            }
            swap(array, left, i);
            swap(array, right, j);
            dualPivotQuickSort(array, left, i - 1);
            dualPivotQuickSort(array, i + 1, j - 1);
            dualPivotQuickSort(array, j + 1, right);
        }
    }
}
