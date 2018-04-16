package one;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Six {

    public static void main(String[] args) {
        String[] strings = {"9001 discuss.leetcode.com"};
        System.out.println(new Solution().subdomainVisits(strings));

    }

    /**
     * 标准答案
     * 见 md
     */
    static class Solution {
        // 1.拆分 url google.mail.com 拆分成 goole-goole.mail-goole.mail.com-mail.com 计数
        // 2.继续拆分 从原有数字++
        Map<String,Integer> map = new HashMap();
        public List<String> subdomainVisits(String[] cpdomains) {
            for(String s : cpdomains){
                Integer endIndex = s.indexOf(" ");
                Integer num = Integer.parseInt(s.substring(0, endIndex));
                String allUrl = s.substring(endIndex+1,s.length());
                String[] it = allUrl.split("\\.");
                for(int i=0;i<it.length;i++){
                    StringBuilder sb = new StringBuilder(it[i]);
                    if(i != it.length-1){
                        sb.append(".");
                    }
                    System.out.println(sb);
                    for(int j=i+1;j<it.length;j++){
                        if(j != it.length-1){
                            sb.append(it[j]+".");
                        }else{
                            sb.append(it[j]);
                        }
                    }
                    putNum(sb.toString(),num);
                }
            }
            List<String> resp = new ArrayList<String>();
            map.forEach((k,v)->{
                resp.add(v + " " +k);
            });
            return resp;
        }
        //可用map函数 getOrDefualt代替
        public void putNum(String name,Integer num){
            if("".equals(name)){
                return;
            }
            Integer i = map.get(name);
            if(i != null){
                map.put(name,i+num);
            }else{
                map.put(name,num);
            }

        }
    }
}
