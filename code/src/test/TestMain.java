package test;

public class TestMain {

    public static void main(String[] args) {
        int[][] ints = {{18},{66}} ;
        System.out.println(new Solution().isToeplitzMatrix(ints));
    }

    static class Solution {
        public boolean isToeplitzMatrix(int[][] matrix) {
            for(int i = 0;i<matrix.length;i++){
                if(i==0){
                    for(int j = 0;j<matrix[i].length;j++){
                        if(noOneMatrix(matrix,i,j)){
                            return false;
                        }
                    }
                }
                if(noOneMatrix(matrix,i,0)){
                    return false;
                }
            }
            return true;
        }

        //不相等 说明不是合格的xxx列
        public boolean noOneMatrix(int[][] matrix,int i,int j){
            int val = matrix[i][j];
            while(i+1<matrix.length&&j+1<matrix[0].length){
                if(matrix[i+1][j+1] != val){
                    return true;
                }
                i++;
                j++;
            }
            return false;
        }
    }
}
