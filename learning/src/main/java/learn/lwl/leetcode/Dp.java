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

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //有障碍的位置只为0
        int m = obstacleGrid[0].length;//列
        int n = obstacleGrid.length;//行
        int[][] dp = new int[n][m];
        if (obstacleGrid[0][0] == 1) {
            dp[0][0] = 0;
        } else {
            dp[0][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[0][i] == 1) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = dp[0][i - 1];
            }

        }

        for (int i = 1; i < n; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int dp[][] = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }

        }
        return dp[m - 1][n - 1];
    }

    public int min(int x, int y) {
        return x < y ? x : y;
    }

    public boolean isMatch(String s, String p) {
        return false;
    }

    public static void main(String[] args) {
        Dp dp = new Dp();
//        System.out.println(dp.uniquePaths(7, 3));
//        int[][] arr = {{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
//        System.out.println(dp.uniquePathsWithObstacles(arr));
        int[][] pathNum = {{1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};
        int[][] pathNum2 = {{1, 2, 5}, {3, 2, 1}};
        System.out.println(dp.minPathSum(pathNum2));
    }
}
