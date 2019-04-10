package learn.lwl.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/25
 * Time:11:36
 **/
public class ThreeNum {
    public List<List<Integer>> threeNumForce(int[] nums, int traget) {
        List<List<Integer>> list = new ArrayList<>();
        if (null == nums || nums.length < 2) {
            return list;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < length - 1; j++) {
                if (j - 1 > i && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = length - 1; k > j; k--) {
                    if (k < length - 1 && nums[k] == nums[k + 1]) {
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[k] == traget) {
                        List<Integer> tmp = Arrays.asList(nums[i], nums[j], nums[k]);
                        list.add(tmp);
                    }
                }
            }
        }
        return list;
    }

    public List<List<Integer>> threeNumTwo(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (null == nums || nums.length < 2) {
            return list;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }

            }
        }
        return list;

    }

    public int threeSumClosest(int[] nums, int target) {
        if (null == nums || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int length = nums.length;
        int closedNum = 0;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < distance) {
                    closedNum = sum;
                    distance = Math.abs(target - sum);
                }
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closedNum;


    }

    public boolean isExist(List<List<Integer>> container, List<Integer> con) {
        if (null == container || container.size() == 0) {
            return false;
        }
        int sum = 0;
        for (Integer num : con) {
            for (List<Integer> list : container) {
                if (list.contains(num)) {
                    sum += 1;
                }
            }
            if (sum > 2) {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        ThreeNum solution = new ThreeNum();
//        int[] nums = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
//        System.out.println("[");
//        solution.threeNumTwo(nums).stream()
//                .forEach(x -> System.out.println(" " + x));
//        System.out.println("]");
        int[] nums2 = {1, 1, -1, -1, 3};
        System.out.println(solution.threeSumClosest(nums2, -1));
    }
}

