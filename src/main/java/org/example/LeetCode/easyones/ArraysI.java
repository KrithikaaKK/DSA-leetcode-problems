package org.example.LeetCode.easyones;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArraysI {

    //sum of max pos & neg numbers
    public  static  int maximumCount(int[] nums) {
        int pos=0,neg=0;
        for(int n : nums){
            if(n>0) pos++;
            else if(n<0) neg++;
            else continue;
        }
        return Math.max(pos,neg);

    }

    public static String removeDup(String s) {
        int[] count = new int[26];
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(count[c-'a']<1) {
                sb.append(c);
            }
            count[c - 'a']++;


        }
        return sb.toString();
    }

    // return duplicates -- will be exactly two duplicated numbers to be found
    public static int[] getSneakyNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int[] result = new int[2];
        int count=0;
        for (int num : nums) {
            if (!set.add(num)) {

                result[count] = num;
                count++;
            }
        }
        return result;
    }


    /// colors = 'abaacad' neededTime = [1,2,3,4,1,2]
    public static int minCost(String colors, int[] neededTime) {
        char[] chars = colors.toCharArray();
        int totalTime =0;
        int maxTime = neededTime[0];

        for(int i=1;i<colors.length();i++) {
            if(chars[i]==chars[i-1]) {
                totalTime += Math.min(maxTime, neededTime[i]);
                maxTime = Math.max(maxTime,neededTime[i]);
            }

            else {
                maxTime = neededTime[i];
            }
        }
        return totalTime;

    }



    public static boolean isSame(String s , int i,int n){
        if(i>=n) return true;
        if(s.charAt(i)!=s.charAt(n-i-1)) {
            return false;
        }
        return isSame(s,i+1,n);
    }



    public static void main(String[] args){
        //System.out.println(maximumCount(new int[]{-2,-1,-1,1,2,3}));
       // System.out.println(removeDup("apprtyuuyle"));
        // System.out.println(Arrays.toString(getSneakyNumbers(new int[]{0, 3, 2, 1, 3, 2})));
        //System.out.println(minCost("aaabbbabbbb",new int[]{3,5,10,7,5,3,5,5,4,8,1}));
      //  System.out.println(minCost("aaabbb",new int[]{3,5,4,7,3,1}));
        /// 8 + 8 +10 - 26
        String s = "assassa";
        boolean res = isSame(s,0,s.length());
        System.out.println(res);
    }
}
