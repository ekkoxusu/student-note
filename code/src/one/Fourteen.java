package one;

import test.TreeNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import static test.TreeNode.stringToTreeNode;

public class Fourteen {

    public static void main(String[] args) throws  Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode left = stringToTreeNode(line);
            TreeNode right = stringToTreeNode("[12,null,-60]");
            System.out.println(new Solution().isSameTree(right,left));
        }
//        int[][] i= {{1,1,1},{1,1,0},{1,0,1}};
//        System.out.println(new Solution().floodFill(i, 1, 1, 2));
    }
    static /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        boolean b = true;
        // 1. 每一层都将当前层放入query，然后比较
        public boolean isSameTree(TreeNode p, TreeNode q) {
            Queue<TreeNode> l = new LinkedList();
            Queue<TreeNode> r = new LinkedList();
            l.offer(p);
            r.offer(q);
            return oneLayerEquals(l,r);
        }

        public boolean oneLayerEquals(Queue<TreeNode> l , Queue<TreeNode> r){
            Queue<TreeNode> q = new LinkedList();
            Queue<TreeNode> p = new LinkedList();
            int size = l.size();
            if(size == r.size()){
                for(int i = 0; i< size; i++){
                    TreeNode leftTemp = l.poll();
                    TreeNode rightTemp = r.poll();
                    if(leftTemp != null && rightTemp != null){
                        if(leftTemp.val != rightTemp.val){
                            b = false;
                            return b;
                        }
                        q.offer(leftTemp.left);
                        q.offer(leftTemp.right);
                        p.offer(rightTemp.left);
                        p.offer(rightTemp.right);
                    }else if(leftTemp == null && rightTemp == null){
                        continue;
                    }else{
                        b = false;
                        return b;
                    }

                }
            }else{
                b = false;
                return b;
            }
            if(q.size()>0) oneLayerEquals(q,p);
            return b;
        }
    }
}
