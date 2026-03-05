package org.example.LeetCode.Design;

import java.util.PriorityQueue;

class KthLargest {

    private int k;
    private PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {


        this.k = k;

        heap = new PriorityQueue<>(k);
        for (int n : nums) {
            if (heap.size() < k) {
                heap.offer(n);
            } else if (n >= heap.peek()) {
                heap.offer(n);
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }

    }

    public int add(int val) {
        if (heap.size() < k) {
            heap.offer(val);
        } else if (val >= heap.peek()) {
            heap.offer(val);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek(); // root is the smallest of the k's -- desired
    }
}
