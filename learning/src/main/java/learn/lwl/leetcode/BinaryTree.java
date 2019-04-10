package learn.lwl.leetcode;

import java.util.TreeSet;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/3/4
 * Time:19:59
 **/
public class BinaryTree {

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> tree = new TreeSet();
        for (int i = 0; i < nums.length; i++) {
            Integer x = tree.floor(nums[i] + t);
            if (x != null && x >= nums[i] - t) {
                return true;
            }
            tree.add(nums[i]);
            if (tree.size() >k) {
                tree.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,5,9,1,5,9};
        System.out.println(containsNearbyAlmostDuplicate(nums, 2, 3));
    }

}
