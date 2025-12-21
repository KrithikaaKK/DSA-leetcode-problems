package org.example.LeetCode;


public class FastSlowPointers {


    public static boolean isHappyNum(int n){
        int slow = n;

        int fast = getNext(n);

        while(slow!=fast){
             slow = getNext(slow);
             fast = getNext(getNext(fast));
            System.out.println("slow.."+"fast.."+slow+ " " +fast);

        }
        return slow==1;// similar to if-else
    }

    //to get the sum of squares
    public static int getNext(int n){
        int sum =0;
        while(n>0){
            int digit = n%10; // gives last digit
            sum += digit*digit; //mc......
            n=n/10; // removes the last digit
        }
        return sum;
    }

    public static int duplicateNumber(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        // Step 1: Detect cycle
        do {
            slow = nums[slow];          // move by 1
            fast = nums[nums[fast]];    // move by 2
        } while (slow != fast);

        // Step 2: Find the entry point of the cycle (duplicate)
        fast = nums[0];
        while (slow != fast) {
            System.out.println("slow.."+slow+"fast.."+fast);
            slow = nums[slow];
            fast = nums[fast];
           // System.out.println("slow.."+slow+"fast.."+fast);
        }

        return slow; // or fast, they are equal
    }





    public static void main(String[] args) {
        System.out.println(isHappyNum(19));
        //System.out.println(duplicateNumber(new int[]{4,4,4,3,2}));

    }

}
