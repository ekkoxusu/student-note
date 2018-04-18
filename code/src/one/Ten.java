package one;

import java.util.LinkedList;
import java.util.Queue;

public class Ten {

    public static void main(String[] args) {
        int[] i = {-10,-3,0,5,9};
        System.out.println(treeNodeToString(new Solution().sortedArrayToBST(i)));
    }
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    static class Solution {
        //使用二分查找法解决问题，将其分为3个整体3个整体看待问题
        public TreeNode sortedArrayToBST(int[] nums) {
            //如果空间为0则返回null
            if(nums.length ==0){
                return null;
            }
            //如果空间为1返回第一个
            if(nums.length == 1){
                return new TreeNode(nums[0]);
            }
            int start = 0;
            int end = nums.length-1;
            int mid = (start+end)/2;
            TreeNode temp = new TreeNode(nums[mid]);
            int left = start + mid;
            int right = end - mid;
            //如果左边右边有大于0 则拆分
            if(left > 0 ){
                int[] leftNums = new int[left];
                System.arraycopy(nums,0, leftNums,0,leftNums.length);
                temp.left = sortedArrayToBST(leftNums);
            }
            if(right > 0){
                int[] rightNums = new int[right];
                System.arraycopy(nums,mid+1, rightNums,0,rightNums.length);
                temp.right = sortedArrayToBST(rightNums);
            }
            return temp;
        }
    }
    static void printArray(Object[]a){
        for (Object o : a) {
            System.out.println(o);
        }
    }
     public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }
}
