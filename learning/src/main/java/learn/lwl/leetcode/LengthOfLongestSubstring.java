package learn.lwl.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/27
 * Time:22:11
 **/
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int longestLen = 0;
        int left = 0;
        for (int i = 1; i < len + 1; i++) {
            int curLen = 1;
            int j = i - 1;
            char c = s.charAt(j);
            while (j > left && c != s.charAt(j - 1)) {
                curLen += 1;
                j--;
            }
            left = j;
            if (curLen > longestLen) {
                longestLen = curLen;
            }
        }
        return longestLen;

    }

    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                //i向j开始删除到j的位置，两端法的应用
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring solution = new LengthOfLongestSubstring();
        System.out.println(solution.lengthOfLongestSubstring1("abba"));

    }
}
