package learn.lwl.leetcode;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/29
 * Time:09:54
 **/
public class Atoi {
    public int myAtoi(String str) {
        if (null == str || str.length() == 0) {
            return 0;
        }
        StringBuffer buffer = new StringBuffer();
        //提取数字
        int len = str.length();
        for (int i = 0; i < len; i++) {
            while (i < len && str.charAt(i) == ' ') {
                i++;
            }

            if (i < len && (isNumber(str.charAt(i)) || isSign(str.charAt(i)))) {
                if ('-' == str.charAt(i)) {
                    buffer.append('-');
                    i++;
                } else if ('+' == str.charAt(i)) {
                    i++;
                }
                while (i < len && isNumber(str.charAt(i))) {
                    buffer.append(str.charAt(i));
                    i++;
                }
                break;
            } else {
                return 0;
            }
        }
        if (buffer.length() == 0) {
            return 0;
        }
        int i;
        boolean isNegative = false;
        if (buffer.charAt(0) == '-') {
            i = 1;
            isNegative = true;
        } else {
            i = 0;
        }
        int sum = 0;
        int min = Integer.MIN_VALUE / 10;
        int max = Integer.MAX_VALUE / 10;
        while (i < buffer.length()) {
            int number = ctoi(buffer.charAt(i));
            if (isNegative) {
                if (sum < min || (min == sum && number > 8)) {
                    return Integer.MIN_VALUE;
                } else {
                    sum = sum * 10 - number;
                }
            } else {
                if (sum > max || (max == sum && number > 7)) {
                    return Integer.MAX_VALUE;
                } else {
                    sum = sum * 10 + number;
                }
            }
            i++;
        }
        return sum;
    }

    public boolean isNumber(char c) {
        return c >= 48 && c <= 57;
    }

    public int ctoi(char c) {
        return c - 48;
    }

    public boolean isSign(char c) {
        return '-' == c || '+' == c;

    }


    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int cur = x;
        int sum = 0;
        int rem = 0;
        while (cur > 0) {
            rem = cur % 10;
            sum = sum * 10 + rem;
            cur = cur / 10;
        }
        return sum == x;
    }

    public static void main(String[] args) {
        Atoi atoi = new Atoi();
//        System.out.println(atoi.myAtoi("-"));
//        Integer.valueOf(i);
//        System.out.println(Integer.parseInt("-91283472332"));
        System.out.println(atoi.isPalindrome(-17));
    }
}
