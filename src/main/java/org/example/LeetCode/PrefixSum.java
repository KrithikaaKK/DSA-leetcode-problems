package org.example.LeetCode;

import com.sun.jdi.IntegerType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrefixSum {

    //k==3
    //result should be 2

    public static int subarraySum(int[] nums, int k) {
        int count=0;
        for(int i=1;i<nums.length;i++) {
            nums[i] += nums[i-1];
            System.out.println(nums[i]);
        }
        System.out.println(Arrays.toString(nums));

        for(int i=0;i<nums.length;i++) {
            for(int j= i+1;j<nums.length;j++){
                System.out.println(nums[j]+ " " +nums[i]);
                if(nums[j] - nums[i] == k){
                    count++;
                }
            }
            if(nums[i]==k){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args){



       // System.out.println("result.."+ subarraySum(nums,3));

    }

}
