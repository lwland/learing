package learn.lwl.leetcode;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/30
 * Time:09:35
 **/
public class RegularExpress {
    public boolean isMatch(String s, String p) {
        //* .
        if (null == p || null == p) {
            return false;
        }
        if ("" == s) {
            return isEmpty(p);
        }
        //i:string traverse cursor;
        //j:pattern traverse cursor
        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {

            if (s.charAt(i) == p.charAt(j)) {
                //match single character
                i++;
                j++;
            } else if (isAnySingle(p.charAt(j))) {
                //match any single character
                i++;
                j++;
            } else if (isMultiPreceding(p.charAt(j))) {
                //match multi
                if (j - 1 >= 0 && (p.charAt(j - 1) == s.charAt(i) || isAnySingle(p.charAt(j - 1)))) {
//                    if (j + 1 < p.length() && p.charAt(j + 1) == s.charAt(i)) {
//                        i++;
//                    }
                    i++;

                } else if (j + 1 < p.length() && (p.charAt(j + 1) == s.charAt(i) || isAnySingle(p.charAt(j + 1)))) {
                    j = j + 2;
                    i++;
                } else {
                    return false;
                }

            } else if (j + 1 < p.length() && isMultiPreceding(p.charAt(j + 1))) {
                j = j + 2;
            } else {
                return false;
            }
        }
        if (i == s.length()) {
            if (j == p.length()) {
                return true;
            } else {
                return isEmpty(p.substring(j, p.length()));
            }
        }
        return false;
    }

    public boolean isMultiPreceding(char c) {
        return '*' == c;
    }

    public boolean isAnySingle(char c) {
        return '.' == c;
    }

    public boolean isEmpty(String p) {
        if ("".equals(p) || "*".equals(p)) {
            return true;
        } else if (p.length() == 2 && isMultiPreceding(p.charAt(1))) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        RegularExpress solution = new RegularExpress();
        String s = "aaab";
        String p = "a*ab";
        System.out.println(solution.isMatch(s, p));
//        String abc = "aaa";
//        abc.matches("a*a");
    }

}
