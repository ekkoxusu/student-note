package one;

public class Fifteen {

    public static void main(String[] args) throws  Exception {

        System.out.println(new Solution().detectCapitalUse("FlaG"));
    }
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    static class Solution {
        public boolean detectCapitalUse(String word) {
            boolean firstLetter = true;
            int big = 0;
            int small = 0;
            char[] val = word.toCharArray();
            //判断首字母是大写还是小写
            if('a' <= val[0] && val[0] >= 'z'){
                firstLetter = false;
            }
            for(int i = 0 ; i<val.length; i++){
                //首字母大写
                if(firstLetter){
                    if('a' <= val[i] && val[i] >= 'z'){
                        small++;
                    }else{
                        big++;
                    }
                }else{
                    if('A'<= val[i] && val[i] >= 'Z'){
                        return false;
                    }
                }
            }
            if(firstLetter){
                if(big==val.length || small+1==val.length){
                    return true;
                }
            }
            return false;
        }
    }
}
