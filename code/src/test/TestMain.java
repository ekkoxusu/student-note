package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static test.TreeNode.stringToTreeNode;

public class TestMain {

    public static void main(String[] args) throws  Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            System.out.println(new Solution().getMinimumDifference(root));
        }

    }
    static class Solution {
        int min = Integer.MAX_VALUE;

        TreeNode prev = null;

        public int getMinimumDifference(TreeNode root) {

            if (root == null) return min;

            getMinimumDifference(root.left);

            if (prev != null)
            {
                min = Math.min(min, root.val - prev.val);
            }

            prev = root;

            getMinimumDifference(root.right);

            return min;
        }

    }
}
