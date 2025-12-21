package org.example.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LinkedListI {

    public ListNode detectCycleStart(ListNode head) {
        if(head==null||head.next==null) return null;

       ListNode slow = head;
       ListNode fast = head;
       while(fast!=null && fast.next!=null) {

           slow = slow.next;
           fast = fast.next.next;
           if(slow==fast) {
               ListNode entry = head;
               while(entry!=slow){
                   entry = entry.next;
                   slow = slow.next;
               }
               return entry;
           }
       }
       return null;
    }


    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = head;
        ListNode prev = dummy;
        while(curr!=null){
            if(curr.val==val) {
                prev.next = curr.next;
            }else {
                prev=curr;

            }
            curr = curr.next;

        }
        return head;
    }

    /// to remove nodes if present in nums array
    /// nums - [1,2,3] head- [1,2,3,4,5] ---> [4,5]
    public  ListNode removeElementsIfPresentInArr (int[] nums,ListNode head) {
        ListNode dummy = new ListNode(-1,head);
        boolean[] toRemove = new boolean[10001]; /// max - 10^5
        for(int n : nums) {
            toRemove[n] = true;
        }

        ListNode prev = dummy;
        ListNode curr = head;
        ListNode temp = null;
        while(curr!=null) {
            if(toRemove[curr.val]){
                temp=curr.next;
                prev.next = curr.next;
                curr=temp;
            }
            else {
                prev = curr;
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
