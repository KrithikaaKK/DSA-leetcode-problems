package org.example.LeetCode;

public class palindrome {

    public static boolean isPalindrome(int x) {

        boolean result = false;
        String s = String.valueOf(x);
        char[] chars = s.toCharArray();

        int j=chars.length-1,i=0;
        while(i<chars.length) {
            //System.out.println(chars[i] + " " + chars[j]);
            if (chars[i] == chars[j]) {
                result = true;
            }
            else {
                result = false;
                break;
            }
            i++;
            j--;
        }
        return result;
    }


    public static boolean validPalindrome(String s) {

        boolean result =false;
        //s = s.replaceAll("[:,.@!#$%^&*;'_=+~`{}/?\\[\\]()\"\\\\]"," ");
        s = s.replaceAll("[:,.@!#$%^&*;'-|_=+~`{}/?\\[\\]()\"\\\\]"," ");

        s = s.replaceAll(" ","").toLowerCase();
        System.out.println(s);
        char[] chars = s.toCharArray();
        System.out.println(chars);

        int j=chars.length-1,i=0;
        while(i<chars.length) {
            System.out.println(chars[i] + "meee" + chars[j]);
            if(chars[i] == chars[j]) result = true;
            else {
                result = false;
                break;
            }
            i++;
            j--;
        }
        return result;

    }



    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(validPalindrome("Dammit I'm mad"));
    }
}
