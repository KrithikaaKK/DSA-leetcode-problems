package org.example.LeetCode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MonotonicStack {

    // stack - decreasing order
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums1.length];
        Map<Integer,Integer> greater = new HashMap<>();

        for(int i =nums2.length-1;i>=0;i--){
            int n = nums2[i];

            while(!stack.empty()&&stack.peek()<=n){
                stack.pop();
            }

            greater.put(n,stack.empty()?-1:stack.peek());
            stack.push(n);
        }

        for (int i=nums1.length-1;i>=0;i--){
            result[i] = greater.get(nums1[i]);
        }
        return  result;

    }

    // circular array
    //for last element return greater from start of array --- circular
    public static int[] nextGreaterElementII (int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;

        for(int i=n-1;i>=0;i--){
            stack.push(nums[i]);
        }

        for(int i=n-1;i>=0;i--){
            int m = nums[i];

            while(!stack.isEmpty()&& stack.peek()<=m){
                stack.pop();
            }

            nums[i] = (!stack.isEmpty()) ? stack.peek() : -1;

            stack.push(m);

        }

        return nums;

    }




    // same as next greater element
    public static int[] dailyTemperature(int[] temp) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temp.length];
        Arrays.fill(result,0);
        for(int i=temp.length-1;i>=0;i--) {
            int t = temp[i];
            while (!stack.empty() && temp[stack.peek()]<=t){
                stack.pop();
            }

            result[i] = stack.empty()?0:stack.peek()-i;
            stack.push(i);

        }
        return result;
    }

    // stack - increasing order
    public static int largestRectangle(int[] heights){
        Stack<Integer> stack = new Stack<>();// to keep track of indices -- whose diff can be later used as width
        int maxArea = 0;
        for(int i=0;i<=heights.length;i++){
            int h = (i==heights.length)?0: heights[i];

            while(!stack.empty() && heights[stack.peek()]>h){
                int n = stack.pop();

                int height = heights[n];
                int width =stack.empty()?i:i-stack.peek()-1;

                int area = height*width;
                maxArea = Math.max(maxArea,area);
            }

            stack.push(i);
        }

        return maxArea;

    }




    public static void main(String[] args) {
      //  System.out.println(Arrays.toString(nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
      //  System.out.println(Arrays.toString(dailyTemperature(new int[]{73,74,75,71,69,72,76,73})));
//        System.out.println(largestRectangle(new int[]{2,4}));
        System.out.println(Arrays.toString(nextGreaterElementII(new int[]{2,1,3,5,4})));

    }


}
