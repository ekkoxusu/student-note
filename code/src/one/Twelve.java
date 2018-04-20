package one;

import test.TreeNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

import static test.TreeNode.stringToTreeNode;

public class Twelve {

    public static void main(String[] args) throws  Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            System.out.println(new Solution().getMinimumDifference(root));
        }

    }
    static class Solution {
        //1.遍历所有节点取出val,排序,两两比较
        public int getMinimumDifference(TreeNode root) {
            int min = Integer.MAX_VALUE;
            Set<Integer> s = new TreeSet();
            allValSet(s, root);
            int temp = Integer.MAX_VALUE;
            for (Integer i : s) {
                int val = Math.abs(i - temp);
                if (val < min) {
                    min = val;
                }
                temp = i;
            }
            return min;
        }

        public Set<Integer> allValSet(Set<Integer> set, TreeNode root) {
            set.add(root.val);
            if (root.left != null) {
                allValSet(set, root.left);
            }
            if (root.right != null) {
                allValSet(set, root.right);
            }
            if (root == null) {
                return null;
            }
            return set;
        }
    }
}
