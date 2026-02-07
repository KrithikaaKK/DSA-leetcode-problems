package org.example.LeetCode.HierachialDS;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Heap {

    private MinHeap minHeap;

    private int k;

    public Heap(int k, int[] nums) {


        this.k = k;
        minHeap = new MinHeap();
        for(int n : nums){
            add(n);
        }

    }

    public int add(int val) {
        minHeap.add(val);
        if(minHeap.size()>k){
            minHeap.remove();
        }
        return minHeap.peek(); // root is the smallest of the k's -- desired
    }

}

class MinHeap{

    private List<Integer> heap = new ArrayList<>();

    public int size(){
        return heap.size();
    }

    public int peek(){
        return heap.get(0);
    }

    public void add(int n) {
        heap.add(n);
        upheap(heap.size()-1);
    }

    public int remove(){
        int root = heap.get(0);
        int last = heap.remove(heap.size()-1);

        if(!heap.isEmpty()){
            heap.set(0,last);
            downheap(0);

        }
        return root;
    }

    public void upheap(int i){
        while(i>0){
            int parent = (i-1)/2;
            if(heap.get(i)>=heap.get(parent)) break;
            swap(parent,i);
            i = parent;
        }
    }

    public void swap ( int i, int j){
        int temp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,temp);
    }

    public void downheap(int i) {
        int min = i;
        int left = (2*i) + 1;
        int right = (2*i) + 2;


            if(left<heap.size() && heap.get(left)>heap.get(min)){
                min = left;
            }
            if(right<heap.size() && heap.get(right)>heap.get(min)){
                min = right;
            }
            if(min!=i){

            }
            swap(min,i);
            i = min;


    }
}


class KthLargest {

    private int k;
    private PriorityQueue<Integer> heap;

    public KthLargest(int k, int[] nums) {


        this.k = k;

        heap = new PriorityQueue<>(k);
        for(int n : nums){
            if(heap.size() < k){
                heap.offer(n);
            } else if (n >= heap.peek()) {
                heap.offer(n);
                if(heap.size()>k){
                    heap.poll();
                }
            }
        }

    }

    public int add(int val) {
        if(heap.size() < k){
            heap.offer(val);
        } else if (val >= heap.peek()) {
            heap.offer(val);
            if(heap.size()>k){
                heap.poll();
            }
        }
        return heap.peek(); // root is the smallest of the k's -- desired
    }
}
