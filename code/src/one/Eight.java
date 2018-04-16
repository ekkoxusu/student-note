package one;

public class Eight {

    public static void main(String[] args) {
        int[][] ints = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        System.out.println(new Solution().islandPerimeter(ints));

    }

    static class Solution {
        public int islandPerimeter(int[][] grid) {
            int resp = 0;
            for(int i=0;i<grid.length;i++){
                boolean b = false;
                for(int j=0;j<grid[i].length;j++){
                    int num = 4;
                    boolean notWater = grid[i][j] == 1;
                    if(b){
                        //上一格子多加的
                        num--;
                        if(notWater){
                            //这一个格子左边被占用了
                            num--;
                        }
                    }
                    if(0<=i-1){
                        num = grid[i-1][j]==1?num-1:num;
                    }
                    if(i+1<grid.length){
                        num = grid[i+1][j]==1?num-1:num;
                    }
                    if(notWater){
                        resp+=num;
                        b = true;
                    }
                }
            }
            return resp;
        }
    }
}
