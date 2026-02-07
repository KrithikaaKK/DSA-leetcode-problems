package org.example.LeetCode;

import java.util.*;

public class ArrayProblemsIII {
    public static void rotate(int[] nums, int k) {
        int[] result = new int[nums.length];
        int n =  nums.length;
        int len = n-1;
        k = k % n; //as rotation is cyclic we reduced it if k greater than length
        int count=k-1,c=0;


        for(int i=0;i<n;i++) {
            result[i] = nums[i];
        }
        //k=2

        while(count>=0) {
            nums[count--]=result[len--];
        }
        count=k;

        while(count<nums.length){
            nums[count++]=result[c++];
        }
        System.out.println(Arrays.toString(nums));

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            StringBuilder sb = new StringBuilder();
            String sorted = sb.append(chars).toString();
            if (map.containsKey(sorted)) {
                List<String> list = map.get(sorted);
                list.add(s);
                map.put(sorted, list);
            } else {
                map.put(sorted, new ArrayList<>(List.of(s)));
            }

        }
        for(Map.Entry<String,List<String>> m : map.entrySet()) {
            result.add(m.getValue());
        }
        return result;

    }

    public static int maximumSubarray(int[] arr) {
        int sum = 0;
        int result = 0;

        for(int i=0;i<arr.length;i++) {
            sum=0;
            for(int j=i;j<arr.length;j++) {
                sum += arr[j];
                result = Math.max(result,sum);
            }

        }
        return result;
    }

    public static int maximumSubarrayOp(int[] nums) {
        int sum = 0;
        int result = Integer.MIN_VALUE;


        for(int i=0;i<nums.length;i++) {
            sum += nums[i];
            result = Math.max(result,sum);
            if(sum<0) {
                sum=0;
            }

        }
        return result;
    }

    public static int maxSubarraySumCircular(int[] nums) {

        int max = nums[0],currMax = 0,currMin = 0,min = nums[0],total=0;

        for(int i=1;i<nums.length;i++){
            int n = nums[i];
            currMax = Math.max(n,currMax+n);
            max = Math.max(currMax,max);
            currMin = Math.min(n,currMin+n);
            min = Math.min(currMin,min);
            total += n;
        }

        return (max>0)? max : Math.max(max,total-currMin);//to get maximum possible one

    }

    //two occurrence of a number is allowed ( sorted)
    public static int removeDuplicates(int[] nums) {

        int index=2;

        for(int i=2;i<nums.length;i++){
           if(nums[i]!=nums[index-2]){
               nums[index++] = nums[i];
           }
        }

        return index;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,List<Integer>> map = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            List<Integer> list = map.getOrDefault(nums[i],new ArrayList<>());
            list.add(i);
            map.put(nums[i],list);
        }
        System.out.println(map);

        for(Map.Entry<Integer,List<Integer>> m : map.entrySet()){
            List<Integer>  list = m.getValue();
            //System.out.println("i..."+i+"..."+list);
            for(int j=1;j<list.size();j++){
                int diff = Math.abs(list.get(j) - list.get(j-1));
                if(diff<=k){
                    return true;
                }
            }
        }
        return false;

    }
    public static boolean containsNearbyDuplicateOp(int[] nums, int k) {
        Map<Integer,Integer> lastIndex = new HashMap<>();



        for(int i=0;i<nums.length;i++){

           if(lastIndex.containsKey(nums[i])){
               if(lastIndex.get(i)- i<=k){
                   return true;
               }
           }
           lastIndex.put(nums[i],i);
        }



        return false;

    }

    public static boolean isValidSudoku(char[][] board) {

        int m = board.length;
        int n = board[0].length;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                char c = board[i][j];

                if((c!='.')&& (isPresentInRow(c,i,j,n,board) || inColumn(c,j,i,m,board) || inGroup(c,i,j,board))) {
                    System.out.println("r.."+c+isPresentInRow(c,i,j,n,board));
                    System.out.println("c.."+inColumn(c,j,i,m,board));
                    System.out.println("g.."+inGroup(c,i,j,board));

                    return false;
                }

            }
        }
        return true;

    }

    public static boolean isPresentInRow (char c,int i,int j,int n,char[][] board){
        for(int k=0;k<n;k++){
            if((j!=k)&&(c==board[i][k])) return true;
        }
        return false;
    }

    public static boolean inColumn (char c,int j,int i,int n,char[][] board){
        for(int k=0;k<n;k++){
            if((i!=k)&& (c==board[k][j])) return true;
        }
        return false;
    }

    public static boolean inGroup(char c,int i,int j,char[][] board){
        int startRow= (i/3) * 3;
        int startCol = (j/3) * 3;

        for(int k=startRow;k<startRow+3;k++){
            for(int l=startCol;l<startCol+3;l++){
                if(((i!=k)&&(j!=l))&&(c==board[k][l])) {
                    return true;
                }
            }
        }

        return false;
    }





    public static void main (String[] args) {
        //rotate(new int[]{1,2,3,4,5},3);
        //System.out.println(groupAnagrams(new String[]{"eat","ate","bat","nat","tan"}));
        //System.out.println(maximumSubarrayOp(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        //System.out.println(removeDuplicates(new int[]{1,1,1,2,2,3}));
        //System.out.println(containsNearbyDuplicateOp(new int[]{1,2,3,1},3));
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        //System.out.println(isValidSudoku(board));

        System.out.println(maxSubarraySumCircular(new int[]{5,-3,5}));


    }
}
