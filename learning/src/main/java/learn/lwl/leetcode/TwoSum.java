package learn.lwl.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/25
 * Time:09:44
 **/
public class TwoSum {
    /**
     * 暴力破解法 时间复杂度是O(n^2)
     */
    public int[] twoSumForce(int[] nums, int target) {
        int[] res = new int[2];
        if (null == nums || nums.length < 1) {
            throw new IllegalArgumentException("No two sum solution");
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int tmp = target - nums[i];
            for (int j = i + 1; j < length; j++) {
                if (tmp == nums[j]) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 借助hash表实现，优化查询 两次遍历 一次插入一次查找
     */
    public int[] twoNumTwoPassHashHelp(int[] nums, int target) {
        int[] res = new int[2];
        if (null == nums || nums.length < 1) {
            throw new IllegalArgumentException("No two sum solution");
        }
        int length = nums.length;
        Map<Integer, Integer> help = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            help.put(nums[i], i);
        }
        for (int i = 0; i < length; i++) {
            if (help.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = help.get(target - nums[i]);
                return res;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoNumOnePassHashHelp(int[] nums, int target) {
        int[] res = new int[2];
        if (null == nums || nums.length < 1) {
            throw new IllegalArgumentException("No two sum solution");
        }
        Map<Integer, Integer> help = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (help.containsKey(target - nums[i])) {
                res[0] = help.get(target - nums[i]);
                res[1] = i;
                return res;
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        TwoSum solution = new TwoSum();
        System.out.println("twoNumForce" + IntStream.of(solution.twoSumForce(nums, 9))
                .mapToObj(x -> String.valueOf(x))
                .collect(Collectors.joining(",", "[", "]")));
        System.out.println("twoNumTwoPassHashHelp" + IntStream.of(solution.twoNumTwoPassHashHelp(nums, 9))
                .mapToObj(x -> String.valueOf(x))
                .collect(Collectors.joining(",", "[", "]")));
    }
}
