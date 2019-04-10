package learn.lwl.leetcode;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/28
 * Time:19:18
 **/
public class ZigZagConvert {


    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuffer buffer = new StringBuffer();
        int len = s.length();
        int cycle = numRows + numRows - 2;
        for (int i = 1; i <= numRows; i++) {
            if (i != 1 && i != numRows) {
                int range = numRows - i;
                int cur = i - 1;
                while (cur < len) {
                    buffer.append(s.charAt(cur));
                    if (cur + 2 * range < len) {
                        buffer.append(s.charAt(cur + 2 * range));
                    }
                    cur = cur + cycle;
                }
            } else {
                //1
                int cur = i - 1;
                while (cur < len) {
                    buffer.append(s.charAt(cur));
                    cur = cur + cycle;
                }
            }
        }
        return buffer.toString();

    }

    public int reverse(int x) {
        boolean isNegative = false;
        int cur;
        if (x < 0) {
            isNegative = true;
            cur = -x;
        } else {
            cur = x;
        }
        int rev = 0;

        while (cur > 0) {
            int rem = cur % 10;
            cur /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && rem > 7)) {
                return 0;
            }

            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && rem < -8)) {
                return 0;
            }
            rev = rev * 10 + rem;

        }
        if (isNegative) {
            return -rev;
        } else {
            return rev;
        }

    }

    public static void main(String[] args) {
        ZigZagConvert zigZagConvert = new ZigZagConvert();
//        System.out.println(zigZagConvert.convert("abcdzfgh", 2));
        System.out.println(zigZagConvert.reverse(-1111330));
    }
}
