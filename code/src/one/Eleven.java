package one;

public class Eleven {

    public static void main(String[] args) {
        System.out.println(new Solution().hasAlternatingBits(5));
    }
    static class Solution {
        //1.转化为二进制字符串 如果触雷则false
        //2.使用二进制算法 见答案-答案才是正解
        public boolean hasAlternatingBits(int n) {
            //1 10 101 1010
            //111  10 01 10 01|10 11
            char[] val = Integer.toBinaryString(n).toCharArray();
            char temp = val[0];
            for(int i=1;i<val.length;i++){
                if(temp == val[i]){
                    return false;
                }
                temp = val[i];
            }
            return true;
        }
    }
}
