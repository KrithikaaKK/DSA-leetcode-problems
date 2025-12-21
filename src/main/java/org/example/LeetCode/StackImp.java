package org.example.LeetCode;

import java.util.Stack;

public class StackImp {


    public static boolean validParentheses (String s) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c =='(' || c == '{' || c == '[') {
                stack.push(c);
            }else {
                if(!stack.empty() && matches(stack.peek(),c)){
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean matches (char a, char b) {
        return( (a == '(' && b == ')') ||
                (a == '[' && b == ']') ||
                (a == '{' && b == '}'));

    }


    public static String removedString(char[] chars){
        Stack<Character> stack = new Stack<>();
        Stack<Character> dupStack = new Stack<>();
        char deleted = 0;
        for (char c : chars) {
            if (c == '#') {
                if (!stack.empty()) deleted = stack.pop();
            } else if (c == '*') stack.push(deleted);
            else {
                stack.push(c);
                dupStack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        // chars = ['a','b','#','#','c','*','b']
        return sb.reverse().toString();

    }

    public static String removeStarsFromString(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for(char c : chars) {
            if(c=='*') {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else {
                stack.push(c);
            }

        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }





    public static void main(String[] args) {
        //System.out.println(validParentheses("[]"));

        // System.out.println(removedString(new char[]{'a','b','#','#','c','*','b'}));
        //System.out.println(removeStarsFromString("leet**code"));

    }



}
