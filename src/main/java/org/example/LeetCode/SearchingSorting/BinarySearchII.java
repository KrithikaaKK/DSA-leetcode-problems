package org.example.LeetCode.SearchingSorting;

public class BinarySearchII {

    /// find minimum in rotated sorted array
    /// comparing using right
    /// right gives a hint whether to move left  or right
    public static int findMin(int[] nums) {
        int left =0,right = nums.length-1;
        while(left<right) {
            int mid = left+(right-left)/2;
            if(nums[mid]>nums[right]) {
                left = mid+1;
            }
            else if(nums[mid]<nums[right]){
                right =  mid;
            }
            else if(nums[left]<nums[right]){ //direct exit
                return nums[left];
            }

        }
        return nums[left];


    }
    public static int searchInRotatedArray(int[] nums, int target) {
        int left = 0, right=nums.length-1;
        while(left<=right) {
            int mid = left +(right-left)/2;
            if(target==nums[mid]) return mid;
            else if(nums[left]<=nums[mid]) {
                if(target>=nums[left] && nums[mid]>target){
                    right=mid-1;
                }
                else {
                    left=mid+1;
                }
            }
            else{
                if(target<=nums[right] && nums[mid]<target){
                    left=mid+1;
                }
                else {
                    right=mid-1;
                }
            }
        }
        return -1;
    }

    public static boolean searchInRotatedArrayWithDup(int[] nums, int target) {
        int left =0,right=nums.length-1;
        while(left<=right) {
            int mid = left +(right-left)/2;
            if(nums[mid]==target) {
                return true;
            }
            else if (nums[mid]==nums[left]&& nums[mid]==nums[right] && nums[left]==nums[right]){ // shrink the boundaries
                left++;
                right--;
            }
            else if (nums[left]<=nums[mid]) {
                if(target>=nums[left] && nums[mid]>target){
                    right=mid-1;
                }
                else {
                    left=mid+1;
                }
            }
            else {
                if(nums[mid]<target && nums[right]>=target) {
                    left = mid+1;
                }
                else {
                    right = mid-1;
                }
            }
        }
        return false;

    }

    /// to solve this in O(logn) => binary search
    public static int findPeakElement(int[] nums) {
        //[1,2,3,1,5] mid = 3, mid+1<mid
        int len=nums.length;
        int low = 0, high = len-1;
        if(len==1) return 0;
        if(nums.length==2) {
            if(nums[0]>nums[1] ){
                return 0;
            }
            else {
                return 1;
            }
        }
        while(low<high) {
            int mid = low + (high-low)/2;

            if(nums[mid]<nums[mid+1]){
                low = mid+1; // peak must be at right
            }
            else{
                high=mid; // the peak may be the mid or at left
            }
        }
        return low;


}


    /// [1,1,2,2,3,4,4] --- output should be 3 - one time occurred
    /// convergeeee towards the result
    ///
    public static int singleNonDuplicate(int[] nums) {
        int low=0,high=nums.length;
        while(low<high){
            int mid = low + (high-low)/2;

            if(mid%2==1) {
                mid--;
            }
            if(nums[mid]==nums[mid+1]) {
                low = mid + 2;
            }
            else {
                high = mid;
            }
        }
        return low;

    }


    public static void main(String[] args) {
       // System.out.println(findMin(new int[]{4,5,6,7,0,1,2}));
        //System.out.println(searchInRotatedArray(new int[]{4,0,1,2,3},4));
        //System.out.println(searchInRotatedArrayWithDup(new int[]{1,0,1,1,1},0));
        //System.out.println(findPeakElement(new int[]{6,5,4,3,2,3,2})); // output --> 0 since 6 greater than - -infinte and 5,
        System.out.println(singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));

    }
}
