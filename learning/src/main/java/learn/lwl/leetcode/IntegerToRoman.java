package learn.lwl.leetcode;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/31
 * Time:10:32
 **/
public class IntegerToRoman {
    public String intToRoman(int num) {
        StringBuffer buffer = new StringBuffer();
        while (num > 0) {
            if (num >= 1000) {
                buffer.append("M");
                num = num - 1000;
            } else if (num >= 900) {
                buffer.append("CM");
                num = num - 900;
            } else if (num >= 500) {
                buffer.append("D");
                num = num - 500;
            } else if (num >= 400) {
                buffer.append("CD");
                num = num - 400;
            } else if (num >= 100) {
                buffer.append("C");
                num = num - 100;
            } else if (num >= 90) {
                buffer.append("XC");
                num = num - 90;
            } else if (num >= 50) {
                buffer.append("L");
                num = num - 50;
            } else if (num >= 40) {
                buffer.append("XL");
                num = num - 40;
            } else if (num >= 10) {
                buffer.append("X");
                num = num - 10;
            } else if (num >= 9) {
                buffer.append("IX");
                num = num - 9;
            } else if (num >= 5) {
                buffer.append("V");
                num = num - 5;
            } else if (num >= 4) {
                buffer.append("IV");
                num = num - 4;
            } else if (num >= 1) {
                buffer.append("I");
                num = num - 1;
            }
        }
        return buffer.toString();
    }

    public int romanToInt(String s) {
        int sum = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == 'M') {
                sum = sum + 1000;
                i++;
            } else if (s.charAt(i) == 'D') {
                sum = sum + 500;
                i++;
            } else if (s.charAt(i) == 'C') {
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) == 'M') {
                        sum = sum + 900;
                        i = i + 2;
                    } else if (s.charAt(i + 1) == 'D') {
                        sum = sum + 400;
                        i = i + 2;
                    } else {
                        sum = sum + 100;
                        i++;
                    }
                } else {
                    sum = sum + 100;
                    i++;
                }
            } else if (s.charAt(i) == 'L') {
                sum = sum + 50;
                i++;
            } else if (s.charAt(i) == 'X') {
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) == 'C') {
                        sum = sum + 90;
                        i = i + 2;
                    } else if (s.charAt(i + 1) == 'L') {
                        sum = sum + 40;
                        i = i + 2;
                    } else {
                        sum = sum + 10;
                        i++;
                    }
                } else {
                    sum = sum + 10;
                    i++;
                }
            } else if (s.charAt(i) == 'V') {
                sum = sum + 5;
                i++;
            } else if (s.charAt(i) == 'I') {
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) == 'X') {
                        sum = sum + 9;
                        i = i + 2;
                    } else if (s.charAt(i + 1) == 'V') {
                        sum = sum + 4;
                        i = i + 2;
                    } else {
                        sum = sum + 1;
                        i++;
                    }
                } else {
                    sum = sum + 1;
                    i++;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        IntegerToRoman solution = new IntegerToRoman();
        System.out.println(solution.intToRoman(1994));
        System.out.println(solution.romanToInt("MCMXCIV"));
    }
}
