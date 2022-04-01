package org.example.stream;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * @author Subham
 * @see : LEETCODE Dekho Bhai
 */
public class KthLargestItemInStream {

    class KthLargest {

        private Queue<Integer> queue;
        private final int SIZE;

        public KthLargest(int k, int[] nums) {
            queue = new PriorityQueue<>(Comparator.comparing(i -> i));
            SIZE = k;
            // --add
            for (int x : nums) {
                add(x);
            }
        }

        public int add(int val) {
            queue.add(val);
            if (queue.size() > SIZE) {
                queue.poll();
            }
            return queue.peek();
        }
    }

    /**
     * Driver Code
     *
     * @param args
     */
    public static void main(String[] args) {
        KthLargestItemInStream largestItemInStream = new KthLargestItemInStream();
        KthLargest kthLargest = largestItemInStream.new KthLargest(10000, new int[]{});

        // ### PERFORMANCE check
        Random random = new Random();
        int[] test = new int[100000];

        //### LARGE TEST
        for (int i = 0; i < 100000; ++i) {
            test[i] = random.nextInt(999999);
            if (random.nextBoolean()) {
                test[i] = -test[i];
            }
        }

        final long start = System.currentTimeMillis();
        // --START
        for (int x : test) {
            kthLargest.add(x); // O(log k) :::: k == 10000, runtime is high
        }
        // --END
        System.out.println("Time taken in millis : " + (System.currentTimeMillis() - start));
    }
}
