package org.example.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class StringProblemsIII {

    public static int romanToInt(String s) {

        Map<Character,Integer> map = new HashMap<>();
        int total =0;

        map.put('M',1000);
        map.put('D',500);
        map.put('C',100);
        map.put('L',50);
        map.put('X',10);
        map.put('V',5);
        map.put('I',1);

        int len = s.toCharArray().length;
        if(len==1){
            return map.get(s.charAt(0));
        }

        for(int i=0;i<len-1;i++){
            int curr = map.get(s.charAt(i));
            int next = map.get(s.charAt(i+1));

            if(curr>=next){
                total = total + curr;
            }
            else{
                total -= curr;
            }
        }
        total += map.get(s.charAt(len-1));
        return total;
    }

    public static String countAndSay(int n) {

        StringBuilder sb = new StringBuilder();
        sb.append(1);

        if(n==1){
            return sb.toString();
        }

        while(n>1){
            String s = sb.toString();
            int len = s.length();
            StringBuilder sb1 = new StringBuilder();
                int m=0,k=0,j=0;
                while(j<len){
                    if(s.charAt(k)==s.charAt(j)) {
                        m++;
                        j++;
                    }else {
                        sb1.append(m).append(s.charAt(k));
                        k = j;
                        m = 0;
                    }
                }
                sb1.append(m).append(s.charAt(k));
                sb = sb1;
                n--;
        }
    return sb.toString();
    }

    public static void main(String[] args){
        //System.out.println(romanToInt("III"));
        System.out.println(countAndSay(4));

    }
}
