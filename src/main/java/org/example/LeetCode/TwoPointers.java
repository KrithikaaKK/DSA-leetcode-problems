package org.example.LeetCode;

import java.util.*;

public class TwoPointers {

    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int left=0,right=numbers.length-1,sum=0;
        while(left<right) {
            sum = numbers[left] + numbers[right];

           if(sum<target) left++;
           if(sum>target)  right--;
           if(sum==target) {
               result[0] = left+1;
               result[1] = right+1;
               break;
           }

        }
        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        // return triplets of sum 0;
        int first,second=0,third=0,sum=0;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for(first=0;first< nums.length-2&&nums[first]<=0;++first){
            if(first>0 && nums[first] == nums[first-1]) {
                continue;
            }
            second = first+1;
            third = nums.length-1;
            while(second<third) {
                sum = nums[first] + nums[second] +  nums[third];
                if(sum<0) ++second;
                if(sum>0) --third;
                else if(sum==0) {
                    result.add(List.of(nums[first],nums[second],nums[third]));
                    while(second<third && nums[second]==nums[second+1]) second++;
                    while(second<third && nums[third] == nums[third-1]) third--;

                    second++;
                    third--;
                }
            }

        }

        return result;

    }

    public static int removeElement(int[] nums, int val) {
        int itr=0;
        for (int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[itr] = nums[i];
                itr++;
            }
        }
        System.out.println(Arrays.toString(nums));

        for(int i=0;i<nums.length;i++){
            if(i>=itr) {
                nums[i] = -1;
            }
        }

        System.out.println(Arrays.toString(nums));
        return itr;
    }

    public static int removeDuplicates(int[] nums){
        //[1,1,2,2,3]
        int itr = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[i-1]){
                nums[itr]=nums[i];
                itr++;
            }
            System.out.println(Arrays.toString(nums));
        }

        for(int i=itr;i<nums.length;i++){
                nums[i] = -1;

        }



        return itr;
    }

    public static int findPivotElement(int[] nums) {
        int left = 0,right = 0;
        for (int num : nums) {
            right += num;
        }

        for(int i=0;i<nums.length;i++) {
            right = right - nums[i];


            System.out.println(nums[i]+" "+left+" "+right);
            if(i==0 && right==0) {
                return 0;
            }
            if(left==right){
                return i;
            }
            left += nums[i];
        }
        return -1;

    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, count = 0;
        for (int num : nums) {
            if (num == 0) {
                maxCount = Math.max(maxCount, count);
                count = 0;
            } else {
                count++;
            }

        }
        maxCount = Math.max(maxCount, count);
        return maxCount;

    }

    public static int majorityElement ( int[] nums) {

        int ref = nums[0],count = 1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i]==ref){
                count++;
            }
            else {
                count--;
                if(count==0) {
                    ref =  nums[i];
                    count++;
                }
            }
        }
        count=0;
        // to double-check if the ref has occurred > n/2 times
        for(int num : nums){
            if (num == ref){
                count++;
            }
        }
        if(count> nums.length/2) return ref;
        else return -1;
    }

    public static List<Integer> majorityElementNByThree(int[] nums) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            if(m.getValue()>nums.length/3) {
                res.add(m.getKey());
            }

        }
        return res;
    }

    public static int maxProfit(int[] prices) {
        int buy =prices[0],profit = 0;

        for(int i=0;i<prices.length;i++){
            if(buy>prices[i]){
                buy = prices[i];
            }
            else if(prices[i]-buy > profit ){
                profit = prices[i] - buy;
            }

        }
        return profit;

    }



    public static void main(String[] args) {
        //System.out.println(Arrays.toString(twoSum(new int[]{-10,-8,-2,1,2,5,6}, 0)));
        //System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        //System.out.println(removeElement(new int[]{3,2,2,3,5,7,3},3));
        //System.out.println(removeDuplicates(new int[]{1,1,2,3,4,4}));
        //System.out.println(findPivotElement(new int[]{2,-1,1}));
        //System.out.println(findMaxConsecutiveOnes(new int[]{1,1,1,0,1,1}));
        //System.out.println(majorityElement(new int[]{3,2,3}));
        //System.out.println(majorityElementNByThree(new int[]{1,2}));

        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
    }
}
