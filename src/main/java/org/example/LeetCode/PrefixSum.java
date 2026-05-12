package org.example.LeetCode;

import com.sun.jdi.IntegerType;

import java.util.*;

public class PrefixSum {

    //k==3
    //result should be 2

    public static int subarraySum(int[] nums, int k) {
        int count=0;

        Map<Integer,Integer> sumCount = new HashMap<>();
        sumCount.put(0,1);

        int prefixSum = 0;
        for(int num : nums){
            prefixSum += num;
            int n = prefixSum - k;
            if(sumCount.containsKey(n)) {
                count += sumCount.get(n);
            }
            sumCount.put(prefixSum,sumCount.getOrDefault(prefixSum,0)+1);
        }


        return count;
    }

    public static void main(String[] args){



       // System.out.println("result.."+ subarraySum(nums,3));

    }

}
