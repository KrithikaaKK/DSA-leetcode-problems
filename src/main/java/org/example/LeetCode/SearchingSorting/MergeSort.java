package org.example.LeetCode.SearchingSorting;

import org.example.LeetCode.ListNode;

import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static int[] sortArray(int[] nums) {
        if(nums==null||nums.length<=1) return nums;
        mergeSort(nums,0,nums.length-1);
        return nums;


    }
    public static void mergeSort(int[] nums,int low,int high) {
        if(low<high) {
            int mid = low + (high - low) / 2;
            mergeSort(nums, low, mid);
            mergeSort(nums, mid + 1, high);
            sortMerged(nums, low, mid, high);
        }
    }

    public static void sortMerged (int[] nums,int left,int mid,int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) temp[k++] = nums[i++];

        while (j <= right) temp[k++] = nums[j++];

        for (int t = 0; t < temp.length; t++) {
            nums[left + t] = temp[t];
        }
    }

    ///  sort the LinkedList in O(logn) time complexity
    public static ListNode sortList(ListNode head) {
        if(head==null || head.next==null) return head; //base case for recursion
        ListNode slow =  head;
        ListNode fast = head;
        ListNode prev  = null;
        while(fast!=null && fast.next!=null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next=null; // make into two halves by terminating the prev of middle
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        return merge(left,right);

    }

    public static ListNode merge(ListNode left,ListNode right) {
        ListNode dummy = new ListNode(1);
        ListNode temp = dummy;
        while(left!=null && right!=null) {
            if(left.val<right.val) {
                temp.next = left;
                temp = left;
                left = left.next;
            }
            else {
                temp.next = right;
                temp = right;
                right = right.next;
            }
        }
        //while - not needed cause the ind list is already sorted
        if (left!=null) {
            temp.next = left;
        }
        if(right!=null) {
            temp.next = right;
        }
        return dummy.next;
    }



    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArray(new int[]{2, 5, 1, 3, 7, 3})));
    }
}
