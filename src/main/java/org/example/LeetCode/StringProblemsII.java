package org.example.LeetCode;

import java.util.*;

public class StringProblemsII {

    public static boolean isAnagram(String s, String t) {
        int[] countS = new int[125];
        int[] countT = new int[125];
        int slen = s.length(),tlen = t.length();

        if(slen!=tlen) return false;

        for(char c:s.toCharArray()){
            countS[c]++;
        }
        for(char c:t.toCharArray()){
            countT[c]++;
        }

        return Arrays.equals(countS,countT);

    }

    public static String removeAdjDuplicates (String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for(char c : chars) {
            int sbLen = sb.length();
            if(sbLen>0 && c==sb.charAt(sbLen-1)) {
                sb.deleteCharAt(sbLen-1);
            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    public static int maxFreqSum(String s) {
        int[] countV = new int[26];
        int[] countC = new int[26];
        int maxV = 0, maxC=0;

        for(char c : s.toCharArray()) {
            if(c=='a'||c=='i'||c=='o'||c=='u'||c=='e') {
                countV[c - 'a']++;
            }
            else {
                countC[c - 'a']++;
            }
        }
        for(int n : countV) {
            maxV = Math.max(maxV,n);
        }
        for(int n : countC) {
            maxC = Math.max(maxC,n);
        }
        return maxV+maxC;



    }

    /// alert : important
    /// all occ of char - replaced with other ones, but each onee mapped to only one. one-one mapping
    /// also possible with arrays
    public static boolean isIsomorphic(String s, String t) {
        Map<Character,Character> sMap = new HashMap<>();
        Map<Character,Character> tMap = new HashMap<>();

        for(int i=0;i<s.length();i++) {
            char c1 = s.charAt(i); //b
            char c2 = t.charAt(i); //a

            if(sMap.containsKey(c1) && sMap.get(c1)!=c2) return false;
            if(tMap.containsKey(c2) && tMap.get(c2)!=c1) return false;

            sMap.put(c1,c2); //preserve one-one mapping
            tMap.put(c2,c1);
        }
        return true;


        // if(a[c1]!= c2 || b[c2] != c1) --> false

    }

    /// to check the one-one mapping from both sides
    public static boolean wordPattern(String pattern, String s) {
        Map<Character,String> patternMap = new HashMap<>();
        Map<String,Character> stringMap = new HashMap<>();

        List<String> s1 = List.of(s.split(" "));


        if(pattern.length()!=s1.size()) return false;

        for(int i=0;i<pattern.length();i++) {
            char c = pattern.charAt(i);
            String s2 = s1.get(i);

            if(patternMap.containsKey(c) && !patternMap.get(c).equals(s2)) return false;
            if(stringMap.containsKey(s2) && stringMap.get(s2)!=c) return false;

            patternMap.put(c,s2);
            stringMap.put(s2,c);

        }
        return true;

    }

    public static int minOperations(String s) {
        int opCount =0;
        char[] chars =s.toCharArray();
        int len = s.length();
        char[] sample = new char[s.length()];
        Arrays.fill(sample,'a');
        while(!Arrays.equals(sample,chars)) {
            for(int i=0;i<chars.length;i++) {
                if(chars[i]!='a') {
                    int ascii = chars[i];
                    char next = 0;
                    if (ascii==122) {
                        next = 97;
                    }
                    else {
                       next = (char) (ascii + 1);
                    }
                    chars[i] = next;
                }
                if(i==len-1){
                    opCount++;
                }
                System.out.println(Arrays.toString(chars));

            }
        }
        return opCount;

    }

    ///alert --> need to check
    /// return the max count to convert all the chars one by one to 'a' through single steps
    /// assume it as cyclic z -> a
    public static int minOperationOptimized(String s) {
        int maxOp =0;
        for(char c : s.toCharArray()) {
            if(c!='a'){
                int op = 'z'- c +1;
                maxOp = Math.max(op,maxOp);
            }
        }
        return maxOp;
    }

    public static String reverseVowels(String s) {
        int left=0;

        char[] chars = s.toCharArray();
        int right = chars.length-1;
        while(left<=right) {

            if(!isVowel(chars[left])) {
                left++;
            }
            if(!isVowel(chars[right])){
                right--;
            }

            if(isVowel(chars[left])&&isVowel(chars[right])) {
                swap(left++,right--,chars);
            }
        }
        StringBuilder sb =  new StringBuilder();
        return sb.append(chars).toString();

    }

    public static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
    public static void swap(int left,int right,char[] chars) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;
    }

    /// s - abc , t - ahbc -- true -- order preserved all elements present
    public static boolean isSubsequence(String s, String t) {

        int i=0,j=0,count=s.length();
        while(i<s.length() && j<t.length()){
            if(s.charAt(i)==t.charAt(j)){
                i++;
                j++;
                count--;
            }else {
                j++;
            }

        }
        return count==0;
    }



    public static void main(String[] args) {

//        System.out.println(isAnagram("anagram","angaaram"));
//        System.out.println(removeAdjDuplicates("abbaca"));
        //System.out.println(maxFreqSum("successes"));
        //System.out.println(isIsomorphic("bbbaaaba","aaabbbba"));
       // System.out.println(wordPattern("abbac","dog cat cat dog dog"));
        //System.out.println(minOperationOptimized("sta"));
        //System.out.println(reverseVowels("race a car"));
        System.out.println(isSubsequence("abc","ahbdef"));
    }
}
