package learn.lwl.leetcode;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/31
 * Time:11:19
 **/
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        int j = 0;
        OUT:
        while (true) {
            if (j < strs[0].length()) {
                char c = strs[0].charAt(j);
                for (int i = 1; i < strs.length; i++) {
                    if (j < strs[i].length() && c == strs[i].charAt(j)) {

                    } else {
                        break OUT;
                    }
                }
                buffer.append(c);
            } else {
                break;
            }
            j++;

        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        LongestCommonPrefix solution = new LongestCommonPrefix();
        System.out.println(solution.longestCommonPrefix(new String[]{
                "dog","racecar","car"
        }));
    }
}
