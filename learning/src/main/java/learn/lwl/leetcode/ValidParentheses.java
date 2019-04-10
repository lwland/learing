package learn.lwl.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/2/15
 * Time:13:40
 **/
public class ValidParentheses {
    public boolean isValid(String s) {
        Map<Character, Character> maps = new HashMap<>();
        maps.put(')', '(');
        maps.put(']', '[');
        maps.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (maps.containsKey(c)) {
                char top = stack.empty() ? '#' : stack.pop();
                if (top != maps.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();
        System.out.println(solution.isValid("{"));
    }
}
