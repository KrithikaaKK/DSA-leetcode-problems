package org.example.LeetCode;

import java.util.Arrays;
import java.util.HashMap;

class ArraysProblems {

     final int[] numbers;

    public ArraysProblems(int[] nums) {
      for(int i=1;i<nums.length;i++) {
          nums[i] += nums[i-1];
      }
      numbers = nums;
      System.out.println("prefixsum.."+Arrays.toString(numbers));
      int res = sumRange(1,3);
        System.out.println(res);
    }

    public int sumRange(int left, int right) {
        System.out.println(numbers[left]);
        return numbers[right] - numbers[left-1];
    }


    public static int[] mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1, k = m + n -1;
        while(i>=0 && j >= 0 && k >=0){
           if(nums1[i]<nums2[j]) {
               nums1[k--] = nums2[j--];
           }
           else {
               nums1[k--] = nums1[i--];
           }
        }
        while(j>=0) {
            nums1[k--] = nums2[j--];
        }

        return nums1;
    }

    ///
    public static int[] partitioningArrayPivotElement(int[] nums,int pivot){
        int len = nums.length;
        int pivotNum = nums[pivot];
        swap(nums,len-1,pivot);
        int i = -1, j = 0;
        while(j<len-1){
            if(nums[j]<pivotNum){
                i++;
                swap(nums,i,j);
            }
            j++;
        }

        swap(nums,len-1,i+1);
        return nums;
    }


    public static int[] pivotArray(int[] nums, int pivot) {
        int[] result = new int[nums.length];
        int count = 0;

        for(int num : nums){
            if(num<pivot){
                result[count] = num;
                count++;
            }
        }
        for(int num : nums){
            if(num==pivot){
                result[count] = num;
                count++;
            }
        }
        for(int num : nums){
            if(num>pivot){
                result[count] = num;
                count++;
            }
        }
        return result;
    }
    public static int[] twoSum(int[] nums,int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int n = target-nums[i];
            if(map.containsKey(n)){
                return new int[]{map.get(n),i};
            }
            map.put(nums[i],i);
        }
        return new int[]{-1,-1};

    }

    //dutch national flag algorithm
    public static int[] sortColors(int[] nums){
        int low=0,mid=0,high= nums.length-1;
        while(mid<=high){
            if(nums[mid]==0) {
                swap(nums,low++,mid++);
            }
            else if(nums[mid]==2) {
                swap(nums,mid,high--);
            }
            else {
                mid++; // if equals 1 then skip
            }
        }
        return nums;
    }

    public static void swap(int[] arr,int i , int j) {
        int t = arr[j];
        arr[j] = arr[i];
        arr[i] = t;

    }

    public static int coinChange (int[] nums, int amount){

        /// [1,2,5] amount = 11 - no of coins - 3 -> 5 -2 1 - 1

        int coins=0;
        Arrays.sort(nums);
        for(int i = nums.length-1; i>=0; i--) {

            while (amount - nums[i] >= 0) {
                amount = amount - nums[i];
                coins++;
                System.out.println(i + " " + amount);
            }

        }
        return coins;

    }


    public static void main(String[] args) {
        int[] numbersss = new int[]{1,4,5,2,7};
//        NumArray numArray = new NumArray(numbersss);
        System.out.println(Arrays.toString(mergeSortedArray(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3)));
//        System.out.println(Arrays.toString(partitioningArrayPivotElement(new int[]{3,7,8,6,5,6,10},3)));
//        System.out.println(Arrays.toString(pivotArray(new int[]{9,12,5,10,14,3,10},10)));
//        System.out.println(Arrays.toString(twoSum(new int[]{2,5,5,11},10)));
      // System.out.println(Arrays.toString(sortColors(new int[]{1,0,2,2,1,0})));
        //System.out.println(coinChange(new int[]{186,419,83,408},6249));
    }
}