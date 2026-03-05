package org.example.LeetCode;

import com.sun.jdi.IntegerValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StringProblemsIIImp {

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

    /// 11 -> 21 - count + then the digit 21 -> 1211
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

    public static String decodeString(String s) {


        Stack<Integer> st1 = new Stack<>();
        Stack<StringBuilder> st2 = new Stack<>();

        StringBuilder sb = new StringBuilder();
        int n =0;
        for(char c : s.toCharArray()) {
            if(Character.isDigit(c)){
                n = n * 10 + (c - '0');
            }
            else if (c == '['){
                st1.push(n);
                n =0;
                st2.push(sb);
                sb = new StringBuilder();
            }
            else if (c == ']'){
                StringBuilder temp = sb; //c
                sb = st2.pop(); //a
                int m = st1.pop();
                while(m-->0){
                    sb.append(temp); //acc
                }
            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    public static void main(String[] args){
        //System.out.println(romanToInt("III"));
        //System.out.println(countAndSay(4));
        System.out.println(decodeString("3[a]2[c]"));

    }
}
