package learn.lwl.leetcode;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/28
 * Time:15:23
 **/
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        int max = 0;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i < s.length(); i++) {
            int left = 0, right = 0;
            int cur = i;
            left = cur - 1;
            cur = cur + 1;
            while (cur < len && s.charAt(cur) == s.charAt(cur - 1)) {
                cur++;
            }
            right = cur;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            int curLen = right - left - 1;
            if (len > max) {
                max = curLen;
                maxLeft = left;
                maxRight = right;
            }
        }
        return s.substring(maxLeft + 1, maxRight);

    }


    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome("dddddddddd"));

    }
}
