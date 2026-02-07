package org.example.LeetCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListNode {
    public int val;         // the value
    public ListNode next;
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
    public ListNode(int val,ListNode next) {
        this.val = val;
        this.next = next;
    }
}

 class DetectCycle {
     public boolean hasCycle(ListNode head) {
         ListNode slow = head;
         // single element head --- > first element of the linked list

         ListNode fast = head;

         while (fast != null && fast.next != null) {
             slow = slow.next;          // move 1 step
             fast = fast.next.next;     // move 2 steps

             if (slow == fast) {
                 return true;           // fast caught up to slow, cycle detected
             }
         }

         return false; // no cycle
     }

     public static ListNode reverseList(ListNode head) {

         ListNode current = head;
         ListNode prev = null;

         while (current!=null){
             ListNode next = current.next;
             current.next=prev;
             prev = current;
             current = next;
         }
         return prev;
     }

     public static ListNode convertArray() {
         int[] nums = new int[]{1,2,3,4};
         ListNode head = new ListNode(nums[0]);
         ListNode mover = head;
         for(int i=1;i<nums.length;i++) {
             ListNode temp = new ListNode(nums[i]);
             mover.next=temp;
             mover = temp;
         }
         return head;
     }

     public static int lengthOfLL(ListNode head) {
         int count=0;
         ListNode temp = head;
         while(temp!=null) {
             temp = temp.next;
             count++;
         }
         return count;
     }

     public ListNode reverseBetween(ListNode head, int left, int right) {
         if(head==null || left==right) return head;
         ListNode curr = head;
         ListNode prev = null;
         int count=1;
         while(count<left) {
             prev = curr;
             curr = curr.next;
             count++;
         }

         ListNode leftPrev = prev;
         ListNode subTail = curr;
         ListNode subPrev = null;
         ListNode next = null;
         while(count<=right) {
             next = curr.next;
             curr.next = subPrev;
             subPrev = curr;
             curr = next;
             count++;
         }
         //Imp
         leftPrev.next = subPrev;
         subTail.next  = curr;

         return head;



     }

     /// swapping the k nodes
     /// [1,2,3,4,5] k =2 --> [1,4,3,2,5]
     public ListNode swapNodes(ListNode head, int k) {
         ListNode slow = head;
         ListNode fast = head;
         int val1 = head.val;
         int cnt=1;
         while(cnt<k) {
             fast = fast.next;
             cnt++;
         }
         val1 = fast.val;
         ListNode p = fast;
         while(fast.next!=null){
             slow = slow.next; // slow will be k nodes away from the fast while fast in the last node
             fast = fast.next;
         }
         //swapping only values not nodes

         p.val = slow.val;
         slow.val = val1;

         return head;

     }

     /// to find the middle of the LL
     public ListNode middleNode(ListNode head) {
         if(head==null) return head;
         int cnt=0,  k=0;
         ListNode curr = head;
         while(curr!=null) {
             curr = curr.next;
             cnt++;
         }
         k = (cnt/2)+1;
         curr = head;
         cnt=1;
         while(curr!=null) {
             if(cnt==k) {
                 return curr;
             }
             curr = curr.next;
             cnt++;
         }
         return head;


     }

     /// check if the LL is palindrome
     /// step 1 : to find the mid
     /// step 2 : reverse all nodes from mid to last
     /// step 3 : compare the reversed one with the first half
     public boolean isPalindrome(ListNode head) {
         // first to find the mid
        ListNode mid = findMid(head);
        ListNode curr = mid;
        ListNode prev = null;
        ListNode next;
        while(curr!=null) {
            next = curr.next;
            curr.next = prev;
            prev = curr; // it is the head of the reversed list
            curr = next;
        }

        ListNode curr2 = prev;
        ListNode curr1 = head;
        while (curr2!=null) {
            if(curr1.val!=curr2.val) {
                return false;
            }
            curr2 = curr2.next;
            curr1 = curr1.next;

        }
        return true;

     }

     public ListNode findMid(ListNode head) {
         ListNode slow = head;
         ListNode fast = head;
         while(fast!=null && fast.next!=null) {
             slow = slow.next;
             fast = fast.next.next;
         }
         return slow;

     }

     ///  [2,3,4,5] [1,1,2,3]-- [1,2,2,3,4,5]
     ///
     public ListNode mergeSortedLL (ListNode list1,ListNode list2) {
         ListNode curr = list1;//head of first LL;
         ListNode dummy = new ListNode(0);
         ListNode temp = dummy;
         List<Integer> list = new ArrayList<>();

         while(curr!=null) {
             list.add(curr.val);
             curr = curr.next;
         }
         curr = list2;
         while(curr!=null) {
             list.add(curr.val);
             curr = curr.next;
         }
         Collections.sort(list);

         for(int i=0;i<list.size();i++) {
             temp.next = new ListNode(list.get(i));
             temp = temp.next;
         }
         return dummy.next;
     }

     public ListNode mergeLLOptimized (ListNode list1,ListNode list2) {
         ListNode t1 = list1;
         ListNode t2 = list2;
         ListNode dummy = new ListNode(-1);
         ListNode temp = dummy;

         while(t1!=null && t2!=null) {
             if(t1.val<t2.val) {
                 temp.next = t1;
                 temp = t1;
                 t1 = t1.next;
             }else {
                 temp.next = t2;
                 temp = t2;
                 t2 = t2.next;
             }
         }
         if(t1!=null) {
             temp.next=t1;
         }else if (t2!=null){
             temp.next=t2;
         }
         return dummy.next;
     }


     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


         ListNode dummy = new ListNode(-1);
         ListNode temp = dummy;
         int carry=0;

         while(l1!=null || l2!=null || carry!=0) {
             int sum =carry;

             if(l1!=null) {
                 sum += l1.val;
                 l1 = l1.next;
             }
             if(l2!=null) {
                 sum += l2.val;
                 l2= l2.next;
             }
             carry = sum/10;
             sum = sum%10;
             ListNode n = new ListNode(sum);
             temp.next = n;
             temp=n;


         }
         return dummy.next;



     }


     /// [1,2,3,4] -> [1,4,2,3]
     public void reorderList(ListNode head) {
         ListNode mid = findMid(head);
         ListNode curr = mid.next;
         mid.next=null;//disconnect from head/first half
         ListNode prev=null;
         ListNode next;
         while(curr!=null) {
             next = curr.next;
             curr.next=prev;
             prev=curr; //the head of rev one
             curr = next;
         }

         ListNode first = head;
         ListNode second = prev;

         while(second!=null) {
             ListNode temp1 = first.next;
             ListNode temp2 = second.next;

             first.next=second;
             second.next=temp1;

             first = temp1;
             second = temp2;
         }


     }
     public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

         ListNode t1 = headA;
         ListNode t2 = headB;

         while(t2!=null) {
             

             while(t1!=null){
                 if(t1.next==t2.next) {
                     return t1.next;
                 }
                 t1 = t1.next;
             }
             t2 = t2.next;
         }
         return null;
     }

     public ListNode oddEvenList(ListNode head) {

         ListNode oddDummy = new ListNode(-1);
         ListNode evenDummy = new ListNode(-1);

         ListNode even = evenDummy;
         ListNode odd = oddDummy;
         ListNode curr = head;
         int index=1;

         while(curr!=null){
             if(index%2==0) {
                 even.next = curr;
                 even = curr;
             }
             else {
                 odd.next = curr;
                 odd = curr;
             }
             curr = curr.next;
             index++;
         }

         even.next = null; //terminate even otherwise it forms a cycle, cause the last node might referring another one
         odd.next = evenDummy.next;
         return oddDummy.next;

     }

     ///Imp..
     public static ListNode reverseKGroup(ListNode head,int k){

         ListNode temp = head;
         ListNode prevLast = null;

         while(temp!=null){

             ListNode kNode = getKthNode(temp,k);

             if(kNode==null){
                 prevLast.next = temp;

                 break;
             }

             ListNode nextNode = kNode.next;

             kNode.next = null;
             reverseList(temp);

             if(temp==head){
                 temp = kNode;
             }else {
                 prevLast.next = kNode;
             }

             prevLast = temp;

             temp = nextNode;



         }

         return head;

     }

     public static ListNode getKthNode(ListNode temp,int k){
         k -= 1;

         while(temp!=null && k>0){
             temp = temp.next;
             k--;
         }
         return temp;

     }





     public static void printLinkedList(ListNode head) {
         ListNode temp = head;
         while (temp != null) {
             System.out.print(temp.val + " ");
             temp = temp.next;
         }
         System.out.println();
     }






         public static void main(String[] args){
         //System.out.println(reverseList());
         //System.out.println(convertArray());
         System.out.println(lengthOfLL(convertArray()));

             ListNode head = new ListNode(5);
             head.next = new ListNode(4);
             head.next.next = new ListNode(3);
             head.next.next.next = new ListNode(7);
             head.next.next.next.next = new ListNode(9);
             head.next.next.next.next.next = new ListNode(2);

             // Print the original linked list
             System.out.print("Original Linked List: ");
             printLinkedList(head);

             // Reverse the linked list in groups of K
             head = reverseKGroup(head, 3);

             // Print the reversed linked list
             System.out.print("Reversed Linked List: ");
             printLinkedList(head);

     }


 }





