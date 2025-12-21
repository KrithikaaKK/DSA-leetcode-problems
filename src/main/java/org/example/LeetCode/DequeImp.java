package org.example.LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class DequeImp {

    public static void test(){
        Deque<Integer> deque = new ArrayDeque<>(10);
        deque.addFirst(5);
        deque.addLast(1);
//        deque.addLast(3);
//        deque.addFirst(2);
       // deque.add(7);
       // System.out.println(de);
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollFirst());

        System.out.println(deque);

    }


    public static void main(String[] args) {
        test();
    }
}


