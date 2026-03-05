package org.example.LeetCode.Design;

import java.util.PriorityQueue;

public class MedianFinder {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;




    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {

        if(maxHeap.isEmpty() || maxHeap.peek() >= num){
            maxHeap.offer(num);
        }else {
            minHeap.offer(num);
        }

        if(maxHeap.size() > minHeap.size()+1){
            minHeap.offer(maxHeap.poll());
        }else if(minHeap.size()>maxHeap.size()){
            maxHeap.offer(minHeap.poll());
        }



    }

    public double findMedian() {

        if(maxHeap.size()==minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }

        return maxHeap.peek();

    }
}
