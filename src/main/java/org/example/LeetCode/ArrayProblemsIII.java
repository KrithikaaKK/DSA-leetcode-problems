package org.example.LeetCode;

import java.util.*;

public class ArrayProblemsIII {
    public static void rotate(int[] nums, int k) {
        int[] result = new int[nums.length];
        int n =  nums.length;
        int len = n-1;
        k = k % n; //as rotation is cyclic we reduced it if k greater than length
        int count=k-1,c=0;


        for(int i=0;i<n;i++) {
            result[i] = nums[i];
        }
        //k=2

        while(count>=0) {
            nums[count--]=result[len--];
        }
        count=k;

        while(count<nums.length){
            nums[count++]=result[c++];
        }
        System.out.println(Arrays.toString(nums));

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            StringBuilder sb = new StringBuilder();
            String sorted = sb.append(chars).toString();
            if (map.containsKey(sorted)) {
                List<String> list = map.get(sorted);
                list.add(s);
                map.put(sorted, list);
            } else {
                map.put(sorted, new ArrayList<>(List.of(s)));
            }

        }
        for(Map.Entry<String,List<String>> m : map.entrySet()) {
            result.add(m.getValue());
        }
        return result;

    }

    public static int maximumSubarray(int[] arr) {
        int sum = 0;
        int result = 0;

        for(int i=0;i<arr.length;i++) {
            sum=0;
            for(int j=i;j<arr.length;j++) {
                sum += arr[j];
                result = Math.max(result,sum);
            }

        }
        return result;
    }

    public static int maximumSubarrayOp(int[] nums) {
        int sum = 0;
        int result = Integer.MIN_VALUE;


        for(int i=0;i<nums.length;i++) {
            sum += nums[i];
            result = Math.max(result,sum);
            if(sum<0) {
                sum=0;
            }

        }
        return result;
    }



    public static void main (String[] args) {
        rotate(new int[]{1,2,3,4,5},3);
        //System.out.println(groupAnagrams(new String[]{"eat","ate","bat","nat","tan"}));
        //System.out.println(maximumSubarrayOp(new int[]{-2,1,-3,4,-1,2,1,-5,4}));


    }
}
