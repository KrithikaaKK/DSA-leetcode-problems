package org.example.LeetCode.SearchingSorting;

import java.util.Arrays;

public class BsOnAnswer {

    /// find kth smallest element in the row wise - col wise sorted matrix but
    /// not globally-- hence wee proceed with BS on answer
    public static int KthSmallestElementInMatrix (int[][] matrix, int k) {
        int n = matrix.length,cols = matrix[0].length;
        int low=matrix[0][0],high = matrix[n-1][cols-1];
        while(low<high) {
            int mid = low + (high-low)/2;
            int count = CountBeforeMid(matrix,mid,n); // to find the count of number before the mid, to check with the k
            if(count<k) {
                low = mid+1;
            }else{
                high = mid;
            }

        }
        return low;
    }

    private static int CountBeforeMid(int[][] matrix,int target,int n) {
        int row = n-1,col=0,count=0;
        while(row>=0 && col<n){
            int val = matrix[row][col];
            if(val<=target){
                count += row+1;
                col++;
            }else {
                row--;
            }
        }
        return count;
    }

    /// Kth element in a multiplication table
    /// In m rows and n columns
    public static int findKthNumber(int m, int n, int k) {
        int low=1,high=m*n;
        while(low<high) {
            int mid = low + (high-low)/2;
            int count = countBeforeMid(m,n,mid);
            if(count<k) {
                low=mid+1;
            }
            else{
                high=mid;
            }
        }
        return low;

    }

    private static int countBeforeMid(int m, int n, int mid) {
        int count=0;
        for(int i=1;i<=m;i++) {
            count += Math.min(n,(mid/i)); // as a row only contains n(column) number of elements
        }
        return count;
    }

    ///brute force approach - not recommended - O(m+n)
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length-1,n = nums2.length-1;
        int total = nums1.length + nums2.length;
        int[] result = new int[total];
        double avg =0;
        int k = total -1;
        while(m>=0 && n>=0 && k>=0) {
            if(nums1[m]<nums2[n]) {
                result[k--] = nums2[n--];
            }
            else {
                result[k--] = nums1[m--];
            }
        }
        while(m>=0) {
            result[k--] = nums1[m--];
        }
        while (n>=0) {
            result[k--] = nums2[n--];
        }
        if(total%2==0) {
           int sum = result[total/2];
            sum += result[(total/2)-1];
            System.out.println("sum.."+sum);
            avg = (double)sum/2;
        }
        else {
            avg = result[total/2];
        }


        System.out.println(Arrays.toString(result));
        return avg;

    }

    /// 3,6,7,11
    public static int minEatingSpeed(int[] piles, int h) {
        int max = 0,totalHrs=0,minSpeed =Integer.MAX_VALUE;

        for(int i=0;i<piles.length;i++) {
            max = Math.max(max, piles[i]);
        }

        for(int i =1;i<=max;i++) {

             totalHrs = (int) findTotalHrs(piles,i,h);
            //System.out.println("i.."+i+"tot.."+totalHrs);
            if(totalHrs<=h) {
                return i;
            }
            minSpeed = Math.min(minSpeed,totalHrs);
        }
        return minSpeed;

    }

    private static double findTotalHrs(int[] piles, double n,int h) {
        double total =0;
        for(int i=0;i<piles.length;i++) {
            total += Math.ceil((double) piles[i]/n);
        }
        return total;
    }

    /// BS on answerr
    public static int minEatingSpeedOptimise(int[] piles, int h) {
        int max = 0,totalHrs=0,answer=0;
        for(int i=0;i<piles.length;i++) {
            max = Math.max(max, piles[i]);
        }
        int low =1,high=max;
        while(low<=high) {
            int mid = low + (high-low)/2;
            totalHrs = (int) findTotalHrs(piles,mid,h);
            if(totalHrs<=h){
                answer=mid; // if koko finishes in mid , then still it might be possible to try slower speeds
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        }
        return answer;

    }


    public static void main(String[] args) {
//        System.out.println(KthSmallestElementInMatrix(new int[][]{
//
//                {1, 5, 9}, //row = 3,col = 3 high = 8 mid = 4 r=4/3=1 c=4%3=1,
//                {10, 11, 13}, // low = 4+1=5 , mid = 6
//                {12, 13, 17}
//
//        },8));
        //System.out.println(findKthNumber(3,3,5));

       // System.out.println(findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
        System.out.println(minEatingSpeedOptimise(new int[]{30,11,23,4,20},10));

    }
}
