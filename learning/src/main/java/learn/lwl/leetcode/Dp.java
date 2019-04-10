package learn.lwl.leetcode;

/**
 * Created with IDEA
 *
 * @author:wenleili@sohu-inc.com
 * @Date:2019/3/28
 * @Time:20:05
 **/
public class Dp {
    public int maxSubArray(int[] nums) {
        //dp的核心是分解子问题 max=Max(max[i-1],v[i])
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        Dp dp = new Dp();
        System.out.println(dp.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
