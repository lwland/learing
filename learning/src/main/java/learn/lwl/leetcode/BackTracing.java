package learn.lwl.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/2/14
 * Time:10:41
 **/
public class BackTracing {
    public List<String> letterCombinations(String digits) {

        Map<Character, String> letters = new HashMap<>();
        List<String> result = new ArrayList<>();
        letters.put('2', "abc");
        letters.put('3', "def");
        letters.put('4', "ghi");
        letters.put('5', "jkl");
        letters.put('6', "mno");
        letters.put('7', "pqs");
        letters.put('8', "tuv");
        letters.put('9', "wxyz");
        List<Character> tmp = new ArrayList<>();
        letterCombinations(digits, 0, digits.length(), letters, result, tmp);
        return result;
    }

    public void letterCombinations(String digits, int index, int length, Map<Character, String> letters, List<String> result, List<Character> tmp) {
        if (index >= length) {
            result.add(tmp.stream().map(x -> x.toString()).collect(Collectors.joining("")));
        } else {
            String letter = letters.get(digits.charAt(index));
            for (int j = 0; j < letter.length(); j++) {
                tmp.add(letter.charAt(j));
                letterCombinations(digits, index + 1, length, letters, result, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();
        generateParenthesis(n, "", 0, 0, result);
        return result;
    }

    private void generateParenthesis(int n, String tmp, int open, int close, List<String> result) {
        if (tmp.length() == n * 2) {
            result.add(tmp);
        } else {
            if (open < n) {
                generateParenthesis(n, tmp + "(", open + 1, close, result);
            }
            if (close < open) {
                generateParenthesis(n, tmp + ")", open, close + 1, result);
            }
        }
    }

    public static void main(String[] args) {
        BackTracing solution = new BackTracing();
//        System.out.println(solution.letterCombinations(""));
        System.out.println(solution.generateParenthesis(3));
    }
}
