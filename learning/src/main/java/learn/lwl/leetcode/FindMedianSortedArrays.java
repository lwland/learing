package learn.lwl.leetcode;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/28
 * Time:11:38
 **/
public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int len = l1 + l2;
        int[] merge = merge(nums1, nums2);
        int mid = len / 2;
        if (len % 2 > 0) {
            return merge[mid] / 1.0;
        } else {
            return (merge[mid] + merge[mid - 1]) / 2.0;
        }

    }

    private int[] merge(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int i = 0;
        int j = 0;
        int cur = 0;
        int[] res = new int[l1 + l2];
        while (i < l1 && j < l2) {
            if (nums1[i] <= nums2[j]) {
                res[cur] = nums1[i];
                i++;
                cur++;
            } else {
                res[cur] = nums2[j];
                j++;
                cur++;
            }
        }
        while (i < l1) {
            res[cur++] = nums1[i++];
        }
        while (j < l2) {
            res[cur++] = nums2[j++];
        }
        return res;
    }

    public static void main(String[] args) {
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        int[] nums1 = {1, 3};
        int[] nums2 = {2, 4};
        System.out.println(findMedianSortedArrays.findMedianSortedArrays(nums1, nums2));
    }
}
