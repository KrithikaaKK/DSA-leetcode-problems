package org.example.LeetCode;
import java.util.*;



public class slidingWindow {

    public static double findMaxAverage(int[] nums, int k) {

        int sum=0,windowSum=0;
                double maxAvg=0;
        for(int i=0;i<k;i++){
            sum += nums[i];
        }
        maxAvg = (double) sum /k;
        windowSum = sum;
        for(int i=k;i<nums.length;i++){
            windowSum += nums[i] - nums[i-k];
            if(((double) windowSum /k)>maxAvg){
                maxAvg = (double) windowSum /k;
            }
        }
        return  maxAvg;
    }

    /**
     *  variable sliding window
     * once the repetition is identified, have to remove all the char until the repeated one is removed
     * or else we have to add the char to set
     * important-- right-left+1;
     *
     */

    public static int lengthOfLongestSubstring(String s) {
        int j=0,i=0,max=0;
        Set<Character> res = new HashSet<>();
        for(j=0;j<s.length();j++){
            char currentChar = s.charAt(j);
            while(res.contains(currentChar)) {
                res.remove(s.charAt(i++));
            }
            res.add(currentChar);

            max = Math.max(max,j-i+1);//right-left +1
        }
        return max;
    }
    public static int maxNumberOfVowelss(String s,int k) {
        int maxCount = 0 , windowCount = 0;
        Set<Character> vowels = new HashSet<>(List.of('a','e','i','o','u'));

        for(int i=0;i<k;i++) {
            if(vowels.contains(s.charAt(i))){
                maxCount++;
            }
        }
        windowCount = maxCount;
        for(int i=k;i<s.length();i++){
            if(vowels.contains(s.charAt(i-k))) windowCount--;

            if(vowels.contains(s.charAt(i))) windowCount++;

            maxCount = Math.max(maxCount,windowCount);

        }

        return maxCount;


    }




    public static boolean areOccurrencesEqual(String s) {
        HashMap<Character,Integer> occur = new HashMap<>();


        for(int i=0;i<s.length();i++){
            occur.put(s.charAt(i),occur.getOrDefault(s.charAt(i),0)+1);
        }

        int first=0;

        for(Integer vals : occur.values()){
            if(first==0){
                first=vals;
            }
            if(first!=0&&first!=vals){
                return false;
            }
        }
        return true;

    }


        public static String smallestWindow(String s, String p)
        {
            int len1 = s.length();
            int len2 = p.length();

            // Check if string's length is less than P's length
            if (len1 < len2) {
                return "-1";
            }

            // Initialize hash maps for P and string S
            HashMap<Character, Integer> hashP = new HashMap<>();
            HashMap<Character, Integer> hashS = new HashMap<>();

            // Store occurrence of characters of P
            for (int i = 0; i < len2; i++) {
                hashP.put(p.charAt(i),
                        hashP.getOrDefault(p.charAt(i), 0)
                                + 1);
            }

            int left = 0, start_idx = -1,
                    min_len = Integer.MAX_VALUE;

            // Count of matched characters
            int count = 0;

            // Start traversing the string S
            for (int j = 0; j < len1; j++) {
                // Count occurrence of characters of string S
                char currentChar = s.charAt(j);
                hashS.put(currentChar,
                        hashS.getOrDefault(currentChar, 0)
                                + 1);

                // If S's char matches with P's char, increment
                // count
                if (hashP.containsKey(currentChar)
                        && hashS.get(currentChar)
                        <= hashP.get(currentChar)) {
                    count++;
                }

                // If all characters are matched
                if (count == len2) {
                    // Try to minimize the window
                    while (
                            hashS.getOrDefault(s.charAt(left), 0)
                                    > hashP.getOrDefault(
                                    s.charAt(left), 0)
                                    || !hashP.containsKey(
                                    s.charAt(left))) {
                        if (hashS.get(s.charAt(left))
                                > hashP.getOrDefault(
                                s.charAt(left), 0)) {
                            hashS.put(s.charAt(left),
                                    hashS.get(s.charAt(left))
                                            - 1);
                        }
                        left++;
                    }

                    // Update window size
                    int len = j - left + 1; // right - left + 1
                    if (min_len > len) {
                        min_len = len;
                        // Update starting index
                        start_idx = left;
                    }
                }
            }

            // If no window found
            if (start_idx == -1)
                return "-1";

            // Return the substring starting from start_idx and
            // length min_len
            // substring from left pt to left + right pointer
            return s.substring(start_idx, start_idx + min_len);
        }

