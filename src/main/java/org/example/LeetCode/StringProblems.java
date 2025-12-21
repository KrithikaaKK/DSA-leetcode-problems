package org.example.LeetCode;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class StringProblems {


    public static int lastWordLength(String s) {
        String S = s.trim();  // optimization
        int count=0;

        for(int i = S.length()-1;i>=0; i--){
            if(s.charAt(i)==' '){
                return count;
            }
            else {
                count++;
            }
        }


        return count;


    }

    public static int indexOfFirstOccurrence(String haystack, String needle) {
        int hlen = haystack.length();
        int nlen = needle.length();

        if(hlen < nlen) return -1;

        for(int i=0;i<=hlen-nlen;i++) {
            int j=0;
            while(j<nlen && haystack.charAt(i+j)==needle.charAt(j)){
                System.out.println(i + " "+ j);
                j++;
            }
            if(j==nlen){
                System.out.println("sdf");
                return i;
            }

            System.out.println("i.."+i);

        }
        return -1;
    }

    public static char[] reverseString (char[] s) {
        int i=0,j=s.length-1;
        while(i<j) {
            swap(s,i++,j--);
        }
        return s;
    }

    public static String reverseWordsInString(String s) {

        String[] words = s.trim().split("\\s+");

        List<String> array = new ArrayList<>(Arrays.asList(words));

        Collections.reverse(array);

        return String.join(" ", array);

    }

    public static String reverseWordsOptimized (String s) {
        StringBuilder builder = new StringBuilder();
        int i = s.length() - 1;

        while(i>=0) {

            while(i>=0 && s.charAt(i) == ' '){
                i--;
            }
            if(i<0) {
                break;
            }
            int j = i;
            while (i>0 && s.charAt(i) != ' '){
                i--;
            }
            if(builder.length()>1){
                builder.append(" ");
            }
            builder.append(s, i+1, j+1);

        }
        return builder.toString();


    }


    public static String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        if(strs.length==1) return strs[0];
        String first = strs[0];
        String last = strs[strs.length-1];
        StringBuilder stringBuilder = new StringBuilder();

        int minLen = Math.min(first.length(), last.length());


        for(int i=0;i<minLen;i++){
            if(first.charAt(i)==last.charAt(i)) {
                stringBuilder.append(first.charAt(i));
            }
            else {
                break;
            }

        }
        return stringBuilder.toString();

    }

    /**
     * true if the capitals were used correctly and false if not
     * ex... BBC , Flag etc..
     */
    public static boolean detectCapitalUse(String word) {

        int count=0;
        for(int i=0;i<word.length();i++) {
            int n = word.charAt(i);
            if(n<97) {
                count++;
            }
        }
        return (count==word.length() ||
                count==0 ||
                (count == 1 && word.charAt(0)<97));

    }

    public static void swap(char[] arr,int i , int j) {
        char t = arr[j];
        arr[j] = arr[i];
        arr[i] = t;

    }

    ///  first unique character --> non repeating one
    public static int firstUniqChar(String s) {
        int len = s.length();
        int[] countArr = new int[26];

        for(char c : s.toCharArray()) {
            countArr[c - 'a']++;
        }
        for(int i=0;i<len;i++) {
            if(countArr[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;


    }


    /// Return true if the ransom note can be constructed from the magazine.
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] countR = new int[125];
        int[] countW  = new int[125];
        int rlen = ransomNote.length(),mlen = magazine.length();

        if(rlen>mlen) return false;
        for(char c : ransomNote.toCharArray()){
            countR[c]++;
        }
        for(char c : magazine.toCharArray()){
            countW[c]++;
        }

        if(Arrays.equals(countW,countR)) return true;
        for(int i=0;i<rlen;i++) {
            char c = ransomNote.charAt(i);
            if(countR[c] > countW[c]) return false;
        }

        return true;
    }



    public static void main(String[] args) {
       // System.out.println(lastWordLength("a"));
        //System.out.println(indexOfFirstOccurrence("abc","c"));
        //System.out.println(Arrays.toString(reverseString(new char[]{'h','e','l'})));
       // System.out.println(reverseWordsInString(" the   sky is  blue  "));
        System.out.println(reverseWordsOptimized(" the   sky is  blue  "));
        //System.out.println(longestCommonPrefix(new String[]{"aaa","aa","aaa"}));
        //System.out.println(detectCapitalUse("Fffff"));
        //System.out.println(firstUniqChar("eetcode"));//teacher
        //System.out.println(canConstruct("ab","aab")); // true

    }
}
