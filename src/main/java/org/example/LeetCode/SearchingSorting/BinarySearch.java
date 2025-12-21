package org.example.LeetCode.SearchingSorting;

import java.util.Arrays;

public class BinarySearch {

    public static int search(int[] nums, int target) {

        int low=0, high=nums.length-1;
        while(low<=high){
           int mid = low + (high-low)/2;
            if(target>nums[mid]) {
                low = mid+1;
            }
            else if(target==nums[mid]){
                return mid;
            }
            else {
                high = mid-1;
            }
        }
        return -1;

    }
    public static boolean twoDArray(int[][] matrix, int target) {
        int rows = matrix.length,cols = matrix[0].length;
        //fetched rows and cols
        //mid=???
        //mid = low + (high-low)/2
        //row , cols = ??
        //[][]

        for(int i=0;i<rows;i++){
            for (int j=0;j<cols;j++) {
                if(matrix[i][j]==target) {
                    return true;
                }

            }
        }
        return false;
    }

    /// O(log(m*n))
    /// search target in a 2D array
    ///
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length,cols = matrix[0].length;
        int low =0,high=(rows * cols)-1;
        while(low<=high) {
            int mid = low + (high-low)/2;
            int row = mid/cols;//integer div gives row no as row contains cols number of elements
            int col = mid%cols;//remainder gives which col
            int midVal = matrix[row][col];
            if(midVal==target) {
                return true;
            }
            else if(midVal<target) {
                low = mid+1;
            }
            else {
                high = mid -1;
            }
        }
        return false;
    }


    public static int KthSmallestElementInMatrix (int[][] matrix, int k) {
        int rows = matrix.length,cols = matrix[0].length;
        int low=0,high = (rows*cols)-1;
        while(low<=high) {
            int mid = low + (high-low)/2;
            int row = mid/cols;
            int col = mid%cols;
            if(mid==k-1) return matrix[row][col];
            else if(mid<k-1) {
                low = mid+1;
            }
            else {
                high = mid-1;
            }
        }
       return -1;
    }

    /// return the first and last occurrence of the target
    /// [3,4,4,4,4,5,6] target - 4 --> result --> [1,4] (index)
    public static int[] firstAndLastOccOfTarget(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = searchIndex(nums,target,true);
        result[1] = searchIndex(nums,target,false);
        return result;
    }

    public static int searchIndex(int[] nums,int target,boolean isFirst) {
        int low=0,high=nums.length-1;
        int result=-1;
        while(low<=high) {
            int mid = low + (high-low)/2;
            if(nums[mid]==target){
                result = mid;

                if(isFirst) {
                    high = mid-1;
                }
                else{
                    low = mid+1;
                }
            }
            else if(nums[mid]>target){
                high = mid -1;
            }
            else  {
                low = mid+1;
            }
        }
        return result;
    }

    /// return the index of the target if present,
    /// or else return the index in which target would be placed correct
    /// sorted & O(logn)
    /// \[1,2,4,5,6]
    public static int searchInsert(int[] nums, int target) {
        int low=0, high=nums.length-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(target>nums[mid]) {
                low = mid+1;
            }
            else if(target==nums[mid]){
                return mid;
            }
            else {

                high = mid-1;

            }
        }
        return low;
    }


    /// binary search on answer
    /// creating a search space
    /// and reducing the search possibilities by half
    /// O(logn) [0,1,2,3,4,5,6,7,.......n]
    ///
    public static int sqrtOfNumber(int n) {
        int low = 1,high=n;
        while(low<=high) {
            int mid = low + (high - low)/2; // round down
            if(mid>n/mid) { //mid * mid > n
                high = mid -1;
            }
            else if((mid*mid)==n) {
                return mid;
            }
            else {
                low = mid+1;
            }
        }
        return low-1;
    }

    /// num - 16 - true
    ///num - 14 -false
    /// BS on answer
    public static boolean isPerfectSquare(int num) {
        int low = 1,high = num;
        while(low<high) {
            int mid = (low+high+1)/2; //round up
            if(num==(mid*mid)) {
                return true;
            }
            else if((mid*mid)>num) {
                high = mid -1;
            }
            else {
                low = mid;
            }
        }
        return false;

    }

    public static int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int low = 1,high = n-2;

        if(arr[0]>arr[1]) return 0;
        if(arr[n-1]>arr[n-2]) return n-1;

        while(low<=high) {
            int mid = low+(high-low)/2;
            if(arr[mid]<arr[mid+1]) {
                low = mid+1;
            }
            else if(arr[mid-1]<arr[mid] && arr[mid]>arr[mid+1]) {
                return mid;
            }
            else {
                high =  mid-1;
            }
        }
        return -1;

    }











    // peak element -- > 1,2 ,3,1
    public static void main(String[] args) {
//        System.out.println(search(new int[]{-1,0,3,5,9,12},9));
//        System.out.println(searchMatrix(new int[][]{
//
//                        {1, 3, 5}, //row = 3,col = 3 high = 8 mid = 4 r=4/3=1 c=4%3=1,
//                        {7, 9, 11}, // low = 4+1=5 , mid = 6
//                        {13, 15, 17}
//
//        },18));
        System.out.println(KthSmallestElementInMatrix(new int[][]{

                {1, 3, 5}, //row = 3,col = 3 high = 8 mid = 4 r=4/3=1 c=4%3=1,
                {7, 9, 11}, // low = 4+1=5 , mid = 6
                {13, 15, 17}

        },7));
//        System.out.println(searchMatrix(new int[][]{
//
//                {1}, //row = 3,col = 3 high = 8 mid = 4 r=4/3=1 c=4%3=1,
//                {7} // low = 4+1=5 , mid = 6
//
//        },7));

//        System.out.println(findPeakElement(new int[]{1,2,3,1,5}));
      //  System.out.println(Arrays.toString(firstAndLastOccOfTarget(new int[]{5, 7, 7,8,8,  10}, 7)));
      //  System.out.println(searchInsert(new int[]{1,2,4,5,7,9},8));
       // System.out.println(sqrtOfNumber(24));
       // System.out.println(isPerfectSquare(26));
        //System.out.println(peakIndexInMountainArray(new int[]{0,1,3,1,0}));

    }
}
