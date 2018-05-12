package one;

public class Sixteen {

    public static void main(String[] args) throws  Exception {

        System.out.println(new Solution().isPalindrome(111));
    }

    static class Solution {
        public boolean isPalindrome(int x) {
            if(x<0){
                return false;
            }
            if(x<10){
                return true;
            }
            int leng = 1;
            //算出长度
            while(x/leng >=10){
                leng *= 10;
            }
            while(x != 0){
                //计算头尾是否一致
                int start = x/leng;
                int end = x%10;
                if(start != end){
                    return false;
                }else{
                    //去掉头尾 并且长度减少2个单位
                    x = x%leng/10;
                    leng /= 100;
                }
            }
            return true;
        }
    }
}
