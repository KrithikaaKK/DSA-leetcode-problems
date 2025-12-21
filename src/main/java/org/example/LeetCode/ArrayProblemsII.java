package org.example.LeetCode;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayProblemsII {



    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        int zeros = 0;
        int product = 1;
        for(int n : nums) {
          if(n!=0) {
              product *= n;
          }else{
              zeros++;
          }

        }
        if(zeros==nums.length || zeros>1){
            Arrays.fill(result,0);
            return result;
        }
        for(int i=0;i<len;i++){
            if(zeros==0) {
                result[i] = product / nums[i];
            }else{
                if(nums[i]==0){
                    result[i] = product;
                }else {
                    result[i] = 0;
                }
            }

        }
        return result;
    }

    /**
     * first need to find pair -- i < i+1 - from where we need to rearrangee as in dictionary
     * with i , swap with the next smallest one
     * reverse the all next to i -- to get lexicographic order
     */
    public static int[] nextPermutation(int[] nums){
        int length = nums.length;
        int i = length-2;
        while(i>=0 && nums[i]>nums[i+1]){
            i--;
        }

        if(i>=0){
            for(int j = length - 1;j>i;j--){
                if(nums[i]<nums[j]){
                    swap(nums,i,j);
                    break;
                }
            }
        }

        // reverse after i
        int start = i+1,end = length-1;
        while (start<end) {
            swap(nums,start,end);
            start++;
            end--;
        }

        return nums;
    }

    /**
     * Input: nums = [10,2]
     * Output: "210"
     * Example 2:
     *
     * Input: nums = [3,30,34,5,9]
     * Output: "9534330"
     * @param nums
     */

    public static String largestNumber(int[] nums) {
        List<String> strNumbers = new ArrayList<>();

        for (int num : nums) {
            strNumbers.add(String.valueOf(num));
        }

        strNumbers.sort((a,b) -> (b+a).compareTo(a+b));

        if(strNumbers.get(0).equals("0")) {
            return "0";
        }

        return String.join("",strNumbers);

    }


    public static void swap(int[] arr,int i , int j) {
        int t = arr[j];
        arr[j] = arr[i];
        arr[i] = t;

    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i : nums) {
            if(!set.add(i)){
                return true;
            }
        }
        return false;

    }

    public static int maxSubarrayLength(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        int left=0,count = 0;
        for(int right=0;right<nums.length;right++) {
            int n = nums[right];
            map.put(n,map.getOrDefault(nums[right],0)+1);
            while(map.get(n) > k) {
                int m = nums[left];
                map.put(m,map.get(m)-1);
                left++;
            }

            count = Math.max(count,right-left+1);

        }
        return count;

    }

    /** fruits and basktes
    each basket will occupy only single type of fruit as many as available.
    totally two buckets ---- based on variable sliding window
     **/
    public static int totalFruit(int[] fruits) {
        int left =0,right,arrLen=0;
        Map<Integer,Integer> map = new HashMap<>();
        for(right =0;right<fruits.length;right++) {
            int n=fruits[right];
            map.put(n,map.getOrDefault(n,0)+1);
            while(map.size()>2) {
                Integer m = fruits[left];
                map.put(m,map.getOrDefault(m,0)-1);
                if(map.get(m)==0) map.remove(m);
                left++;
            }

            arrLen = Math.max(arrLen,right-left+1);

        }
        return arrLen;

    }

    /// return the longest consecutive sequence count without duplicates
    /// using hashing
    public static int longestConsecutive(int[] nums) {
        int count = 0,maxCount =0;
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }
        for(int n : nums) {
            if(set.contains(n) && !set.contains(n-1)) {
                int curr = n;
                while(set.contains(curr)) {
                    set.remove(curr);
                    curr++;
                    count++;
                }
            }
            maxCount = Math.max(maxCount,count);

        }
        return maxCount;

    }
    /// without using sets
    /// and by using sorting
    public static int longestConsecNums(int[] nums) {
        Arrays.sort(nums);
        int curr = 1;
        int maxCount = 1;
        for(int i=1;i<nums.length;i++) {
            if(nums[i]==nums[i-1]) {
                continue;
            }
            if (nums[i] == nums[i-1] +1){
                curr++;
            }
            else {
                curr=1;
            }
            maxCount = Math.max(maxCount,curr);
        }
        return maxCount;
    }



    static int max(int a, int b, int c) {
        return Math.max(a,Math.max(b,c));
    }

    static int min(int a, int b, int c) {
        return Math.min(a,Math.min(b,c));
    }



    /// return maximum product
    public static int maxProduct(int[] nums) {
        int maxProd = nums[0],currMax = nums[0], currMin = nums[0];

        for(int i=1;i<nums.length;i++){
            int n = nums[i];
            int temp = max(n,n*currMax,n*currMin);

            currMin =  min(n,n*currMax,n*currMin);

            currMax = temp;

            maxProd = Math.max(maxProd,currMax);
        }

        return maxProd;
    }

    public static int maxConsecOnes(int[] nums,int k) {
        int left=0,count=0,maxLen =0;
        for(int right=0;right<nums.length;right++) {
            if(nums[right]==0) {
                count++;
            }
            while(count>k) {
                if(nums[left]==0) {
                    count--;
                }
                left++;
            }
            maxLen = Math.max(maxLen,right-left+1);

        }
        return maxLen;
    }

    public static int[] finalPrices(int[] prices) {
        int[] result = new int[prices.length];
        int discount=0;

        for(int i=0;i<prices.length;i++) {

            for(int j=i+1;j<prices.length;j++) {
                if(prices[i]>=prices[j]){
                    discount=prices[j];
                    break;
                }else {
                    discount=0;
                }



            }
            if(i==prices.length-1){
                result[i] = prices[i];
            }else {
                result[i] = prices[i] - discount;
            }

        }
        return result;
    }
    /// return the missing number from 0 to n in nums
    public static int missingNumber(int[] nums) {
        int sum =0;
        int total=0;

        for(int i : nums) {
          sum +=i;
        }
        for(int i=0;i<=nums.length;i++){
            total+=i;
        }
        return total-sum;
    }






    public static void main(String[] args) {
        //System.out.println(Arrays.toString(productExceptSelf(new int[]{9,6,0,1,0,3})));
        //System.out.println(Arrays.toString(nextPermutation(new int[]{1,3,2})));
        //System.out.println(largestNumber(new int[]{3,30,34,5,9}));
        //System.out.println(containsDuplicate(new int[]{1,2,1,3}));
        //System.out.println(maxSubarrayLength(new int[]{1,4,4,3},1));
        //System.out.println(totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4}));
        //System.out.println(longestConsecNums(new int[]{0,3,7,2,5,8,4,6,0,1}));
        //System.out.println(maxProduct(new int[]{2,3,-2,-7,4}));
        System.out.println(maxConsecOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0},2));
        //System.out.println(Arrays.toString(finalPrices(new int[]{8, 4, 6, 2, 3})));
        //System.out.println(missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));

    }
}
