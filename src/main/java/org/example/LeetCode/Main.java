package org.example.LeetCode;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
public class Main {


        public static int subarraySum(int[] nums, int k) {
            int count=0,windowSum=0,maxSum=0,l=2,sum=0;
            if(nums.length>l){
                for(int i=0;i<l;i++) {
                    maxSum += nums[i];
                }
                if(maxSum==k){
                    count++;
                }
                windowSum = maxSum;
                for(int i=l;i<nums.length;i++){
                    windowSum += nums[i]-nums[i-l];
                    if(windowSum==k) {
                        count++;
                    }
                }
            }

            for(int i=0;i<nums.length;i++){
                if(nums[i]==k){
                    count++;
                }
                sum += nums[i];

            }
            if(sum==k && nums.length !=1) {
                count++;
            }

            return count;
        }

    public static List<String> findStopWords(String s, String t, int k) {

        List<String> ss = List.of(s.toLowerCase().split(" "));
        List<String> res = new ArrayList<>();
        System.out.println(res);
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < ss.size(); i++) {
            map.put(ss.get(i), map.getOrDefault(ss.get(i), 0) + 1);
        }
        System.out.println(map);

        for (Map.Entry<String, Integer> m : map.entrySet()) {
            if (m.getValue() == k) {
                res.add(m.getKey());
            }
        }
        return res;
    }


    // 1 - based indices...index starts from 1 not 0
    public static int[][] stanleyBreakBricks(int bigHits, int[] newtons) {
        // write your code here
        List<int[]> indexedNewtons = new ArrayList<>();
        for(int i=0;i<newtons.length;i++) {
            indexedNewtons.add(new int[]{newtons[i],i+1});
        }
        indexedNewtons.forEach(Array -> System.out.println(Arrays.toString(Array)));

        indexedNewtons.sort((a,b) -> b[0] - a[0]);

        return null;

    }

    // 1- based indices
    public static String lexiographicString (String s , int l , int r) {

            // s = "thgyfh", l = 2 , r = 6
         char[] arr = s.toCharArray();
        System.out.println(arr);
         Arrays.sort(arr);
         //swapping
       /*  for(int i =0;i<arr.length/2;i++){
             char temp = arr[i];
             arr[i] = arr[arr.length-1-i];
             arr[arr.length-1-i] = temp;
         }

        */

        return new StringBuilder(new String(arr)).reverse().toString();
    }

    /**
    To divide the string into num non-empty substrings and find the
    lexicographically largest string among them
     maximum length substring - n - num + 1; n -> s.length
     */
    public static String lexicographicallyLargestString(String s , int numFriends){
        int length = s.length();
        if (numFriends == 1) return s;
        String result = "";

        for (int i = 0; i < length; i++) {
            int maximumLength = Math.min(length-i,length-numFriends+1);
            String currStr = s.substring(i,i+maximumLength);
            if(result.compareTo(currStr)<0) {
                result = currStr;
            }
        }
        return result;
    }




    public static void main(String[] args) {

        int[] nums = {1,1,1};
        int k = 3,windows=0,maxSum=0;

        for(int i=0 ; i<k ; i++){
            maxSum += nums[i];
        }
        windows=maxSum;
        for(int i=k;i<nums.length;i++){
            windows += nums[i] - nums[i-k];
            System.out.println("i.."+i+"nums[i].."+nums[i]+"i-k.."+nums[i-k]);
            maxSum = Math.max(windows,maxSum);
        }
       /* System.out.println("result..."+maxSum);
        System.out.println(subarraySum(nums,2));

        System.out.println(stanleyBreakBricks(4,new int[]{3,2,5,6,7,9}));
        System.out.println(findStopWords("the quick brown fox jumps over the lazy brown dog and runs away to a brown house","hackerrank",2));

        System.out.println(lexiographicString("thgyfh",2,6));

        */
        System.out.println(lexicographicallyLargestString("dbca",2));

        }
}