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
//            System.out.println(new Solution().averageOfLevels(root));
        }
    }
    static class Solution {


    }
}
