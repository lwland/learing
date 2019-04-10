package learn.lwl.leetcode;

import java.util.*;

/**
 * Created with IDEA
 * author:wenleili@sohu-inc.com
 * Date:2019/3/5
 * Time:19:34
 **/
public class Tree {
    public static List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root == null) {
                TreeNode cur = stack.pop();
                res.add(cur.val);
                root = cur.right;
            } else {
                stack.push(root);
                root = root.left;
            }
        }
        return res;
    }

    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return Collections.EMPTY_LIST;
        }
        return generateTrees(1, n);
    }

    public static List<TreeNode> generateTrees(int begin, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (begin > end) {
            list.add(null);
            return list;
        } else if (begin == end) {
            list.add(new TreeNode(begin));
            return list;
        }
        for (int i = begin; i <= end; i++) {
            List<TreeNode> left = generateTrees(begin, i - 1);
            List<TreeNode> right = generateTrees(i + 1, end);
            for (int k = 0; k < left.size(); k++) {
                TreeNode lnLeft = left.get(k);
                for (int j = 0; j < right.size(); j++) {
                    TreeNode node = new TreeNode(i);
                    node.left = lnLeft;
                    node.right = right.get(j);
                    list.add(node);
                }
            }
        }
        return list;
    }

    public static int numTrees(int n) {
        if (n == 0) {
            return 0;
        }
        return numTrees(1, n);
    }

    public static int numTrees(int begin, int end) {
        if (begin >= end) {
            return 1;
        }
        int num = 0;
        for (int i = begin; i <= end; i++) {
            int left = numTrees(begin, i - 1);
            int right = numTrees(i + 1, end);
            num += left * right;
        }
        return num;
    }

    public static int numTreesDp(int n) {
        if (n < 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            int r = 0;
            for (int j = 0; j < i; j++) {
                r += dp[j] * dp[i - 1 - j];
            }
            dp[i] = r;
        }
        return dp[n];

    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean isLeftValid = isValidBST(root.left);
        boolean isRightValid = isValidBST(root.right);
        long val = (long) root.val;
        boolean isSelfValid = val > max(root.left) && val < min(root.right);
        return isLeftValid && isSelfValid && isRightValid;

    }

    public static long min(TreeNode root) {
        long min = Long.MAX_VALUE;
        while (root != null) {
            min = root.val;
            root = root.left;

        }
        return min;
    }

    public static long max(TreeNode root) {
        long max = Integer.MIN_VALUE;
        while (root != null) {
            max = root.val;
            root = root.right;
        }
        return max;
    }

    static TreeNode firstElement = null;
    static TreeNode secodeElement = null;
    static TreeNode preElement = new TreeNode(Integer.MIN_VALUE);

    public static void recoverTree(TreeNode root) {
        //

        inorder(root);
        int temp = firstElement.val;
        firstElement.val = secodeElement.val;
        secodeElement.val = temp;
    }

    public static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (firstElement == null && preElement.val >= root.val) {
            firstElement = preElement;
        }
        if (firstElement != null && preElement.val >= root.val) {
            secodeElement = root;
        }
        preElement = root;
        inorder(root.right);

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return inorder(p, q);
    }

    private static boolean inorder(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null) {
            boolean isLeftSame = inorder(p.left, q.left);
            boolean isRightSame = inorder(p.right, q.right);
            return isLeftSame && isRightSame && p.val == q.val;
        }
        return false;
    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);

    }

    public boolean isMirror(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 != null && root2 != null && root1.val == root2.val) {
            return isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
        }
        return false;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> nodes = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int curNum = nodes.size();
            for (int i = 0; i < curNum; i++) {
                TreeNode now = nodes.poll();
                if (now == null) {
                    continue;
                } else {
                    levelList.add(now.val);
                    if (now.left != null) {
                        nodes.offer(now.left);

                    }
                    if (now.right != null) {
                        nodes.offer(now.right);

                    }
                }
            }
            res.add(levelList);
        }
        return res;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> stack = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        stack.offer(root);
        int level = 1;
        while (!stack.isEmpty()) {
            int curSize = stack.size();
            List<Integer> curLevel = new ArrayList<>();
            List<TreeNode> curList = new ArrayList<>();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = stack.poll();
                if (node != null) {
                    curList.add(node);
                    if (node.left != null) {
                        stack.offer(node.left);
                    }
                    if (node.right != null) {
                        stack.offer(node.right);
                    }
                }

            }
            if (level % 2 > 0) {
                for (int i = 0; i < curSize; i++) {
                    TreeNode node = curList.get(i);
                    curLevel.add(node.val);

                }
            } else {
                for (int i = curSize - 1; i >= 0; i--) {
                    TreeNode node = curList.get(i);
                    curLevel.add(node.val);
                }
            }
            res.add(curLevel);
            level++;
        }
        return res;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Math.max(left, right);

    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd) {
        if (preBegin > preEnd || inBegin > inEnd) {
            return null;
        }
        if (preBegin == preEnd && inBegin == inEnd) {
            return new TreeNode(preorder[preBegin]);
        }
        int rootVal = preorder[preBegin];
        int inRootIndex = findIndex(inorder, inBegin, inEnd, rootVal);
        TreeNode left = buildTree(preorder, preBegin + 1, preBegin + inRootIndex - inBegin, inorder, inBegin, inRootIndex - 1);
        TreeNode right = buildTree(preorder, preBegin + inRootIndex - inBegin + 1, preEnd, inorder, inRootIndex + 1, inEnd);
        TreeNode root = new TreeNode(rootVal);
        root.left = left;
        root.right = right;
        return root;
    }

    public TreeNode buildTreeInAndPos(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        return buildTreeInAndPos(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);

    }

    public TreeNode buildTreeInAndPos(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd) {
        if (inBegin > inEnd || postBegin > postEnd) {
            return null;
        }
        if (inBegin == inEnd && postBegin == postEnd) {
            return new TreeNode(inorder[inBegin]);
        }
        int rootVal = postorder[postEnd];
        int rootIndex = findIndex(inorder, inBegin, inEnd, rootVal);
        TreeNode root = new TreeNode(rootVal);
        TreeNode left = buildTreeInAndPos(inorder, inBegin, rootIndex - 1, postorder, postBegin, postBegin + rootIndex - inBegin - 1);
        TreeNode right = buildTreeInAndPos(inorder, rootIndex + 1, inEnd, postorder, postBegin + rootIndex - inBegin, postEnd - 1);
        root.right = right;
        root.left = left;
        return root;
    }

    private int findIndex(int[] inorder, int inBegin, int inEnd, int rootVal) {
        for (int i = inBegin; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                return i;
            }
        }
        return 0;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return Collections.EMPTY_LIST;
        }
        levelOrderBottom(root, res, 0);
        Collections.reverse(res);
        return res;

    }

    private void levelOrderBottom(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (level >= res.size()) {
            List<Integer> subList = new ArrayList<>();

            res.add(subList);
        }
        levelOrderBottom(root.left, res, level + 1);
        levelOrderBottom(root.right, res, level + 1);
        res.get(level).add(root.val);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int begin, int end) {
        if (begin > end) {
            return null;
        }
        int mid = (end + begin) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, begin, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }


    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return (Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2)
                && isBalanced(root.left)
                && isBalanced(root.right);

    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root != null && root.left == null && root.right == null && sum - root.val == 0) {
            return true;
        }
        boolean left = hasPathSum(root.left, sum - root.val);
        boolean right = hasPathSum(root.right, sum - root.val);
        return left || right;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        List<Integer> sub = new ArrayList<>();
        pathSum(root, sum, res, sub);
        return res;
    }

    public void pathSum(TreeNode root, int sum, List<List<Integer>> res, List<Integer> sub) {
        if (root == null) {
            return;
        }
        sub.add(root.val);
        if (root != null && root.right == null && root.left == null && sum - root.val == 0) {
            res.add(new LinkedList<>(sub));
            sub.remove(sub.size() - 1);
            return;
        } else {
            pathSum(root.left, sum - root.val, res, sub);

            pathSum(root.right, sum - root.val, res, sub);
        }
        sub.remove(sub.size() - 1);
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        flatten(left);
        flatten(right);
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) {
            cur = root.right;
        }
        cur.right = right;

    }

    public Node connect(Node root) {
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.right = node5;
//        System.out.println(inorderTraversal(root));
//        System.out.println(generateTrees(3));
//        System.out.println(numTreesDp(3));
//        System.out.println(isValidBST(new TreeNode(2147483647)));
//        recoverTree(root);
//        System.out.println(levelOrder(root));
//        System.out.println(zigzagLevelOrder(root));
//        System.out.println(maxDepth(root));
        Tree tree = new Tree();
//        TreeNode node = tree.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
//        TreeNode node = tree.buildTreeInAndPos(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
//        System.out.println(tree.levelOrderBottom(root));
        System.out.println(tree.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
