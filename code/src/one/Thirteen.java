package one;

import test.TreeNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static test.TreeNode.stringToTreeNode;

public class Thirteen {

    public static void main(String[] args) throws  Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            System.out.println(new Solution().averageOfLevels(root));
        }
        Queue<TreeNode> queue = new LinkedList<>();
    }
    static class Solution {
        public List<Double> averageOfLevels(TreeNode root) {
            //每一层的平均值 left right root root.left root.right ……
            Map<Integer,List<Integer>> map = new HashMap<>();
            tierIntArray(0,map,root);

            for(Map.Entry<Integer,List<Integer>> e :map.entrySet()){
                System.out.print(e.getKey()+":"+e.getValue());
            }
            return null;
        }

        public void tierIntArray(int i,Map<Integer,List<Integer>> map,TreeNode root){
            if(root == null){
                return;
            }
            List<Integer> list = map.get(i);
            if(list == null){
                list = new ArrayList();
                map.put(i,list);
            }
            list.add(root.val);
            //记录左边
            tierIntArray(++i,map,root.left);
            //记录右边
            tierIntArray(i,map,root.right);
        }

    }
}
