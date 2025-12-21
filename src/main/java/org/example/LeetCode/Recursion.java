package org.example.LeetCode;

public class Recursion {

    public static boolean isPowerOfThree(int n) {
        if (n==3){
            return true;
        }

        if(n==0||n==1){
            return false;
        }


        if(n%3==0) {
            n=n/3;
        }
        else {
            return false;
        }
        return isPowerOfThree(n);
    }

    public static int numberOfSteps(int num) {

        if(num==0) return num;


        int[] res = new int[1];
        recursive(num,res);


        return res[0]+1;

    }

    public static void recursive(int num,int[] steps) {

        //base case -- 1

        if(num==1) return;

        if(num%2==0){
            num = num/2;
        }else {
            num = num-1;
        }
        steps[0]++;

        recursive(num,steps);

    }

    public static void main(String[] args) {

        //System.out.println(isPowerOfThree(27));
        System.out.println(numberOfSteps(14));

    }
}
