package learn.lwl.algorithm.exercise;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/18
 * Time:14:39
 **/
public class BinaryAdd {
    public int[] add(int[] a, int[] b) {
        int al = a.length;
        int bl = b.length;
        int n = 0;
        if (al > bl) {
            n = al;
        } else {
            n = bl;
        }
        int[] c = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            c[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            int key = (i < al ? a[i] : 0) + (i < bl ? b[i] : 0) + c[i];
            if (key > 1) {
                c[i] = key % 2;
                c[i + 1] = key / 2;
            } else {
                c[i] = key;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int a[] = {1, 1, 1, 1, 0, 1, 0, 1};
        int b[] = {1, 1, 0, 1, 0, 0, 1, 1, 1};
        System.out.println(Arrays.stream(a)
                .mapToObj(x -> String.valueOf(x))
                .collect(Collectors.joining()));
        System.out.println(Arrays.stream(b)
                .mapToObj(x -> String.valueOf(x))
                .collect(Collectors.joining()));
        System.out.println(Arrays.stream(new BinaryAdd().add(a, b))
                .mapToObj(x -> String.valueOf(x))
                .collect(Collectors.joining()));
    }
}