    public static int minSubarraySumLen (int target,int[] nums) {

        int len = Integer.MAX_VALUE,windowSum = 0,left=0;
        for(int right = 0;right<nums.length;right++) {
            windowSum += nums[right];
            while(windowSum>=target) {
                len = Math.min(len,right-left+1);
                windowSum -= nums[left++];
            }

        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }

    /**
     * In this array is used to track the count of char in the window
     *array - > when we know that alphabets will only come
     * char -> int [ ascii value]
     * char c = 'a'
     * int n = c; --> ascii assigned
     */
    public static List<Integer> findAllAnagrams(String s,String p) {
        int n = s.length(),k = p.length();

        List<Integer> indices = new ArrayList<>();

        int[] countP = new int[256];
        int[] countW = new int[256];

        for(int i=0;i<k;i++) {
            countP[p.charAt(i)]++;        //countP[ascii value of char]++
            countW[s.charAt(i)]++;
        }

        if(n<k) return indices;


        for(int i=k;i<n;i++) {

            countW[s.charAt(i)]++;
            countW[s.charAt(i-k)]--;

            if(Arrays.equals(countP,countW)) {
                indices.add(i-k);
            }

        }
        //for last window
        if(Arrays.equals(countP,countW)) {
            indices.add(n-k);
        }
        return indices;
    }


    public static boolean PermutationCheck (String s1,String s2) {
        int n = s2.length(), k = s1.length();

        int[] countN = new int[125];
        int[] countW = new int[125];

        if(n<k) return false;

        for(int i=0;i<k;i++) {
            countW[s2.charAt(i)]++;
            countN[s1.charAt(i)]++;
        }

        for(int i = k;i<n;i++){
            if(Arrays.equals(countW,countN)) {
                return true;
            }
            countW[s2.charAt(i)]++;
            countW[s2.charAt(i-k)]--;

        }

        return Arrays.equals(countN, countW);

    }

    /// return the longest subarray by obtaining k opertions of
    /// replacement
    public static int characterReplacement(String s, int k) {
        int start=0,maxCount=0,maxLen =0;
        int[] chars = new int[128];
        for(int end =0;end<s.length();end++) {

            maxCount = Math.max(maxCount,++chars[s.charAt(end) - 'A']);

            while((end-start+1) - maxCount>k) {
                chars[s.charAt(start) - 'A']--;
                start++;
            }
            maxLen = Math.max(maxLen,end-start+1);

        }
        return maxLen;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int count =0,maxNum=0;
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<k;i++) {
            maxNum = Math.max(maxNum,nums[i]);

        }
        list.add(maxNum);
        for(int i=k;i<nums.length;i++) {
            if(nums[i-k]==maxNum) {


            }
        }
        return null;

    }

    public static int[] findXSum(int[] nums, int k, int x) {
        Map<Integer,Integer> map = new HashMap<>();
        int count=0,n=nums.length;
        for(int i=0;i<k;i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        int[] sums = new int[n-k+1];
        for(int i=k;i<=n;i++) {
            List<int[]> list = new ArrayList<>();
            for(int m : map.keySet()) {
                list.add(new int[]{m,map.get(m)});
            }
            list.sort(
                    (a,b) -> {
                        if(a[1]==b[1]) return b[0] - a[0]; /// if freq same then have to keep larger value
                        return b[1] - a[1];
                    }
            );
            int sum =0;
            for(int j=0;j<x;j++) {
               int val = list.get(j)[0];
               int frq = list.get(j)[1];
               sum += val * frq;
            }
            sums[count]=sum;
            count++;
            if(i==n) break;

            map.put(nums[i-k],map.get(nums[i-k])-1);
            if(map.get(nums[i-k])==0) map.remove(nums[i-k]);

            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }


        return sums;


    }

    public int[] findXSumOptimized(int[] nums, int k, int x) {
        int n = nums.length;
        int[] res = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            int[] freq = new int[51]; // nums[i] in [1..50]
            for (int j = i; j < i + k; j++) {
                freq[nums[j]]++;
            }

            // Collect (value, count) pairs for values present
            java.util.List<int[]> list = new java.util.ArrayList<>();
            for (int v = 1; v <= 50; v++) {
                if (freq[v] > 0) list.add(new int[]{v, freq[v]});
            }

            // Sort by count desc, then value desc
            list.sort((a, b) -> {
                if (b[1] != a[1]) return b[1] - a[1];
                return b[0] - a[0];
            });

            int take = Math.min(x, list.size());
            int sum = 0;
            for (int t = 0; t < take; t++) {
                sum += list.get(t)[0] * list.get(t)[1];
            }
            res[i] = sum;
        }
        return res;
    }





    public static void main(String[] args) {
//        System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
//       System.out.println(lengthOfLongestSubstring("abcabcbba"));
//        System.out.println(smallestWindow("ADOBECODEBANC", "ABC"));
    //    System.out.println(areOccurrencesEqual("abcabcf"));
      //  System.out.println(maxNumberOfVowelss("abciiidef",3));
       // System.out.println(minSubarraySumLen(15,new int[]{1,2,3,4,5}));
        //System.out.println(PermutationCheck("adc","dcda"));
       // System.out.println(characterReplacement("ABABB",1));
       // System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3)));//3,3,3,4,5
        System.out.println(Arrays.toString(findXSum(new int[]{1,1,2,2,3,4,2,3}, 6, 2)));
    }
}