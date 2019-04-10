package learn.lwl.leetcode;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/1/31
 * Time:09:48
 **/
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        if (null == height || height.length < 2) {
            return 0;
        }
        int i = 0, j = height.length - 1;
        int max = 0;
        int container = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                container = (j - i) * height[i];
                i++;
            } else {
                container = (j - i) * height[j];
                j--;
            }
            if (container > max) {
                max = container;
            }

        }
        return max;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        ContainerWithMostWater solution = new ContainerWithMostWater();
        System.out.println(solution.maxArea(height));
    }
}
