package learn.lwl.leetcode;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2018/10/2
 * Time:22:01
 **/
public class Lcs{
    public static int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }
        int max = 1;
        int begin = 0;
        int end = 0;
        for (int i = 1; i < s.length(); i++) {
            int tmp = dupChar(begin, end, s, s.charAt(i));
            if (tmp < 0) {
                end++;
            } else {
                begin = tmp + 1;
                end = i;
            }
            if (end - begin + 1 > max) {
                max = end - begin + 1;
            }
        }
        return max;
    }

    private static int dupChar(int begin, int end, String s, char c) {
        for (int i = begin; i <= end; i++) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] res = new int[len1 + len2];
        int i = 0, j = 0, cur = 0;
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                res[cur++] = nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                res[cur++] = nums2[j++];
            } else {
                res[cur++] = nums1[i++];
                res[cur++] = nums2[j++];
            }

        }
        if (i < len1) {
            for (int index = i; index < len1; index++) {
                res[cur++] = nums1[index];
            }
        }
        if (j < len2) {
            for (int index = j; index < len2; index++) {
                res[cur++] = nums2[index];
            }
        }
        int w = (len1 + len2) / 2;
        if ((len1 + len2) % 2 == 0) {
            return (res[w - 1] + res[w]) / 2.0;
        } else {
            return res[w];
        }


    }

    public String longestPalindrome(String s) {
        int len = s.length();
        int max = 0;
        //babad
        for (int i = 0; i < len; i++) {

        }
        return "";

    }

    private int isPalindRome(String s, int begin, int end) {
        // 4 0 1 2 3    mid 1,2
        int strLen = s.length();
        int len = begin - end + 1;
        int mid = len / 2;
        for (int i = begin; i < mid; i++) {
            if (s.charAt(i) != s.charAt(strLen - 1 - i)) {
                return -1;
            }
        }
        return len;

    }

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("dedf"));
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3}));
    }
}