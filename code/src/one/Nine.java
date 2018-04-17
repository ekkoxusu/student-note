package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestMain {

    public static void main(String[] args) {
        int[][] ints = {{18},{66}} ;
        String[] strings = {"hit"};
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        System.out.println(
                new Solution().mostCommonWord(paragraph , strings));
    }

    static class Solution {
        public String mostCommonWord(String paragraph, String[] banned) {
            char[] val = paragraph.toCharArray();
            StringBuilder sb = new StringBuilder();
            Map<String,Integer> resp = new HashMap();
            for(char v:val){
                if(v<='z' && v>='A'){
                    sb.append(v);
                    //防止不打特殊字符结束的语句 ，比如 tom is Sb 防止捕获不到最后一个Sb
                    if(v == val[val.length-1]){
                        String name = sb.toString().toLowerCase();
                        resp.put(name,resp.getOrDefault(name,0)+1);
                    }
                    //特殊字符就做put处理（非英文一定是特殊字符）
                }else{
                    String name = sb.toString().toLowerCase();
                    resp.put(name,resp.getOrDefault(name,0)+1);
                    sb = new StringBuilder();
                }

            }
            String backStr = "";
            Integer mustNum = 0;
            Set<String> s = resp.keySet();
            //遍历所有map 取出最大值的数字，并且不能为空和排除字段
            for(String k:s){
                Integer v = resp.get(k);
                boolean b = true;
                for(String str: banned){
                    if(k.equals(str)){
                        b = false;
                        break;
                    }
                }
                if(b&&v>mustNum&&!"".equals(k)){
                    mustNum = v;
                    backStr = k;
                }
            }
            return backStr;
        }
    }
}
